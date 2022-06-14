import React, { useEffect, useState } from 'react';
import { Button, Input, message, Popconfirm, Tooltip, Tree } from 'antd';
import './index.scss';
import { customAlphabet } from 'nanoid/non-secure';
import {
	DeleteOutlined,
	EditOutlined,
	PlusCircleOutlined
} from '@ant-design/icons';
import {
	Api,
	deleteApisByApiIdsApi,
	getApiTreeApi,
	updateApiApi
} from '@/networks/api';
import { ResponseType } from '@/globals/responseType';
import { DataNode } from 'antd/lib/tree';
import { BasicDataNode } from '.pnpm/rc-tree@5.5.0_zpnidt7m3osuk7shl3s4oenomq/node_modules/rc-tree';
import { Key } from 'antd/es/table/interface';

const nanoid = customAlphabet('1234567890', 10);

const initTreeData = (treeData: any, parent = null) => {
	for (const node of treeData) {
		node.parent = parent;
		node.isTemp = false;
		if (node.children?.length) {
			initTreeData(node.children, node);
		}
	}
	return treeData;
};

const getAllKeys = (treeData: any) => {
	let res = [] as any[];

	function dfs(children: any) {
		for (const item of children) {
			res.push(item.apiId);
			if (item.children?.length) {
				dfs(item.children);
			}
		}
	}
	dfs(treeData);
	return res;
};

export interface Props {
	draggable?: boolean;
	checkable?: boolean;
	editable?: boolean;
	addTopable?: boolean;
	expandAllKey?: boolean;
	checkedTreeData?: Api[];
	onChecked?: (checkedKeys: Key[], e: Key[]) => void;
	maxHeight?: string;
}

export const ApiTree = (props: Props) => {
	const {
		draggable,
		checkable,
		editable,
		addTopable,
		expandAllKey,
		checkedTreeData,
		onChecked,
		maxHeight
	} = props;
	const [treeData, setTreeData] = useState([] as Api[]);
	const [currentItem, setCurrentItem] = useState({} as Api);
	const [expandedKeys, setExpandedKeys] = useState([] as string[]);
	const [hasEditting, setHasEditting] = useState(false);
	const [checked, setChecked] = useState([] as Key[]);
	const [halfChecked, setHalfChecked] = useState([] as Key[]);
	useEffect(() => {
		(async () => {
			let res = await getApiTreeApi();
			console.log(res);
			setTreeData(initTreeData(res.data.apiTree));
			expandAllKey && setExpandedKeys(getAllKeys(res.data.apiTree));
		})();
	}, []);

	useEffect(() => {
		(async () => {
			treeData.length && checkedTreeData && setCheckedTreeData(checkedTreeData);
		})();
	}, [checkedTreeData, treeData]);

	function setCheckedTreeData(checkedTreeData: Api[] | undefined | null) {
		if (!checkedTreeData) {
			return;
		}
		const half: Key[] = [];
		const all: Key[] = [];
		console.log(checkedTreeData);

		for (const api of checkedTreeData) {
			if (api.apiType === 0) {
				half.push(api.apiId as Key);
			} else {
				all.push(api.apiId as Key);
			}
		}
		setChecked(all);
		setHalfChecked(half);
		console.log(all, half);
	}

	const onDrop = async (info: any) => {
		console.log(info);
		const dropKey = info.node.key;
		const dragKey = info.dragNode.key;
		const dropPos = info.node.pos.split('-');
		const dropPosition =
			info.dropPosition - Number(dropPos[dropPos.length - 1]);

		const loop = (
			data: Api[],
			key: string | bigint,
			callback: (item: Api, index: number, arr: Api[]) => void
		) => {
			for (let i = 0; i < data.length; i++) {
				if (data[i].apiId === key) {
					return callback(data[i], i, data);
				}
				if (data[i].children) {
					loop(data[i].children, key, callback);
				}
			}
		};
		const data = [...treeData];
		let dragObj: any;
		loop(data, dragKey, (item, index, arr) => {
			arr.splice(index, 1);
			dragObj = item;
		});

		if (!info.dropToGap) {
			// Drop on the content
			loop(data, dropKey as unknown as bigint, item => {
				item.children = item.children || [];
				// where to insert 示例添加到头部，可以是随意位置
				dragObj.parent = item;
				item.children.unshift(dragObj);
			});
		} else if (
			(info.node.children || []).length > 0 && // Has children
			info.node.expanded && // Is expanded
			dropPosition === 1 // On the bottom gap
		) {
			loop(data, dropKey as unknown as bigint, item => {
				item.children = item.children || [];
				// where to insert 示例添加到头部，可以是随意位置
				item.children.unshift(dragObj);
				dragObj.parent = item;
				// in previous version, we use item.children.push(dragObj) to insert the
				// item to the tail of the children
			});
		} else {
			let ar: Api[] = [];
			let i: number = -1;
			loop(data, dropKey as unknown as bigint, (item, index, arr) => {
				ar = arr;
				i = index;
			});
			if (dragObj && ar.length) {
				dragObj.parent = ar[0].parent;
			}
			if (dropPosition === -1) {
				ar.splice(i, 0, dragObj);
			} else {
				ar.splice(i + 1, 0, dragObj);
			}
		}
		let res = await save(dragObj);
		if (res.data.type === ResponseType.SUCCESS) {
			setTreeData(data);
		}
	};

	const titleRander = (item: any) => {
		const apiNameChange = (e: { target: { value: any } }) => {
			setCurrentItem({ ...currentItem, apiName: e.target.value });
		};
		const apiPathChange = (e: { target: { value: any } }) => {
			setCurrentItem({ ...currentItem, apiPath: e.target.value });
		};
		const saveHandle = async () => {
			let res = await save({
				...item,
				apiName: currentItem.apiName,
				apiPath: currentItem.apiPath
			});
			if (res.data.type === ResponseType.SUCCESS) {
				setHasEditting(false);
				item.apiName = currentItem.apiName;
				item.apiPath = currentItem.apiPath;
				item.isTemp = false;
				setTreeData([...treeData]);
				setCurrentItem({} as Api);
			}
		};
		const cancelHandle = () => {
			console.log(item);

			if (item.isTemp) {
				deleteHandle(true);
			}
			setHasEditting(false);
			setCurrentItem({} as Api);
		};
		const editHandle = () => {
			if (hasEditting) {
				return message.warning('请先退出编辑操作');
			}
			setHasEditting(true);
			console.log(item);
			setCurrentItem({ ...item });
		};
		const addHandle = () => {
			if (hasEditting) {
				return message.warning('请先退出编辑操作');
			}
			setHasEditting(true);
			let tempNode = {
				apiId: nanoid(),
				apiName: '默认权限名',
				apiPath: '/默认权限路径',
				parent: item,
				isTemp: true
			};
			if (item.children) {
				item.children.push(tempNode);
			} else {
				item.children = [tempNode];
			}
			setTreeData([...treeData]);
			setCurrentItem(tempNode as unknown as Api);
			setExpandedKeys([...expandedKeys, item.apiId]);
		};
		const deleteHandle = async (canDelete?: boolean) => {
			if (!canDelete) {
				if (hasEditting) {
					return message.warning('请先退出编辑操作');
				}
			}

			console.log(item);
			let shouldDeleteTruly = !item.isTemp;
			let deleteIds = [item.apiId];
			function dfs(children: any[]) {
				for (const deleteNode of children) {
					shouldDeleteTruly = deleteNode.isTemp ? shouldDeleteTruly : true;
					deleteIds.push(deleteNode.apiId);
					if (deleteNode.children?.length) {
						dfs(deleteNode.children);
					}
				}
			}
			if (item.children?.length) {
				dfs(item.children);
			}
			if (shouldDeleteTruly) {
				let res = await deleteApisByApiIdsApi(deleteIds);
				console.log(res);
				message[res.data.type](res.data.msg);
				if (res.data.type === ResponseType.SUCCESS) {
					deleteNodeByLocal();
				}
			} else {
				deleteNodeByLocal();
			}
			setHasEditting(false);
			function deleteNodeByLocal() {
				if (item.parent) {
					item.parent.children = item.parent.children.filter(
						(node: any) => node.apiId !== item.apiId
					);
					setTreeData([...treeData]);
				} else {
					setTreeData(
						treeData.filter((node: any) => node.apiId !== item.apiId)
					);
				}
			}
		};

		const onKeyUp = (e: { keyCode: number }) => {
			if (e.keyCode === 13) {
				saveHandle();
			}
		};
		return (
			<div className="tree-node" title="">
				{item.apiId === currentItem.apiId ? (
					<>
						<div className="edit-inputs">
							<div className="edit-input">
								<span>权限名称：</span>
								<Input
									size="small"
									placeholder="请输入权限名称"
									type="username"
									value={currentItem.apiName}
									onChange={apiNameChange}
								/>
							</div>
							<div className="edit-input">
								<span>权限路径：</span>
								<Input
									size="small"
									placeholder="请输入权限路径"
									type="username"
									value={currentItem.apiPath}
									onChange={apiPathChange}
									onKeyUp={onKeyUp}
								/>
							</div>
						</div>
						<div className="edit-options">
							<a className="option" href="#!" onClick={saveHandle}>
								确定
							</a>
							<a className="option" href="#!" onClick={cancelHandle}>
								取消
							</a>
						</div>
					</>
				) : (
					<>
						<div className="content" title={item.apiPath}>
							{item.apiName}
						</div>
						{editable && (
							<div className="options">
								<Tooltip mouseLeaveDelay={0} placement="bottom" title="编辑">
									<EditOutlined onClick={editHandle} />
								</Tooltip>
								<Tooltip mouseLeaveDelay={0} placement="bottom" title="添加">
									<PlusCircleOutlined onClick={addHandle} />
								</Tooltip>
								<Tooltip mouseLeaveDelay={0} placement="bottom" title="删除">
									<Popconfirm
										title="确定要删除此权限？（此操作不可回退！）"
										onConfirm={() => deleteHandle()}
										okText="确定"
										cancelText="取消"
									>
										<DeleteOutlined className="delete" />
									</Popconfirm>
								</Tooltip>
							</div>
						)}
					</>
				)}
			</div>
		);
	};

	const onExpand = (expandedKeys: any) => {
		setExpandedKeys(expandedKeys);
	};

	const addTopApi = () => {
		if (hasEditting) {
			return message.warning('请先退出编辑操作');
		}
		setHasEditting(true);
		const newApi: any = {
			apiId: nanoid(),
			apiName: '默认权限名',
			apiPath: '/默认权限路径',
			parent: null,
			isTemp: true
		};
		setTreeData([...treeData, newApi]);
		setCurrentItem(newApi);
	};

	async function save(api: any) {
		let newApi = {
			apiId: api.apiId,
			parentApiId: api.parent ? api.parent.apiId : null,
			apiName: api.apiName,
			apiPath: api.apiPath,
			apiType: api.children?.length ? 0 : 1
		};
		let res = await updateApiApi(newApi);
		message[res.data.type](res.data.msg);
		return res;
	}

	const onCheck = (checkedKeys: any, e: any) => {
		onChecked && onChecked(checkedKeys, e.halfCheckedKeys);
		setChecked(checkedKeys);
		setHalfChecked(e.halfCheckedKeys);
	};

	return (
		<div className="api-tree">
			{addTopable && (
				<div className="top-options">
					<div className="top-item">
						<Button type="primary" onClick={addTopApi}>
							添加顶级
						</Button>
					</div>
				</div>
			)}
			<Tree
				style={{ maxHeight: maxHeight }}
				draggable={draggable && !hasEditting}
				checkable={checkable}
				onDrop={info => {
					draggable && onDrop(info);
				}}
				expandedKeys={expandedKeys}
				titleRender={titleRander}
				treeData={treeData as unknown as (DataNode | BasicDataNode)[]}
				onExpand={onExpand}
				checkedKeys={{ checked: checked, halfChecked: halfChecked }}
				onCheck={onCheck}
				fieldNames={{
					title: 'apiName',
					key: 'apiId'
				}}
			/>
		</div>
	);
};

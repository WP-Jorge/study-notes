import React, { Key, useEffect, useState } from 'react';
import './index.scss';
import { message, Popconfirm, Space, Table, Tooltip } from 'antd';
import { ResponseType } from '@/globals/responseType';
import { ColumnsType } from 'antd/es/table';
import { TableTop } from '@/components/content/TableTop';
import { RoleModal } from '../RoleModal';
import {
	Role,
	deleteRolesByRoleIdsApi,
	getRolesByRoleNamePageApi
} from '@/networks/role';
import { RoleApiModal } from '../RoleApiModal';
import { RoleApi, updateRoleApisByRoleIdApi } from '@/networks/roleApi';

export const RoleTable = () => {
	const [pageData, setPageData] = useState([] as Array<Role>);
	const [currentPage, setCurrentPage] = useState(1);
	const [pageSize, setPageSize] = useState(5);
	const [total, setTotal] = useState(0);
	const [selectedRowKeys, setSelectedRowKeys] = useState([] as Key[]);
	const [editData, setEditData] = useState({} as Role);
	const [roleVisible, setRoleVisible] = useState(false);
	const [roleApiVisible, setRoleApiVisible] = useState(false);
	const [keyword, setKeyword] = useState('');
	const columns: ColumnsType<Role> = [
		{
			title: '角色ID',
			dataIndex: 'roleId',
			ellipsis: true,
			width: 180,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '角色名',
			dataIndex: 'roleName',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '操作',
			key: 'action',
			width: 140,
			render: item => {
				const deleteHandle = async (clickItem: Role) => {
					let res = await deleteRolesByRoleIdsApi([clickItem.roleId]);
					console.log(res);
					message[res.data.type](res.data.msg);
					if (res.data.type === ResponseType.SUCCESS) {
						pageBarChange(
							(currentPage - 1) * pageSize >= total - 1
								? currentPage - 1
								: currentPage,
							pageSize
						);
					}
				};
				const editHandle = (clickItem: Role) => {
					setEditData(clickItem);
					setRoleVisible(true);
				};
				const roleApiHandle = (clickItem: Role) => {
					setEditData(clickItem);
					setRoleApiVisible(true);
				};
				return (
					<Space>
						<a href="#!" onClick={() => editHandle(item)}>
							编辑
						</a>
						<a href="#!" onClick={() => roleApiHandle(item)}>
							权限
						</a>
						<Popconfirm
							title="确定要删除？（此操作将永久删除该角色）"
							onConfirm={() => deleteHandle(item)}
							okText="确定"
							cancelText="取消"
						>
							<a className="dangerous" href="#!">
								删除
							</a>
						</Popconfirm>
					</Space>
				);
			}
		}
	];
	useEffect(() => {
		pageBarChange(currentPage, pageSize ?? 3);
	}, []);
	async function pageBarChange(
		pageNumber: number,
		pageSize: number,
		searchWord?: string
	) {
		let res = await getRolesByRoleNamePageApi(
			pageNumber,
			pageSize,
			searchWord ?? keyword
		);
		setPageData(res.data.pageList);
		setCurrentPage(res.data.currentPage);
		setPageSize(res.data.pageSize);
		setTotal(res.data.total);
	}
	async function onSearch(searchWord: string) {
		setKeyword(searchWord);
		pageBarChange(1, pageSize, searchWord);
	}
	async function onDelete() {
		let res = await deleteRolesByRoleIdsApi(
			selectedRowKeys as unknown as bigint[]
		);
		console.log(res);
		message[res.data.type](res.data.msg);
		pageBarChange(
			(currentPage - 1) * pageSize >= total - selectedRowKeys.length
				? currentPage - 1
				: currentPage,
			pageSize
		);
		if (res.data.type === ResponseType.SUCCESS) {
			setSelectedRowKeys([]);
		}
	}
	async function onAdd() {
		setEditData({} as Role);
		setRoleVisible(true);
	}
	function onSelectChange(selectedRowKeys: Key[]) {
		console.log(selectedRowKeys);
		setSelectedRowKeys(selectedRowKeys);
	}
	const clickRoleSubmit = () => {
		pageBarChange(currentPage, pageSize);
		setRoleVisible(false);
	};
	const clickRoleCancel = () => {
		setRoleVisible(false);
	};
	const clickRoleApiSubmit = async (allCheckedKeys: Key[]) => {
		console.log(allCheckedKeys);
		let updateRoleApi = createUpdateData(
			allCheckedKeys,
			editData.roleId as unknown as Key
		);
		let res = await updateRoleApisByRoleIdApi({
			roleId: editData.roleId,
			roleApis: updateRoleApi
		});
		console.log(res);
		message[res.data.type](res.data.msg);

		function createUpdateData(allCheckedKeys: Key[], roleId: Key) {
			let updateRoleApi = [] as RoleApi[];
			for (const apiId of allCheckedKeys) {
				let roleApi = {} as RoleApi;
				roleApi.roleId = roleId;
				roleApi.apiId = apiId;
				updateRoleApi.push(roleApi);
			}
			return updateRoleApi;
		}

		if (res.data.type === ResponseType.SUCCESS) {
			setRoleApiVisible(false);
			pageBarChange(currentPage, pageSize);
		}
	};
	const clickRoleApiCancel = () => {
		setRoleApiVisible(false);
	};
	return (
		<div className="role-table">
			<TableTop
				searchOption={{
					placeholder: '请输入角色名',
					onSearch: (searchWord: string) => onSearch(searchWord)
				}}
				deleteOption={{
					deleteTips: '确定要删除？（此操作将永久删除所选角色）',
					onDelete: () => onDelete()
				}}
				addOption={{
					title: '添加角色',
					onAdd: () => onAdd()
				}}
			/>
			<Table
				columns={columns}
				pagination={{
					current: currentPage,
					pageSize: pageSize,
					total: total,
					onChange: pageBarChange,
					hideOnSinglePage: true
				}}
				dataSource={pageData}
				rowSelection={{
					selectedRowKeys,
					onChange: selectedRowKeys => onSelectChange(selectedRowKeys)
				}}
				rowKey="roleId"
			/>
			{roleVisible && (
				<RoleModal
					formData={editData}
					clickSubmit={clickRoleSubmit}
					clickCancel={clickRoleCancel}
				/>
			)}
			{roleApiVisible && (
				<RoleApiModal
					roleData={editData}
					clickSubmit={clickRoleApiSubmit}
					clickCancel={clickRoleApiCancel}
				/>
			)}
		</div>
	);
};

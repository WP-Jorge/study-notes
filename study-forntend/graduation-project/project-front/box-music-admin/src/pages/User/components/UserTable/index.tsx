import React, { Key, useEffect, useState } from 'react';
import './index.scss';
import {
	Image,
	message,
	Popconfirm,
	Space,
	Switch,
	Table,
	Tooltip
} from 'antd';
import {
	deleteUsersByUserIdsApi,
	getUsersByUsernamePageApi,
	resetPasswordByUserIdApi,
	updateUserStatusApi,
	User
} from '@/networks/user';
import { ResponseType } from '@/globals/responseType';
import { ColumnsType } from 'antd/es/table';
import { TableTop } from '@/components/content/TableTop';
import { UserModal } from '../UserModal';

export const UserTable = () => {
	const [pageData, setPageData] = useState([] as Array<User>);
	const [currentPage, setCurrentPage] = useState(1);
	const [pageSize, setPageSize] = useState(3);
	const [total, setTotal] = useState(0);
	const [selectedRowKeys, setSelectedRowKeys] = useState([] as Key[]);
	const [editData, setEditData] = useState({} as User);
	const [visible, setVisible] = useState(false);
	const [keyword, setKeyword] = useState('');
	const columns: ColumnsType<User> = [
		{
			title: '用户ID',
			dataIndex: 'userId',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '用户名',
			dataIndex: 'username',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '性别',
			dataIndex: 'sex',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '年龄',
			dataIndex: 'age',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '电话号码',
			dataIndex: 'tel',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '邮箱',
			dataIndex: 'email',
			ellipsis: true,
			width: 160,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '用户头像',
			dataIndex: 'userPic',
			align: 'center',
			width: 120,
			render: (userPic: string) => {
				userPic =
					import.meta.env.VITE_BASE_URL +
					import.meta.env.VITE_USER_PICTURES +
					userPic;
				return (
					<Image
						width={70}
						src={userPic}
						fallback="/src/assets/userImage.jfif"
					/>
				);
			}
		},
		{
			title: '用户状态',
			dataIndex: 'status',
			width: 100,
			render: (_: number, item: User) => {
				const onChange = async (checked: boolean) => {
					let userStatus = checked ? 1 : 0;
					let res = await updateUserStatusApi(item.userId, userStatus);
					console.log(res);
					if (res.data.type === ResponseType.SUCCESS) {
						item.status = userStatus;
						setPageData([...pageData]);
					}
					message[res.data.type](res.data.msg);
				};
				return (
					<Switch
						checked={item.status === 1}
						checkedChildren="正常"
						unCheckedChildren="冻结"
						onChange={(checked: boolean) => onChange(checked)}
					/>
				);
			}
		},
		{
			title: '操作',
			key: 'action',
			width: 100,
			render: item => {
				const deleteHandle = async (clickItem: User) => {
					let res = await deleteUsersByUserIdsApi([clickItem.userId]);
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
				const editHandle = (clickItem: User) => {
					setEditData(clickItem);
					setVisible(true);
				};
				const resetPassword = async (clickItem: User) => {
					let res = await resetPasswordByUserIdApi(clickItem.userId);
					console.log(res);
					message[res.data.type](res.data.msg);
				};
				return (
					<Space direction="vertical" size={6}>
						<Popconfirm
							title="确定要重置该用户密码？"
							onConfirm={() => resetPassword(item)}
							okText="确定"
							cancelText="取消"
						>
							<a href="#!">重置密码</a>
						</Popconfirm>
						<a href="#!" onClick={() => editHandle(item)}>
							编辑
						</a>
						<Popconfirm
							title="确定要删除该用户？（此操作将进行逻辑删除）"
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
		let res = await getUsersByUsernamePageApi(
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
		let res = await deleteUsersByUserIdsApi(
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
		setEditData({} as User);
		setVisible(true);
	}
	function onSelectChange(selectedRowKeys: Key[]) {
		console.log(selectedRowKeys);
		setSelectedRowKeys(selectedRowKeys);
	}
	const clickSubmit = () => {
		pageBarChange(currentPage, pageSize);
		setVisible(false);
	};
	const clickCancel = () => {
		setVisible(false);
	};
	return (
		<div className="user-table">
			<TableTop
				searchOption={{
					placeholder: '请输入用户名',
					onSearch: (searchWord: string) => onSearch(searchWord)
				}}
				deleteOption={{
					onDelete: () => onDelete()
				}}
				addOption={{
					title: '添加用户',
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
				rowKey="userId"
			/>
			{visible && (
				<UserModal
					formData={editData}
					clickSubmit={() => clickSubmit()}
					clickCancel={() => clickCancel()}
				/>
			)}
		</div>
	);
};

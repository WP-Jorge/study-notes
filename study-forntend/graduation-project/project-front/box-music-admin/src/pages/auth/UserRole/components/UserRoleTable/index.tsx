import React, { Key, useEffect, useState } from 'react';
import './index.scss';
import { Space, Table, Tooltip } from 'antd';
import { ColumnsType } from 'antd/es/table';
import { TableTop } from '@/components/content/TableTop';
import { UserRoleModal } from '../UserRoleModal';
import { getUserRolesByUsernamePageApi, UserRole } from '@/networks/userRole';
import { Role } from '@/networks/system';

export const UserTable = () => {
	const [pageData, setPageData] = useState([] as Array<UserRole>);
	const [currentPage, setCurrentPage] = useState(1);
	const [pageSize, setPageSize] = useState(5);
	const [total, setTotal] = useState(0);
	const [selectedRowKeys, setSelectedRowKeys] = useState([] as Key[]);
	const [editData, setEditData] = useState({} as UserRole);
	const [visible, setVisible] = useState(false);
	const [keyword, setKeyword] = useState('');
	const columns: ColumnsType<UserRole> = [
		{
			title: '用户ID',
			dataIndex: 'userId',
			ellipsis: true,
			width: 180,
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
			width: 130,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '角色',
			dataIndex: 'roleList',
			ellipsis: true,
			render: (roleList: Array<Role>) => {
				let roles = [] as string[];
				roleList.map(item => {
					roles.push(item.roleName);
				});
				return (
					<Tooltip placement="topLeft" title={roles.join(',')}>
						{roles.length ? roles.join(',') : '暂无数据'}
					</Tooltip>
				);
			}
		},
		{
			title: '操作',
			key: 'action',
			width: 100,
			render: item => {
				const editHandle = (clickItem: UserRole) => {
					setEditData(clickItem);
					setVisible(true);
				};
				return (
					<Space direction="vertical">
						<a href="#!" onClick={() => editHandle(item)}>
							编辑角色
						</a>
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
		let res = await getUserRolesByUsernamePageApi(
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
		<div className="user-role-table">
			<TableTop
				searchOption={{
					placeholder: '请输入用户名',
					onSearch: (searchWord: string) => onSearch(searchWord)
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
				<UserRoleModal
					userRole={editData}
					clickSubmit={() => clickSubmit()}
					clickCancel={() => clickCancel()}
				/>
			)}
		</div>
	);
};

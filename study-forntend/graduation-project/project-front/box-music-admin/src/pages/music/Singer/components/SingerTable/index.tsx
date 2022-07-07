import React, { Key, useEffect, useState } from 'react';
import './index.scss';
import { Image, message, Popconfirm, Space, Table, Tooltip } from 'antd';
import { ResponseType } from '@/globals/responseType';
import { ColumnsType } from 'antd/es/table';
import { TableTop } from '@/components/content/TableTop';
import { SingerModal } from '../SingerModal';
import {
	deleteSingersBySingerIdsApi,
	getSingersBySingerNamePageApi,
	Singer
} from '@/networks/singer';

export const SingerTable = () => {
	const [pageData, setPageData] = useState([] as Array<Singer>);
	const [currentPage, setCurrentPage] = useState(1);
	const [pageSize, setPageSize] = useState(3);
	const [total, setTotal] = useState(0);
	const [selectedRowKeys, setSelectedRowKeys] = useState([] as Key[]);
	const [editData, setEditData] = useState({} as Singer);
	const [visible, setVisible] = useState(false);
	const [keyword, setKeyword] = useState('');
	const columns: ColumnsType<Singer> = [
		{
			title: '歌手ID',
			dataIndex: 'singerId',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '歌手名',
			dataIndex: 'singerName',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '歌手描述',
			dataIndex: 'singerDescription',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '浏览量',
			dataIndex: 'totalViews',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '歌手图片',
			dataIndex: 'singerPic',
			align: 'center',
			width: 120,
			render: (singerPic: string) => {
				singerPic =
					import.meta.env.VITE_BASE_URL +
					import.meta.env.VITE_SINGER_PICTURES +
					singerPic;
				return (
					<Image
						width={70}
						src={singerPic}
						fallback="/src/assets/userImage.jfif"
					/>
				);
			}
		},
		{
			title: '操作',
			key: 'action',
			width: 100,
			render: item => {
				const deleteHandle = async (clickItem: Singer) => {
					let res = await deleteSingersBySingerIdsApi([
						clickItem.singerId as bigint
					]);
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
				const editHandle = (clickItem: Singer) => {
					setEditData(clickItem);
					setVisible(true);
				};
				return (
					<Space size={6}>
						<a href="#!" onClick={() => editHandle(item)}>
							编辑
						</a>
						<Popconfirm
							title="确定要删除该歌手？（此操作将进行逻辑删除）"
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
		let res = await getSingersBySingerNamePageApi(
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
		let res = await deleteSingersBySingerIdsApi(
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
		setEditData({} as Singer);
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
		<div className="singer-table">
			<TableTop
				searchOption={{
					placeholder: '请输入歌手名',
					onSearch: (searchWord: string) => onSearch(searchWord)
				}}
				deleteOption={{
					deleteTips: '确定要删除所选歌手？（此操作将进行逻辑删除）',
					onDelete: () => onDelete()
				}}
				addOption={{
					title: '添加歌手',
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
				rowKey="singerId"
			/>
			{visible && (
				<SingerModal
					formData={editData}
					clickSubmit={() => clickSubmit()}
					clickCancel={() => clickCancel()}
				/>
			)}
		</div>
	);
};

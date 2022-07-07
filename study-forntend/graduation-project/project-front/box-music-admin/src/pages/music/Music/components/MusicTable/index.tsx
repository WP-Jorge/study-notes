import React, { Key, useEffect, useState } from 'react';
import './index.scss';
import { message, Popconfirm, Space, Table, Tooltip } from 'antd';
import { ResponseType } from '@/globals/responseType';
import { ColumnsType } from 'antd/es/table';
import { TableTop } from '@/components/content/TableTop';
import { UserModal } from '../MusicModal';
import {
	deleteMusicsByMusicIdsApi,
	getMusicsByMusicTitlePageApi,
	Music
} from '@/networks/music';
import { Album } from '@/networks/album';

export const MusicTable = () => {
	const [pageData, setPageData] = useState([] as Array<Music>);
	const [currentPage, setCurrentPage] = useState(1);
	const [pageSize, setPageSize] = useState(3);
	const [total, setTotal] = useState(0);
	const [selectedRowKeys, setSelectedRowKeys] = useState([] as Key[]);
	const [editData, setEditData] = useState({} as Music);
	const [visible, setVisible] = useState(false);
	const [keyword, setKeyword] = useState('');
	const columns: ColumnsType<Music> = [
		{
			title: '音乐ID',
			dataIndex: 'musicId',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '歌曲名',
			dataIndex: 'musicTitle',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '歌词',
			dataIndex: 'lyric',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '专辑',
			dataIndex: 'album',
			ellipsis: true,
			render: (record: Album) => {
				return (
					<Tooltip placement="topLeft" title={record.albumName}>
						{record.albumName}
					</Tooltip>
				);
			}
		},
		{
			title: '时长',
			dataIndex: 'duration',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '大小',
			dataIndex: 'size',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '品质',
			dataIndex: 'level',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '格式',
			dataIndex: 'musicFormat',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '码率',
			dataIndex: 'bitrate',
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
			title: '操作',
			key: 'action',
			width: 100,
			render: item => {
				const editHandle = (clickItem: Music) => {
					setEditData(clickItem);
					setVisible(true);
				};
				const deleteHandle = async (clickItem: Music) => {
					let res = await deleteMusicsByMusicIdsApi([
						clickItem.musicId as bigint
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
				return (
					<Space direction="vertical" size={6}>
						<a
							href="#!"
							className={item.deleted === 1 ? 'disabled' : ''}
							onClick={() => editHandle(item)}
						>
							编辑
						</a>
						<Popconfirm
							title="确定要删除该音乐？（此操作将进行逻辑删除）"
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
		let res = await getMusicsByMusicTitlePageApi(
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
		let res = await deleteMusicsByMusicIdsApi(
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
		setEditData({} as Music);
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
					placeholder: '请输入音乐名',
					onSearch: (searchWord: string) => onSearch(searchWord)
				}}
				deleteOption={{
					onDelete: () => onDelete()
				}}
				addOption={{
					title: '添加音乐',
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
				rowKey="musicId"
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

import React, { Key, useEffect, useState } from 'react';
import './index.scss';
import { Image, message, Popconfirm, Space, Table, Tooltip } from 'antd';
import { ResponseType } from '@/globals/responseType';
import { ColumnsType } from 'antd/es/table';
import { TableTop } from '@/components/content/TableTop';
import { PlaylistModal } from '../PlaylistModal';
import {
	deletePlaylistsByPlaylistIdsApi,
	getPlaylistsByPlaylistNamePageApi,
	Playlist
} from '@/networks/playlist';

export const PlaylistTable = () => {
	const [pageData, setPageData] = useState([] as Array<Playlist>);
	const [currentPage, setCurrentPage] = useState(1);
	const [pageSize, setPageSize] = useState(3);
	const [total, setTotal] = useState(0);
	const [selectedRowKeys, setSelectedRowKeys] = useState([] as Key[]);
	const [editData, setEditData] = useState({} as Playlist);
	const [visible, setVisible] = useState(false);
	const [keyword, setKeyword] = useState('');
	const columns: ColumnsType<Playlist> = [
		{
			title: '歌单ID',
			dataIndex: 'playlistId',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '歌单名',
			dataIndex: 'playlistName',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '歌单描述',
			dataIndex: 'playlistDescription',
			ellipsis: true,
			render: (str: string) => (
				<Tooltip placement="topLeft" title={str}>
					{str}
				</Tooltip>
			)
		},
		{
			title: '创建者',
			dataIndex: 'userName',
			ellipsis: true,
			render: (str: string, record) => (
				<Tooltip placement="topLeft" title={str}>
					{record.user.username}
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
			title: '公开状态',
			dataIndex: 'opened',
			ellipsis: true,
			render: (str: string) => {
				str = str == '0' ? '私有' : '公开';
				return (
					<Tooltip placement="topLeft" title={str}>
						{str}
					</Tooltip>
				);
			}
		},
		{
			title: '歌单图片',
			dataIndex: 'playlistPic',
			align: 'center',
			width: 120,
			render: (picture: string) => {
				picture =
					import.meta.env.VITE_BASE_URL +
					import.meta.env.VITE_PLAYLIST_PICTURES +
					picture;
				return (
					<Image
						width={70}
						src={picture}
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
				const deleteHandle = async (clickItem: Playlist) => {
					let res = await deletePlaylistsByPlaylistIdsApi([
						clickItem.playlistId as bigint
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
				const editHandle = (clickItem: Playlist) => {
					setEditData(clickItem);
					setVisible(true);
				};
				return (
					<Space size={6}>
						<a href="#!" onClick={() => editHandle(item)}>
							编辑
						</a>
						<Popconfirm
							title="确定要删除所选歌单？（此操作将永久删除该歌单）"
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
		let res = await getPlaylistsByPlaylistNamePageApi(
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
		let res = await deletePlaylistsByPlaylistIdsApi(
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
		setEditData({} as Playlist);
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
		<div className="playlist-table">
			<TableTop
				searchOption={{
					placeholder: '请输入歌单名',
					onSearch: (searchWord: string) => onSearch(searchWord)
				}}
				deleteOption={{
					deleteTips: '确定要删除所选歌单？（此操作将永久删除所选歌单）',
					onDelete: () => onDelete()
				}}
				addOption={{
					title: '添加歌单',
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
				rowKey="playlistId"
			/>
			{visible && (
				<PlaylistModal
					formData={editData}
					clickSubmit={() => clickSubmit()}
					clickCancel={() => clickCancel()}
				/>
			)}
		</div>
	);
};

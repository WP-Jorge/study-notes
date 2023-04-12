import { ResponseType } from '@/globals/responseType';
import { addMusicApi, Music, updateMusicApi } from '@/networks/music';
import { UploadOutlined } from '@ant-design/icons';
import { Button, Form, Input, message, Modal, Upload } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import TextArea from 'antd/lib/input/TextArea';
import { UploadFile } from 'antd/lib/upload/interface';
import React, { useEffect, useState } from 'react';
import './index.scss';
import { LabeledValue } from 'antd/lib/select';
import { DebounceSelect } from '../../../../../components/common/DebounceSelect';
import {
	Category,
	getCategoriesByCategoryNamePageApi
} from '@/networks/category';
import { getSingersBySingerNamePageApi, Singer } from '@/networks/singer';
import { Album, getAlbumsByAlbumNamePageApi } from '@/networks/album';
// import { Album, getAlbumsByAlbumNamePageApi } from '@/networks/album';

export interface MusicModalProps {
	formData?: Music;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const UserModal = (props: MusicModalProps) => {
	const { formData, clickSubmit, clickCancel } = props;
	const [title] = useState(formData?.musicId ? '编辑音乐' : '添加音乐');
	const [form] = useForm();

	const [value, setValue] = useState([] as LabeledValue[]);
	const [album, setAlbum] = useState({} as LabeledValue);

	useEffect(() => {
		console.log(formData);

		if (formData?.musicId) {
			let categoryList = [];
			let singerList = [];
			for (const category of formData.categories) {
				let item = {} as LabeledValue;
				item.label = category.categoryName;
				item.value = category.categoryId as string;
				categoryList.push(item);
			}
			for (const singer of formData.singers) {
				let item = {} as LabeledValue;
				item.label = singer.singerName;
				item.value = singer.singerId as string;
				singerList.push(item);
			}
			formData.categoryList = categoryList;
			formData.singerList = singerList;
			formData.albumLabel = {
				label: formData.album?.albumName,
				value: formData.album?.albumId
			};
		}
		form.setFieldsValue({
			...formData
		});
	}, []);

	const layout = {
		labelCol: { span: 4 },
		wrapperCol: { span: 20 }
	};

	const onFinish = async (values: any) => {
		const data = new FormData();
		console.log(values);

		if (values.pictures?.length) {
			data.append('picture', values.pictures[0]?.originFileObj);
		}
		if (values.musics?.length) {
			data.append('song', values.musics[0]?.originFileObj);
		}
		if (values.categoryList.length) {
			values.categoryList.forEach((item: LabeledValue, index: number) => {
				data.append(`categoryList[${index}].categoryId`, item.value as string);
				data.append(
					`categoryList[${index}].categoryName`,
					item.label as string
				);
			});
		}

		if (values.singerList.length) {
			values.singerList.forEach((item: LabeledValue, index: number) => {
				data.append(`singerList[${index}].singerId`, item.value as string);
				data.append(`singerList[${index}].singerName`, item.label as string);
			});
		}
		data.append('albumId', values.albumLabel.value);
		for (const key in values) {
			if (
				Object.prototype.hasOwnProperty.call(values, key) &&
				key !== 'musics' &&
				key !== 'pictures' &&
				key !== 'categoryList' &&
				key !== 'singerList'
			) {
				const value = values[key];
				if (value || value === 0) {
					data.append(key, value);
				}
			}
		}
		let res;
		if (formData?.musicId) {
			data.append('musicId', formData.musicId + '');
			res = await updateMusicApi(data);
		} else {
			res = await addMusicApi(data);
		}
		console.log(res);
		message[res.data.type](res.data.msg);
		if (res.data.type === ResponseType.SUCCESS) {
			clickSubmit();
		}
	};
	const normFile = (e: any) => {
		console.log('Upload event:', e);
		if (Array.isArray(e)) {
			return e;
		}
		return e && e.fileList;
	};
	const beforeMusicUpload = (file: UploadFile) => {
		const isSong = file.type?.startsWith('audio');
		if (!isSong) {
			message.error('只能上传音频');
			return Upload.LIST_IGNORE;
		}
		const isLt100M = (file.size as number) / 1024 / 1024 < 100;
		if (!isLt100M) {
			message.error('音频大小必须小于 100MB');
			return Upload.LIST_IGNORE;
		}
		return false;
	};

	async function fetchCategoryList(categoryName: string) {
		console.log('fetching category', categoryName);
		let res = await getCategoriesByCategoryNamePageApi(1, 10, categoryName);
		let categoryList = [];
		if (res.data.type === ResponseType.SUCCESS) {
			categoryList = res.data.pageList.map((item: Category) => ({
				label: item.categoryName,
				value: item.categoryId
			}));
		}
		return categoryList;
	}

	async function fetchSingerList(singerName: string) {
		console.log('fetching singer', singerName);
		let res = await getSingersBySingerNamePageApi(1, 10, singerName);
		let singerList = [];
		if (res.data.type === ResponseType.SUCCESS) {
			singerList = res.data.pageList.map((item: Singer) => ({
				label: item.singerName,
				value: item.singerId
			}));
		}
		return singerList;
	}

	async function fetchAlbumList(albumName: string) {
		console.log('fetching album', albumName);
		let res = await getAlbumsByAlbumNamePageApi(1, 10, albumName);
		let albumList = [];
		if (res.data.type === ResponseType.SUCCESS) {
			albumList = res.data.pageList.map((item: Album) => ({
				label: item.albumName,
				value: item.albumId
			}));
		}
		return albumList;
	}

	return (
		<div className="music-modal">
			<Modal
				getContainer={false}
				title={title}
				open={true}
				onCancel={clickCancel}
				footer={[
					<Button key="back" onClick={clickCancel}>
						取消
					</Button>,
					<Button key="submit" type="primary" onClick={form.submit}>
						确定
					</Button>
				]}
			>
				<Form {...layout} form={form} name="control-hooks" onFinish={onFinish}>
					<Form.Item
						name="musics"
						label="音乐"
						valuePropName="fileList"
						getValueFromEvent={normFile}
						rules={[{ required: !formData?.musicId, message: '请上传音乐' }]}
						extra={!!formData?.musicId && '如果不上传音乐则使用原音乐'}
					>
						<Upload
							name="音乐"
							listType="picture"
							beforeUpload={beforeMusicUpload}
							maxCount={1}
						>
							<Button icon={<UploadOutlined />}>点击上传音乐</Button>
						</Upload>
					</Form.Item>
					<Form.Item
						name="musicTitle"
						label="音乐名"
						rules={[{ required: true, message: '音乐名不能为空' }]}
					>
						<Input />
					</Form.Item>
					<Form.Item name="lyric" label="歌词">
						<TextArea rows={6} />
					</Form.Item>
					<Form.Item
						name="categoryList"
						label="分类"
						rules={[{ required: true, message: '分类不能为空' }]}
					>
						<DebounceSelect
							mode="multiple"
							value={value}
							placeholder="请选择分类"
							fetchOptions={fetchCategoryList}
							onChange={(newValue: LabeledValue[]) => {
								setValue(newValue);
							}}
							style={{
								width: '100%'
							}}
						/>
					</Form.Item>
					<Form.Item
						name="singerList"
						label="歌手"
						rules={[{ required: true, message: '歌手不能为空' }]}
					>
						<DebounceSelect
							mode="multiple"
							value={value}
							placeholder="请选择歌手"
							fetchOptions={fetchSingerList}
							onChange={(newValue: LabeledValue[]) => {
								setValue(newValue);
							}}
							style={{
								width: '100%'
							}}
						/>
					</Form.Item>
					<Form.Item
						name="albumLabel"
						label="专辑"
						rules={[{ required: true, message: '专辑不能为空' }]}
					>
						<DebounceSelect
							showSearch
							mode="combobox"
							value={album}
							placeholder="请选择专辑"
							fetchOptions={fetchAlbumList}
							onChange={(newValue: LabeledValue) => {
								setAlbum(newValue);
							}}
							style={{
								width: '100%'
							}}
						/>
						{/* <Select
							showSearch
							placeholder="select your gender"
							onChange={albumChange}
							onSearch={albumSearch}
						>
							<Select.Option value="male">Male</Select.Option>
							<Select.Option value="female">Female</Select.Option>
							<Select.Option value="other">Other</Select.Option>
						</Select> */}
					</Form.Item>
					{formData?.musicId ? (
						<Form.Item name="totalViews" label="浏览量">
							<Input disabled />
						</Form.Item>
					) : (
						''
					)}
				</Form>
			</Modal>
		</div>
	);
};

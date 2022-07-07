import { DebounceSelect } from '@/components/common/DebounceSelect';
import { ResponseType } from '@/globals/responseType';
import {
	Category,
	getCategoriesByCategoryNamePageApi
} from '@/networks/category';
import {
	addPlaylistApi,
	Playlist,
	updatePlaylistApi
} from '@/networks/playlist';
import { PlusOutlined } from '@ant-design/icons';
import { Button, Form, Input, message, Modal, Switch, Upload } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import { LabeledValue } from 'antd/lib/select';
import { UploadFile } from 'antd/lib/upload/interface';
import React, { useEffect, useState } from 'react';
import './index.scss';

export interface PlaylistModalProps {
	formData?: Playlist;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const PlaylistModal = (props: PlaylistModalProps) => {
	const { formData, clickSubmit, clickCancel } = props;
	const [title] = useState(formData?.playlistId ? '编辑歌单' : '添加歌单');
	const [fileList, setFileList] = useState([] as UploadFile[]);
	const [value, setValue] = useState([] as LabeledValue[]);
	const [form] = useForm();
	useEffect(() => {
		if (formData?.playlistPic) {
			const files = [
				{
					url:
						import.meta.env.VITE_BASE_URL +
						import.meta.env.VITE_PLAYLIST_PICTURES +
						formData.playlistPic
				} as unknown as UploadFile
			];
			setFileList(files);
			formData.fileList = files;
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
		console.log(values);
		const data = new FormData();
		if (values.fileList?.length) {
			data.append('picture', values.fileList[0]?.originFileObj);
		}
		for (const key in values) {
			if (Object.prototype.hasOwnProperty.call(values, key)) {
				const value = values[key];
				if (value || value === 0) {
					data.append(key, value);
				}
			}
		}
		let res;
		if (formData?.playlistId) {
			data.append('playlistId', formData.playlistId + '');
			res = await updatePlaylistApi(data);
		} else {
			res = await addPlaylistApi(data);
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
	const beforeUpload = (file: UploadFile) => {
		const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
		if (!isJpgOrPng) {
			message.error('只能上传 JPG 或者 PNG 图片');
			return Upload.LIST_IGNORE;
		}
		const isLt2M = (file.size as number) / 1024 / 1024 < 2;
		if (!isLt2M) {
			message.error('图片大小必须小于 2MB');
			return Upload.LIST_IGNORE;
		}
		return false;
	};
	const onImgChange = (e: any) => {
		setFileList(e.fileList);
	};

	const normSwitch = (e: any) => {
		console.log('Upload event:', e);
		if (e === true) {
			return 1;
		}
		return 0;
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

	return (
		<div className="playlist-modal">
			<Modal
				getContainer={false}
				title={title}
				visible={true}
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
						name="fileList"
						label="歌单图片"
						valuePropName="fileList"
						getValueFromEvent={normFile}
						rules={[
							{ required: !formData?.playlistId, message: '歌单图片不能为空' }
						]}
						extra={!!formData?.playlistId && '如果不上传图片则使用原图片'}
					>
						<Upload
							listType="picture-card"
							name="歌单图片"
							fileList={fileList}
							beforeUpload={beforeUpload}
							maxCount={1}
							showUploadList={{
								showPreviewIcon: false
							}}
							onChange={onImgChange}
						>
							{fileList.length >= 1 ? null : <PlusOutlined />}
						</Upload>
					</Form.Item>
					<Form.Item
						name="playlistName"
						label="歌单名"
						rules={[{ required: true, message: '歌单名不能为空' }]}
					>
						<Input />
					</Form.Item>
					<Form.Item
						name="playlistDescription"
						label="歌单描述"
						rules={[{ required: true, message: '歌单描述不能为空' }]}
					>
						<Input />
					</Form.Item>
					{formData?.playlistId ? (
						<Form.Item label="创建者"> {formData.user.username}</Form.Item>
					) : (
						''
					)}
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
						name="opened"
						label="公开状态"
						valuePropName="checked"
						getValueFromEvent={normSwitch}
					>
						<Switch
							disabled={!!formData?.playlistId}
							checkedChildren="公开"
							unCheckedChildren="私有"
						/>
					</Form.Item>
					{formData?.playlistId ? (
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

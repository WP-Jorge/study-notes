import { ResponseType } from '@/globals/responseType';
import { addAlbumApi, Album, updateAlbumApi } from '@/networks/album';
import { PlusOutlined } from '@ant-design/icons';
import { Button, Form, Input, message, Modal, Upload } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import TextArea from 'antd/lib/input/TextArea';
import { UploadFile } from 'antd/lib/upload/interface';
import React, { useEffect, useState } from 'react';
import './index.scss';

export interface AlbumModalProps {
	formData?: Album;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const AlbumModal = (props: AlbumModalProps) => {
	const { formData, clickSubmit, clickCancel } = props;
	const [title] = useState(formData?.albumId ? '编辑专辑' : '添加专辑');
	const [fileList, setFileList] = useState([] as UploadFile[]);
	const [form] = useForm();
	useEffect(() => {
		if (formData?.albumPic) {
			const files = [
				{
					url:
						import.meta.env.VITE_BASE_URL +
						import.meta.env.VITE_ALBUM_PICTURES +
						formData.albumPic
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
		if (formData?.albumId) {
			data.append('albumId', formData.albumId + '');
			res = await updateAlbumApi(data);
		} else {
			res = await addAlbumApi(data);
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

	return (
		<div className="album-modal">
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
						name="fileList"
						label="专辑图片"
						valuePropName="fileList"
						getValueFromEvent={normFile}
						rules={[
							{ required: !formData?.albumId, message: '专辑图片不能为空' }
						]}
						extra={!!formData?.albumId && '如果不上传图片则使用原图片'}
					>
						<Upload
							listType="picture-card"
							name="专辑图片"
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
						name="albumName"
						label="专辑名"
						rules={[{ required: true, message: '专辑名不能为空' }]}
					>
						<Input />
					</Form.Item>
					<Form.Item name="albumDescription" label="专辑介绍">
						<TextArea rows={6} />
					</Form.Item>
					{formData?.albumId ? (
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

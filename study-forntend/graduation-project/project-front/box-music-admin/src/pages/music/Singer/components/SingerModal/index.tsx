import { ResponseType } from '@/globals/responseType';
import { addSingerApi, Singer, updateSingerApi } from '@/networks/singer';
import { PlusOutlined } from '@ant-design/icons';
import { Button, Form, Input, message, Modal, Upload } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import { UploadFile } from 'antd/lib/upload/interface';
import React, { useEffect, useState } from 'react';
import './index.scss';

export interface SingerModalProps {
	formData?: Singer;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const SingerModal = (props: SingerModalProps) => {
	const { formData, clickSubmit, clickCancel } = props;
	const [title] = useState(formData?.singerId ? '编辑歌手' : '添加');
	const [fileList, setFileList] = useState([] as UploadFile[]);
	const [form] = useForm();
	useEffect(() => {
		if (formData?.singerPic) {
			const files = [
				{
					url:
						import.meta.env.VITE_BASE_URL +
						import.meta.env.VITE_SINGER_PICTURES +
						formData.singerPic
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
		if (formData?.singerId) {
			data.append('singerId', formData.singerId + '');
			res = await updateSingerApi(data);
		} else {
			res = await addSingerApi(data);
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
		<div className="singer-modal">
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
						label="歌手图片"
						valuePropName="fileList"
						getValueFromEvent={normFile}
						rules={[
							{ required: !formData?.singerId, message: '歌手图片不能为空' }
						]}
						extra={!!formData?.singerId && '如果不上传图片则使用原图片'}
					>
						<Upload
							listType="picture-card"
							name="歌手图片"
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
						name="singerName"
						label="歌手名"
						rules={[{ required: true, message: '歌手名不能为空' }]}
					>
						<Input />
					</Form.Item>
					{formData?.singerId ? (
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

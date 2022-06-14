import { ResponseType } from '@/globals/responseType';
import {
	addCategoryApi,
	Category,
	updateCategoryApi
} from '@/networks/category';
import { PlusOutlined } from '@ant-design/icons';
import { Button, Form, Input, message, Modal, Upload } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import { UploadFile } from 'antd/lib/upload/interface';
import React, { useEffect, useState } from 'react';
import './index.scss';

export interface CategoryModalProps {
	formData?: Category;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const CategoryModal = (props: CategoryModalProps) => {
	const { formData, clickSubmit, clickCancel } = props;
	const [title] = useState(formData?.categoryId ? '编辑分类' : '添加分类');
	const [fileList, setFileList] = useState([] as UploadFile[]);
	const [form] = useForm();
	useEffect(() => {
		if (formData?.categoryPic) {
			console.log(formData);

			const files = [
				{
					url:
						import.meta.env.VITE_BASE_URL +
						import.meta.env.VITE_CATEGORY_PICTURES +
						formData.categoryPic
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
		if (formData?.categoryId) {
			data.append('categoryId', formData.categoryId + '');
			res = await updateCategoryApi(data);
		} else {
			res = await addCategoryApi(data);
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
		<div className="category-modal">
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
						label="分类图片"
						valuePropName="fileList"
						getValueFromEvent={normFile}
						rules={[
							{ required: !formData?.categoryId, message: '分类图片不能为空' }
						]}
						extra={!!formData?.categoryId && '如果不上传图片则使用原图片'}
					>
						<Upload
							listType="picture-card"
							name="分类图片"
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
						name="categoryName"
						label="分类名"
						rules={[{ required: true, message: '分类名不能为空' }]}
					>
						<Input />
					</Form.Item>
				</Form>
			</Modal>
		</div>
	);
};

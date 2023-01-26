import { ResponseType } from '@/globals/responseType';
import {
	addCategoryApi,
	Category,
	updateCategoryApi
} from '@/networks/category';
import { Button, Form, Input, message, Modal, Select } from 'antd';
import { useForm } from 'antd/lib/form/Form';
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
	const [form] = useForm();
	useEffect(() => {
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

	return (
		<div className="category-modal">
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
						name="categoryName"
						label="分类名"
						rules={[{ required: true, message: '分类名不能为空' }]}
					>
						<Input />
					</Form.Item>
					<Form.Item
						name="categoryType"
						label="分类类型"
						rules={[{ required: true, message: '分类类型不能为空' }]}
					>
						<Select placeholder="请选择分类类型" allowClear>
							<Select.Option value={0}>语种</Select.Option>
							<Select.Option value={1}>风格</Select.Option>
							<Select.Option value={2}>场景</Select.Option>
							<Select.Option value={3}>情感</Select.Option>
							<Select.Option value={4}>主题</Select.Option>
						</Select>
					</Form.Item>
				</Form>
			</Modal>
		</div>
	);
};

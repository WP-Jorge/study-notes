import { ResponseType } from '@/globals/responseType';
import { registerApi } from '@/networks/system';
import { PlusOutlined } from '@ant-design/icons';
import { Button, Form, Input, message, Select, Upload } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import { UploadFile } from 'antd/lib/upload/interface';
import React, { useState } from 'react';
import './index.scss';

export interface RegisterFormProps {
	onLoginTypeChange: () => void;
}

export const RegisterForm = (props: RegisterFormProps) => {
	const { onLoginTypeChange } = props;
	const [form] = useForm();
	const [fileList, setFileList] = useState([] as UploadFile[]);
	const layout = {
		labelCol: { span: 6 },
		wrapperCol: { span: 18 }
	};
	const onFinish = async (values: any) => {
		console.log(values);
		if (values.password !== values.confirmPassword) {
			return message.warning('两次输入的密码不一致');
		}
		const data = new FormData();
		if (values.fileList?.length) {
			if (values.fileList[0].originFileObj) {
				data.append('picture', values.fileList[0].originFileObj);
			}
		}
		for (const key in values) {
			if (Object.prototype.hasOwnProperty.call(values, key)) {
				const value = values[key];
				if (value) {
					data.append(key, value);
				}
			}
		}
		let res = await registerApi(data);
		console.log(res);
		message[res.data.type](res.data.msg);
		if (res.data.type === ResponseType.SUCCESS) {
			onLoginTypeChange();
		}
	};

	const normFile = (e: any) => {
		if (Array.isArray(e)) {
			return e;
		}
		return e && e.fileList;
	};

	const onImgChange = (e: any) => {
		setFileList(e.fileList);
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

	return (
		<div className="register-form-conatiner">
			<h1 className="title">注册</h1>
			<Form {...layout} form={form} name="control-hooks" onFinish={onFinish}>
				<Form.Item
					name="fileList"
					label="头像"
					valuePropName="fileList"
					getValueFromEvent={normFile}
					rules={[{ required: true, message: '请上传图片' }]}
				>
					<Upload
						listType="picture-card"
						name="头像"
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
					name="username"
					label="用户名"
					rules={[{ required: true, message: '请填写用户名' }]}
				>
					<Input />
				</Form.Item>
				<Form.Item
					name="password"
					label="密码"
					rules={[{ required: true, message: '请填写密码' }]}
				>
					<Input type="password" />
				</Form.Item>
				<Form.Item
					name="confirmPassword"
					label="确认密码"
					rules={[{ required: true, message: '请填写确认密码' }]}
				>
					<Input type="password" />
				</Form.Item>
				<Form.Item name="sex" label="性别">
					<Select placeholder="请选择用户性别" allowClear>
						<Select.Option value="男">男</Select.Option>
						<Select.Option value="女">女</Select.Option>
						<Select.Option value="保密">保密</Select.Option>
					</Select>
				</Form.Item>
				<Form.Item name="age" label="年龄">
					<Input type="number" />
				</Form.Item>
				<Form.Item name="tel" label="电话号码">
					<Input type="tel" />
				</Form.Item>
				<Form.Item name="email" label="邮箱">
					<Input type="email" />
				</Form.Item>
				<div className="register-btn">
					<Button htmlType="submit" size="large" className="login-form-button">
						注册
					</Button>
				</div>
			</Form>
		</div>
	);
};

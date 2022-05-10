import { ResponseType } from '@/globals/responseType';
import { updateUserApi, User } from '@/networks/user';
import { UploadOutlined } from '@ant-design/icons';
import {
	Button,
	Form,
	Input,
	message,
	Modal,
	Select,
	Switch,
	Upload
} from 'antd';
import { useForm } from 'antd/lib/form/Form';
import React, { useEffect, useState } from 'react';
import './index.scss';

export interface UserFormProps {
	formData?: User;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const UserForm = (props: UserFormProps) => {
	const { formData, clickSubmit, clickCancel } = props;
	const [title] = useState(formData?.userId ? '编辑用户' : '添加用户');
	const [form] = useForm();
	useEffect(() => {
		console.log(formData);

		if (formData?.userPic) {
			formData.fileList = [
				{
					url: formData.userPic.startsWith('data:')
						? formData.userPic
						: import.meta.env.VITE_BASE_URL +
						  (formData.userPic.startsWith('/')
								? formData.userPic.slice(1)
								: formData.userPic)
				}
			];
		}
		form.setFieldsValue({
			...formData
		});
	}, []);

	const layout = {
		labelCol: { span: 4 },
		wrapperCol: { span: 20 }
	};

	const handleOk = () => {
		form.submit();
	};

	const handleCancel = () => {
		clickCancel();
	};

	const onFinish = async (values: any) => {
		console.log(values);
		const data = new FormData();
		if (values.fileList?.length) {
			if (values.fileList[0].originFileObj) {
				data.append('file', values.fileList[0].originFileObj);
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
		if (formData?.userId) {
			data.append('userId', formData.userId + '');
			// console.log(formData.userPic);
			// data.append('userPic', formData.userPic);
		}
		let res = await updateUserApi(data);
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
	const normSwitch = (e: any) => {
		console.log('Upload event:', e);
		if (e === true) {
			return 1;
		}
		return 0;
	};
	const beforeUpload = () => {
		return false;
	};

	return (
		<>
			<Modal
				getContainer={false}
				title={title}
				visible={true}
				onOk={handleOk}
				onCancel={handleCancel}
			>
				<Form {...layout} form={form} name="control-hooks" onFinish={onFinish}>
					<Form.Item
						name="username"
						label="用户名"
						rules={[{ required: true, message: '请填写用户名' }]}
					>
						<Input />
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
					<Form.Item
						name="fileList"
						label="头像"
						valuePropName="fileList"
						getValueFromEvent={normFile}
					>
						<Upload
							name="头像"
							listType="picture"
							beforeUpload={() => beforeUpload()}
							maxCount={1}
						>
							<Button icon={<UploadOutlined />}>点击上传头像</Button>
						</Upload>
					</Form.Item>
					<Form.Item
						name="status"
						label="状态"
						valuePropName="checked"
						getValueFromEvent={normSwitch}
						rules={[{ required: true, message: '请选择用户状态' }]}
					>
						<Switch checkedChildren="正常" unCheckedChildren="冻结" />
					</Form.Item>
				</Form>
			</Modal>
		</>
	);
};

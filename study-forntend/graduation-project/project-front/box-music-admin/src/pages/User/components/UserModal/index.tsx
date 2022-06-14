import { ResponseType } from '@/globals/responseType';
import { addUserApi, updateUserApi, User } from '@/networks/user';
import { PlusOutlined } from '@ant-design/icons';
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
import { UploadFile } from 'antd/lib/upload/interface';
import React, { useEffect, useState } from 'react';
import './index.scss';

export interface UserModalProps {
	formData?: User;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const UserModal = (props: UserModalProps) => {
	const { formData, clickSubmit, clickCancel } = props;
	const [title] = useState(formData?.userId ? '编辑用户' : '添加用户');
	const [fileList, setFileList] = useState([] as UploadFile[]);
	const [form] = useForm();
	useEffect(() => {
		console.log(formData);

		if (formData?.userPic) {
			const files = [
				{
					url:
						import.meta.env.VITE_BASE_URL +
						import.meta.env.VITE_USER_PICTURES +
						formData.userPic
				} as unknown as UploadFile
			];
			setFileList(files);
			formData.fileList = files;
		}
		form.setFieldsValue({
			...formData
		});
		if (!formData?.userId) {
			form.setFieldsValue({
				status: 1
			});
		}
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
			console.log(key);

			if (
				Object.prototype.hasOwnProperty.call(values, key) &&
				key !== 'fileList'
			) {
				const value = values[key];
				if (value || value === 0) {
					data.append(key, value);
				}
			}
		}
		let res;
		if (formData?.userId) {
			data.append('userId', formData.userId + '');
			res = await updateUserApi(data);
		} else {
			res = await addUserApi(data);
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
	const onImgChange = (e: any) => {
		setFileList(e.fileList);
	};

	return (
		<div className="user-modal">
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
						label="头像"
						valuePropName="fileList"
						getValueFromEvent={normFile}
						rules={[{ required: !formData?.userId, message: '头像不能为空' }]}
						extra={!!formData?.userId && '如果不上传头像则使用原头像'}
					>
						<Upload
							listType="picture-card"
							name="头像"
							fileList={fileList}
							beforeUpload={() => beforeUpload()}
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
						rules={[{ required: !formData?.userId, message: '用户名不能为空' }]}
					>
						<Input disabled={!!formData?.userId} />
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
						name="status"
						label="状态"
						valuePropName="checked"
						getValueFromEvent={normSwitch}
					>
						<Switch checkedChildren="正常" unCheckedChildren="冻结" />
					</Form.Item>
				</Form>
			</Modal>
		</div>
	);
};

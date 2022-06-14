import { ResponseType } from '@/globals/responseType';
import { addRoleApi, Role, updateRoleApi } from '@/networks/role';
import { Button, Form, Input, message, Modal } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import React, { useEffect, useState } from 'react';
import './index.scss';

export interface RoleModalProps {
	formData?: Role;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const RoleModal = (props: RoleModalProps) => {
	const { formData, clickSubmit, clickCancel } = props;
	const [title] = useState(formData?.roleId ? '编辑角色' : '添加角色');
	const [form] = useForm();
	useEffect(() => {
		console.log(formData);
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
		let res;
		if (formData?.roleId) {
			res = await updateRoleApi({ roleId: formData.roleId, ...values });
		} else {
			res = await addRoleApi(values);
		}
		console.log(res);
		message[res.data.type](res.data.msg);
		if (res.data.type === ResponseType.SUCCESS) {
			clickSubmit();
		}
	};

	return (
		<div className="role-modal">
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
						name="roleName"
						label="角色名"
						rules={[{ required: true, message: '请填写角色名' }]}
					>
						<Input />
					</Form.Item>
				</Form>
			</Modal>
		</div>
	);
};

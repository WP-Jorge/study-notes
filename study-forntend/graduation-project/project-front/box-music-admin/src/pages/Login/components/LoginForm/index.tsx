import { ResponseType } from '@/globals/responseType';
import { getVerificationCodeApi, loginApi } from '@/networks/system';
import { login } from '@/redux/reducers/systemSlice';
import { AppDispatch } from '@/redux/store';
import {
	LockOutlined,
	UserOutlined,
	VerifiedOutlined
} from '@ant-design/icons';
import { Button, Checkbox, Form, Image, Input, message } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import './index.scss';

export const LoginForm = () => {
	const dispatch = useDispatch<AppDispatch>();
	const navigate = useNavigate();
	const [form] = useForm();
	const [imgUrl, setImgUrl] = useState('');

	useEffect(() => {
		(async () => {
			let res = await getVerificationCodeApi('111111');
			console.log(res);
			setImgUrl(res.data.VerificationCodeBase64);
		})();
	}, []);

	const onFinish = async (values: any) => {
		console.log('Received values of form: ', values);
		let res = await loginApi({ ...values, codeId: '111111' });
		console.log(res);
		message[res.data.type](res.data.msg);
		if (res.data.type === ResponseType.SUCCESS) {
			dispatch(
				login({
					userInfo: res.data.userInfo,
					token: res.data.token,
					roles: res.data.roles,
					apis: res.data.apis
				})
			);
			navigate('/home');
		} else {
			changeVerificationCodeImg();
			form.setFieldsValue({
				verificationCode: ''
			});
		}
	};
	async function changeVerificationCodeImg() {
		let res = await getVerificationCodeApi('111111');
		console.log(res);
		setImgUrl(res.data.VerificationCodeBase64);
	}
	return (
		<div className="login-form-container">
			<h1 className="title white">登录</h1>
			<Form
				name="normal_login"
				className="login-form"
				initialValues={{ remember: true }}
				onFinish={onFinish}
				form={form}
			>
				<Form.Item
					name="username"
					rules={[{ required: true, message: '请输入用户名！' }]}
				>
					<Input
						size="large"
						prefix={<UserOutlined className="site-form-item-icon" />}
						placeholder="用户名"
					/>
				</Form.Item>
				<Form.Item
					name="password"
					rules={[{ required: true, message: '请输入密码！' }]}
				>
					<Input
						size="large"
						prefix={<LockOutlined className="site-form-item-icon" />}
						type="password"
						placeholder="密码"
					/>
				</Form.Item>
				<Form.Item
					name="verificationCode"
					rules={[{ required: true, message: '请输入验证码！' }]}
				>
					<div className="verification-code">
						<Input
							size="large"
							prefix={<VerifiedOutlined className="site-form-item-icon" />}
							placeholder="验证码"
						/>
						<Image
							preview={false}
							height={32}
							src={imgUrl}
							alt="验证码"
							onClick={() => changeVerificationCodeImg()}
							onKeyDown={() => changeVerificationCodeImg()}
						/>
					</div>
				</Form.Item>
				<Form.Item>
					<Form.Item name="remember" valuePropName="checked" noStyle>
						<Checkbox className="remember">记住我</Checkbox>
					</Form.Item>
				</Form.Item>

				<Form.Item></Form.Item>
				<div className="login-btn">
					<Button htmlType="submit" size="large" className="login-form-button">
						登录
					</Button>
				</div>
			</Form>
		</div>
	);
};

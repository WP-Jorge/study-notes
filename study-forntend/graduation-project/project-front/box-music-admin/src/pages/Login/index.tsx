import { Button, Image } from 'antd';
import React, { useState } from 'react';
import { LoginForm } from './components/LoginForm';
import { RegisterForm } from './components/RegisterForm';

import './index.scss';

export const Login = () => {
	const [loginType, setLoginType] = useState('login');
	return (
		<div className="login">
			<div className="login-register-container">
				<div
					className={`over-container ${
						loginType === 'register' ? 'over-container-register' : ''
					}`}
				>
					{loginType === 'login' ? (
						<LoginForm />
					) : (
						<RegisterForm onLoginTypeChange={() => setLoginType('login')} />
					)}
				</div>
				<div className="login-container">
					<h2 className="title">Box Music</h2>
					<h2 className="title">聆听世界的声音</h2>
					<div className="image">
						<Image
							height={135}
							preview={false}
							src="/src/assets/logo.png"
						></Image>
					</div>
					<p className="tip">已经有账号了？</p>
					<div className="button">
						<Button onClick={() => setLoginType('login')}>登录</Button>
					</div>
				</div>
				<div className="register-container">
					<h2 className="title">Box Music</h2>
					<h2 className="title">聆听世界的声音</h2>
					<div className="image">
						<Image
							height={135}
							preview={false}
							src="/src/assets/logo2.png"
						></Image>
					</div>
					<p className="tip">还没有账号？</p>
					<div className="button">
						<Button onClick={() => setLoginType('register')}>注册</Button>
					</div>
				</div>
			</div>
		</div>
	);
};

import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { AppDispatch } from '@/redux/store';
import { login, logout } from '@/redux/reducers/systemSlice';
import { getVerifyCodeApi, loginApi, logoutApi } from '@/networks/system';
import { Button, message } from 'antd';
import { ResponseType } from '@/globals/responseType';
import useStore from '@/hooks/useStore';

export default function Login() {
	const dispatch = useDispatch<AppDispatch>();
	const systemStore = useStore().system;
	const { userInfo, token, roles, apis } = systemStore;
	const [imgUrl, setImgUrl] = useState('');
	const [username, setUsername] = useState('');
	const [password, setPassword] = useState('');
	const [verifycationCode, setVerifycationCode] = useState('');
	useEffect(() => {
		(async () => {
			let res = await getVerifyCodeApi('111111');
			console.log(res);
			setImgUrl(res.data.verifyCodeBase64);
		})();
	}, []);
	function usernameChange(e: { target: { value: any } }) {
		setUsername(e.target.value);
	}
	function passwordChange(e: { target: { value: any } }) {
		setPassword(e.target.value);
	}
	function verificationCodeChange(e: { target: { value: any } }) {
		setVerifycationCode(e.target.value);
	}
	async function changeVerificationCodeImg() {
		let res = await getVerifyCodeApi('111111');
		console.log(res);
		setImgUrl(res.data.verifyCodeBase64);
	}
	async function loginHandle() {
		let res = await loginApi({
			username,
			password,
			verifycationCode,
			codeId: '111111'
		});
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
		} else {
			changeVerificationCodeImg();
			setVerifycationCode('');
		}
	}
	async function logoutHandle() {
		let res = await logoutApi();
		console.log(res);
		message[res.data.type](res.data.msg);
		dispatch(logout());
	}
	function enterHandle(e: { keyCode: number }) {
		if (e.keyCode === 13) {
			loginHandle();
		}
	}
	return (
		<>
			<div>Login 页面</div>
			登录
			<br />
			<input
				type="text"
				value={username}
				onChange={e => usernameChange(e)}
				placeholder="请输入用户名"
			/>
			<br />
			<input
				type="password"
				value={password}
				onChange={e => passwordChange(e)}
				placeholder="请输入密码"
			/>
			<br />
			<input
				type="text"
				value={verifycationCode}
				onChange={e => verificationCodeChange(e)}
				placeholder="请输入验证码"
				onKeyUp={e => enterHandle(e)}
			/>
			<img
				src={imgUrl}
				alt="验证码"
				onClick={() => changeVerificationCodeImg()}
				onKeyDown={() => changeVerificationCodeImg()}
			/>
			<br />
			<Button onClick={() => loginHandle()}>登录</Button>
			<Button onClick={() => logoutHandle()}>退出登录</Button>
			<br />
			<hr />
			用户信息
			<br />
			用户信息：{userInfo?.username}
			<br />
			token：{token}
			<br />
			roles：
			{roles?.map(item => (
				<div key={item.roleId}>{item.roleName}</div>
			))}
			<br />
			apis：
			{apis?.map(item => (
				<div key={item.apiId}>{item.apiName}</div>
			))}
			<br />
		</>
	);
}

import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Image, message, Popover } from 'antd';

import './index.scss';
import useStore from '@/hooks/useStore';
import { logoutApi } from '@/networks/system';
import { logout } from '@/redux/reducers/systemSlice';
import { useDispatch } from 'react-redux';
import { AppDispatch } from '@/redux/store';

export default function Header() {
	const dispatch = useDispatch<AppDispatch>();
	const systemSore = useStore().system;
	const navigate = useNavigate();
	const { userInfo } = systemSore;
	const [userImg, setUserImg] = useState('');
	useEffect(() => {
		setUserImg(
			userInfo.userPic
				? import.meta.env.VITE_BASE_URL +
						import.meta.env.VITE_USER_PICTURES +
						userInfo.userPic
				: ''
		);
	}, [userInfo]);
	async function logoutHandle() {
		let res = await logoutApi();
		console.log(res);
		message[res.data.type](res.data.msg);
		dispatch(logout());
	}
	const getHoverContent = () => {
		return systemSore.token ? (
			<div>
				<p>电话号码：{systemSore.userInfo.tel}</p>
				<p>邮箱：{systemSore.userInfo.email}</p>
				<p>性别：{systemSore.userInfo.sex}</p>
				<a className="dangerous" href="#!" onClick={() => logoutHandle()}>
					退出登录
				</a>
			</div>
		) : (
			<Link to="/login">请登录</Link>
		);
	};
	return (
		<header className="header">
			<div className="container">
				<Link className="logo" to="/">
					<img src="/src/assets/logo.png" alt="logo" />
					<span>盒子音乐</span>
				</Link>
				<div className="info">
					<div className="userInfo">
						<Popover
							content={getHoverContent()}
							title={systemSore.userInfo.username}
							trigger="hover"
						>
							<Image
								width={40}
								height={40}
								preview={false}
								src={userImg}
								fallback="/src/assets/userImage.jfif"
								onClick={() => navigate('/personalCenter')}
							/>
						</Popover>
					</div>
				</div>
			</div>
		</header>
	);
}

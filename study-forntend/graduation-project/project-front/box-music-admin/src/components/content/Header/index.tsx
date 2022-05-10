import React from 'react';
import { Link } from 'react-router-dom';
import { Image, Popover } from 'antd';

import './index.scss';
import useStore from '@/hooks/useStore';

export default function Header() {
	const systemtore = useStore().system;
	const getHoverContent = () => {
		return systemtore.token ? (
			<div>
				<p>电话号码：{systemtore.userInfo.tel}</p>
				<p>邮箱：{systemtore.userInfo.email}</p>
				<p>性别：{systemtore.userInfo.sex}</p>
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
					<div className="message">消息</div>
					<div className="userInfo">
						<Popover
							content={getHoverContent()}
							title={systemtore.userInfo.username}
							trigger="hover"
						>
							<Image
								width={40}
								height={40}
								preview={false}
								src={systemtore.userInfo.userPic ?? ''}
								fallback="/src/assets/logo.png"
							/>
						</Popover>
					</div>
				</div>
			</div>
		</header>
	);
}

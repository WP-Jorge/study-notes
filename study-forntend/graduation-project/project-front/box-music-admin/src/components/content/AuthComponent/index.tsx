import React, { useEffect } from 'react';
import { RootState } from '@/redux/store';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { message } from 'antd';

export default function AuthComponent(props: any) {
	const { children } = props;
	const systemtore = useSelector((store: RootState) => store.system);
	const navigate = useNavigate();
	useEffect(() => {
		if (!systemtore.token) {
			message.warning('权限不足，请登录！');
			navigate('/login');
		}
	}, []);

	return <>{systemtore.token ? children : ''}</>;
}

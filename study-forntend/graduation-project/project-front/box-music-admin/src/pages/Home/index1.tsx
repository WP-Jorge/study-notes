import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { setHomeInfo, setHomeInfoAsync } from '@/redux/reducers/homeSlice';
import { AppDispatch, RootState } from '@/redux/store';
import { Button } from 'antd';

export default function Home() {
	const homeStore = useSelector((state: RootState) => state.home);
	const dispatch = useDispatch<AppDispatch>();
	const { name, address } = homeStore.homeInfo;
	const changeHomeInfo = () => {
		dispatch(
			setHomeInfo({
				name: '老王家',
				address: '隔壁'
			})
		);
	};
	const changeHomeInfoAsync = () => {
		dispatch(
			setHomeInfoAsync({
				name: '老王家族',
				address: '隔壁村'
			})
		);
	};
	return (
		<>
			<div>Home 页面</div>
			页面信息
			<br />
			name：{name}
			<br />
			address：{address}
			<br />
			<Button type="primary" onClick={() => changeHomeInfo()}>
				点击我修改Home信息
			</Button>
			<br />
			<Button type="primary" danger onClick={() => changeHomeInfoAsync()}>
				点击我异步修改Home信息
			</Button>
		</>
	);
}

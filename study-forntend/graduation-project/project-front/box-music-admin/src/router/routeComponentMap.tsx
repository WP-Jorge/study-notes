import WithProgress from '@/components/content/WithProgress';
import React, { lazy, ReactNode, Suspense } from 'react';

import LazyLoading from '@/components/common/LazyLoading';
import { UserOutlined } from '@ant-design/icons';
import PersonalCenter from '@/pages/PersonalCenter';
import AuthComponent from '@/components/content/AuthComponent';
import { Navigate } from 'react-router-dom';
const Home = lazy(() => import('@/pages/Home'));
const User = lazy(() => import('@/pages/User'));
const Music = lazy(() => import('@/pages/music/Music'));
const Singer = lazy(() => import('@/pages/music/Singer'));
const Category = lazy(() => import('@/pages/music/Category'));
const Playlist = lazy(() => import('@/pages/music/Playlist'));
const Role = lazy(() => import('@/pages/auth/Role'));
const UserRole = lazy(() => import('@/pages/auth/UserRole'));
const Api = lazy(() => import('@/pages/auth/Api'));
const NotFound = lazy(() => import('@/pages/NotFound'));

const lazyLoad = (children: ReactNode): ReactNode => (
	<Suspense fallback={<LazyLoading />}>
		<WithProgress>{children}</WithProgress>
	</Suspense>
);

export const defaultRoutes = [
	{
		path: '/',
		element: <Navigate to="/home" />
	},
	// {
	// 	path: '/home',
	// 	name: '主页',
	// 	element: lazyLoad(<Home />),
	// 	icon: <DesktopOutlined />,
	// 	isPage: true
	// },
	{
		path: '/personalCenter',
		name: '个人中心',
		element: lazyLoad(
			<AuthComponent>
				<PersonalCenter />
			</AuthComponent>
		),
		icon: <UserOutlined />
	}
];

export const notFoundRoute = {
	path: '*',
	name: '未知页面',
	element: lazyLoad(<NotFound />)
};

export const routeComponentMap = {
	'/home': lazyLoad(<Home />),
	'/auth/api': lazyLoad(<Api />),
	'/auth/role': lazyLoad(<Role />),
	'/auth/userRole': lazyLoad(<UserRole />),
	'/user': lazyLoad(<User />),
	'/music/music': lazyLoad(<Music />),
	'/music/singer': lazyLoad(<Singer />),
	'/music/category': lazyLoad(<Category />),
	'/music/playlist': lazyLoad(<Playlist />)
};

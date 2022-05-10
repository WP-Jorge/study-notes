import WithProgress from '@/components/content/WithProgress';
import React, { lazy, ReactNode, Suspense } from 'react';

import LazyLoading from '@/components/common/LazyLoading';
import {
	DesktopOutlined,
	LoginOutlined,
	UserOutlined
} from '@ant-design/icons';
import PersonalCenter from '@/pages/PersonalCenter';
import AuthComponent from '@/components/content/AuthComponent';
import { Navigate } from 'react-router-dom';
const Home = lazy(() => import('@/pages/Home'));
const Login = lazy(() => import('@/pages/Login'));
const User = lazy(() => import('@/pages/User'));
const Music = lazy(() => import('@/pages/Music'));
const Role = lazy(() => import('@/pages/auth/Role'));
const UserRole = lazy(() => import('@/pages/auth/UserRole'));
const ApiComponent = lazy(() => import('@/pages/auth/Api'));
const RoleApi = lazy(() => import('@/pages/auth/RoleApi'));
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
	{
		path: '/home',
		name: '主页',
		element: lazyLoad(<Home />),
		icon: <DesktopOutlined />,
		isPage: true
	},
	{
		path: '/login',
		name: '登录',
		element: lazyLoad(<Login />),
		icon: <LoginOutlined />
	},
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
	'/auth/api': lazyLoad(<ApiComponent />),
	'/auth/role': lazyLoad(<Role />),
	'/auth/userRole': lazyLoad(<UserRole />),
	'/auth/roleApi': lazyLoad(<RoleApi />),
	'/music': lazyLoad(<Music />),
	'/user': lazyLoad(<User />)
};

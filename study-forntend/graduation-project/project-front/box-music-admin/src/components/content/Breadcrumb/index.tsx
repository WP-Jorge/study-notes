import React, { ReactNode, useEffect, useState } from 'react';
import { useLocation, Link } from 'react-router-dom';
import { Breadcrumb as AntdBreadcrumb } from 'antd';
import { getRoutes, Route } from '@/router';

import './index.scss';
import useStore from '@/hooks/useStore';

export default function Breadcrumb() {
	const systemtore = useStore().system;
	const location = useLocation();
	const [breadcrumbNameMap, setBreadcrumbNameMap] = useState({});
	const [pathSnippets, setPathSnippets] = useState([] as Array<string>);
	const [extraBreadcrumbItems, setExtraBreadcrumbItems] = useState(
		[] as Array<ReactNode>
	);
	useEffect(() => {
		setPathSnippets(location.pathname.split('/').filter(i => i));
	}, [location.pathname]);
	useEffect(() => {
		setExtraBreadcrumbItems(
			pathSnippets.map((item, index) => {
				const url = `/${pathSnippets.slice(0, index + 1).join('/')}`;
				return (
					<AntdBreadcrumb.Item key={url}>
						{breadcrumbNameMap[url] ? (
							<Link to={url}>{breadcrumbNameMap[url]}</Link>
						) : (
							<Link to={url}>{breadcrumbNameMap['*']}</Link>
						)}
					</AntdBreadcrumb.Item>
				);
			})
		);
	}, [pathSnippets]);
	useEffect(() => {
		setBreadcrumbNameMap(getFullRouterPathMap(getRoutes()));
	}, [systemtore.apis]);
	function getFullRouterPathMap(routes: Array<Route>) {
		let res = {};
		function dfs(route: Route, path: Array<string>) {
			if (!route.children?.length) {
				let pathStr = path.join('/');
				res[pathStr] = route.name;
				return;
			}
			for (const item of route.children) {
				let pathStr = path.join('/');
				res[pathStr] = route.name;
				if (item.path.startsWith('/')) {
					continue;
				}
				dfs(item, [...path, item.path]);
			}
		}
		for (const route of routes) {
			if (route.path === '/') {
				continue;
			}
			dfs(route, [route.path]);
		}
		return res;
	}

	const breadcrumbItems = () => {
		if (location.pathname === '/home') {
			return [
				<AntdBreadcrumb.Item key="home">
					<Link to="/">首页</Link>
				</AntdBreadcrumb.Item>
			];
		}

		return [
			<AntdBreadcrumb.Item key="home">
				<Link to="/">首页</Link>
			</AntdBreadcrumb.Item>,
			...extraBreadcrumbItems
		];
	};
	return (
		<div>
			<AntdBreadcrumb>{breadcrumbItems()}</AntdBreadcrumb>
		</div>
	);
}

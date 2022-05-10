import React, { useEffect, useState } from 'react';
import { Layout, Menu, MenuProps } from 'antd';

import './index.scss';
import { useLocation, useNavigate } from 'react-router-dom';
import { getRoutes, Route } from '@/router';
import { ItemType } from 'antd/lib/menu/hooks/useItems';
import useStore from '@/hooks/useStore';

export default function Sider() {
	const navigate = useNavigate();
	const location = useLocation();
	const systemtore = useStore().system;
	const [collapsed, setCollapsed] = useState(false);
	const [sideRouterMapTree, setSideRouterMapTree] = useState(
		[] as Array<ItemType>
	);
	const [selectKeys, setSelectKeys] = useState([] as Array<string>);
	const [openKeys, setOpenKeysKeys] = useState([] as Array<string>);
	useEffect(() => {
		setSelectKeys(getSelectKeys());
		setOpenKeysKeys(getOpenKeys());
		setSideRouterMapTree(getSideRouteMapTree(getRoutes()));
	}, [location.pathname, systemtore.apis]);
	function getSideRouteMapTree(routes: Array<Route>) {
		function dfs(route: Route, path: Array<string>) {
			const menuItem = { key: '' };
			if (!route.name) {
				return menuItem;
			}
			let pathStr = path.join('/');
			menuItem['label'] = route.name;
			menuItem['key'] = pathStr;
			menuItem['icon'] = route.icon;
			if (route.icon) {
				menuItem['icon'] = route.icon;
			}
			if (!route.children?.length) {
				return menuItem;
			}
			for (const childRoute of route.children) {
				if (!childRoute.name || childRoute.isOption) {
					continue;
				}
				menuItem['children']
					? menuItem['children'].push(
							dfs(childRoute, [...path, childRoute.path])
					  )
					: (menuItem['children'] = [
							dfs(childRoute, [...path, childRoute.path])
					  ]);
			}

			return menuItem;
		}
		let menuItems: MenuProps['items'] = [];
		for (const route of routes) {
			if (
				route.path === '/' ||
				!route.name ||
				route.path === '*' ||
				route.isOption
			) {
				continue;
			}
			menuItems.push(dfs(route, [route.path]));
		}
		return menuItems;
	}
	function getSelectKeys() {
		const pathArr = location.pathname.split('/').filter(item => item);
		return [location.pathname, ...pathArr.map(item => '/' + item)];
	}
	function getOpenKeys() {
		const pathArr = location.pathname.split('/').filter(item => item);
		return pathArr.map(item => '/' + item);
	}
	const onCollapse = (collapsed: boolean) => {
		setCollapsed(collapsed);
	};

	const onMenuClick: MenuProps['onClick'] = item => {
		navigate(item.key);
	};

	const onOpenChange: MenuProps['onOpenChange'] = item => {
		setOpenKeysKeys([item[item.length - 1]]);
	};

	return (
		<Layout.Sider
			className="sider"
			theme="light"
			collapsible
			collapsed={collapsed}
			onCollapse={onCollapse}
		>
			<Menu
				onClick={item => onMenuClick(item)}
				selectedKeys={selectKeys}
				openKeys={openKeys}
				onOpenChange={item => onOpenChange(item)}
				mode="inline"
				items={sideRouterMapTree}
				triggerSubMenuAction="hover"
			/>
		</Layout.Sider>
	);
}

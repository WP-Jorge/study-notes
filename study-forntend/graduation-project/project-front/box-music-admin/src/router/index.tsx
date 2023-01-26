import React, { ReactNode } from 'react';
import { Navigate, RouteObject, useRoutes } from 'react-router-dom';

import { store } from '@/redux/store';
import { Api } from '@/networks/system';
import {
	routeComponentMap,
	notFoundRoute,
	defaultRoutes
} from '@/router/routeComponentMap';
import { routeIconMap } from '@/router/routeIconMap';

export function getRoutes(): Array<Route> {
	return [
		...getRoutesFromApiTrees(getApiTrees()),
		...defaultRoutes,
		notFoundRoute
	];
}

export function getApiTrees(): Array<Api> {
	const apis = JSON.parse(JSON.stringify(store.getState().system.apis));

	const res = [] as Array<Api>;
	for (let parent of apis) {
		if (!parent.parentApiId) {
			if (parent.apiPath === '/home') {
				res.unshift(parent);
			} else {
				res.push(parent);
			}
		}
		for (let child of apis) {
			if (child.parentApiId === parent.apiId && child.apiType !== 1) {
				parent.children
					? parent.children.push(child)
					: (parent.children = [child]);
			}
		}
	}
	return res;
}

export function getRoutesFromApiTrees(apiTrees: Array<Api>, parentApi = '') {
	let res = [];
	for (const item of apiTrees) {
		let obj = {} as any;
		if (parentApi.startsWith('/')) {
			parentApi = parentApi.slice(1);
		}
		// 添加重定向路由
		if (item.children?.length && !item.children[0].apiType) {
			let redirectObj = {} as any;
			const redirectPath = item.children[0].apiPath as string;
			redirectObj.path = item.apiPath;
			redirectObj.element = <Navigate to={redirectPath} />;
			res.push(redirectObj);
		}
		let parentApiPath = parentApi ? '/' + parentApi + '/' : '';
		obj.path = item.apiPath?.replace(parentApiPath, '');
		obj.name = item.apiName;
		obj.isOption = item.apiType;
		if (routeIconMap[item.apiPath as string]) {
			obj.icon = routeIconMap[item.apiPath as string];
		}
		if (routeComponentMap[item.apiPath as string]) {
			obj.element = routeComponentMap[item.apiPath as string];
		}
		if (item.children?.length) {
			obj.children = getRoutesFromApiTrees(
				item.children as Array<Api>,
				item.apiPath as string
			);
		}
		res.push(obj);
	}
	return res;
}

export function renderRoutes() {
	return useRoutes(getRoutes());
}

export interface Route extends RouteObject {
	path: string;
	name?: string;
	children?: Array<Route>;
	icon?: ReactNode;
	isOption?: Boolean | number;
	isRedirect?: Boolean;
}

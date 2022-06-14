import React from 'react';
import {
	CustomerServiceOutlined,
	DesktopOutlined,
	GoldOutlined,
	TeamOutlined
} from '@ant-design/icons';

export const routeIconMap = {
	'/home': <DesktopOutlined />,
	'/auth': <GoldOutlined />,
	'/user': <TeamOutlined />,
	'/music': <CustomerServiceOutlined />
};

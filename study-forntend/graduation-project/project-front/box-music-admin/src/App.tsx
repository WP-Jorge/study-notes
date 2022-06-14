import React from 'react';
import { Layout } from 'antd';
import { useLocation, useRoutes } from 'react-router-dom';

import './App.scss';
import Header from '@/components/content/Header';
import Sider from '@/components/content/Sider';
import Footer from '@/components/content/Footer';
import Breadcrumb from '@/components/content/Breadcrumb';
import { getRoutes } from '@/router';
import useStore from './hooks/useStore';
import { Login } from './pages/Login';

function App() {
	const renderRoutes = useRoutes(getRoutes());
	const systemStore = useStore().system;
	const location = useLocation();
	const { pathname } = location;

	return (
		<div className="app">
			{pathname == '/login' || !systemStore.token ? (
				<Login />
			) : (
				<>
					<Header />
					<Layout>
						<Sider />
						<Layout>
							<Layout.Content>
								<Breadcrumb />
								<div className="site-layout-background">{renderRoutes}</div>
							</Layout.Content>
							<Footer />
						</Layout>
					</Layout>
				</>
			)}
		</div>
	);
}
export default App;

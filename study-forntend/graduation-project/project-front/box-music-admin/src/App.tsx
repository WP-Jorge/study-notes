import React from 'react';
import { Layout } from 'antd';
import { useRoutes } from 'react-router-dom';

import './App.scss';
import Header from '@/components/content/Header';
import Sider from '@/components/content/Sider';
import Footer from '@/components/content/Footer';
import Breadcrumb from '@/components/content/Breadcrumb';
import { getRoutes } from '@/router';

function App() {
	const renderRoutes = useRoutes(getRoutes());
	return (
		<div className="app">
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
		</div>
	);
}
export default App;

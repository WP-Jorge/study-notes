import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
// import 'antd/dist/antd.css';
import 'nprogress/nprogress.css';

import App from '@/App';
import { store } from '@/redux/store';
import { interceptor } from '@/networks/index';
import '@/assets/index.scss';

interceptor(store);
const root = document.getElementById('root');
if (!root) throw new Error('Failed to find the root element');
ReactDOM.createRoot(root).render(
	<Provider store={store}>
		<BrowserRouter>
			<App />
		</BrowserRouter>
	</Provider>
);

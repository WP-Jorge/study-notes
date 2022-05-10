import React from 'react';

import './index.scss';
import { UserTable } from './components/UserTable';

export default function User() {
	return (
		<div className="user">
			<UserTable></UserTable>
		</div>
	);
}

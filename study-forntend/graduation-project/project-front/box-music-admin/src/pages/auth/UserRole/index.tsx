import React from 'react';

import './index.scss';
import { UserTable } from './components/UserRoleTable';

export default function User() {
	return (
		<div className="user-role">
			<UserTable></UserTable>
		</div>
	);
}

import React from 'react';
import './index.scss';
import { ApiTree } from '@/components/content/ApiTree';

export default function Api() {
	return (
		<div className="api">
			<ApiTree
				editable
				draggable
				addTopable
				expandAllKey
				onChecked={(selectedKeys, halfCheckedKeys) =>
					console.log(selectedKeys, halfCheckedKeys)
				}
			/>
		</div>
	);
}

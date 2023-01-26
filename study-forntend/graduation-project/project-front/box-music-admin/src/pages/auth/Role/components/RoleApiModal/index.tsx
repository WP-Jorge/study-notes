import { ApiTree } from '@/components/content/ApiTree';
import { ResponseType } from '@/globals/responseType';
import { Api } from '@/networks/api';
import { Role } from '@/networks/role';
import { getApisByRoleIdApi } from '@/networks/roleApi';
import { Button, Modal } from 'antd';
import { Key } from 'antd/es/table/interface';
import React, { useEffect, useState } from 'react';
import './index.scss';

export interface RoleApiModalProps {
	roleData: Role;
	clickCancel: () => void;
	clickSubmit: (allCheckedKeys: Key[]) => void;
}

export const RoleApiModal = (props: RoleApiModalProps) => {
	const { roleData, clickSubmit, clickCancel } = props;
	const [roleApiData, setRoleApiData] = useState([] as Api[]);
	const [allCheckedKeys, setAllCheckedKeys] = useState([] as Key[]);
	useEffect(() => {
		(async () => {
			let res = await getApisByRoleIdApi(roleData.roleId);
			console.log(res);

			if (res.data.type === ResponseType.SUCCESS) {
				setRoleApiData(res.data.apis);
			}
		})();
	}, []);

	const onSubmit = () => {
		clickSubmit(allCheckedKeys);
	};

	const onChecked = (checkedKeys: Key[], halfCheckedKeys: Key[]) => {
		console.log(checkedKeys, halfCheckedKeys);
		setAllCheckedKeys([...checkedKeys, ...halfCheckedKeys]);
	};

	return (
		<div className="role-api-modal">
			<Modal
				getContainer={false}
				title="编辑角色权限"
				open={true}
				onCancel={clickCancel}
				footer={[
					<Button key="back" onClick={clickCancel}>
						取消
					</Button>,
					<Button key="submit" type="primary" onClick={onSubmit}>
						确定
					</Button>
				]}
			>
				<div className="content">
					<div className="content-item">
						<span className="label">角色名称：</span>
						{roleData?.roleName}
					</div>
					<div className="content-item">
						<ApiTree
							checkable
							expandAllKey
							maxHeight="350px"
							checkedTreeData={roleApiData}
							onChecked={onChecked}
						/>
					</div>
				</div>
			</Modal>
		</div>
	);
};

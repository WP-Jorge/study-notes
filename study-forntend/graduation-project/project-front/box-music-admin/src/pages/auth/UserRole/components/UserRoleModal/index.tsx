import React, { useEffect, useState } from 'react';
import { Button, message, Modal, Select } from 'antd';
import './index.scss';
import { getRolesByRoleNamePageApi } from '@/networks/role';
import { Role } from '@/networks/system';
import { ResponseType } from '@/globals/responseType';
import { Key } from 'antd/es/table/interface';
import { updateUserRolesByUserIdApi, UserRole } from '@/networks/userRole';

export interface UserRoleModalProps {
	userRole: UserRole;
	clickCancel: () => void;
	clickSubmit: () => void;
}

export const UserRoleModal = (props: UserRoleModalProps) => {
	const { userRole, clickSubmit, clickCancel } = props;
	const [title] = useState('编辑用户角色');
	const [selectDatas, setSelectDatas] = useState([] as Array<Role>);
	const [selectedDatas, setSelectedKeys] = useState(
		[] as Array<bigint | string | Key>
	);
	useEffect(() => {
		(async () => {
			let res = await getRolesByRoleNamePageApi(1, -1);
			console.log(res);
			if (res.data.type === ResponseType.SUCCESS) {
				setSelectDatas(res.data.pageList);
				setSelectedKeys(userRole.roleList.map((item: Role) => item.roleId));
			}
		})();
	}, []);

	const onFinish = async () => {
		let res = await updateUserRolesByUserIdApi(userRole.userId, selectedDatas);
		console.log(res);
		message[res.data.type](res.data.msg);
		if (res.data.type === ResponseType.SUCCESS) {
			clickSubmit();
		}
	};

	const onChange = (selectedKeys: Array<bigint | string | Key>) => {
		console.log(setSelectedKeys);

		setSelectedKeys(selectedKeys);
	};
	return (
		<div className="user-role-modal">
			<Modal
				getContainer={false}
				title={title}
				open={true}
				onCancel={clickCancel}
				footer={[
					<Button key="back" onClick={clickCancel}>
						取消
					</Button>,
					<Button key="submit" type="primary" onClick={onFinish}>
						确定
					</Button>
				]}
			>
				<Select
					style={{ width: '100%' }}
					mode="multiple"
					showArrow
					allowClear
					fieldNames={{
						label: 'roleName',
						value: 'roleId'
					}}
					value={selectedDatas}
					options={selectDatas}
					onChange={onChange}
				/>
			</Modal>
		</div>
	);
};

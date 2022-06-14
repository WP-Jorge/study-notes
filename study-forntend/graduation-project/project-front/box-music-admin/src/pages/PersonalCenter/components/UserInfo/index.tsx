import { ResponseType } from '@/globals/responseType';
import {
	getUserInfoApi,
	updatePasswordApi,
	updateUserApi
} from '@/networks/system';
import { User } from '@/networks/user';
import { setUserInfo } from '@/redux/reducers/systemSlice';
import { AppDispatch, RootState } from '@/redux/store';
import { EditOutlined } from '@ant-design/icons';
import {
	Avatar,
	Button,
	Col,
	Divider,
	Form,
	Input,
	message,
	Modal,
	Row,
	Select,
	Tag,
	Upload
} from 'antd';
import { useForm } from 'antd/lib/form/Form';
import { UploadFile } from 'antd/lib/upload/interface';
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './index.scss';

export const UserInfo = () => {
	const [form] = useForm();
	const [passwordForm] = useForm();
	const systemStroe = useSelector((state: RootState) => state.system);
	const dispatch = useDispatch<AppDispatch>();
	const { userInfo, roles } = systemStroe;
	const { userPic } = userInfo;
	const [fileList, setFileList] = useState([] as UploadFile[]);
	const [avatar, setAvatar] = useState('');
	const [isEdit, setIsEdit] = useState(false);
	const [isEditPassword, setIsEditPassword] = useState(false);
	const colors = [
		'magenta',
		'volcano',
		'gold',
		'green',
		'blue',
		'geekblue',
		'red',
		'orange',
		'lime',
		'cyan'
	];
	useEffect(() => {
		let imgUrl = userPic
			? import.meta.env.VITE_BASE_URL +
			  import.meta.env.VITE_USER_PICTURES +
			  userPic
			: '/src/assets/userImage.jfif';
		setAvatar(imgUrl);
		console.log(imgUrl);

		setFileList([
			{
				url: imgUrl
			} as unknown as UploadFile
		]);
		form.setFieldsValue({ ...userInfo });
		console.log(userInfo);
	}, [userInfo]);

	const onImgChange = (e: any) => {
		setFileList(e.fileList);
	};

	const onFinish = async (values: User) => {
		console.log(values);
		console.log(fileList);
		fileList.length && console.log(fileList[0].url);
		fileList.length && console.log(fileList[0].originFileObj);

		const formData = new FormData();
		fileList.length &&
			fileList[0].originFileObj &&
			formData.append('picture', fileList[0].originFileObj);
		for (const key in values) {
			if (Object.prototype.hasOwnProperty.call(values, key)) {
				const item = values[key];
				if (item) {
					console.log(key, item);

					formData.append(key, item);
				}
			}
		}
		let res = await updateUserApi(formData);
		console.log(res);
		message[res.data.type](res.data.msg);
		if (res.data.type === ResponseType.SUCCESS) {
			let userInfoRes = await getUserInfoApi();
			console.log(userInfoRes);
			if (userInfoRes.data.type === ResponseType.SUCCESS) {
				dispatch(setUserInfo(userInfoRes.data.userInfo));
			}
			setIsEdit(false);
		}
	};

	const handleOk = () => {
		passwordForm.submit();
	};

	const handleCancel = () => {
		setIsEditPassword(false);
	};

	const onEditPasswordFinish = async (values: any) => {
		console.log(values);
		let res = await updatePasswordApi(values.password);
		console.log(res);
		message[res.data.type](res.data.msg);
		if (res.data.type === ResponseType.SUCCESS) {
			setIsEditPassword(false);
		}
	};

	const beforeUpload = (file: UploadFile) => {
		const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
		if (!isJpgOrPng) {
			message.error('只能上传 JPG 或者 PNG 图片');
			return Upload.LIST_IGNORE;
		}
		const isLt2M = (file.size as number) / 1024 / 1024 < 2;
		if (!isLt2M) {
			message.error('图片大小必须小于 2MB');
			return Upload.LIST_IGNORE;
		}
		return false;
	};

	return (
		<div className="user-info">
			<Form
				form={form}
				labelCol={{ span: 8 }}
				wrapperCol={{ span: 16 }}
				onFinish={onFinish}
			>
				<Row justify="center" align="middle">
					{!isEdit && <Avatar src={avatar} />}
					{isEdit && (
						<Upload
							name="头像"
							maxCount={1}
							listType="picture-card"
							fileList={fileList}
							beforeUpload={beforeUpload}
							showUploadList={{
								showPreviewIcon: false
							}}
							onChange={onImgChange}
						>
							{fileList.length >= 1 ? null : (
								<>
									<div>
										<EditOutlined />
										<div>点击编辑头像</div>
									</div>
								</>
							)}
						</Upload>
					)}
				</Row>
				<Divider>个人信息</Divider>
				{isEdit && (
					<>
						<Row className="info-row">
							<Col span={8}>
								<Form.Item name="username" label="用户名">
									<Input disabled />
								</Form.Item>
							</Col>
							<Col span={8} offset={4}>
								<Form.Item name="age" label="年龄">
									<Input type="number" />
								</Form.Item>
							</Col>
						</Row>
						<Row className="info-row">
							<Col span={8}>
								<Form.Item name="tel" label="电话号码">
									<Input type="tel" />
								</Form.Item>
							</Col>
							<Col span={8} offset={4}>
								<Form.Item name="email" label="邮箱">
									<Input type="email" />
								</Form.Item>
							</Col>
						</Row>
						<Row className="info-row">
							<Col span={8}>
								<Form.Item name="sex" label="性别">
									<Select placeholder="请选择用户性别" allowClear>
										<Select.Option value="男">男</Select.Option>
										<Select.Option value="女">女</Select.Option>
										<Select.Option value="保密">保密</Select.Option>
									</Select>
								</Form.Item>
							</Col>
						</Row>
					</>
				)}
				{!isEdit && (
					<>
						<Row className="info-row">
							<Col span={12}>
								<span className="info-row-label">用户名：</span>
								{userInfo.username}
							</Col>
							<Col span={12}>
								<span className="info-row-label">年龄：</span>
								{userInfo.age}
							</Col>
						</Row>
						<Row className="info-row">
							<Col span={12}>
								<span className="info-row-label">电话号码：</span>
								{userInfo.tel}
							</Col>
							<Col span={12}>
								<span className="info-row-label">邮箱：</span>
								{userInfo.email}
							</Col>
						</Row>
						<Row className="info-row">
							<Col span={12}>
								<span className="info-row-label">性别：</span>
								<Tag
									color={
										userInfo.sex === '男'
											? 'blue'
											: userInfo.sex === '女'
											? 'pink'
											: 'green'
									}
								>
									{userInfo.sex}
								</Tag>
							</Col>
							<Col span={12}>
								<span className="info-row-label">用户角色：</span>
								{roles.length ? (
									roles.map((role, index) => (
										<Tag color={colors[index % 10]} key={role.roleId}>
											{role.roleName}
										</Tag>
									))
								) : (
									<Tag color={colors[2]}>暂无</Tag>
								)}
							</Col>
						</Row>
						<Row className="info-row">
							<Col span={12}>
								<span className="info-row-label">用户状态：</span>
								{userInfo.status === 0 ? (
									<Tag color={'#f50'}>冻结</Tag>
								) : (
									<Tag color={'#2db7f5'}>正常</Tag>
								)}
							</Col>
							<Col span={12}>
								<span className="info-row-label">密码：</span>
								<Button onClick={() => setIsEditPassword(true)}>
									重设密码
								</Button>
							</Col>
						</Row>
					</>
				)}
				<Col className="user-info-btn">
					{!isEdit && <Button onClick={() => setIsEdit(true)}>编辑</Button>}
					{isEdit && <Button onClick={() => setIsEdit(false)}>取消</Button>}
					{isEdit && (
						<Button type="primary" onClick={form.submit}>
							保存
						</Button>
					)}
				</Col>
			</Form>
			<Modal
				title="编辑密码"
				visible={isEditPassword}
				okText="确定"
				cancelText="取消"
				onOk={handleOk}
				onCancel={handleCancel}
			>
				<Form
					form={passwordForm}
					labelCol={{ span: 4 }}
					wrapperCol={{ span: 20 }}
					onFinish={onEditPasswordFinish}
				>
					<Form.Item
						name="password"
						label="密码"
						rules={[
							{ required: true, message: '密码不能为空' },
							{
								pattern: /^[\w]{6,12}$/,
								message: '密码格式错误，需包含6-12位数字、字母、下划线'
							}
						]}
					>
						<Input type="password" />
					</Form.Item>
					<Form.Item
						name="confirmPassword"
						label="确认密码"
						dependencies={['password']}
						rules={[
							{
								required: true,
								message: '确认密码不能为空'
							},
							({ getFieldValue }) => ({
								validator(_, value) {
									if (!value || getFieldValue('password') === value) {
										return Promise.resolve();
									}
									return Promise.reject(
										new Error('两次输入的密码不一致，请重新输入')
									);
								}
							})
						]}
					>
						<Input type="password" />
					</Form.Item>
				</Form>
			</Modal>
		</div>
	);
};

<script setup lang="ts">
import { FormRules, UploadProps } from 'element-plus';

import { registerApi, RegisterParams } from '@/networks/user';
import { getAssetsFileUrl, getFileFromUrl } from '@/utils/fileUtil';

const form = reactive({
	picture: {} as File,
	username: '',
	password: '',
	confirmPassword: '',
	sex: '',
	age: 0,
	tel: '',
	email: ''
} as RegisterParams);
const defaultAvator = ref({} as File);
const testConfirmPassword = (rule: any, value: any, callback: any) => {
	if (!value) {
		callback(new Error('请再次输入密码'));
	} else if (value !== form.password) {
		callback(new Error('两次密码不一致，请重新输入'));
	} else {
		callback();
	}
};
const rules = reactive<FormRules>({
	username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
	password: [
		{
			required: true,
			message: '请输入密码',
			trigger: 'blur'
		}
	],
	confirmPassword: [
		{
			required: true,
			message: '请再次输入密码',
			trigger: 'blur'
		},
		{
			validator: testConfirmPassword
		}
	]
});
const beforeUpload: UploadProps['beforeUpload'] = (file: File) => {
	console.log('🦃🦃file', file);
	form.picture = file;
	return false;
};

const register = async () => {
	console.log('🦃🦃form', form);
	const formData = new FormData();
	Object.keys(form).map((key: string) => {
		const value = form[key];
		if (key === 'picture') {
			if (!value.path) {
				formData.append('picture', defaultAvator.value);
			} else {
				formData.append('picture', value);
			}
		} else if (value) {
			formData.append(key, value);
		}
	});
	const res = await registerApi(formData);
	console.log('🦃🦃res', res);
};
onMounted(() => {
	getFileFromUrl(
		getAssetsFileUrl('images', '音符.jpg'),
		document.getElementById('img') as HTMLImageElement,
		file => {
			defaultAvator.value = file;
		}
	);
});
</script>
<template>
	<div class="register-form">
		<h1 class="title white">注册</h1>
		<el-form :model="form" label-width="90px" :rules="rules">
			<el-form-item label="头像:">
				<el-upload
					class="avatar-uploader"
					:show-file-list="false"
					:before-upload="beforeUpload">
					<img
						v-if="form.picture.path"
						:src="form.picture.path"
						class="avatar" />
					<el-icon v-else class="avatar-uploader-icon">
						<i-ant-design:plus-outlined />
					</el-icon>
				</el-upload>
			</el-form-item>
			<el-form-item label="用户名:" prop="username">
				<el-input v-model="form.username" placeholder="请输入用户名" />
			</el-form-item>
			<el-form-item label="密码:" prop="password">
				<el-input
					v-model="form.password"
					type="password"
					placeholder="请输入密码" />
			</el-form-item>
			<el-form-item label="确认密码:" prop="confirmPassword">
				<el-input
					v-model="form.confirmPassword"
					type="password"
					placeholder="请再次输入密码" />
			</el-form-item>
			<el-form-item label="性别:">
				<el-select v-model="form.sex" placeholder="请选择性别">
					<el-option label="保密" value="保密" />
					<el-option label="男" value="男" />
					<el-option label="女" value="女" />
				</el-select>
			</el-form-item>
			<el-form-item label="年龄:">
				<el-input
					v-model.number="form.age"
					type="number"
					placeholder="请输入年龄" />
			</el-form-item>
			<el-form-item label="邮箱:">
				<el-input v-model="form.email" type="email" placeholder="请输入邮箱" />
			</el-form-item>
			<el-form-item label="电话:">
				<el-input v-model="form.tel" type="tel" placeholder="请输入电话号码" />
			</el-form-item>
			<div class="register-btn">
				<el-button plain class="register-form-button" @click="register">
					注册
				</el-button>
			</div>
		</el-form>
		<img
			id="img"
			:src="getAssetsFileUrl('images', '音符.jpg')"
			width="0"
			height="0" />
	</div>
</template>
<style lang="scss" scoped>
.register-form {
	padding: 30px;
	width: 375px;
	height: 600px;
	border-radius: 10px;
	box-shadow: 2px 2px 20px rgba($color: #000000, $alpha: 0.2);
	background-color: #fff;
	box-sizing: border-box;

	.title {
		text-align: center;
		color: #666666;
		margin-bottom: 0.5em;
	}

	.el-form {
		.avatar {
			height: 70px;
			width: 70px;
		}

		.el-icon.avatar-uploader-icon:hover {
			border-color: var(--el-color-primary);
		}

		.el-icon.avatar-uploader-icon {
			position: relative;
			width: 70px;
			height: 70px;
			font-size: 28px;
			color: #8c939d;
			text-align: center;
			border: 1px dashed var(--el-border-color);
			border-radius: 6px;
			cursor: pointer;
			overflow: hidden;
			transition: var(--el-transition-duration-fast);
		}

		.ant-row {
			margin-bottom: 10px;
		}

		.verification-code {
			display: flex;
			.ant-image {
				margin-left: 10px;
			}
		}

		.remember {
			font-size: 12px;
			font-weight: 900;
			color: #ff8888;
		}

		.register-btn {
			display: flex;
			justify-content: center;
			.register-form-button {
				color: #40a9ff;
			}
		}
	}
}
</style>

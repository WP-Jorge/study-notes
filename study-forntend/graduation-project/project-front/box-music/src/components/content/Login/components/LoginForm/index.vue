<script setup lang="ts">
import { ResponseType } from '@/globals/ResponseType';
import { getVerificationCodeApi, loginApi } from '@/networks/user';
import { useSystemStore } from '@/store/system';
import { useUserStore } from '@/store/user';
import { ElMessage } from 'element-plus';

const systemStore = useSystemStore();
const userStore = useUserStore();
const verificationCode = ref('');
const form = reactive({
	username: '',
	password: '',
	verificationCode: '',
	codeId: '111111',
	remember: false
});
const testUsername = (rule: any, value: any, callback: any) => {
	console.log(1);

	if (!value) {
		callback(new Error('请输入用户名'));
	} else if (value !== form.username) {
		callback(new Error("Two inputs don't match!"));
	} else {
		callback();
	}
};
const testPassword = (rule: any, value: any, callback: any) => {
	if (!value) {
		callback(new Error('请输入密码'));
	} else {
		callback();
	}
};
const testVerificationCode = (rule: any, value: any, callback: any) => {
	if (!value) {
		callback(new Error('请输入验证码'));
	} else {
		callback();
	}
};
const rules = reactive({
	username: [{ validator: testUsername, trigger: 'blur' }],
	password: [{ validator: testPassword, trigger: 'blur' }],
	verificationCode: [{ validator: testVerificationCode, trigger: 'blur' }]
});
const getVerificationCode = async () => {
	const res = await getVerificationCodeApi('111111');
	verificationCode.value = res.data.VerificationCodeBase64;
};

const login = async () => {
	const res = await loginApi(form);
	console.log('🦃🦃res', res);
	if (res && res.data.type === ResponseType.SUCCESS) {
		userStore.login(res.data);
		ElMessage.success(res.data.msg);
		systemStore.loginState.show = false;
	} else {
		ElMessage.error(res.data.msg);
	}
	form.verificationCode = '';
	getVerificationCode();
};

getVerificationCode();
</script>
<template>
	<div class="login-form">
		<h1 class="title white">登录</h1>
		<el-form :model="form" :rules="rules">
			<el-form-item prop="username">
				<el-input v-model="form.username" placeholder="请输入用户名">
					<template #prefix>
						<i-ant-design:user-outlined />
					</template>
				</el-input>
			</el-form-item>
			<el-form-item prop="password">
				<el-input
					v-model="form.password"
					type="password"
					placeholder="请输入密码">
					<template #prefix>
						<i-ant-design:lock-outlined />
					</template>
				</el-input>
			</el-form-item>
			<el-form-item class="verification-code" prop="verificationCode">
				<el-input
					v-model="form.verificationCode"
					placeholder="请输入验证码"
					@keydown.enter="login">
					<template #prefix>
						<i-ant-design:verified-outlined />
					</template>
				</el-input>
				<el-image
					style="height: 32px"
					:src="verificationCode"
					@click="getVerificationCode" />
			</el-form-item>
			<el-form-item>
				<el-checkbox v-model="form.remember" label="记住我"></el-checkbox>
			</el-form-item>
			<div class="login-btn">
				<el-button plain class="login-form-button" @click="login">
					登录
				</el-button>
			</div>
		</el-form>
	</div>
</template>
<style lang="scss" scoped>
.login-form {
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

	:deep(.el-input__wrapper) {
		height: 40px;
	}

	.el-form {
		margin-top: 80px;
		.el-form-item {
			margin-bottom: 28px;
		}

		.verification-code {
			display: flex;

			.el-input {
				width: 200px;
			}
			.el-image {
				margin-left: 15px;
				cursor: pointer;
				border-radius: 5px;
				border: 1px solid #e4e4e4;
				transition: all 0.2s ease;
			}
			.el-image:hover {
				transform: scale(5);
			}
		}

		.remember {
			font-size: 12px;
			font-weight: 900;
			color: #ff8888;
		}

		.login-btn {
			display: flex;
			justify-content: center;
			margin-top: 70px;
			.login-form-button {
				color: #40a9ff;
			}
		}
	}
}
</style>

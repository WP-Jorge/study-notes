<template>
	<div></div>
	<!-- <div>login 页面</div>
	<div>
		验证码：
		<img :src="verificationCodeImg" @click="getVerifyCode" alt="验证码" />
	</div>
	<br />
	<div v-show="userStore.userInfo?.username">
		用户名：{{ userStore.userInfo?.username }}
	</div>
	<br />
	<div v-for="role of roles" :key="role.roleId">
		角色id：{{ role.roleId }} - {{ role.roleName }}
	</div>
	<br />
	<div v-for="api of apis" :key="api.apiId">
		权限id：{{ api.apiId }} - {{ api.apiName
		}}{{ api.apiPath ? ' - ' + api.apiPath : '' }}
	</div>
	<br />
	<div>token：{{ token }}</div>
	<div>
		验证码：
		<input type="text" v-model="verificationCode" />
	</div>
	<button @click="login">登录</button>
	<br />
	<button @click="logout">退出登录</button> -->
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue';
import { useUserStore } from '@/store/user';
import { storeToRefs } from 'pinia';

import { loginApi, logoutApi, getVerifyCodeApi, UserLoginResponse } from '@/networks/user';
import { responseType } from '@/globals/responseType';
import { getRandomNum } from '@/utils/mathUtil';
import { ElMessage } from 'element-plus';

let userStore = useUserStore();

let { apis, roles, token } = storeToRefs(userStore);

let verificationCode = ref('');
let codeId = ref(getRandomNum());
let verificationCodeImg = ref('');

let getVerifyCode = async () => {
	let res = await getVerifyCodeApi(codeId.value);
	console.log(res);
	verificationCodeImg.value = res.data.msg;
};

getVerifyCode();

let login = async () => {
	let res = await loginApi({
		username: 'admin',
		password: '111111',
		verificationCode: verificationCode.value,
		codeId: codeId.value
	});
	console.log(res);
	ElMessage({
		type: res.data.type as responseType,
		message: res.data.msg
	});
	userStore.login(res.data as UserLoginResponse);
};
let logout = async () => {
	let res = await logoutApi();
	userStore.logout();
	console.log(res);
};
</script>
<style lang="scss" scoped></style>

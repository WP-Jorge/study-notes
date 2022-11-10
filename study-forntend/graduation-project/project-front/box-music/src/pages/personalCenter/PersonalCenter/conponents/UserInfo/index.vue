<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { useSystemStore } from '@/store/system';
import { useUserStore } from '@/store/user';
import { getResourceUrl } from '@/utils/fileUtil';
const userStore = useUserStore();
const systemStore = useSystemStore();
const form = reactive({});
const userState = computed(() => {
	return userStore.userInfo.status === 1 ? '正常' : '冻结';
});
const change = (flag: boolean) => {
	systemStore.isEdit = flag;
};
userStore.getUserInfo();
</script>
<template>
	<div class="user-info">
		<div class="avator">
			<el-avatar
				:size="100"
				:src="
					getResourceUrl(userStore.userInfo.userPic as string, ResourceType.USER_PICTURE)
				" />
		</div>
		<el-divider>个人信息</el-divider>
		<el-form :inline="true" :model="form" label-width="85px">
			<div class="row">
				<el-form-item label="用户名：">
					<span>{{ userStore.userInfo.username }}</span>
				</el-form-item>
				<el-form-item label="年龄：">
					<span>{{ userStore.userInfo.age }}</span>
				</el-form-item>
			</div>
			<div class="row">
				<el-form-item label="电话号码：">
					<span>{{ userStore.userInfo.tel }}</span>
				</el-form-item>
				<el-form-item label="邮箱：">
					<span>{{ userStore.userInfo.email }}</span>
				</el-form-item>
			</div>
			<div class="row">
				<el-form-item label="性别：">
					<span>{{ userStore.userInfo.sex }}</span>
				</el-form-item>
				<el-form-item label="用户状态：">
					<span>{{ userState }}</span>
				</el-form-item>
			</div>
			<div class="row">
				<el-form-item label="密码：">
					<el-button>修改密码</el-button>
				</el-form-item>
				<el-form-item></el-form-item>
			</div>
		</el-form>
		<div class="footer">
			<div class="button-container">
				<el-button @click="change(true)">编辑</el-button>
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.user-info {
	.avator {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 30px;
	}
	.el-form {
		.row {
			display: flex;
			justify-content: space-around;
			margin-bottom: 30px;
			.el-form-item {
				display: flex;
				min-width: 350px;
				max-width: 500px;
			}
		}
	}
	.footer {
		display: flex;
		justify-content: end;
		.el-button {
			margin-right: 20px;
		}
	}
}
</style>

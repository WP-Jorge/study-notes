<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { useUserStore } from '@/store/user';
import { getResourceUrl } from '@/utils/fileUtil';
import { ElMessage, UploadProps } from 'element-plus';
import { RegisterParams, updateUserApi } from '@/networks/user';
import { useSystemStore } from '@/store/system';
import { ResponseType } from '@/globals/ResponseType';

const userStore = useUserStore();
const systemStore = useSystemStore();
const form = reactive(userStore.userInfo as RegisterParams);
const picture = ref({} as File);

const beforeUpload: UploadProps['beforeUpload'] = (file: File) => {
	console.log('🦃🦃file', file);
	picture.value = file;
	return false;
};
const pictureUrl = computed(
	() =>
		picture.value.path ??
		getResourceUrl(
			userStore.userInfo.userPic as string,
			ResourceType.USER_PICTURE
		)
);

const updateUser = async () => {
	const formData = new FormData();
	if (picture.value.path) {
		formData.append('picture', picture.value);
	}
	Object.keys(form).map(item => {
		formData.append(item, form[item]);
	});
	const res = await updateUserApi(formData);
	console.log('🦃🦃res', res);
	if (res && res.data.type === ResponseType.SUCCESS) {
		ElMessage.success(res.data.msg);
		systemStore.isEdit = false;
		userStore.getUserInfo();
	} else {
		ElMessage.error(res.data.msg);
	}
};
const change = (flag: boolean) => {
	systemStore.isEdit = flag;
};
</script>
<template>
	<div class="edit">
		<div class="avator">
			<el-upload
				class="avatar-uploader"
				:show-file-list="false"
				:before-upload="beforeUpload">
				<img v-if="pictureUrl" :src="pictureUrl" class="avatar" />
				<el-icon v-else class="avatar-uploader-icon">
					<i-ant-design:plus-outlined />
				</el-icon>
			</el-upload>
		</div>
		<el-divider>个人信息</el-divider>
		<el-form :inline="true" :model="form" label-width="85px">
			<div class="row">
				<el-form-item label="用户名：">
					<el-input v-model="form.username" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="年龄：">
					<el-input v-model="form.age"></el-input>
				</el-form-item>
			</div>
			<div class="row">
				<el-form-item label="电话号码：">
					<el-input v-model="form.tel"></el-input>
				</el-form-item>
				<el-form-item label="邮箱：">
					<el-input v-model="form.email"></el-input>
				</el-form-item>
			</div>
			<div class="row">
				<el-form-item label="性别：">
					<el-select v-model="form.sex" placeholder="请选择性别">
						<el-option label="保密" value="保密" />
						<el-option label="男" value="男" />
						<el-option label="女" value="女" />
					</el-select>
				</el-form-item>
				<el-form-item></el-form-item>
			</div>
		</el-form>
		<div class="footer">
			<div class="button-container">
				<el-button @click="change(false)">取消</el-button>
				<el-button type="primary" @click="updateUser">保存</el-button>
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.edit {
	.avator {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 30px;
		.avatar {
			height: 100px;
			width: 100px;
		}

		.el-icon.avatar-uploader-icon:hover {
			border-color: var(--el-color-primary);
		}

		.el-icon.avatar-uploader-icon {
			position: relative;
			width: 100px;
			height: 100px;
			font-size: 28px;
			color: #8c939d;
			text-align: center;
			border: 1px dashed var(--el-border-color);
			border-radius: 6px;
			cursor: pointer;
			overflow: hidden;
			transition: var(--el-transition-duration-fast);
		}
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

<script setup lang="ts">
import { useSystemStore } from '@/store/system';
import LoginForm from './components/LoginForm/index.vue';
import RegisterForm from './components/RegisterForm/index.vue';
const systemStore = useSystemStore();
const close = () => {
	systemStore.loginState.show = false;
};
const setLoginType = (state: number) => {
	systemStore.loginState.state = state;
};
</script>
<template>
	<div class="login">
		<el-dialog
			:model-value="systemStore.loginState.show"
			:close-on-click-modal="false"
			:close-on-press-escape="false"
			:fullscreen="true"
			@close="close">
			<div class="outer-container">
				<div class="login-register-container">
					<div
						:class="{
							'over-container': true,
							'over-container-register': systemStore.loginState.state
						}">
						<LoginForm v-show="!systemStore.loginState.state" />
						<RegisterForm
							v-show="systemStore.loginState.state"
							onLoginTypeChange="setLoginType('login')" />
					</div>
					<div class="login-container">
						<h2 class="title">Box Music</h2>
						<h2 class="title">聆听世界的声音</h2>
						<div class="image">
							<el-image
								style="width: 135px; height: 135px"
								src="/src/assets/images/logo.png" />
						</div>
						<p class="tip">已经有账号了？</p>
						<div class="button">
							<el-button plain @click="setLoginType(0)">登录</el-button>
						</div>
					</div>
					<div class="register-container">
						<h2 class="title">Box Music</h2>
						<h2 class="title">聆听世界的声音</h2>
						<div class="image">
							<el-image
								style="width: 135px; height: 135px"
								src="/src/assets/images/logo2.png" />
						</div>
						<p class="tip">还没有账号？</p>
						<div class="button">
							<el-button plain @click="setLoginType(1)">注册</el-button>
						</div>
					</div>
				</div>
			</div>
		</el-dialog>
	</div>
</template>
<style lang="scss" scoped>
.login {
	:deep(.el-dialog__header) {
		padding: 0;
		margin: 0;
		height: 0;
		width: 0;
	}

	:deep(.el-dialog__body) {
		height: 100%;
		padding: 0;
		margin: 0;
	}

	.outer-container {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		height: 100%;
		background-color: #d6eaf8;

		.login-register-container {
			position: relative;
			width: 850px;
			height: 400px;
			border-radius: 10px;
			box-shadow: 2px 2px 20px rgba($color: #000000, $alpha: 0.3);
			background-color: #f6f6f6;

			.over-container {
				z-index: 1;
				position: absolute;
				top: -100px;
				left: 50px;
				transition: all 0.5s ease;
			}
			.over-container-register {
				transform: translateX(375px);
			}

			.login-container,
			.register-container {
				// flex: 1;
				display: inline-block;
				box-sizing: border-box;
				width: 50%;
				height: 100%;
				padding: 30px;

				.title {
					margin-bottom: 0.5em;
					text-align: center;
				}

				.image {
					display: flex;
					justify-content: center;
				}
				.tip {
					margin: 20px 0 10px 0;
					text-align: center;
					color: #8e9aaf;
				}
				.button {
					display: flex;
					justify-content: center;

					.ant-btn {
						background-color: transparent;
					}
				}
			}
		}
	}
}
</style>

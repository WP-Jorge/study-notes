<script setup lang="ts">
import { MessageType, ResourceType } from '@/globals/GlobalValues';
import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';
import { getAssetsFileUrl, getResourceUrl } from '@/utils/fileUtil';
import { useUserStore } from '@/store/user';
import { ElButton } from 'element-plus';
import { useRouter } from 'vue-router';
interface ListItem {
	value: string;
	label: string;
}
const router = useRouter();
const systemStore = useSystemStore();
const userStore = useUserStore();
const { showMusicDetail } = storeToRefs(systemStore);
const { isMax, isFullscreen, routerNum, routerPos } = storeToRefs(systemStore);
const leftDisable = computed(() => routerPos.value === 0);
const rightDisable = computed(() => routerPos.value === routerNum.value - 1);
const list = ref<ListItem[]>([]);
const states = [
	'Alabama',
	'Alaska',
	'Arizona',
	'Arkansas',
	'California',
	'Colorado',
	'Connecticut',
	'Delaware',
	'Florida',
	'Georgia',
	'Hawaii',
	'Idaho',
	'Illinois',
	'Indiana',
	'Iowa',
	'Kansas',
	'Kentucky',
	'Louisiana',
	'Maine',
	'Maryland',
	'Massachusetts',
	'Michigan',
	'Minnesota',
	'Mississippi',
	'Missouri',
	'Montana',
	'Nebraska',
	'Nevada',
	'New Hampshire',
	'New Jersey',
	'New Mexico',
	'New York',
	'North Carolina',
	'North Dakota',
	'Ohio',
	'Oklahoma',
	'Oregon',
	'Pennsylvania',
	'Rhode Island',
	'South Carolina',
	'South Dakota',
	'Tennessee',
	'Texas',
	'Utah',
	'Vermont',
	'Virginia',
	'Washington',
	'West Virginia',
	'Wisconsin',
	'Wyoming'
];
const go = (num: number) => {
	history.go(num);
};
const open = (state: number) => {
	systemStore.loginState.show = true;
	systemStore.loginState.state = state;
};
onMounted(() => {
	list.value = states.map(item => {
		return { value: `value:${item}`, label: `label:${item}` };
	});
});
</script>
<template>
	<div class="header">
		<div class="container">
			<div class="left">
				<router-link class="logo" to="/" tabindex="-1">
					<img :src="getAssetsFileUrl('images', 'logo.png')" alt="logo" />
					<span>盒子音乐</span>
				</router-link>
				<div :class="{ router: true, hidden: showMusicDetail }">
					<i-system-uicons:arrow-left-circle
						:class="{ item: true, disabled: leftDisable }"
						@click="go(-1)" />
					<i-system-uicons:arrow-right-circle
						:class="{ item: true, disabled: rightDisable }"
						@click="go(1)" />
					<i-ion:reload-circle-outline class="item" @click="go(0)" />
				</div>
			</div>
			<div class="right">
				<div class="search">
					<SearchSelect />
				</div>
				<div class="info">
					<div
						v-if="userStore.token"
						class="user-info"
						@click="router.push('/personalCenter')">
						<el-popover
							:width="250"
							popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;">
							<template #reference>
								<el-avatar
									:src="getResourceUrl(userStore.userInfo.userPic as string, ResourceType.USER_PICTURE)" />
							</template>
							<template #default>
								<div class="user-info-container">
									<div class="title">
										<h3>{{ userStore.userInfo.username }}</h3>
									</div>
									<div class="content">
										<p>电话号码：{{ userStore.userInfo.tel }}</p>
										<p>邮箱：{{ userStore.userInfo.email }}</p>
										<p>性别：{{ userStore.userInfo.sex }}</p>
										<p>年龄：{{ userStore.userInfo.age }}</p>
									</div>
									<div class="footer">
										<el-button
											type="danger"
											size="small"
											@click="userStore.logout">
											退出登录
										</el-button>
									</div>
								</div>
							</template>
						</el-popover>
					</div>
					<div v-else class="login">
						<span @click="open(0)">登录</span>
						/
						<span @click="open(1)">注册</span>
					</div>
				</div>
				<div class="options">
					<el-tooltip
						v-if="!isFullscreen"
						effect="light"
						content="全屏"
						placement="bottom">
						<i-ic-round-fullscreen
							class="box-item"
							tabindex="-1"
							@click="
								systemStore.optionChange(MessageType.WINDOW_FULLSCREEN)
							" />
					</el-tooltip>
					<el-tooltip
						v-if="isFullscreen"
						effect="light"
						content="退出全屏"
						placement="bottom">
						<i-ic-round-fullscreen-exit
							class="box-item fullscreen-exit"
							tabindex="-1"
							@click="
								systemStore.optionChange(MessageType.WINDOW_FULLSCREEN_EXIT)
							" />
					</el-tooltip>
					<el-tooltip effect="light" content="最小化" placement="bottom">
						<i-ant-design-line-outlined
							v-show="!isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="systemStore.optionChange(MessageType.WINDOW_MIN)" />
					</el-tooltip>
					<el-tooltip effect="light" content="最小窗口" placement="bottom">
						<i-ant-design-shrink-outlined
							v-show="isMax && !isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="systemStore.optionChange(MessageType.WINDOW_CHANGE)" />
					</el-tooltip>
					<el-tooltip effect="light" content="最大窗口" placement="bottom">
						<i-ant-design-arrows-alt-outlined
							v-show="!isMax && !isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="systemStore.optionChange(MessageType.WINDOW_CHANGE)" />
					</el-tooltip>
					<el-tooltip effect="light" content="关闭" placement="bottom">
						<i-ant-design-close-outlined
							v-show="!isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="systemStore.optionChange(MessageType.WINDOW_CLOSE)" />
					</el-tooltip>
				</div>
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.header {
	width: 100%;
	min-width: 980px;
	height: 60px;
	background: linear-gradient(#e4e4e4, white);

	.container {
		display: flex;
		justify-content: space-between;
		height: 100%;
		margin: 0 10px 0 40px;

		.left {
			display: flex;
			justify-content: space-between;
			align-items: center;
			width: 260px;
			height: 60px;
			-webkit-app-region: no-drag;

			.logo {
				display: flex;
				justify-content: space-between;
				align-items: center;
				width: 110px;
				height: 100%;
				-webkit-app-region: no-drag;

				img {
					width: 40px;
				}

				span {
					color: #444444;
					font-weight: bold;
					font-size: 14px;
				}
			}

			.router {
				display: flex;
				justify-content: space-between;
				width: 100px;
				.item {
					font-size: 22px;
					color: #949494;
				}
				.item:hover {
					color: #545454;
					cursor: pointer;
				}
				.disabled {
					color: #d4d4d4;
					cursor: not-allowed;
				}
				.disabled:hover {
					color: #d4d4d4;
					cursor: not-allowed;
				}
			}

			.hidden {
				visibility: hidden;
			}
		}
		.right {
			display: flex;
			justify-content: space-between;
			width: 350px;

			.search {
				display: flex;
				justify-content: center;
				align-items: center;
				margin-right: 20px;
				-webkit-app-region: no-drag;
				.el-input {
					width: 150px;
					.search-icon {
						cursor: pointer;
					}
					:deep(.el-input__wrapper) {
						border-radius: 50px;
						background-color: transparent;
						font-size: 12px;
					}
					:deep(.el-select__input) {
						padding-left: 20px;
					}
				}
				.search-content {
					max-height: 500px;
					overflow: overlay;
					&::-webkit-scrollbar {
						width: 5px;
						height: 8px;
						background-color: var(--el-color-info-light-9);
					}
					&::-webkit-scrollbar-thumb {
						background-color: transparent;
					}
				}
				.search-content:hover {
					&::-webkit-scrollbar-thumb {
						background-color: var(--el-color-primary-light-5);
					}
				}
			}
			.info {
				display: flex;
				justify-content: space-between;
				align-items: center;
				width: 120px;
				height: 100%;
				-webkit-app-region: no-drag;
				.user-info {
					width: 40px;
					height: 40px;
					cursor: pointer;
					border-radius: 50%;
					overflow: hidden;

					.demo-rich-conent {
						display: flex;
						flex-direction: column;

						.user-info-top {
							margin-bottom: 10px;
							border-bottom: 1px solid #f0f0f0;
						}
					}
				}
				.login {
					font-size: 12px;
					color: #949494;
					-webkit-app-region: no-drag;
					span {
						cursor: pointer;
					}
				}
			}
			.options {
				display: flex;
				justify-content: space-around;
				align-items: center;
				width: 100px;
				-webkit-app-region: no-drag;

				.box-item {
					cursor: pointer;
				}
				.fullscreen-exit {
					font-size: 26px;
				}
			}
		}
	}
}
</style>

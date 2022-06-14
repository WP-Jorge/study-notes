<script setup lang="ts">
import { GlobalValues } from '@/globals/GlobalValues';
import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';
import { getAssetsFileUrl } from '@/utils/fileUtil';
const systemStore = useSystemStore();
const { isMax, isFullscreen } = storeToRefs(systemStore);
</script>
<template>
	<div class="header">
		<div class="container">
			<router-link class="logo" to="/" tabindex="-1">
				<img :src="getAssetsFileUrl('images', 'logo.png')" alt="logo" />
				<span>盒子音乐</span>
			</router-link>
			<div class="right">
				<div class="info">
					<div class="user-info">
						<el-popover
							:width="300"
							popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;">
							<template #reference>
								<el-avatar :src="getAssetsFileUrl('images', 'logo.png')" />
							</template>
							<template #default>
								<div
									class="demo-rich-conent"
									style="display: flex; gap: 16px; flex-direction: column">
									<el-avatar :src="getAssetsFileUrl('images', 'logo.png')" />
									<div>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500">
											Element Plus
										</p>
										<p
											class="demo-rich-content__mention"
											style="
												margin: 0;
												font-size: 14px;
												color: var(--el-color-info);
											">
											@element-plus
										</p>
									</div>

									<p class="demo-rich-content__desc" style="margin: 0">
										Element Plus, a Vue 3 based component library for
										developers, designers and product managers
									</p>
								</div>
							</template>
						</el-popover>
					</div>
				</div>
				<div class="options">
					<el-tooltip effect="light" content="全屏" placement="bottom">
						<i-ic-round-fullscreen
							v-show="!isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="
								systemStore.optionChange(GlobalValues.WINDOW_FULLSCREEN)
							" />
					</el-tooltip>
					<el-tooltip effect="light" content="退出全屏" placement="bottom">
						<i-ic-round-fullscreen-exit
							v-show="isFullscreen"
							class="box-item fullscreen-exit"
							tabindex="-1"
							@click="
								systemStore.optionChange(GlobalValues.WINDOW_FULLSCREEN_EXIT)
							" />
					</el-tooltip>
					<el-tooltip effect="light" content="最小化" placement="bottom">
						<i-ant-design-line-outlined
							v-show="!isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="systemStore.optionChange(GlobalValues.WINDOW_MIN)" />
					</el-tooltip>
					<el-tooltip effect="light" content="最小窗口" placement="bottom">
						<i-ant-design-shrink-outlined
							v-show="isMax && !isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="systemStore.optionChange(GlobalValues.WINDOW_CHANGE)" />
					</el-tooltip>
					<el-tooltip effect="light" content="最大窗口" placement="bottom">
						<i-ant-design-arrows-alt-outlined
							v-show="!isMax && !isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="systemStore.optionChange(GlobalValues.WINDOW_CHANGE)" />
					</el-tooltip>
					<el-tooltip effect="light" content="关闭" placement="bottom">
						<i-ant-design-close-outlined
							v-show="!isFullscreen"
							class="box-item"
							tabindex="-1"
							@click="systemStore.optionChange(GlobalValues.WINDOW_CLOSE)" />
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
		.right {
			display: flex;
			justify-content: space-between;
			width: 200px;
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

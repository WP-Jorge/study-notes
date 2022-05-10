<template>
	<div class="tab-bar-item" @click="itemClick">
		<div v-if="!isActive">
			<slot name="item-icon"></slot>
		</div>
		<div v-else>
			<slot name="item-icon-active"></slot>
		</div>
		<div :style="activeStyle">
			<slot name="item-text"></slot>
		</div>
	</div>

</template>

<script>
	export default {
		name: 'TabBarItem',
		props: {
			path: String,
			// 传入参数activeColor，默认红色
			activeColor: {
				type: String,
				default: 'red'
			}
		},
		data() {
			return {
				// isActive: false
			}
		},
		computed: {
			// 动态获取当前item是否选中
			isActive() {
				return this.$route.path == this.path
			},
			// 使用传入的颜色
			activeStyle() {
				return this.isActive ? {color: this.activeColor} : {}
			}
		},
		methods: {
			itemClick() {
				console.log("itemClick")
				this.$router.replace(this.path).catch((err) => err)
			}
		}
	}
</script>

<style scoped>
	.tab-bar-item {
		flex: 1;
		text-align: center;
		height: 49px;
		font-size: 14px;
	}

	.tab-bar-item img {
		margin-top: 3px;
		width: 24px;
		vertical-align: middle;
	}
	
	.active {
		color: red;
	}
</style>

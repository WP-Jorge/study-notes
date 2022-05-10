<template>
	<!-- view 相当于html里的div -->
	<!-- block 不会在页面输出标签 -->
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
			<!-- @input 触发input事件 -->
			<input type="text" :value="title" @input="change" />
		</view>
		<!-- open-type
			switchTab: 如果要跳转tabbar中的地址，要使用这个
			redirect: ，页面卸载，重新打开新的页面，重定向，无法使用返回键 
			
		-->
		<navigator url="../hello/hello" open-type="switchTab">去hello页面</navigator>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello My First App'
			}
		},
		// 监听页面加载，其参数为上个页面传递的数据，参数类型为Object（用于页面传参）
		onLoad() {},
		// 监听页面显示。页面每次出现在屏幕上都触发，包括从下级页面点返回露出当前页面
		onShow() {},
		// 监听页面初次渲染完成。注意如果渲染速度快，会在页面进入动画完成前触发
		onReady() {},
		// 监听页面隐藏
		onHide() {},
		// 监听页面关闭	
		onUnload() {},
		// 监听窗口尺寸变化	
		onResize() {},
		// 监听用户下拉动作，一般用于下拉刷新
		onPullDownRefresh() {},
		// 页面滚动到底部的事件（不是scroll-view滚到底），常用于下拉下一页数据。具体见下方注意事项
		onReachBottom() {},
		// 点击 tab 时触发，参数为Object
		onTabItemTap() {},
		// 用户点击右上角分享
		onShareAppMessage() {},
		// 监听页面滚动，参数为Object
		onPageScroll() {},
		methods: {
			change(e) {
				let txtTitle = e.detail.value
				this.title = txtTitle
			}
		}
	}
</script>

<style lang="less">
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		.text-area {
			font-size: 30rpx;
		}
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		/* display: flex;
		justify-content: center; */
		text-align: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>

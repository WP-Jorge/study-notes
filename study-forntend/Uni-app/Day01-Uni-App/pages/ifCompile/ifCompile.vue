<template>
	<view>
		<!-- 多端运行 -->
		<!-- #ifdef H5 -->
		<view>只在H5编译</view>
		<!-- #endif -->
		<!-- #ifdef APP-PLUS -->
		<view>只在ios/Android编译</view>
		<!-- #endif -->
		<!-- #ifdef MP -->
		<view>只在小程序（微信、支付宝、百度）编译</view>
		<!-- #endif -->
		<!-- #ifdef MP-WEIXIN -->
		<view>只在微信小程序编译</view>
		<!-- #endif -->
		
		<!-- #ifndef H5 -->
		<view>在除了H5的设备上编译</view>
		<!-- #endif -->
		
		<view class="color"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		onLoad() {
			// #ifdef H5
			console.log('只在H5编译');
			// #endif
			// #ifdef APP-PLUS
			console.log('只在ios/Android编译');
			// #endif
			// #ifdef MP
			console.log('只在小程序（微信、支付宝、百度）编译');
			// #endif
			// #ifdef MP-WEIXIN
			console.log('只在微信小程序编译');
			// #endif
			
			// #ifndef H5
			console.log('在除了H5的设备上编译');
			// #endif
		}
	}
</script>

<style>
	html {
		font-size: 20px;
	}
	.color {
		/* #ifdef H5 */
		background-color: pink;
		/* #endif */
		/* #ifdef APP-PLUS */
		background-color: yellowgreen;
		/* #endif */
		height: 100rpx;
		width: 150rpx;
	}
</style>

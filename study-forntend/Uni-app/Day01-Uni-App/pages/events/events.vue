<template>
	<view>
		<!-- 
			1.focus:获得焦点触发
			2.blur:失去焦点时触发
			3.confirm:点击完成按钮时触发 键盘输入完	点击start / enter
			4.click:鼠标单击事件
				pc上有这个后click不触发
				手机上先触发触摸事件，后触发单击事件
				这两个只选其一
			5.tap:组件被触摸
			6.longpress:按压超过350ms
				效果同上
				不推荐使用longtap
				推荐使用longpress
			7.longtap:手指触摸超过350ms
			8.touchstart:手指按下生命周期函数
			9.touchmove:手指按下移动生命周期函数
			10.touchend:手指按下抬起生命周期函数
			11.touchcancel:手指触摸动作被打断，如来电提醒，弹窗 
		-->
		<input type="text" @input="change" value="1111"
			@focus="focus"
			@blur="blur"
			@confirm="confirm"
			@click="click"
			@tap="tap"
			@longpress="longpress"
			@longtap="longtap"
			@touch
		 />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			change() {
				
			},
			focus() {
				console.log("获得焦点");
			},
			blur() {
				console.log("失去焦点");
			},
			confirm() {
				console.log("键盘点击完成");
			},
			click() {
				console.log("组件单击事件");
			},
			tap() {
				console.log("组件被触摸");
			},
			longpress() {
				console.log("按压超过350ms");
			},
			longtap() {
				console.log("手指触摸超过350ms");
			}
		}
	}
</script>

<style>

</style>

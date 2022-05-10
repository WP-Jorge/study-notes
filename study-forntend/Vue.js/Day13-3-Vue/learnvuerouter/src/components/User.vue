<template>
	<div>
		<h2>我是用户界面</h2>
		<p>我是用户的相关信息，嘿嘿嘿嘿</p>
		
		<!-- 获取当前活跃的动态路由的属性 -->
		<h2>{{ userId }}</h2>
		<h2>{{ $route.params.id }}</h2>
	</div>
</template>

<script>
	export default {
		name: 'User',
		computed: {
			userId() {
				// 获取当前活跃的路由的属性userId
				return this.$route.params.id
			}
		},
		created() {
			console.log("User创建")
		},
		destroyed() {
			console.log("User销毁")
		}
	}
</script>

<style scoped>
</style>

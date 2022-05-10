<template>
	<div>
		<h2>我是档案组件</h2>
		<p>我是档案组件的内容，霍霍霍霍</p>
		<p>姓名：{{ $route.query.name }}</p>
		<p>年龄：{{ $route.query.age }}</p>
		<p>身高：{{ $route.query.height }}</p>
	</div>
</template>

<script>
	export default {
		name: 'Profile',
		created() {
			console.log("Profile创建")
		},
		destroyed() {
			console.log("Profile销毁")
		}
	}
</script>

<style scoped>
</style>

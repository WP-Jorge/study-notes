<template>
	<div>
		<h2>我是首页</h2>
		<p>我是首页内容，哈哈哈哈</p>
		
		<!-- to后面不用加 /home/直接加子组件path -->
		<!-- 或者to后面或者加 /home/+子组件path -->
		<RouterLink to="news">新闻</RouterLink>
		<RouterLink to="/home/messages">消息</RouterLink>
		<RouterView></RouterView>
	</div>
</template>

<script>
	export default {
		name: 'Home',
		data() {
			return{
				path: '/home/news'
			}
		},
		created() {
			console.log('home created')
		},
		destroyed() {
			console.log('home destroyed')
		},
		// 只有在keep-alive中activated、deactivated函数才能被使用
		// 在vue对象存活的情况下，进入当前存在activated()函数的页面时，一进入页面就触发
		activated() {
			this.$router.push(this.path)
		},
		// deactivated() {
		// 	this.path = this.$route.path
		// }
		// 组件内导航守卫
		beforeRouteLeave(to, from, next) {
			this.path = this.$route.path
			next()
		}
	}
</script>

<style scoped>
</style>

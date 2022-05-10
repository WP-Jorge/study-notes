<template>
  <div id="app">
	  <!-- <router-link></router-link>是vue-router中注册的两个全局组件 默认会被渲染成a标签 -->
	  <!-- 使用tag属性设置标签 -->
	  <!-- 添加replace 则无法返回 -->
	  <!-- 选中后会添加router-link-active类，可以修改样式 -->
	  <!-- active-class=""指定选中后的样式 -->
	  <!-- <router-link tag="button" to="/home" replace active-class="active">首页</router-link>
	  <router-link tag="button" to="/about" replace active-class="active">关于</router-link> -->
	  <!-- 也可以i在配置路由的地方添加linkActiveClass来指定样式 -->
	  <!-- <router-link tag="button" to="/home" >首页</router-link> -->
	  <!-- <router-link tag="button" to="/about" >关于</router-link> -->
	  
	  <!-- 不使用router-link标签，使用绑定事件的形式 -->
	  <!-- <button @click="homeClick">首页</button> -->
	  <!-- <button @click="aboutClick">关于</button> -->
	  <!-- 通过<router-view></router-view>根据当前path将对应组件显示出来 -->
	  
	  <router-link to="/home">首页</router-link>
	  <router-link to="/about">关于</router-link>
	  <!-- params传参 -->
	  <!-- 动态路由 -->
	  <!-- <router-link :to="'/user/'+userId">用户</router-link> -->
	  
	  <!-- query传参 -->
	  <!-- <router-link to="/profile">档案</router-link> -->
	  <!-- URL: 协议://主机:端口/路径?查询(query)#片段 -->
	  <!-- scheme://host:port/path?query#frament -->
	  <!-- <router-link :to="{path: '/profile', query: {name: 'Jorge', age: 18, height: 1.88}}">档案</router-link> -->
	  
	  <!-- 不使用router-link标签，使用绑定事件的形式 -->
	  <button @click="userCilck">用户</button>
	  <button @click="profileClick">档案</button>
	  <!-- 通过<router-view></router-view>根据当前path将对应组件显示出来 -->
	  
	  <!-- 使组件保持存活keep-alive -->
	  <!-- exclude属性排除不需要保持存活的组件 exclude="组件名,组件名" 逗号后边不能加空格-->
	  <keep-alive exclude="Profile,User">
		  <router-view></router-view>
	  </keep-alive>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
	  return{
		  userId: 'zhangsan'
	  }
  },
  methods: {
	  homeClick() {
		  // console.log('首页')
		  // this.$router.replace('/home')
		  // 加上.catch(err => {})防止连按两次报错
		  this.$router.push('/home').catch(err => {})
	  },
	  aboutClick() {
		  // console.log('关于我们')
		  // this.$router.replace('/about').catch(err => {})
		  console.log(this)
		  this.$router.push('/about').catch(err => {})
	  },
	  userCilck() {
		  this.$router.push('/user/' + this.userId).catch(err => {})
	  },
	  profileClick() {
		  this.$router.push({
			  path: '/profile',
			  query: {
				  name: 'Jorge',
				  age: 18,
				  height: 1.88
			  }
		  }).catch(err => {})
	  }
  }
}
</script>

<style>
	/* .router-link-active{
		color: pink;
	} */
	.active{
		color: pink;
	}
</style>

<script>
	export default {
		// 当uni-app 初始化完成时触发（全局只触发一次）
		onLaunch: function() {
			console.log('App Launch')
		},
		// 当 uni-app 启动，或从后台进入前台显示
		onShow: function() {
			console.log('App Show')
		},
		// 当 uni-app 从前台进入后台
		onHide: function() {
			console.log('App Hide')
		},
		// 当 uni-app 报错时触发
		onError() {
			
		},
		// 对 nvue 页面发送的数据进行监听，可参考 nvue 向 vue 通讯
		onUniNViewMessage() {
			
		},
		// 对未处理的 Promise 拒绝事件监听函数（2.8.1+）
		onUnhandledRejection() {
			
		},
		// 页面不存在监听函数
		onPageNotFound() {
			
		},
		// 监听系统主题变化
		onThemeChange() {
			
		}
	}
</script>

<style>
	/*每个页面公共css */
</style>

function animate(obj, target, callback) {
	// 防止重复触发定时器, 每次调用动画时先清除定时器
	clearInterval(obj.timer)
	// 开启定时器
	obj.timer = setInterval(function() {
		// 设置缓动移动步长
		var step = (target - obj.offsetLeft) / 10
		// 判断左右移动方向
		step = step > 0 ? Math.ceil(step) : Math.floor(step)
		// 到达目的地清除定时器
		if (obj.offsetLeft == target) {
			clearInterval(obj.timer)
			// 判断是否有回调函数,有则执行
			callback && callback()
		}
		// 使目标移动
		obj.style.left = obj.offsetLeft + step + 'px'
	}, 10)
}

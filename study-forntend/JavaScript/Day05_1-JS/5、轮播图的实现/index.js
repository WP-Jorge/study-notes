window.addEventListener('load', function() {
	var outer = document.querySelector('#outer')
	var span_l = document.querySelector('#span_l')
	var span_r = document.querySelector('#span_r')
	var ul = document.querySelector('ul')
	var lis = ul.children
	var ol = document.querySelector('ol')
	var focusWidth = outer.offsetWidth
	var circle = 0 // 记录变红小球索引号
	var num = 1 // 记录图片索引号 初始值为第二图片索引号 如果轮播有4张图片,则需在首位处分别添加最后最前的图片
	var flag = true // 节流阀
	
	// 根据图片数量动态生成索引小球
	for (var i = 0; i < lis.length; i++) {
		var li = document.createElement('li')
		li.setAttribute('index', i)
		if(i == 0){
			li.className = 'current'
		}
		ol.appendChild(li)
		// 给每个小球添加点击事件
		li.addEventListener('click', function() {
			var index = this.getAttribute('index')
			num = parseInt(index) + 1
			animate(ul, -(num * focusWidth))
			circle = num - 1
			circleChange()
		})
	}
	
	// 克隆最后一个轮播图
	var cloneLast = lis[lis.length - 1].cloneNode(true) // true深克隆
	// 克隆第一个轮播图
	var cloneFirst = lis[0].cloneNode(true) // true深克隆
	// 克隆的最后一个轮播图放至最前
	ul.insertBefore(cloneLast, ul.children[0])
	// 克隆的第一个轮播图放至最后
	ul.appendChild(cloneFirst)

	// 进入时左右箭头动画
	outer.addEventListener('mouseenter', function() {
		span_l.style.display = 'block'
		span_r.style.display = 'block'
		clearInterval(timer)
		timer = null
	})
	// 离开时左右箭头动画
	outer.addEventListener('mouseleave', function() {
		span_l.style.display = 'none'
		span_r.style.display = 'none'
		timer = setInterval(function(){
			span_r.click()
		}, 2000)
	})
	
	// 右箭头点击事件
	span_r.addEventListener('click', function() {
		if(flag){ // 添加节流阀
			flag = false // 关闭节流阀
			if (num == lis.length - 1) {
				num = 1
				ul.style.left = -num * focusWidth + 'px'
			}
			// 每点击一次右箭头 图片索引++
			num++
			animate(ul, -(num * focusWidth), function(){
				flag = true // 回调函数开启节流阀
			})
			
			// 每点击一次右箭头 小球索引++
			circle++
			if(circle == lis.length-2){
				circle = 0
			}
			circleChange()
		}
	})
	// 左箭头点击事件
	span_l.addEventListener('click', function() {
		if(flag){ // 添加节流阀
			flag = false // 关闭节流阀
			if (num == 0) {
				num = lis.length - 2
				ul.style.left = -num * focusWidth + 'px'
			}
			// 每点击一次右箭头 图片索引--
			num--
			animate(ul, -(num * focusWidth), function(){
				flag = true // 回调函数开启节流阀
			})
			
			// 每点击一次右箭头 小球索引--
			circle--
			if(circle == -1){
				circle = 3
			}
			circleChange()
		}
	})
	
	// 小球变红函数
	function circleChange() {
		for (var i = 0; i < lis.length - 2; i++) {
			ol.children[i].className = ''
		}
		ol.children[circle].className = 'current'
	}
	
	// 添加自动轮播动画
	var timer = setInterval(function(){
		span_r.click()
	}, 2000)
})

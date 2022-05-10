var that
class Tab {
	constructor(id) {
		that = this
		this.main = document.querySelector(id)
		this.add = this.main.querySelector('.tabadd')
		this.ul = this.main.querySelector('.fisrstnav ul:first-child')
		this.fsection = this.main.querySelector('.tabscon')
		this.init()
	}

	// 初始化绑定事件
	init() {
		// 初始化时获取所有li、section
		this.updateNode()
		this.add.onclick = this.addTab
		for (var i = 0; i < this.lis.length; i++) {
			this.lis[i].index = i
			this.lis[i].onclick = this.toggleTab
			this.removes[i].onclick = this.removeTab
			this.spans[i].ondblclick = this.editTab
			this.sections[i].ondblclick = this.editTab
		}
	}
	// 获取所有li、section
	updateNode() {
		this.lis = this.main.querySelectorAll('li')
		this.sections = this.main.querySelectorAll('section')
		this.removes = this.main.querySelectorAll('.icon-guanbi')
		this.spans = this.main.querySelectorAll('.fisrstnav li span:first-child')
	}

	// 清除类
	clearClass() {
		for (var i = 0; i < this.lis.length; i++) {
			this.lis[i].className = ''
			this.sections[i].className = ''
		}
	}

	// 切换功能
	toggleTab() {
		that.clearClass()
		this.className = 'liactive'
		that.sections[this.index].className = 'conactive'
	}

	// 添加功能
	addTab() {
		that.clearClass()
		// (1) 创建li和section元素
		var li = '<li class="liactive"><span>新选项</span><span class="iconfont icon-guanbi"></span></li>'
		var section = '<section class="conactive">new section</section>'

		// (2) 追加新元素
		that.ul.insertAdjacentHTML('beforeend', li)
		that.fsection.insertAdjacentHTML('beforeend', section)
		that.init()
	}

	// 删除功能
	removeTab(e) {
		e.stopPropagation() //  阻止冒泡 防止触发li的点击事件
		var index = this.parentNode.index
		// console.log(index)
		that.lis[index].remove() // 使用remove()删除数组元素
		that.sections[index].remove()
		that.init()
		// 如果被删除的选项没被选中，则不执行前一元素选中功能
		if(document.querySelector('.liactive')) {return}
		// 删除后让该元素前一个选项选中
		index--
		that.lis[index] && that.lis[index].click()
	}

	// 修改功能
	editTab() {
		var str = this.innerHTML
		// 添加双击不选中文字效果
		window.getSelection?window.getSelection().removeAllRanges():document.selection.empty()
		
		// 双击添加文本框
		this.innerHTML = '<input type="text"/>'
		var input = this.children[0]
		input.value = str
		input.select() // 让文本框内容处于选定状态
		
		// 当焦点离开文本框后将文本框内容给span
		input.onblur = function() {
			this.parentNode.innerHTML = this.value
		}
		// 按下回车事件
		input.onkeyup = function(e) {
			if(e.keyCode === 13){
				// 手动调用表单失去焦点事件 不需要鼠标离开操作
				this.blur()
			}
		}
	}
}
new Tab('#tab')

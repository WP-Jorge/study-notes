new Vue({
    el: '#app',
    data: {
		datas: [
			{work: '学习node.js', finished: false},
			{work: '学习sass', finished: true},
			{work: '学习Echarts', finished: false},
			{work: '学习flex布局', finished: true}
		],
		work: '',
		showType: 0,
        activeName: '1',
		checked: false
    },
    methods: {
		// 点击导航栏功能
		navClick(tab) {
			this.showType = parseInt(tab.paneName) - 1;
		},
		
		// 添加待办
		addItem() {
			let item = {
				work: this.work,
				finished: false
			}
			// 判断输入框是否为空
			let flag = this.formCheck();
			if (flag) {
				this.datas.unshift(item);
				this.work = '';
			}
		},
		
		// 删除待办
		deleteItem(index) {
			this.datas.splice(index, 1);
		},
		
		// 清除已完成信息
		clearFinished() {
			for (let i = 0; i < this.datas.length; i++) {
				if (this.datas[i].finished) {
					this.datas.splice(i, 1);
					i--;
				}
			}
		},
		
		// 输入框验证
		formCheck() {
			// 失败弹出框
			if (!this.work) {
				this.$message({
					message: '输入内容不能为空！请重新输入！',
					center: true,
					duration: 2500,
					type: 'error'
				});
				return false;
				
			// 成功弹出框
			} else {
				this.$message({
					message: '添加成功！',
					center: true,
					duration: 2000,
					type: 'success'
				});
				return true;
			}
		}
    },
	computed: {
		// 计算当前标签页的项目数
		totalItem() {
			let num = 0;
			if (this.showType === 1) {
				for (let i = 0; i < this.datas.length; i++) {
					if (this.datas[i].finished === true) {
						num++;
					}
				}
			} else if (this.showType === 2) {
				for (let i = 0; i < this.datas.length; i++) {
					if (this.datas[i].finished === false) {
						num++;
					}
				}
			} else {
				num = this.datas.length;
			}
			return num;
		}
	}
})
new Vue({
	el: "#app",
	data: {
		// 学生数据库
		datas: [
			{ID: 1, userName: '张三', age: 18, school: '希望中学', remark: '三好学生'},
			{ID: 2, userName: '李四', age: 19, school: '光明中学', remark: '优秀生'},
			{ID: 3, userName: '王五', age: 18, school: '希望中学', remark: ''},
			{ID: 4, userName: '赵六', age: 18, school: '阳光中学', remark: '三好学生'},
			{ID: 5, userName: '宋七', age: 17, school: '梅花中学', remark: '优秀志愿者'},
			{ID: 6, userName: '赵老丁', age: 20, school: '第一中学', remark: ''},
			{ID: 7, userName: '黄老丙', age: 18, school: '第一中学', remark: ''},
			{ID: 8, userName: '大逵', age: 18, school: '希望中学', remark: '优秀班干部'},
			{ID: 9, userName: '梅十三', age: 19, school: '第一中学', remark: '优秀班干部'},
			{ID: 10, userName: '李铁柱', age: 19, school: '梅花中学', remark: ''},
			{ID: 11, userName: '张小花', age: 17, school: '梅花中学', remark: '三好学生'}
		],
		// 学校数据库
		schools: ['希望中学', '光明中学', '阳光中学', '梅花中学', '第一中学'],
		// 当前点击的用户item
		item: null,
		// 每页数据条数
		pageSize: 3,
		// 初始化页码
		count: 1,
		// 控制上一页、下一页是否可用
		preFlag: true,
		nexFlag: true,
		// 当前编辑的用户item
		editItem: {},
		// 判断是否需要添加新用户
		addFlag: false,
		// 控制信息弹出框
		alertFlag: false,
		timer: false,
		alertMsg: '',
		failClass: 'alert alert-danger',
		successClass: 'alert alert-success',
		alertClass: ''
	},
	mounted() {
		// 初始化分页
		this.pageBarContral();
	},
	methods: {
		// 获取当前点击的item
		clickItem(item) {
			this.item = item;
		},
		// 深拷贝编辑后的数据，防止v-model实时修改页面数据
		edit(item) {
			this.editItem = JSON.parse(JSON.stringify(item));
		},
		// 修改当前页码
		countClick(count) {
			this.count = count;
		},
		// 保存数据
		save() {
			// 判断当前用户是否有ID
			for (let i = 0; i < this.datas.length; i++) {
				if (this.datas[i].userName === this.editItem.userName) {
					this.editItem.ID = parseInt(this.datas[i].ID);
				}
			}
			this.addFlag = false;
			// 输入数据的正确性检测
			if (!this.editItem.userName || !this.editItem.age || !this.editItem.school) {
				this.alertMsg = '用户名、年龄或毕业学校不能为空！';
				this.alertClass = this.failClass;
				this.alertFlag = true;
				clearInterval(this.timer);
				this.timer = setInterval(() => {
					this.alertFlag = false;
				}, 2000);
			} else {
				for (let i = 0; i < this.datas.length; i++) {
					// 判断用户是否是点击编辑修改的，是则更新该用户信息
					if (this.editItem.ID && this.editItem.ID === this.datas[i].ID) {
						temp = JSON.parse(JSON.stringify(this.datas));
						temp.splice(i, 1, this.editItem);
						this.datas = JSON.parse(JSON.stringify(temp));
						this.addFlag = true;
						break;
					// 判断用户输入的用户名是否存在，存在则更新该用户信息
					} else if (this.datas[i].userName === this.editItem.userName) {
						temp = JSON.parse(JSON.stringify(this.datas));
						temp.splice(i, 1, this.editItem);
						this.datas = JSON.parse(JSON.stringify(temp));
						this.addFlag = true;
						break;
					}
				}
				// 判断是否是已存在的用户，不存在则添加新用户
				if (!this.addFlag) {
					this.editItem.ID = parseInt(this.datas[this.datas.length - 1].ID) + 1;
					this.datas.push(this.editItem);
				}
				// 调用提示信息弹出框
				this.alertMsg = '保存成功！';
				this.alertClass = this.successClass;
				this.alertFlag = true;
				clearInterval(this.timer);
				this.timer = setInterval(() => {
					this.alertFlag = false;
				}, 2000);
				// 重置输入框信息
				this.editItem = {
					ID: '',
					userName: '',
					age: '',
					school: '',
					remark: ''
				}
			}
		},
		// 分页控制
		pageBarContral() {
			this.preFlag = true;
			this.nexFlag = true;
			if (this.count - 1 === 0) {
				this.preFlag = false;
			} 
			if (this.count + 1 > this.totalPage) {
				this.nexFlag = false;
			}
			if (this.datas.length === 0) {
				this.nexFlag = false;
				this.preFlag = false;
			}
		},
		// 上一页
		prePage() {
			this.count--;
		},
		// 下一页
		nexPage() {
			this.count++;
		},
		// 删除用户
		deleteStu() {
			for (let i = 0; i < this.datas.length; i++) {
				if (this.datas[i].ID === this.item.ID) {
					this.datas.splice(i, 1);
				}
			}
			let trs = document.getElementsByClassName('flag').length;
			if (trs - 1 === 0) {
				this.count--;
			}
		}
	},
	computed: {
		// 动态获取当前页码的学生信息
		studentsList() {
			return this.datas.slice((this.count - 1) * this.pageSize, (this.count) * this.pageSize);
		},
		// 实时计算总页数
		totalPage() {
			if (Math.ceil(this.datas.length / this.pageSize) == 0) {
				this.count = 1;
				return 1;
			} else {
				return Math.ceil(this.datas.length / this.pageSize);
			}
		}
	}
})
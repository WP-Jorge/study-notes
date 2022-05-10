// 封装链表类
class LinkedList {
	#head = null
	#length = 0

	get length() {
		return this.#length
	}
	get head() {
		return this.#head
	}

	// 1.append(data) :向列表尾部添加个新的项
	append(data) {
		// ① 创建新节点
		let node = new LinkedList.Node(data)
		// ② 判断是否时添加的第一个节点
		if (this.#length === 0) {
			// 是就插入
			this.#head = node
		} else {
			// 不是则查找最后一个节点
			let current = this.#head
			while (current.next !== null) {
				current = current.next
			}
			// 插入最后一个节点
			current.next = node
		}
		// ③ 链表长度 + 1
		this.#length++
	}

	// 2.insert(position, data) :向列表的特定位置插入一个新的项
	insert(position, data) {
		// ① 做越界判断
		if (position < 0 || position > this.#length) {
			return false
		}

		// ② 根据data创建node
		let node = new LinkedList.Node(data)

		// ③ 判断插入的位置是否是第一个
		if (position == 0) {
			node.next = this.#head
			this.#head = node
		} else {
			let index = 0
			let previous = null
			let current = this.#head
			while (index++ < position) {
				previous = current
				current = current.next
			}
			node.next = current
			previous.next = node
		}

		// ④ length + 1
		this.#length++
		return true
	}

	// 3.get(position) :获取对应位置的元素
	get(position) {
		// ① 越界判断
		if (position < 0 || position >= this.#length) {
			return null
		}

		// ② 获取对应的data
		let current = this.#head
		let index = 0
		while (index++ < position) {
			current = current.next
		}
		return current.data
	}

	// 4.indexOf(data) :返回元素在列表中的索引。如果列表中没有该元素则返回-1
	indexOf(data) {
		// ① 定义变量
		let current = this.#head
		let index = 0
		while (current) {
			if (current.data === data) {
				return index
			}
			current = current.next
			index++
		}
		if (index >= this.#length) {
			return -1
		}
		return index
	}

	// 5.update(position, data) :修改某个位置的元素
	update(position, data) {
		// ① 越界判断
		if (position < 0 || position >= this.#length) {
			return false
		}

		// ② 查找正确的节点
		let current = this.#head
		let index = 0
		while (index++ < position) {
			current = current.next
		}

		// ③ 更新position位置上的data
		current.data = data
		return true
	}

	// 6.removeAt(position) :从列表的特定位置移除项
	removeAt(position) {
		// ① 判断是否越界
		if (position < 0 || position >= this.#length) {
			return false
		}

		// ② position是否是第一个节点
		if (position === 0) {
			this.#head = this.#head.next
		} else {
			let current = this.#head
			let previous = null
			let index = 0
			while (index++ < position) {
				previous = current
				current = current.next
			}
			// ④ 前一个节点指向current的next
			previous.next = current.next
		}

		// ⑤ length--
		this.#length--
		return true
	}

	// 7.remove(data) :从列表中移除一项
	remove(data) {
		// ① 获取data在链表中的位置
		let position = this.indexOf(data)

		// ② 根据位置信息删除节点
		return this.removeAt(position)
	}

	// 8.isEmpty() :如果链表中不包含任何元素， 返回true，如果链表长度大于0则返回false
	isEmpty() {
		return this.#length === 0 ? true : false
	}

	// 9.size() :返回链表包含的元素个数。与数组的length属性类似
	size() {
		return this.#length
	}

	// 10.toString() :由于列表项使用了Node类,就需要重写继承自JavaScript对象默认的toString方法,让其只输出元素的值
	toString() {
		// ① 定义变量
		let current = this.#head
		let listString = ''

		// ② 循环获取一个个的节点
		while (current !== null) {
			listString += current.data + ' '
			current = current.next
		}
		return listString
	}
}

// 内部类：节点类
LinkedList.Node = class {
	#data
	#next
	constructor(data) {
		this.#data = data
		this.#next = null
	}
	get data() {
		return this.#data
	}
	get next() {
		return this.#next
	}
	set next(value) {
		this.#next = value
	}
	set data(value) {
		this.#data = value
	}
}

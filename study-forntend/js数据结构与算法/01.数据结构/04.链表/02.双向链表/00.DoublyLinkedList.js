// 封装双向链表
class DoublyLinkedList {
	// 属性
	#head = null
	#tail = null
	#length = 0

	// 1.append(data) :向列表尾部添加一个新的项
	append(data) {
		// ① 创建deta节点
		let node = new DoublyLinkedList.Node(data)

		// ② 判断是否添加的是第一个节点
		if (this.#length === 0) {
			this.#head = node
			this.#tail = node
		} else {
			node.prev = this.#tail
			this.#tail.next = node
			this.#tail = node
		}
		// ③ length++
		this.#length++
	}

	// 2.insert(position, data) :向列表的特定位置插入一个新的项
	insert(position, data) {
		// ① 判断越界
		if (position < 0 || position > this.#length) {
			return false
		}

		// ② 根据data创建新节点
		let node = new DoublyLinkedList.Node(data)

		// ③ 判断原来的链表是否为空
		if (this.#length === 0) {
			this.#tail = node
			this.#head = node
		} else {
			// ③ 判断position是否位0
			if (position === 0) {
				this.#head.prev = node
				node.next = this.#head
				this.#head = node
				// ④ 判断position是否是最后一个
			} else if (position === this.#length) {
				node.prev = this.#tail
				this.#tail.next = node
				this.#tail = node
			} else {
				// ⑤ 中间插入
				let current = this.#head
				let index = 0
				while (index++ < position) {
					current = current.next
				}
				node.prev = current.prev
				node.next = current
				current.prev.next = node
				current.prev = node
			}
		}

		// ⑥ length++
		this.#length++
		return true
	}

	// 3.get(position) :获取对应位置的元素
	get(position) {
		// ① 判断越界
		if (position < 0 || position >= this.#length) {
			return null
		}

		let current
		// 判断元素节点离头部近还是离尾部近
		// ② 离头部近，从头到尾遍历
		if (this.#length > position * 2) {
			current = this.#head
			let index = 0
			while (index++ < position) {
				current = current.next
			}
			// ④ 离尾部近，从尾部开始遍历
		} else {
			current = this.#tail
			let index = this.#length - 1
			while (index-- > position) {
				current = current.prev
			}
		}
		return current.data
	}

	// 4.indexOf(data) :返回元素在列表中的索引。如果列表中没有该元素则返回-1
	indexOf(data) {
		let current = this.#head
		let index = 0
		while (current) {
			if (current.data === data) {
				return index
			}
			current = current.next
			index++
		}
		return -1
	}

	// 5.update(position, data) :修改某个位置的元素
	update(position, data) {
		// ① 判断position是否越界
		if (position < 0 || position >= this.#length) {
			return false
		}

		// 寻找正确的节点
		// 判断元素节点离头部近还是离尾部近
		let current
		// ② 离头部近，从头到尾遍历
		if (this.#length > position * 2) {
			current = this.#head
			let index = 0
			while (index++ < position) {
				current = current.next
			}
			// ④ 离尾部近，从尾部开始遍历
		} else {
			current = this.#tail
			let index = this.#length - 1
			while (index-- > position) {
				current = current.prev
			}
		}
		current.data = data
		return true
	}

	// 6.removeAt(position) :从列表的特定位置移除一项
	removeAt(position) {
		// ① 判断是否越界
		if (position < 0 || position >= this.#length) {
			return false
		}

		// ② 判断是否只有一个节点
		if (this.#length === 1) {
			this.#head = null
			this.#tail = null
		} else {
			// ③判断是否删除的是第一个节点
			if (position === 0) {
				this.#head.next.prev = null
				this.#head = this.#head.next
				// ④ 删除的是否指最后一个节点
			} else if (position === this.#length - 1) {
				this.#tail = this.#tail.prev
				this.#tail.next = null
			} else {
				// ⑤ 在中间
				let index = 0
				let current = this.#head
				while (index++ < position) {
					current = current.next
				}
				current.prev.next = current.next
				current.next.prev = current.prev
			}
		}

		// ⑥ length--
		this.#length--
		return true
	}

	// 7.remove(data) :从列表中移除一项
	remove(data) {
		let index = this.indexOf(data)
		return this.removeAt(index)
	}

	// 8.isEmpty() :如果链表中不包含任何元素,返回true , 如果链表长度大于0则返回false
	isEmpty() {
		return this.#length === 0 ? true : false
	}

	// 9.size() :返回链表包含的元素个数。与数组的length属性类似
	size() {
		return this.#length
	}

	// 10.toString() :由于列表项使用了Node类，就需要重写继承自JavaScript对象默认的toString方法，让其只输出元素的值
	toString() {
		return this.backwordString()
	}

	// 11.forwardString() :返回正向遍历的节点字符串形式
	forwardString() {
		// ① 定义变量
		let current = this.#tail
		let resultString = ''

		// ② 一次向前遍历获取每一个节点
		while (current) {
			resultString += current.data + ' '
			current = current.prev
		}
		return resultString
	}

	// 12.backwordString() :返回反向遍历的节点字符串形式
	backwordString() {
		// ① 定义变量
		let current = this.#head
		let resultString = ''

		// ② 依次向后遍历，获取每一个节点
		while (current) {
			resultString += current.data + ' '
			current = current.next
		}
		return resultString
	}

	// 13.获取链表第一个元素
	getFirst() {
		return this.#head.data
	}

	// 14.获取链表最后一个元素
	getLast() {
		return this.#tail.data
	}
}

// 内部类
DoublyLinkedList.Node = class {
	#data
	#prev
	#next
	constructor(data) {
		this.#data = data
		this.#prev = null
		this.#next = null
	}
	get data() {
		return this.#data
	}
	get prev() {
		return this.#prev
	}
	get next() {
		return this.#next
	}
	set data(data) {
		this.#data = data
	}
	set prev(prev) {
		this.#prev = prev
	}
	set next(next) {
		this.#next = next
	}
}

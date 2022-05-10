// 封装优先级队列
class PriorityQueue {
	#items = []
	// 1.向队列尾部添加一个(或多个)新的项
	enqueue(item, priority) {
		// ① 创建QueueElement对象
		let queueItem = new PriorityQueue.QueueItem(item, priority)
		// ② 判断队列是否为空
		if (this.#items.length === 0) {
			this.#items.push(queueItem)
		} else {
			let isAdded = false
			for (let i = 0; i < this.#items.length; i++) {
				if (queueItem.priority < this.#items[i].priority) {
					this.#items.splice(i, 0, queueItem)
					isAdded = true
					break
				}
			}
			if (!isAdded) {
				this.#items.push(queueItem)
			}
		}
	}

	// 2.dequeue() :移除队列的第一(即排在队列最前面的)项,并返回被移除的元素
	dequeue() {
		return this.#items.shift()
	}

	// 3.front() :返回队列中第一个元素最先被添加 ，也将是最先被移除的元素。队列不做任何变动(不移除元素，只返回元素信息一与Stack类的peek方法非常类似)
	front() {
		return this.#items[0]
	}

	// 4.isEmpty() :如果队列中不包含任何元素，返回true ,否则返回false
	isEmpty() {
		return this.#items.length === 0
	}

	// 5.size() :返回队列包含的元素个数，与数组的ength属性类似
	size() {
		return this.#items.length
	}

	// 6.toString() :将队列中的内容，转成字符串形式
	toString() {
		console.log(this.#items)
		let resString = ''
		for (const item of this.#items) {
			resString += item.item + '-' + item.priority + '\n'
		}
		return resString
	}
}
// 创建内部类
PriorityQueue.QueueItem = class {
	#item
	#priority
	constructor(item, priority) {
		this.#item = item
		this.#priority = priority
	}
	get priority() {
		return this.#priority
	}
	get item() {
		return this.#item
	}
}

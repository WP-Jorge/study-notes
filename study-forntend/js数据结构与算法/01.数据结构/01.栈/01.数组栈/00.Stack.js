// 封装栈类
class Stack {
	// 栈中的属性
	#items = []

	// 栈的基本操作
	// 1.push(element):添加一个新元素到栈顶位置
	push(item) {
		this.#items.push(item)
	}

	// 2.pop() :移除栈顶的元素，同时返回被移除的元素
	pop() {
		return this.#items.pop()
	}

	// 3.peek() :返回栈顶的元素，不对栈做任何修改(这个方法不会移除栈顶的元素，仅仅返回它)
	peek() {
		return this.#items[this.#items.length - 1]
	}

	// 4.isEmpty() :如果栈里没有任何元素就返回true ,否则返回false
	isEmpty() {
		return this.#items.length === 0
	}

	// 5.size() :返回栈里的元素个数。这个方法和数组的length属性很类似
	size() {
		return this.#items.length
	}

	// 6.toString() :将栈结构的内容以字符形式返回
	toString() {
		return this.#items.reverse().join(' ')
	}
}

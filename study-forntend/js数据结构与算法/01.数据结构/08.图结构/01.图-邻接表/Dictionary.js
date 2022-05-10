// 字典的封装
class Dictionary {
	// 字典属性
	#items = {}

	// 字典操作方法
	// 1.添加键值对
	set(key, value) {
		this.#items[key] = value
	}

	// 2.判断字典中是否拥有某个key
	has(key) {
		return this.#items.hasOwnProperty(key)
	}

	// 3.删除元素
	remove(key) {
		if (!this.has(key)) {
			return false
		}
		delete this.#items[key]
		return true
	}

	// 4.根据key获取元素的值
	get(key) {
		return this.has(key) ? this.#items[key] : undefined
	}

	// 5.获取虽有keys
	keys() {
		return Object.keys(this.#items)
	}

	// 6.获取所有values
	values() {
		return Object.values(this.#items)
	}

	// 7.获取所有键值对
	entries() {
		return Object.entries(this.#items)
	}

	// 8.清空字典
	clear() {
		for (const key in this.#items) {
			if (Object.hasOwnProperty.call(this.#items, key)) {
				delete this.#items[key]
			}
		}
	}
}

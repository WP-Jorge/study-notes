// 封装哈希表类
class HashTable {
	// 属性
	// 所有的元素
	#storage = []
	// 元素个数
	#count = 0
	// 数组长度
	#limit = 7

	// 方法
	// 1.设计哈希函数
	// 1) 将字符串转换为比较大的数：hashcode
	// 2）将较大的数压缩到数组范围之内
	#hashFunc(str, size) {
		// ① 定义hashcode变量
		let hashCode = 0

		// ② 霍纳算法，来计算hashCode的值
		for (let i = 0; i < str.length; i++) {
			hashCode = hashCode * 37 + str.charCodeAt(i)
		}

		// ③ 取余操作
		let index = hashCode % size
		return index
	}

	// 2.插入 修改操作
	put(key, value) {
		// ① 根据key获取对应的index
		let index = this.#hashFunc(key, this.#limit)

		// ② 根据index取出对应的bucket
		let bucket = this.#storage[index]

		// ③ 判断bucket是否为null
		if (!bucket) {
			bucket = []
			this.#storage[index] = bucket
		}

		// ④ 判断是否是修改数据
		for (let i = 0; i < bucket.length; i++) {
			const tuple = bucket[i]
			if (tuple[0] === key) {
				tuple[1] = value
				return
			}
		}

		// ⑤ 进行添加操作
		bucket.push([key, value])
		this.#count++

		// ⑥ 判断是否需要扩容
		if (this.#count > this.#limit * 0.75) {
			let newSize = this.#limit * 2
			let newPeime = this.#getPrime(newSize)
			this.#resize(newPeime)
		}
	}

	// 3.获取操作
	get(key) {
		// ① 根据key获取对应的index
		let index = this.#hashFunc(key, this.#limit)

		// ② 根据index获取对应的bucket
		let bucket = this.#storage[index]

		// ③ 判断bucket是否为空
		if (!bucket || bucket.length === 0) {
			return null
		}

		// ④ 有bucket，且长度不为零，进行现性查找
		for (let i = 0; i < bucket.length; i++) {
			const tuple = bucket[i]
			if (tuple[0] === key) {
				return tuple[1]
			}
		}

		// ⑤ 依然没有找到
		return null
	}

	// 4.删除操作
	remove(key) {
		// ① 根据key获取对应的index
		let index = this.#hashFunc(key, this.#limit)

		// ② 根据index获取对应的bucket
		let bucket = this.#storage[index]

		// ③ 判断bucket是否为空
		if (bucket === null || bucket.length === 0) {
			return null
		}

		// ④ 有bucket，且长度不为零，进行现性查找
		for (let i = 0; i < bucket.length; i++) {
			const tuple = bucket[i]
			if (tuple[0] === key) {
				bucket.splice(i, 1)
				this.#count--

				// 缩小容量
				if (this.#limit > 7 && this.#count < this.#limit * 0.25) {
					let newSize = Math.floor(this.#limit / 2)
					let newPrime = this.#getPrime(newSize)
					this.#resize(newPrime)
				}
				return tuple[1]
			}
		}

		// ⑤ 依然没有找到
		return null
	}

	// 5.判断哈希表是否为空
	isEmpty() {
		return this.#count === 0
	}

	// 6.获取哈希表的元素个数
	size() {
		return this.#count
	}

	// 7.哈希表扩容
	#resize(newLimit) {
		// ① 保存旧的数组内容
		let oldStorage = this.#storage

		// ② 重置所有属性
		this.#storage = []
		this.#count = 0
		this.#limit = newLimit

		// ③ 遍历oldStorage中所有的bucket
		for (let i = 0; i < oldStorage.length; i++) {
			const bucket = oldStorage[i]
			if (!bucket) {
				continue
			}
			for (let j = 0; j < bucket.length; j++) {
				const tuple = bucket[j]
				this.put(tuple[0], tuple[1])
			}
		}
		console.log('容量变化至：' + this.#limit)
	}

	// 8.判断是否是质数
	#isPrime(num) {
		if (num < 2) {
			return false
		}
		// 1.获取num的平方根
		let temp = parseInt(Math.sqrt(num))
		for (let i = 2; i <= temp; i++) {
			if (num % i === 0) {
				return false
			}
		}
		return true
	}

	// 9.获取质数的方法
	#getPrime(num) {
		while (!this.#isPrime(num)) {
			num++
		}
		return num
	}
}

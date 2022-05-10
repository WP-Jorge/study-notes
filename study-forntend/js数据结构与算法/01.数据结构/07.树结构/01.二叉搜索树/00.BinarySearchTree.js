// 封装二叉搜索树
class BinarySearchTree {
	// 属性
	#root = null

	// 方法
	// 1.insert(key) :向树中插入一个新的键。
	insert(key) {
		// ① 根据key创建节点
		let node = new BinarySearchTree.Node(key)

		// ② 判断根节点是否有值
		if (this.#root === null) {
			this.#root = node
		} else {
			this.#insertNode(this.#root, node)
		}
	}

	#insertNode(node, newNode) {
		// 向左查找
		if (newNode.key < node.key) {
			if (node.left === null) {
				node.left = newNode
			} else {
				this.#insertNode(node.left, newNode)
			}
			// 向右查找
		} else {
			if (node.right === null) {
				node.right = newNode
			} else {
				this.#insertNode(node.right, newNode)
			}
		}
	}

	// 2.search(key) :在树中查找一个键，如果节点存在,则返回true ;如果不存在，则返回false
	// 非递归
	// search(key) {
	// 	// ① 获取根节点
	// 	let node = this.#root

	// 	// ② 循环搜索key
	// 	while (node) {
	// 		if (key < node.key) {
	// 			node = node.left
	// 		} else if (key > node.key) {
	// 			node = node.right
	// 		} else {
	// 			return true
	// 		}
	// 	}
	// 	return false
	// }

	// 递归
	search(key) {
		let node = this.#root
		return this.#searchNode(node, key)
	}

	#searchNode(node, key) {
		if (!node) {
			return false
		}
		if (key < node.key) {
			return this.#searchNode(node.left, key)
		} else if (key > node.key) {
			return this.#searchNode(node.right, key)
		} else {
			return true
		}
	}

	// 3.inOrderTraversal :通过中序遍历方式遍历所有节点
	inOrderTraversal(handle) {
		this.#inOrderTraversalNode(this.#root, handle)
	}

	#inOrderTraversalNode(node, handler) {
		if (node !== null) {
			// ① 处理经过节点的左子节点
			this.#inOrderTraversalNode(node.left, handler)

			// ② 处理经过的节点
			handler(node.key)

			// ③ 处理经过节点的右子节点
			this.#inOrderTraversalNode(node.right, handler)
		}
	}

	// 4.preOrderTraversal :通过先序遍历方式遍历所有节点
	preOrderTraversal(handle) {
		this.#preOrderTraversalNode(this.#root, handle)
	}

	#preOrderTraversalNode(node, handler) {
		if (node !== null) {
			// ① 处理经过的节点
			handler(node.key)

			// ② 处理经过节点的左子节点
			this.#preOrderTraversalNode(node.left, handler)

			// ③ 处理经过节点的右子节点
			this.#preOrderTraversalNode(node.right, handler)
		}
	}

	// 5.postOrderTraversal :通过后序遍历方式遍历所有节点
	postOrderTraversal(handle) {
		this.#postOrderTraversalNode(this.#root, handle)
	}

	#postOrderTraversalNode(node, handler) {
		if (node !== null) {
			// ① 处理经过节点的左子节点
			this.#postOrderTraversalNode(node.left, handler)

			// ② 处理经过节点的右子节点
			this.#postOrderTraversalNode(node.right, handler)

			// ③ 处理经过的节点
			handler(node.key)
		}
	}

	// 6.min :返回树中最小的值/键
	min() {
		// ① 获取根节点
		let node = this.#root
		let key = null

		// ② 获取最左边的叶子
		while (node.left) {
			key = node.key
			node = node.left
		}
		return key
	}

	// 7.max :返回树中最大的值/键
	max() {
		// ① 获取根节点
		let node = this.#root
		let key = null

		// ② 获取最右边的叶子
		while (node.right) {
			key = node.key
			node = node.right
		}
		return key
	}

	// 8.remove(key) :从树中移除某个键
	remove(key) {
		// ① 寻找要删除的节点
		// 当前节点
		let current = this.#root
		// 当前节点的父节点
		let parent = null
		// 是否是当前父节点的左孩子
		let isLeftChild = true

		// ② 开始寻找要删除的节点
		while (current.key != key) {
			parent = current
			if (key < current.key) {
				isLeftChild = true
				current = current.left
			} else {
				isLeftChild = false
				current = current.right
			}
			// 未找到
			if (!current) {
				return false
			}
		}

		// ③ 找到了current.key === key
		// 删除的节点是叶子节点
		if (current.left === null && current.right === null) {
			if (current === this.#root) {
				this.#root = null
			} else if (isLeftChild) {
				parent.left = null
			} else {
				parent.right = null
			}
			return true
		}

		// 删除的节点只有一个子节点
		else if (current.right === null) {
			if (current === this.#root) {
				this.#root = current.left
			} else if (isLeftChild) {
				parent.left = current.left
			} else {
				parent.right = current.left
			}
			return true
		} else if (current.left === null) {
			if (current === this.#root) {
				this.#root = current.right
			} else if (isLeftChild) {
				parent.left = current.right
			} else {
				parent.right = current.right
			}
			return true
		}

		// 删除的节点右两个子节点
		else {
			// ① 获取后继节点
			let successor = this.#getSuccessor(current)

			// ② 判断是否是根节点
			if (current === this.#root) {
				this.#root = successor
			} else if (isLeftChild) {
				parent.left = successor
			} else {
				parent.right = successor
			}

			// ③ 将删除的节点的左子树 = current.left
			successor.left = current.left
			return true
		}
		return false
	}

	// 找后继的方法
	#getSuccessor(delNode) {
		// ① 定义变量，保存找到的后继
		let successor = delNode
		let current = delNode.right
		let successorParent = delNode

		// ② 循环
		while (current) {
			successorParent = successor
			successor = current
			current = current.left
		}

		// ③ 判断寻找到的后继节点是否就是delNode的right节点
		if (successor !== delNode.right) {
			successorParent.left = successor.right
			successor.right = delNode.right
		}
		return successor
	}
}

// 封装内部类
BinarySearchTree.Node = class {
	// 属性
	#key
	#left
	#right

	constructor(key) {
		this.#key = key
		this.#left = null
		this.#right = null
	}

	get key() {
		return this.#key
	}
	get left() {
		return this.#left
	}
	get right() {
		return this.#right
	}

	set key(key) {
		this.#key = key
	}
	set left(left) {
		this.#left = left
	}
	set right(right) {
		this.#right = right
	}
}

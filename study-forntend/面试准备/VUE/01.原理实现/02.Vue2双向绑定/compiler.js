import Watcher from './watcher.js';

export default class Compiler {
	constructor(vm) {
		this.el = vm.$el;
		this.vm = vm;
		this.methods = vm.$methods;
		this.compiler(vm.$el);
	}

	/** 编译模板 */
	compiler(el) {
		const childNodes = el.childNodes;
		Array.from(childNodes).forEach(node => {
			// 文本节点
			if (this.isTextNode(node)) {
				this.compilerText(node);
			} else if (this.isElementNode(node)) {
				// 元素节点
				this.compilerElement(node);
			}
			// 有子节点，递归调用
			if (node.childNodes && node.childNodes.length > 0) {
				this.compiler(node);
			}
		});
	}

	/** 判断文本节点 */
	isTextNode(node) {
		return node.nodeType === 3;
	}

	/** 判断元素节点 */
	isElementNode(node) {
		return node.nodeType === 1;
	}

	/** 编译文本节点 */
	compilerText(node) {
		// {{ msg }}
		const reg = /\{\{(.+?)\}\}/;
		const value = node.textContent;
		let res = '';
		if (res = reg.exec(value)) {
			const key = res[1].trim();
			node.textContent = value.replace(reg, this.vm[key]);
			new Watcher(this.vm, key, newValue => {
				node.textContent = newValue;
			});
		}
	}

	/** 编译元素节点 */
	compilerElement(node) {
		if (node.attributes.length) {
			Array.from(node.attributes).forEach(attr => {
				// 遍历元素节点的所有属性
				const attrName = attr.name; // v-html v-model="msg" v-on:click
				if (this.isDriective(attrName)) {
					let directiveName = attrName.includes(':') ? attrName.substring(5) : attrName.substring(2);
					let key = attr.value; // msg
					this.update(node, key, directiveName);
				}
			});
		}
	}

	/** 判断元素属性是否是 vue 指令 */
	isDriective(attrName) {
		// v- 开头的
		return attrName.startsWith('v-');
	}

	/** 更新元素节点 */
	update(node, key, directiveName) {
		// v-on:click, v-html, v-model
		const updateFn = this[directiveName + 'Updater'];
		updateFn && updateFn.call(this, node, this.vm[key], key, directiveName);
	}

	/** 解析 v-text */
	textUpdater(node, value, key) {
		node.textContent = value;
		new Watcher(this.vm, key, newValue => {
			node.textContent = newValue;
		});
	}

	/** 解析 v-model */
	modelUpdater(node, value, key) {
		node.value = value;
		new Watcher(this.vm, key, newValue => {
			node.value = newValue;
		});
		node.addEventListener('input', () => {
			this.vm[key] = node.value;
		});
	}

	/** 解析 v-html */
	htmlUpdater(node, value, key) {
		node.innerHTML = value;
		new Watcher(this.vm, key, newValue => {
			node.innerHTML = newValue;
		});
	}

	/** 解析 v-on */
	clickUpdater(node, value, key, directiveName) {
		node.addEventListener(directiveName, this.methods[key].bind(this.vm));
	}
}
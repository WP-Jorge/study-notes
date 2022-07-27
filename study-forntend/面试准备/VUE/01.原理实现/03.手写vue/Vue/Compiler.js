import Watcher from './Watcher.js';

/** HTML 指令解析器，对每个元素节点的指令进行扫描和解析，根据指令模板替换数据，以及绑定相应的更新函数 */
export default class Compiler {
	constructor(vm) {
		this.el = vm.$el;
		this.vm = vm;
		this.methods = vm.$methods;
		this.compiler(vm.$el);
	}

	/** 编译模板 */
	compiler(el) {
		const childNodes = [...el.childNodes];
		childNodes.forEach(node => {
			if (this.isTextNode(node)) {
				// 文本节点
				this.compileText(node);
			} else if (this.isElementNode(node)) {
				// 元素节点
				this.compileElement(node);
			}
			// 有子节点，递归调用
			if (node.childNodes && node.childNodes.length) {
				this.compiler(node);
			}
		});
	}

	/** 编译文本节点 */
	compileText(node) {
		const reg = /\{\{(.+?)\}\}/;
		const value = node.textContent;
		if (reg.test(value)) {
			const key = reg.exec(value)[1].trim();
			node.textContent = value.replace(reg, this.vm[key]);

			new Watcher(
				this.vm,
				key,
				newValue => (node.textContent = newValue)
			);
		}
	}

	/** 编译元素节点 */
	compileElement(node) {
		if (node.attributes.length) {
			[...node.attributes].forEach(attr => {
				// 遍历所有元素节点的属性
				const attrName = attr.name; // v-html v-model
				if (this.isDirective(attrName)) {
					let directiveName =
						attrName.indexOf(':') > -1
							? attrName.substring(5)
							: attrName.substring(2);
					let key = attr.value; // msg
					this.update(node, key, directiveName);
				}
			});
		}
	}

	update(node, key, directiveName) {
		const updateFn = this[directiveName + 'Updater'];
		updateFn && updateFn.call(this, node, this.vm[key], key, directiveName);
	}

	/** 解析 v-text */
	textUpdater(node, value, key) {
		node.textContent = value;
		new Watcher(this.vm, key, newValue => (node.textContent = newValue));
	}

	/** 解析 v-model */
	modelUpdater(node, value, key) {
		node.value = value;
		new Watcher(this.vm, key, newValue => (node.value = newValue));
		node.addEventListener('input', e => {
			this.vm[key] = node.value;
		});
	}

	/** 解析 v-html */
	htmlUpdater(node, value, key) {
		node.innerHTML = value;
		new Watcher(this.vm, key, newValue => (node.innerHTML = newValue));
	}

	/** 解析 v-on:click */
	clickUpdater(node, value, key, directiveName) {
		node.addEventListener(directiveName, this.methods[key]);
	}

	/** 判断是否是文本节点 */
	isTextNode(node) {
		return node.nodeType === 3;
	}

	/** 判断是否是元素节点 */
	isElementNode(node) {
		return node.nodeType === 1;
	}

	/** 判断元素属性是否是指令 */
	isDirective(attrName) {
		return attrName.startsWith('v-');
	}
}

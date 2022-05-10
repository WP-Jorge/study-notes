- Vue2.0 原理
```text
1、Vue 中的三个核心类
	(1) Observer：给对象的属性添加 getter、setter，用于依赖收集和派发更新
	(2) Dep：用于收集当前响应式对象的依赖关系，每个响应式对象都有一个 Dep 实例
		① dep.subs = watcher[]
		② 当数据更新时会通过 dep.notify() 通知各个 watcher
	(3) Watcher：观察者对象
		① render watcher
		② computed watcher
		③ user watcher
2、依赖收集
	(1) initState，对 computed 属性初始化时，会触发 computed watcher 依赖收集
	(2) initState，监听属性初始化的时候，触发 user watcher 依赖收集
	(3) render，触发 render watcher 依赖收集
3、派发更新
	(1) 组件中对响应式数据进行了修改，会触发 setter 逻辑
	(2) dep.notify()
	(3) 遍历所有的 subs，调用一个 watcher 的 update 方法
4、总结原理
	(1) 当创建 Vue 实例时， Vue 会遍历 data 里的属性，Object.defineProperty 为属性添加 getter 和 setter 对属性的读取进行劫持
	(2) getter：依赖收集
	(3) setter：派发更新
	(4) 每个组件实例都会有对应的 watcher 实例
5、目录结构
	(1) index.html 主页面
	(2) vue.js 主文件
	(3) compiler.js 编译模板，解析指令，如 v-model、v-html
	(4) dep.js 收集依赖关系，存储观察者
	(5) observer.js 数据劫持
	(6) watcher.js 观察者对象类
```
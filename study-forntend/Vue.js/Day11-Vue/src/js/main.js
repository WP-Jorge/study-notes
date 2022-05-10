//1.1、使用Vue.extend来创建全局的Vue组件
// var com1 = Vue.extend({
// 	template:'<h3>这是通过Vue.extend来创建全局的Vue组件</h3>' //通过template属性，指定html结构
// })
//1.2、使用Vue.component('组件的名称',创建出来的组件模板com1)
// Vue.component('myCom1',com1)

//2、第一个参数：组件的名称，将来引用组件时，以标签的形式引入
//第二个参数：Vue.extend 创建的组件，里面的template就是组件要展示的内容
// Vue.component('myCom2',Vue.extend({
// 	template:'<h3>这是通过Vue.component来创建全局的Vue组件</h3>' //通过template属性，指定html结构
// }))

//3、省略extend，直接用{}代替extend({})
// Vue.component('myCom3',{
// 	//template中的' '不能有两个并集标签，只能通过一个大的<div>来包裹住其他标签
// 	// template:'<h3>这是通过Vue.component并省略extend来创建全局的Vue组件</h3>'
// 	template:'<div><h3>这是通过Vue.component并省略extend来创建全局的Vue组件</h3><span>啦啦啦啦啦</span></div>'
// })

// 4、将模板组件对象分离出来的形式创建component
var myLogin={
	template:'<h3>啦啦啦</h3>'
}
// Vue.component的作用相当于帮myLogin取了个名字，可以放在html中当标签使用
// Vue.component('myCom4',myLogin)

//5、通过在div01外部使用template元素标签，定义html模板结构
// Vue.component('myCom5',{
// 	template:'#temp'
// })

var nv =new Vue({
	el:'#div01',
	data:{
		
	},
	components:{
		//4中的组件模板对象可在此简写成组件模板对象的名字
		myLogin
	}
})

var nv2 =new Vue({
	el:'#div02',
	data:{
		
	},
	components:{ //存放私有组件
		login:{
			template:'#temp2'
		}
	}
})
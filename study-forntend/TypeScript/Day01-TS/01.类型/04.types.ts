// 1.object 表示一个js对象
let a: object
a = {}
a = function () {
    
}

// 2.{}指定对象中包含哪些属性
// 语法：{属性名: 属性值, 属性名: 属性值}
let b: {name: string, age?: number} // age?表示属性是可选的
b = {name: 'hello'}

let c: {name: string, [propName: string]: any} // 添加上这个[propName: string]: any可以有任意个任意类型的参数 表示任意字符串的属性名
c = {name: '111', age: 18, gender: '男'}

// 表示b是一个函数，有两个参数都是number返回值也是number
/**
 * 语法：（形参: 类型, 形参: 类型...） => 返回值
 */
let d: (a: number, b: number) => number
d = function (n1, n2) {
    return n1 + n2
}

// 3.string[] 表示字符串数组
let e: string[]
e = ['a', 'b', 'c']

let g: Array<number>
g = [1, 2, 3]

/**
 * 4.元组：固定长度的数组
 */
let h: [string, string]
h = ['hello', 'abc']

// 5.枚举
enum Gender {
    male,
    female = 1
}

let i: {name: string, gender: Gender}
i = {
    name: '王伟',
    gender: Gender.male
}

// 6.&表示同时
let j: {name: string} & {age: number}
j = {name: '张三', age: 18}

// 7.类型的别名
type myType = 1 | 2 | 3 | 4 | 5
let k: myType
let l: myType
let m: myType

k = 5

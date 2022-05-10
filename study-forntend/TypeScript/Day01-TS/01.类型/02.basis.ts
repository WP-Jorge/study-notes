// 申明一个变量，同时指定他的类型为number
let a: number

// a的类型设置了number， 在以后的使用过程中a的值只能是数字
a = 10
a = 33

// a = 'hello' // 此行代码会报错，因为a的类型是number，不能复制字符串

// 如果变量的申明和赋值是同时进行的，TS可以自动的对变量进行类型检测
// 申明变量直接赋值
let c: boolean = false

// 函数返回值
function sum(a: number, b: number): number {
    return a + b;
}
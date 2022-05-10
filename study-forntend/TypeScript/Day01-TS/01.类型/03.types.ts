// 1.可以直接使用字面量进行类型的声明
let a: 10
a = 10
// a = 11 // 报错，a只能为10

// 2.使用 | 来赋值 表示或(联合类型)
let b: 'male' | 'female'
b = 'male'
b = 'female'

let c: boolean | string | number
c = true
c = '111'

// 3.任何类型 相当于这个变量关闭了TS的类型检测 （不建议使用）
// 不写类型也是any
let d: any
d = 1
d = '11'
d = true

// 4.nuknow 表示未知类型
let e: unknown
e = 10
e = 'hello'
e = true
let s:string
// d的类型是any，它可以赋值给任意变量，不会报错
s = d
// 但是unknown不能给他人赋值
// s = e // 报错

// unknown 实际上就是一个类型安全的any
// unknown 类型的变量不能直接给其他变量赋值
if (typeof e === 'string') {
    s = e
}

// 5.类型断言,用来告诉解析器变量的实际类型
// 下面两种用法效果都一样
s = e as string
s = <string> e

// 6.空返回值 void表示空
function fn(): void { // 可以返回null undefined 空
    return null
}

// 7.never 表示不会返回结果
function fn2(): never { // 可以用来抛异常
    throw new Error('报错了')
}


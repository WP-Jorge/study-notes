// import { hi } from './module'
// console.log(hi)

let a: number
a = 1
console.log(a);

function fn (this: Window) { // 开启noImplicitThis后 要指定this的类型
    console.log(this)
}
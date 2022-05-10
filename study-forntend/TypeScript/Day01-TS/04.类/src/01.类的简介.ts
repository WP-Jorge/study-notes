// 使用class关键字定义一个类
class Person {
    // 定义实例属性
    name: string = '张三'
    age: number = 18

    // 在属性前使用static关键字可以定义类属性（静态属性）
    static sex: string = '男'

    // 在属性前面加关键字readonly后该属性只能读不能写
    readonly home: string = '北京'

    // 静态的只读属性
    static readonly height: number = 180

    // 定义方法 可以添加static属性
    sayHello() {
        console.log('hello 大家好')
    }
}

const per = new Person()
console.log(per);
console.log(per.name);
console.log(per.age);
console.log(Person.sex);
console.log(per.home);
console.log(Person.height);
// per.home = '1' // 报错
per.sayHello()
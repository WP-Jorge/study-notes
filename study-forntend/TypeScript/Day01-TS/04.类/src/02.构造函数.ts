class Dog {

    name: string
    age: number

    // constructor 构造函数
    // 构造函数会在对象创建的时候被调用
    constructor(name: string, age: number ) {
        // 在实例方法中，this就表示当前的实例
        // 在构造函数中当前对象就是新创建的对象
        // 可以通过this向新创建的对象中添加属性
        console.log('构造函数执行了')
        this.name = name
        this.age = age
    }

    bark() {
        // 在方法中可以通过this来表示调用方法的对象
        console.log(this.name + '：汪汪')
    }
}

const dog = new Dog('旺财', 3)

console.log(dog);
dog.bark()
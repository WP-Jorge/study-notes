(() => {

    // 以abstract开头的类是抽象类
    abstract class Animal {
        name: string
        constructor(name: string) {
            this.name = name
        }

        // 抽象方法 使用abstract 开头
        abstract sayHello(): void
    }

    class Dog extends Animal{
        sayHello(): void {
            console.log('喵喵喵')
        }
    }

    const dog = new Dog('旺财')
    dog.sayHello()

})()
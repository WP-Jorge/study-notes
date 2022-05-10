(() => {
    // 定义一个animal类
    class Animal {
        name: string
        age: number

        constructor(name: string, age: number) {
            this.name = name
            this.age = age
        }

        sayHello() {
            console.log('动物在叫');
        }
    }

    // 定义一个表示狗的类
    // 继承Animal类
    class Dog extends Animal {
        run() {
            console.log(`${this.name}在跑`);
        }

        sayHello() {
            console.log(`${this.name}在叫`)
        }
    }

    /**
     * 继承后子类可以使用父类所有的方法 和属性
     * 如果希望在子类中添加一些父类中没有的属性或者方法，直接添加就行
     * 子类中如果有父类的方法 子类的方法会覆盖父类的方法
     */
    // 定义一个表示猫的类
    // 继承Animal类
    class Cat extends Animal {

    }

    const dog = new Dog('旺财', 3)
    const cat = new Cat('喵喵', 2)
    console.log(dog);
    dog.sayHello()
    dog.run()

    console.log(cat);
    cat.sayHello()

})()
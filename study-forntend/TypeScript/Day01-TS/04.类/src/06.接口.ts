(() => {
   // 描述一个对象的类型
    type myType = {
        name: string,
        age: number,
    }

    // 接口用来定义一个类的结构，用来定义一个类中包含哪些属性和方法
    // 同时接口也可以当成类型声明去使用
    interface myInterface {
        name: string
        age: number
    }
    interface myInterface {
        gender: string
    }

    // const obj: myInterface = {
    //     name: 'sss',
    //     age: 111,
    //     gender: '男'
    // }

    // 接口可以再定义类的时候去限制类的结构
    interface myInter {
        name: string

        sayHello(): void
    }

    // 实现接口
    class MyClass implements myInter {
        name: string;

        constructor(name: string) {
            this.name = name
        }

        sayHello(): void {
            console.log('大家好')
        }

    }

})()
(() => {
   // 定义一个表示人的类
    class Person {

        /**
         * 属性修饰符
         * public 默认
         * private 私有属性， 只允许当前类内部调用，子类无法调用
         * protected 受保护的属性， 只能在当前类和当前类的子类中使用
         */
        private _name: string

        private _age: number

        constructor(name: string, age: number) {
            this._name = name
            this._age = age
        }

        // TS中设置getter方法的方式
        get name(): string {
            console.log('get name() 执行了')
            return this._name;
        }

        set name(value: string) {
            this._name = value;
        }

        get age(): number {
            return this._age;
        }

        set age(value: number) {
            this._age = value;
        }

    }

    const per = new Person('张三', 18)
    console.log(per)
    console.log(per.name);
    console.log(per.age);

    class B {
        // 可以直接将属性定义在构造函数中
        constructor(public name: string, public age: number) {
        }
    }

    const b = new B('xxx', 1)
    console.log(b);

})()
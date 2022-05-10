// 1.object 表示一个js对象
var a;
a = {};
a = function () {
};
// 2.{}指定对象中包含哪些属性
// 语法：{属性名: 属性值, 属性名: 属性值}
var b; // age?表示属性是可选的
b = { name: 'hello' };
var c; // 添加上这个[propName: string]: any可以有任意个任意类型的参数 表示任意字符串的属性名
c = { name: '111', age: 18, gender: '男' };
// 表示b是一个函数，有两个参数都是number返回值也是number
/**
 * 语法：（形参: 类型, 形参: 类型...） => 返回值
 */
var d;
d = function (n1, n2) {
    return n1 + n2;
};
// 3.string[] 表示字符串数组
var e;
e = ['a', 'b', 'c'];
var g;
g = [1, 2, 3];
/**
 * 4.元组：固定长度的数组
 */
var h;
h = ['hello', 'abc'];
// 5.枚举
var Gender;
(function (Gender) {
    Gender[Gender["male"] = 0] = "male";
    Gender[Gender["female"] = 1] = "female";
})(Gender || (Gender = {}));
var i;
i = {
    name: '王伟',
    gender: Gender.male
};

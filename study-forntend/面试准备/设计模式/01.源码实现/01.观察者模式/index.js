const Subject = require('./Subject');
const Observer = require('./Observer');

let subject = new Subject();

let observer1 = new Observer('张三');
let observer2 = new Observer('李四');
let observer3 = new Observer('王五');
let observer4 = new Observer('赵六');

subject.addObserver([observer1, observer2, observer3, observer4]);

subject.removeObserver(observer2);

console.log('最新消息：台湾回归了！！！');
subject.notify('台湾回归了！！！');

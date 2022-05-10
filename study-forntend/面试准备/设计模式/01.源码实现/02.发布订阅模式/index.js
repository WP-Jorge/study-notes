const SubHub = require('./SubHub.js');
const Subscriber = require('./Subscriber.js');
const Publisher = require('./Publisher.js');

// 节目类型
const MUSIC = 'music';
const MOVIE = 'movie';
const FOOD = 'food';

// 创建订阅中心
const subHub = new SubHub();

// 创建订阅者
const subscriberA = new Subscriber('subscriberA', subHub);
const subscriberB = new Subscriber('subscriberB', subHub);
const subscriberC = new Subscriber('subscriberC', subHub);

// 创建发布者
const publisherA = new Publisher('publisherA', subHub);
const publisherB = new Publisher('publisherB', subHub);
const publisherC = new Publisher('publisherC', subHub);

// 发布者发布信息
publisherA.publish(MUSIC, 'publisherA：我的新歌曲：我心永恒');
publisherB.publish(MOVIE, 'publisherB：我的新电影：泰坦尼克号');
publisherC.publish(FOOD, 'publisherC：我的新菜谱：夫妻肺片');

// 订阅者订阅信息
subscriberA.subscribe(MUSIC, msg => {
	console.log(`subscriberA：接收到 ${MUSIC} 频道的信息：${msg}`);
});
subscriberB.subscribe(MOVIE, msg => {
	console.log(`subscriberB：接收到 ${MOVIE} 频道的信息：${msg}`);
});
subscriberC.subscribe(FOOD, msg => {
	console.log(`subscriberC：接收到 ${FOOD} 频道的信息：${msg}`);
});

// 订阅中心通知订阅者 发布中心发布新信息
subHub.notify(MUSIC);
subHub.notify(MOVIE);
subHub.notify(FOOD);
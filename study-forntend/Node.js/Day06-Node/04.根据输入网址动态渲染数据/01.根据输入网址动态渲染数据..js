const MyApp = require('./MyApp')

let app = new MyApp()

app.on('/', (req, res) => {
	res.setHeader('Content-Type', 'text/html; charset=utf-8')
	res.end('这是首页')
})

app.on('/gnxw', (req, res) => {
	res.setHeader('Content-Type', 'text/html; charset=utf-8')
	if (req.pathObj.base === 'index.html') {
		res.end('这是国内新闻首页')
	} else {
		res.end('这是国内新闻其他页面')
	}
})

app.on('/static', (req, res) => {
	// console.log(req.pathObj);
	res.setHeader("content-type", res.getContentType(res.pathObj.ext))
	let rs = res.fs.createReadStream('./static/' + res.pathObj.base)
	// 将本地文件写道res中响应出去到页面
	rs.pipe(res)
})

app.on('/movies', (req, res) => {
	let movies = [{
		name: "雪暴",
		brief: "电影《雪暴》讲述了在一座极北的边陲小镇，一伙穷凶极恶、作案手法老到的悍匪为抢夺黄金，打劫运金车，并借助大雪掩盖了所有犯罪痕迹。为了探求真相，警察王康浩暗地里搜集证据，熟悉地形，终于在一场灾难级的暴雪降临时，与谋财害命的悍匪发生了惊心动魄的正面对决……",
		author: "张震",
		// stars: ['小明', '蔡徐坤', '乌龟']
		stars: [
			{name: '蔡徐坤', gender: '男'},
			{name: '阿离', gender: '女'},
			{name: '老王', gender: '男'}
		]
	}, {
		name: "少年的你",
		brief: "陈念（周冬雨 饰）是一名即将参加高考的高三学生，同校女生胡晓蝶（张艺凡 饰）的跳楼自杀让她的生活陷入了困顿之中。胡晓蝶死后，陈念遭到了以魏莱（周也 饰）为首的三人组的霸凌，魏莱虽然表面上看来是乖巧的优等生，实际上却心思毒辣，胡晓蝶的死和她有着千丝万缕的联系。",
		author: "周冬雨 ",
		// stars: ['冬瓜', '南瓜', '向阳瓜']
		stars: [
			{name: '坤坤', gender: '男'},
			{name: '王五', gender: '女'},
			{name: '老三', gender: '男'}
		]
	}]
	
	let index = res.pathObj.base
	// res.end(JSON.stringify(movies[index]))
	
	// 将数据渲染出去
	res.render(movies[index], './index.html')
})

app.run(80, () => {
	console.log('启动服务器：http://127.0.0.1');
})



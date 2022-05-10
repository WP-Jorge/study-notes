let events = require('events')
let fs = require('fs')

let ee = new events.EventEmitter()

ee.on('helloSuccess', msg => {
	console.log('1吃夜宵')
	console.log(msg);
})
ee.on('helloSuccess', msg => {
	console.log('2唱K')
	console.log(msg);
})
ee.on('helloSuccess', msg => {
	console.log('3打王者')
	console.log(msg);
})
ee.on('helloSuccess', msg => {
	console.log('4打豆豆')
	console.log(msg);
})

function readfs(path) {
	return new Promise((resolve, reject) => {
		fs.readFile(path, (err, data) => {
			if (err) {
				reject(err)
			} else {
				resolve(data)
			}
		})
	})
}

// readfs('hello.txt').then(res => {
// 	ee.emit('helloSuccess', res.toString())
// })

async function test() {
	let data = await readfs('hello.txt')
	ee.emit('helloSuccess', data.toString())
}

test()
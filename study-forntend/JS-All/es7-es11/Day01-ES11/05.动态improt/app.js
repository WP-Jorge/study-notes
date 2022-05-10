// 获取元素
const btn = document.getElementById('btn')

btn.onclick = function () {
	import('./hello.js').then(
		res => {
			res.hello()
		},
		err => {
			console.log(err)
		}
	)
}

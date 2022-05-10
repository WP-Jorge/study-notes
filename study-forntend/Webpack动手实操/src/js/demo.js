import 'css/title';
import 'css/title.scss';
import 'font/iconfont';
import axios from 'axios';
import 'js/index';

export let demo = () => {
	let div = document.createElement('div');
	div.innerText = 'loader的使用';
	div.classList.add('title');
	document.body.appendChild(div);

	// 图片loader
	let img = document.createElement('img');
	img.width = 400;
	img.src = require('img/风景.jpg');
	document.body.appendChild(img);

	// 字体
	let span = document.createElement('span');
	span.classList.add('iconfont', 'icon-camera');
	document.body.appendChild(span);

	// babel
	let func = () => {
		console.log(1234);
	};
	func();

	// babel babel / core-js / regenerator-runtime
	let sleep = time => {
		return new Promise((resolve, reject) => {
			setTimeout(() => console.log('sleep: ' + time), time);
		});
	};
	sleep(2000);

	// proxy 代理解决跨域
	axios.get('/api/toplist/detail').then(res => {
		console.log(res);
	});
};

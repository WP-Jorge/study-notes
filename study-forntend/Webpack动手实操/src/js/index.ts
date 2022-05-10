const title: string = 'ts-loader 的使用';

const foo = (msg: string) => {
	console.log(msg);
}

const promise = new Promise((resolve, reject) => {
	console.log(1);
})

foo(title);
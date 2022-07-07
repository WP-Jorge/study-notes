import crypto from 'crypto';

export const getMd5 = (str: string) => {
	// 规定使用哈希算法中的MD5算法
	const hash = crypto.createHash('md5');

	// 可任意多次调用update(),效果相当于多个字符串相加
	hash.update(str);

	// 最终加密的字符串为'987653abc123456789',hash.digest('hex')表示输出的格式为16进制
	return hash.digest('hex');
};

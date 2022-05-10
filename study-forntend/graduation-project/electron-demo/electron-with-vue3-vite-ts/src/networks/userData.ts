import { userData } from '../datas/userData';

export default function (userId: string | number): Promise<null | object> {
	return new Promise((resolve, reject) => {
		let userInfo = userData.find(item => item.userId === userId);
		if (userInfo) {
			resolve(userInfo);
		}
		reject(null);
	});
}

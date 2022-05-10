import NProgress from 'nprogress';
import { useEffect } from 'react';

export default function useProgress() {
	NProgress.start();
	useEffect(() => {
		NProgress.done();
	});
}

import React from 'react';
import useProgress from './useProgress';

export default function WithProgress(props: any) {
	useProgress();
	const { children } = props;
	return <>{children}</>;
}

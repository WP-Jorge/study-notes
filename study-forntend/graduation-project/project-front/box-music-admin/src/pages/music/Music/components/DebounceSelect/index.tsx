import { Select, Spin } from 'antd';
import { debounce } from 'lodash';
import React, { useMemo, useRef, useState } from 'react';

export interface DebounceSelectProps {
	fetchOptions: (keyword: string) => any;
	debounceTimeout?: number;
	[x: string]: any;
}

export const DebounceSelect = ({
	fetchOptions,
	debounceTimeout = 800,
	...props
}: DebounceSelectProps) => {
	const [fetching, setFetching] = useState(false);
	const [options, setOptions] = useState(
		[] as { label: string; value: string }[]
	);
	const fetchRef = useRef(0);
	const debounceFetcher = useMemo(() => {
		const loadOptions = (keyword: string) => {
			fetchRef.current += 1;
			const fetchId = fetchRef.current;
			setOptions([]);
			setFetching(true);
			fetchOptions(keyword).then(
				(newOptions: { label: string; value: string }[]) => {
					if (fetchId !== fetchRef.current) {
						return;
					}

					setOptions(newOptions);
					setFetching(false);
				}
			);
		};

		return debounce(loadOptions, debounceTimeout);
	}, [fetchOptions, debounceTimeout]);
	return (
		<Select
			labelInValue
			filterOption={false}
			onSearch={debounceFetcher}
			notFoundContent={fetching ? <Spin size="small" /> : null}
			{...props}
			options={options}
		/>
	);
};

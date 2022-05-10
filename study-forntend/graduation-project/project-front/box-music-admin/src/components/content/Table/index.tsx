import React, { useEffect, useState } from 'react';
import { Table as AntdTable } from 'antd';
import { ColumnsType } from 'antd/es/table';
import { AxiosResponse } from 'axios';
import './index.scss';

export interface ColumnsHooks<T> {
	pageData?: Array<T>;
	setPageData?: React.Dispatch<React.SetStateAction<Array<any>>>;
	pageBarChange?: (
		pageNumber: number,
		pageSize: number,
		searchWord?: string
	) => void;
	pageBarConfig?: {
		pageSize: number;
		currentPage: number;
	};
}

interface IProps<T> {
	columns: ColumnsType<T>;
	getPageDataApi: (
		currentPage?: number,
		pageSize?: number,
		keyword?: string
	) => Promise<AxiosResponse<any, any>>;
	pageBarConfig: {
		pageSize: number;
		currentPage: number;
	};
}

export const Table = <T extends object = any>(props: IProps<T>) => {
	const { columns, getPageDataApi, pageBarConfig } = props;
	const [pageData, setPageData] = useState([] as Array<T>);
	const rowKey = columns[0]['dataIndex'];
	const [currentPage, setCurrentPage] = useState(
		pageBarConfig.currentPage ?? 1
	);
	const [pageSize, setPageSize] = useState(pageBarConfig.pageSize ?? 1);
	const [total, setTotal] = useState(0);
	useEffect(() => {
		pageBarChange(currentPage, pageSize);
	}, []);
	async function pageBarChange(pageNumber: number, pageSize: number) {
		let res = await getPageDataApi(pageNumber, pageSize);
		setPageData(res.data.pageList);
		setCurrentPage(res.data.currentPage);
		setPageSize(res.data.pageSize);
		setTotal(res.data.total);
	}
	return (
		<div className="table">
			<div className="table-container">
				<AntdTable
					columns={columns}
					pagination={{
						current: currentPage,
						pageSize,
						total,
						onChange: pageBarChange
					}}
					dataSource={pageData}
					rowKey={rowKey}
				/>
			</div>
		</div>
	);
};

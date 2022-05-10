import React from 'react';
import { Button, Popconfirm } from 'antd';
import Search from 'antd/lib/input/Search';
import './index.scss';

export interface TableTopProps {
	searchOption?: {
		placeholder?: string;
		onSearch: (keyword: string) => void;
	};
	deleteOption?: {
		title?: string;
		onDelete: () => void;
	};
	addOption?: {
		title?: string;
		onAdd: () => void;
	};
}

export const TableTop = (props: TableTopProps) => {
	const { searchOption, deleteOption, addOption } = props;
	function confirm() {
		deleteOption?.onDelete();
	}
	return (
		<div className="table-top">
			{searchOption && (
				<div className="top-item">
					<Search
						placeholder={searchOption.placeholder ?? '请输入关键词'}
						enterButton
						onSearch={(keyword: string) => searchOption.onSearch(keyword)}
					/>
				</div>
			)}
			{deleteOption && (
				<div className="top-item">
					<Popconfirm
						title="确定要删除？（该操作将进行逻辑删除）"
						onConfirm={confirm}
						okText="确定"
						cancelText="取消"
					>
						<Button danger>{deleteOption.title ?? '批量删除'}</Button>
					</Popconfirm>
				</div>
			)}
			{addOption && (
				<div className="top-item">
					<Button type="primary" onClick={() => addOption.onAdd()}>
						{addOption.title ?? '添加'}
					</Button>
				</div>
			)}
		</div>
	);
};

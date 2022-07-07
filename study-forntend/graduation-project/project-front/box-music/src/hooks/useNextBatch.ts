import { BaseFunctionType } from '@/globals/GlobalTypes';

export interface PageDataType {
	total: number;
	pageSize: number;
	currentPage: number;
	totalPages: number;
	nextBatchSize: number;
}

export const useNextBatch = (
	nextBatchCallback: (...args: any) => any,
	pageData: PageDataType
) => {
	const nextBatch: BaseFunctionType = () => {
		if (
			pageData.currentPage < pageData.totalPages &&
			pageData.currentPage < pageData.nextBatchSize
		) {
			pageData.currentPage++;
		} else {
			pageData.currentPage = 1;
		}
		nextBatchCallback();
	};
	const showNextBatch = computed(() => {
		return pageData.total > pageData.pageSize;
	});
	return { nextBatch, showNextBatch, pageData };
};

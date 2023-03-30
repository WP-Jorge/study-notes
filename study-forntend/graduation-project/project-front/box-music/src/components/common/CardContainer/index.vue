<script setup lang="ts">
import { useNextBatch, PageDataType } from '@/hooks/useNextBatch';
const props = defineProps({
	title: {
		type: String,
		required: false,
		default: '标题'
	},
	alwaysShowNextBatch: {
		type: Boolean,
		required: false,
		default: false
	},
	pageData: {
		type: Object,
		required: false,
		default: () => {
			return {
				total: 0,
				pageSize: 20,
				currentPage: 1,
				totalPages: 0,
				nextBatchSize: 5
			};
		}
	},
	nextBatchCallback: {
		type: Function,
		required: false,
		default: () => console.log()
	}
});

const { nextBatch, showNextBatch } = useNextBatch(
	props.nextBatchCallback as (...args: any) => void,
	props.pageData as PageDataType
);
</script>
<template>
	<div class="card-container">
		<div class="title">
			<slot name="title">
				<div class="title-bar">
					<div class="title">
						{{ title }}
					</div>
					<div v-show="showNextBatch || alwaysShowNextBatch" class="options">
						<span class="text" @click="() => nextBatch()">换一批</span>
					</div>
				</div>
			</slot>
		</div>
		<div class="content">
			<slot name="content"></slot>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.card-container {
	margin: 20px 0;
	.title {
		font-weight: bold;
		.title-bar {
			display: flex;
			justify-content: space-between;
			align-items: center;
			.options {
				color: #000;
				font-weight: normal;
				span {
					padding-right: 10px;
					cursor: pointer;
					&:hover {
						color: var(--el-color-info-dark-2);
					}
				}
			}
		}
	}
	.content {
		margin-top: 20px;
		display: grid;
		justify-content: space-between;
		grid-template-columns: repeat(auto-fill, 220px);
		grid-gap: 10px;
	}
}
</style>

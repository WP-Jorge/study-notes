<template>
	<div>Home</div>
	<div>{{ obj }}</div>
	<div>
		<input type="text" v-model="obj.parents[0].name">
	</div>
</template>

<script>
import {
	onBeforeMount,
	onBeforeUnmount,
	onBeforeUpdate,
	onMounted,
	onUnmounted,
	onUpdated, reactive, ref
} from 'vue';

export default {
	setup() {
		let msg = ref('Vue3-app');
		let obj = reactive({
			name: '小明',
			age: 18,
			parents: [
				{
					name: '大明',
					age: 48
				},
				{
					name: '大红',
					age: '48'
				}
			]
		});
		obj.parents[1].age = 45;
		onBeforeMount(() => {
			console.log('---onBeforeMount---');
		});
		onMounted(() => {
			console.log('---onMounted---');
		});
		onBeforeUpdate(() => {
			console.log('---onBeforeUpdate---');
		});
		onUpdated(() => {
			console.log('---onUpdated---');
		});
		onBeforeUnmount(() => {
			console.log('---onBeforeUnmount---');
		});
		onUnmounted(() => {
			console.log('---onUnmounted---');
		});
		return {
			msg,
			obj
		};
	}
};
</script>

<style scoped lang="scss">

</style>

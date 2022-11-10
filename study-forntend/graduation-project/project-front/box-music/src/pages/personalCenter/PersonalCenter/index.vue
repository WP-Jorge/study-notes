<script setup lang="ts">
import UserInfo from './conponents/UserInfo/index.vue';
import Edit from './conponents/Edit/index.vue';
import { useSystemStore } from '@/store/system';
const systemStore = useSystemStore();
</script>
<template>
	<div class="personal-center">
		<div class="container">
			<UserInfo v-show="!systemStore.isEdit" />
			<Edit v-show="systemStore.isEdit" />
		</div>
	</div>
</template>
<style lang="scss" scoped>
.personal-center {
	.container {
		min-height: 500px;
	}
}
</style>

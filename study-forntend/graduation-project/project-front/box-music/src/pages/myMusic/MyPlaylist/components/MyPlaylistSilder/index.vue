<script setup lang="ts">
import { Playlist } from '@/networks/playlist';
import { Search } from '@element-plus/icons-vue';
import PlayListSilderCard from './components/PlaylistSilderCard/index.vue';
import PurePlaylistSilderCard from './components/PurePlaylistSilderCard/index.vue';
import SimplePlaylistContainer from '@/components/common/SimplePlaylistContainer/index.vue';
import { debounce } from '@/utils/baseUtil';
import { ElMessage, ElMessageBox } from 'element-plus';
interface PropType {
	tableData: Playlist[];
}
withDefaults(defineProps<PropType>(), {
	tableData() {
		return [];
	}
});
const emits = defineEmits(['search']);
const searchWord = ref('');

const search = debounce(() => {
	console.log('🦃🦃1', 1);
	emits('search', searchWord.value);
});
const openAddPlaylist = () => {
	ElMessageBox.prompt('请输入歌单名称', '添加歌单', {
		confirmButtonText: '确定',
		cancelButtonText: '取消'
	})
		.then(({ value }) => {
			ElMessage({
				type: 'success',
				message: `成功${value}`
			});
		})
		.catch(() => ({}));
};
</script>
<template>
	<div class="my-palylist-silder">
		<div class="search-bar">
			<el-input
				v-model="searchWord"
				placeholder="请输入歌单名称"
				clearable
				:suffix-icon="Search"
				@keyup="search" />
			<el-button type="primary" @click="openAddPlaylist">添加歌单</el-button>
		</div>
		<div class="playlist-container">
			<SimplePlaylistContainer title="我创建的">
				<template #content>
					<PurePlaylistSilderCard
						v-for="item of tableData"
						:key="item.playlistId"
						:card-data="item"
						:show-img="false" />
				</template>
			</SimplePlaylistContainer>
			<SimplePlaylistContainer title="我收藏的">
				<template #content>
					<PlayListSilderCard
						v-for="item of tableData"
						:key="item.playlistId"
						:card-data="item" />
				</template>
			</SimplePlaylistContainer>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.my-palylist-silder {
	width: 300px;
	height: 100%;
	.search-bar {
		display: flex;
		margin: 0 10px;
		.el-button {
			margin-left: 10px;
		}
	}
	.playlist-container {
		height: calc(100% - 35px);
	}
}
</style>

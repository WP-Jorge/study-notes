<script setup lang="ts">
import { usePlaylistStore } from '@/store/playlist';
import MyPlaylistSilder from './components/MyPlaylistSilder/index.vue';
import MyPlaylistTable from './components/MyPlaylistTable/index.vue';

const playlistStore = usePlaylistStore();

const search = (keyword: string) => {
	playlistStore.getPlaylistsByPlaylistNameAndUserIdPage(keyword);
};

// getMusicList();
// playlistStore.getPlaylistsByPlaylistNameAndUserIdPage =
// getPlaylistsByPlaylistNameAndUserIdPage;
playlistStore.getPlaylistsByPlaylistNameAndUserIdPage();
playlistStore.getSimplePlaylists();
</script>
<template>
	<div class="my-playlist">
		<SimpleContainer title="我的歌单">
			<template #content>
				<div class="my-playlist-content">
					<MyPlaylistSilder
						:my-collected="playlistStore.collectionPlaylist"
						:my-created="playlistStore.playlists"
						@search="search"
						@reflash-table="playlistStore.getSimplePlaylists" />
					<MyPlaylistTable :playlist="playlistStore.currentPlaylist" />
				</div>
			</template>
		</SimpleContainer>
	</div>
</template>
<style lang="scss" scoped>
.my-playlist {
	height: calc(100% - 20px);
	// height: 100%;
	.simple-container {
		margin-bottom: 0;
		height: calc(100%);
		:deep(.content) {
			height: calc(100% - 40px);
		}
	}
	&-content {
		display: flex;
		height: 100%;
		.my-palylist-silder {
			margin-right: 20px;
		}
		.playlist {
			width: 300px;
			height: calc(100% - 100px);
		}
	}
}
</style>

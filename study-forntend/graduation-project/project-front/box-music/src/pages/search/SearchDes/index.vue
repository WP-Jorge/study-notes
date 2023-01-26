<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { DesMusic, Music } from '@/networks/music';
import { useSearchStore } from '@/store/search';
import { getFormatTime } from '@/utils/mathUtil';
const searchStore = useSearchStore();
// const tempMusicList = [
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: '671171',
// 		album: {
// 			albumName: '拾贰',
// 			albumPic: 'G:/box-music/temp-pictures/下一站茶山刘.jpg'
// 		},
// 		bitrate: 820338,
// 		duration: 205.573787,
// 		level: '无损',
// 		lyric: '',
// 		musicTitle: '下一站茶山刘',
// 		local: true,
// 		singers: [{ singerName: '房东的猫' }],
// 		musicUrl: 'G:\\DownLoad\\template/下一站茶山刘.flac',
// 		downloadItemInfo: { localPath: 'G:\\DownLoad\\template/下一站茶山刘.flac' }
// 	},
// 	{
// 		musicId: 1956640185,
// 		musicTitle:
// 			'The King and I (From the Original Motion Picture Soundtrack ELVIS)',
// 		lyric:
// 			"[00:00.000] 作词 : Marshall Mathers/Andre Young/Luis Resto/Thomas Callaway/Jerry Leiber/Mike Stoller\n[00:01.000] 作曲 : Marshall Mathers/Andre Young/Luis Resto/Thomas Callaway/Jerry Leiber/Mike Stoller\n[00:04.674] I roll up like the bottom of a toothpaste tube\n[00:07.632] Blue suede shoes, one missin' a shoelace to it\n[00:10.209] Two new chains, you can call me 2 Chainz\n[00:12.232] Ropes hang like Hussein's noose (Woo)\n[00:13.757] Yeah, they let the fruitcake loose, and it goes\n[00:15.448] One, for the trailer park, two, for my baby ma\n[00:18.216] Three, for the tater tots, four, if you ate a lot\n[00:20.771] Five, if you came to rock, straight up, while l'm shittin' on my comp\n[00:23.698] l'm about to use da John like Grey Poupon (Dijon)\n[00:26.518] Money like a scroll, *****, my paper long (Bag)\n[00:28.831] Longer than it takes a blonde to put her make-up on (Yeah)\n[00:31.466] 'Cause me and Elvis gel together like cell mates (Hey)\n[00:34.250] Yeah, this the jail house, *****, and I don't give a\n[00:36.063]\n[00:36.529] And I don't give a shit about a thing you say\n[00:38.989] I just wanna feel like a king today\n[00:41.377] And if you don't like it, you can sit and spin\n[00:43.896] Middle fingers up, we 'bout to do this shit again, uh-huh (Yuh)\n[00:46.046]\n[00:46.425] Modus operandi, bottle of blonde dye (Yuh)\n[00:48.650] Top five since I discovered peroxide (Where)\n[00:51.051] Yeah, since I got signed, I went from pot pies\n[00:53.711] To Jack and the bean, l'm watchin' my stock rise (Huh)\n[00:56.221] These little attention seekers, I'm finna treat 'em like diabetics\n[00:58.871] Got 'em all on pins and needles (Yeah)\n[01:00.455] Just like Ozempic meanin' (Right)\n[01:01.949] Give these little *****s the finger (Good)\n[01:03.468] And when I stick this thing up (Where?)\n[01:04.523] It's higher than Wiz Khalifa (Woo)\n[01:05.731] Soon as he lit the weed up (Huh)\n[01:07.079] As I go pickin' speed up (Where?)\n[01:08.341] Like I was finna re-up (Yeah)\n[01:09.624] Rap is my new Vicodin, Suboxone is how I treat it (Get it)\n[01:12.222] Still goin' toe to toe, l'm still boxin' with all my demons (Yeah)\n[01:14.664] But a couple of Xanny bars and l'm Danny Gar- (See ya)\n[01:17.424] Been stuntin' on you from the jump like Evel Knievel\n[01:19.868] Back in the cut and stackin' chips up like a can of Pringles\n[01:22.551] Sometimes I feel like Pete Rose (Why?)\n[01:23.833] I got so many hit singles (Yeah)\n[01:25.059] *****, I barely have any wrinkles (Huh)\n[01:26.301] You sleepin' on me like l'm ZzzQuil, so (Woo)\n[01:27.293]\n[01:27.805] And I don't give a shit about a thing you say\n[01:30.285] I just wanna feel like a king today\n[01:32.752] And if you don't like it, you can sit and spin\n[01:35.235] Middle fingers up, we 'bout to do this shit again, uh-huh\n[01:38.093] And it goes, one, for the trailer park, two, for my baby ma\n[01:40.794] Three, for the tater tots, four, if you ate a lot\n[01:43.361] Five, if you came to rock and you never gave a ****\n[01:45.874] Middle fingers up, we 'bout to do this shit again, uh-huh\n[01:47.561]\n[01:48.535] I stole black music, yeah, true, perhaps used it (For what?)\n[01:51.535] As a tool to combat school kids, came back on some bathroom shit, now\n[01:55.583] I call a hater a bidet (Why?) 'Cause they mad, but they can't do shit\n[01:59.914] And I know l'm such a ****, huh (Yeah)\n[02:01.209] And it must be ****in' with ya (Yeah)\n[02:02.484] To know I up and quit the (What?)\n[02:03.616] Prescription drugs and liquor (Yeah)\n[02:04.987] And yet my buzz is bigger (Wow)\n[02:06.175] Still rappin' circles around you (Yeah)\n[02:07.460] Like a boa constrictor (Break it down)\n[02:08.296]\n[02:09.097] Now, l'm about to explain to you all the parallels between\n[02:11.549] Elvis and me, myself, that seems obvious, one, he's pale as me\n[02:14.095] Second, we both been hailed as king, he used to rock the jailhouse\n[02:16.754] And 1 used to rock The Shelter, we sell like Velveeta shells and cheese (Go)\n[02:19.693] But y'all miss the meaner me, back when I had felonies\n[02:22.382] That went over your head because you just fell on knees\n[02:24.840] You literally knelt to me, I used to have no self esteem (Yeah, yeah)\n[02:27.207] I used to cry myself to sleep (What?)\n[02:28.341] Honestly, I need doubters because you mother****ers motivate me (Yeah)\n[02:31.251] To make you look stupid, believers, a little faith is all I seek from you\n[02:34.066] All I need to do is hear you say the same shit my father\n[02:36.533] Said to me when I was just a week or two\n[02:38.727] \"Marshall, I be leavin' you\" (Ha, stupid)\n[02:40.955] No more Guinness Stout, but my belief in myself\n[02:43.668] It once again is stout, so many world records, I'm Guinness'd out (Yeah, huh?)\n[02:46.789] Shit you say goes in and out (Oh)\n[02:48.559] My ear canal, so either my hearing's out or I don't give a shit about\n[02:50.036]\n[02:50.736] And I don't give a shit about a thing you say\n[02:53.063] I just wanna feel like a king today\n[02:55.565] And if you don't like it, you can sit and spin\n[02:58.141] Middle fingers up, we 'bout to do this shit again, uh-huh\n",
// 		album: {
// 			albumId: 146645446,
// 			albumName:
// 				'The King and I (From the Original Motion Picture Soundtrack ELVIS)',
// 			albumPic: 'http://127.0.0.1:8081/album-pictures/146645446.jpg',
// 			albumDescription:
// 				'姆爷Eminem携手CeeLo Green献唱传记音乐电影《Elvis》（猫王）原声《The King and I》\n',
// 			totalViews: 0
// 		},
// 		duration: 193.802,
// 		size: 7753187,
// 		level: '超高品',
// 		musicFormat: 'mp3',
// 		bitrate: 320000,
// 		totalViews: 0,
// 		musicUrl: 'http://127.0.0.1:8081/musics/1956640185.mp3',
// 		createTime: '2022-06-23T18:30:38.000+00:00',
// 		updateTime: null,
// 		deleted: 0,
// 		categories: [
// 			{
// 				categoryId: '1532572655671128309',
// 				categoryName: '流行',
// 				categoryType: 1,
// 				createTime: '2022-06-23T18:30:38.000+00:00',
// 				updateTime: null
// 			},
// 			{
// 				categoryId: '1532572655671128316',
// 				categoryName: '欧美',
// 				categoryType: 0,
// 				createTime: '2022-06-23T18:30:38.000+00:00',
// 				updateTime: null
// 			},
// 			{
// 				categoryId: '1532572655671128365',
// 				categoryName: '说唱',
// 				categoryType: 1,
// 				createTime: '2022-06-23T18:30:38.000+00:00',
// 				updateTime: null
// 			}
// 		],
// 		singers: [
// 			{
// 				singerId: 30836,
// 				singerName: 'CeeLo Green',
// 				singerPic: 'http://127.0.0.1:8081/singer-pictures/30836.jpg',
// 				singerDescription:
// 					"\t\t\t\n\t\t\tCee-Lo Green来自美国著名的老牌hip-hop团体Goodie Mob，同时他也是一位颇为活跃的solo艺人，《Crazy》《Forget you》《Don't Cha》等都是他的代表曲目。",
// 				totalViews: 0,
// 				createTime: '2022-06-23T18:30:38.000+00:00',
// 				updateTime: null,
// 				deleted: 0
// 			},
// 			{
// 				singerId: 32665,
// 				singerName: 'Eminem',
// 				singerPic: 'http://127.0.0.1:8081/singer-pictures/32665.jpg',
// 				singerDescription:
// 					'埃米纳姆（Eminem），本名马歇尔·布鲁斯·马瑟斯三世（Marshall Bruce Mathers III），1972年10月17日出生于美国密苏里州圣约瑟夫，美国说唱男歌手、词曲作者、唱片制作人、演员。\n1996年，埃米纳姆首次以个人身份推出专辑《Infinite》。1998年，受Dr.Dre赏识，与Aftermath娱乐签约。1999年，成立Shady唱片公司，并推出第二张录音室专辑《The Slim Shady LP》，该专辑获得了第42届格莱美奖最佳说唱专辑，专辑曲目《My Name Is》让他获得了最佳说唱歌手。2000年，发行第三张录音室专辑《The Marshall Mathers LP》，该专辑不仅收获了两项格莱美奖，其全球销量还突破了3200万张。2002年，第四张录音室专辑《The Eminem Show》正式发行，该专辑的全球销量超过了2700万张；同年，主演剧情电影《8英里》，其演唱的主题曲《Lose Yourself》获得了第75届奥斯卡金像奖最佳原创歌曲。2004年，发行第五张录音室专辑《Encore》。2005年，因药物问题而暂缓音乐事业。2009年，推出第六张录音室专辑《Relapse》而复出。2010年，发行第七张录音室专辑《Recovery》，专辑曲目《Not Afraid》《Love the Way You Lie》成为美国公告牌百强单曲榜的冠军单曲。2013年，第八张录音室专辑《The Marshall Mathers LP2》正式发行。2017年，发行第九张录音室专辑《Revival》。2018年，第十张录音室专辑《Kamikaze》正式发行。2020年，推出第十一张录音室专辑《Music to Be Murdered By》。2020年12月18日，推出第十一张录音室专辑《Music to Be Murdered By》的豪华版《Music to Be Murdered By - Side B》。',
// 				totalViews: 0,
// 				createTime: '2022-06-23T18:30:38.000+00:00',
// 				updateTime: null,
// 				deleted: 0
// 			}
// 		]
// 	}
// ];
const contextMenu = useContextMenu({
	downloadOne: true,
	playMusic: true,
	addMusicToPlaylist: true
});
const open = (row: DesMusic, cloumn: any, e: PointerEvent) => {
	contextMenu.openContextMenu(e, row);
};
</script>
<template>
	<div class="search-des">
		<SimpleContainer :title="`搜索 ${searchStore.keyword}`">
			<template #content>
				<el-table
					:data="searchStore.searchMusicList"
					@row-contextmenu="open"
					@row-dblclick="contextMenu.menuFunctions.playMusic">
					<el-table-column #default="{ row: music }" label="封面" :width="80">
						<div class="music-img">
							<img :src="music.album.albumPic" :alt="music.musicTitle" />
						</div>
					</el-table-column>
					<el-table-column
						#default="{ row: music }"
						label="音乐标题"
						:width="220">
						<div class="music-title ellipse">
							{{ music.musicTitle }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="歌手">
						<div class="ellipse">
							{{ (music as Music).singers.map(singer => singer.singerName).join(' / ') }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="专辑">
						<div class="ellipse">
							{{ music.album.albumName }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="时长">
						<div class="ellipse">
							{{ getFormatTime(music.duration) }}
						</div>
					</el-table-column>
				</el-table>
			</template>
		</SimpleContainer>
	</div>
</template>
<style lang="scss" scoped>
.search-des {
	.el-table {
		.music-img {
			display: flex;
			justify-content: space-between;
			align-items: center;
			height: 100%;
			img {
				width: 50px;
				height: 50px;
				border-radius: 5px;
			}
		}
	}
}
</style>

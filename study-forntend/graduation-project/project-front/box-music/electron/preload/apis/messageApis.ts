import { ipcRenderer } from 'electron';

// type ChannelTypes = {
// 	[x: string]: MessageChannel;
// };

// const channels = {} as ChannelTypes;

// let historyChannel: MessageChannel;

export const postMessageToMain = (
	channel: string,
	music: any,
	callback: (music: any) => any
) => {
	let messageChannel: MessageChannel;
	// if (!historyChannel) {
	// 	messageChannel = new MessageChannel();
	// 	historyChannel = messageChannel;
	// 	const { port2 } = historyChannel;
	// 	console.log('ipcRenderer.postMessage');
	// 	ipcRenderer.postMessage(channel, null, [port2]);
	// }
	messageChannel = new MessageChannel();
	// historyChannel = messageChannel;
	const { port2 } = messageChannel;
	console.log('ipcRenderer.postMessage');
	ipcRenderer.postMessage(channel, null, [port2]);
	const { port1 } = messageChannel;
	port1.postMessage(music);
	port1.onmessage = e => {
		callback({
			msg: e.data
		});
	};
};

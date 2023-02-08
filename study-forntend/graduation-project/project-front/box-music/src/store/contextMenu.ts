import { defineStore } from 'pinia';

export interface ContextMenuItem {
	type: string;
	title?: string;
	class?: string;
	disabled?: boolean | ((...args: any) => boolean);
	hide?: boolean | ((...args: any) => boolean);
	callback?: (...args: any) => any;
	children?: ContextMenuItem[];
}

export interface ContextMenuProps {
	contextMenuList?: ContextMenuItem[];
	event?: PointerEvent;
	args?: any;
}

export interface ContextMenuOptions {
	offsetTop: number;
	offsetLeft: number;
	offsetBottom: number;
	offsetRight: number;
	visible: boolean;
	event: PointerEvent;
}

const baseContextMenuList = [
	{
		type: 'li',
		title: '暂停'
	},
	{
		type: 'li',
		title: '下载'
	},
	{
		type: 'li',
		title: '取消'
	}
] as ContextMenuItem[];

export const useContextMenuStore = defineStore('contextMenu', {
	state: () => {
		return {
			contextMenu: {} as HTMLElement,
			contextMenuList: baseContextMenuList,
			options: {} as ContextMenuOptions
		};
	},
	getters: {},
	actions: {
		openContextMenu(props: ContextMenuProps) {
			if (props.event) {
				if (
					props.event.view &&
					props.event.x >
						props.event.view?.innerWidth - this.contextMenu.offsetWidth
				) {
					this.options.offsetLeft =
						props.event.x - this.contextMenu.offsetWidth;
				} else {
					this.options.offsetLeft = props.event.x;
				}
				if (
					props.event.view &&
					props.event.y >
						props.event.view?.innerHeight - this.contextMenu.offsetHeight
				) {
					this.options.offsetTop =
						props.event.y - this.contextMenu.offsetHeight;
				} else {
					this.options.offsetTop = props.event.y;
				}
			}
			props.contextMenuList && (this.contextMenuList = props.contextMenuList);
			this.options.visible = true;
		}
	}
});

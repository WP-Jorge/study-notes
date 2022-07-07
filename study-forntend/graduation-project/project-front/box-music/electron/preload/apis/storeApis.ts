import Store from 'electron-store';

const store = new Store();

export const getData = (key: string, defaultValue: any) =>
	store.get(key, defaultValue);

export const setData = (key: string, data: any) => {
	store.set(key, data);
};

export const deleteData = (key: string) => {
	store.delete(key);
};

export const getStore = () => store.store;

export const clearStore = () => {
	store.clear();
};

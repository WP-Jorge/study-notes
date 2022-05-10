import { sleep } from '@/utils/baseUtil';
import { createSlice } from '@reduxjs/toolkit';
import { AppDispatch } from '../store';

export interface HomeInfo {
	name: string;
	address: string;
}

export interface InitialState {
	homeInfo: HomeInfo;
}

const initialState: InitialState = {
	homeInfo: {
		name: '家',
		address: '中国'
	}
};

const homeSlice = createSlice({
	name: 'home',
	initialState,
	reducers: {
		setHomeInfo(state, action) {
			state.homeInfo = action.payload;
		}
	}
});

export const setHomeInfoAsync = (homeInfo: HomeInfo) => async (dispatch: AppDispatch) => {
	await sleep(500);
	dispatch(setHomeInfo(homeInfo));
};

export const { setHomeInfo } = homeSlice.actions;

export default homeSlice.reducer;

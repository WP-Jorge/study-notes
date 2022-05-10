import { configureStore } from '@reduxjs/toolkit';
import homeSlice from './reducers/homeSlice';
import systemSlice from './reducers/systemSlice';

export const store = configureStore({
	reducer: {
		system: systemSlice,
		home: homeSlice
	}
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export type Store = typeof store;

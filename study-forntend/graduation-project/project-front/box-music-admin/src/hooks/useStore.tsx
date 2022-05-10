import { RootState } from '@/redux/store';
import { useSelector } from 'react-redux';

export default function useStore(): RootState {
	return useSelector((state: RootState) => state);
}

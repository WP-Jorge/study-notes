import React from 'react';
import { PlaylistTable } from './components/PlaylistTable';
import './index.scss';

export default function Playlist() {
	return (
		<div className="playlist">
			<PlaylistTable />
		</div>
	);
}

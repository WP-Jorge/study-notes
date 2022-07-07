import { shell } from 'electron';
import fs from 'fs';
import path from 'path';

export const deleteFile = (filepath: string) => {
	return new Promise((resolve, reject) => {
		fs.unlink(filepath, err => {
			if (err) {
				reject(err);
			}
			resolve(true);
		});
	});
};

export const openFolder = (filepath: string) => {
	shell.openPath(filepath.replace(path.basename(filepath), ''));
};

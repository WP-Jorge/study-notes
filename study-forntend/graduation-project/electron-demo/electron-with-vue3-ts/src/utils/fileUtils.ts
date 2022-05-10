import fs from 'fs';

export function readFileNames(path: string): Array<string | null> {
	return fs.readdirSync(path);
}

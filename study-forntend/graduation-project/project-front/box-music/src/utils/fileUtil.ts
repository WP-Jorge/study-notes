export const getAssetsImageUrl = (filename: string) => {
	const path = `/src/assets/images/${filename}`;
	const modules = import.meta.globEager('/src/assets/images/*');
	return modules[path].default;
};

export const getAssetsAudioUrl = (filename: string) => {
	const path = `/src/assets/audios/${filename}`;
	const modules = import.meta.globEager('/src/assets/audios/*');
	return modules[path].default;
};

export const getAssetsFileUrl = (dirname: string, filename: string) => {
	switch (dirname) {
		case 'images':
			return getAssetsImageUrl(filename);
		case 'audios':
			return getAssetsAudioUrl(filename);
	}
};

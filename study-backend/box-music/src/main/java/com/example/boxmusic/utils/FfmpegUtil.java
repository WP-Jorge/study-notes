package com.example.boxmusic.utils;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.example.boxmusic.pojo.dto.AddMusicDTO;
import com.example.boxmusic.pojo.dto.UpdateMusicDTO;
import com.example.boxmusic.pojo.entity.Category;
import com.example.boxmusic.pojo.entity.Singer;
import com.example.boxmusic.service.FileService;
import com.github.wujun234.uid.impl.CachedUidGenerator;
import com.github.wujun234.uid.impl.DefaultUidGenerator;
import org.bytedeco.ffmpeg.ffmpeg;
import org.bytedeco.ffmpeg.ffprobe;
import org.bytedeco.javacpp.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Map;
import java.util.Optional;

@Component
public class FfmpegUtil {
	
	@Autowired
	private FileService fileService;
	
	@Value("${tempPath}")
	private String tempPath;
	
	private String getTargetSrc(String target, String fileName) {
		return target.endsWith("/") ? target + fileName : target + '/' + fileName;
	}
	
	private Optional<String> doTransform(String fromSrc, String toSrc, String bitrate) {
		String ffmpeg = Loader.load(ffmpeg.class);
		ProcessBuilder transformBuilder = new ProcessBuilder(ffmpeg, "-i", fromSrc, "-b:a", bitrate, "-map_metadata", "0", toSrc);
		try {
			transformBuilder.inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			System.out.println(e);
			return Optional.empty();
		}
		return Optional.of(toSrc);
	}
	
	public String transformAudio(String from, String fileName, String to, String targetFileName, String bitrate) {
		String fromSrc = getTargetSrc(from, fileName);
		String toSrc = getTargetSrc(to, targetFileName);
		Optional<String> transPath = Optional.empty();
		try {
			transPath = doTransform(fromSrc, toSrc, bitrate);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (transPath.isPresent()) {
			return transPath.get();
		}
		return null;
	}
	
	public String saveMusic(String targetPath, String filename, AddMusicDTO addMusicDTO) {
		Optional<String> transPath = Optional.empty();
		try {
			transPath = writeInMusicInfo(targetPath, filename, addMusicDTO);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e.getMessage());
		}
		if (transPath.isPresent()) {
			return transPath.get();
		}
		return null;
	}
	
	public String saveMusic(String targetPath, String filename, UpdateMusicDTO updateMusicDTO) {
		Optional<String> transPath = Optional.empty();
		try {
			transPath = writeInMusicInfo(targetPath, filename, updateMusicDTO);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e.getMessage());
		}
		if (transPath.isPresent()) {
			return transPath.get();
		}
		return null;
	}
	
	public String saveMusic(String targetPath, String filename) {
		Optional<String> transPath = Optional.empty();
		try {
			transPath = writeInMusicInfo(targetPath, filename);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e.getMessage());
		}
		if (transPath.isPresent()) {
			return transPath.get();
		}
		return null;
	}
	
	// public String saveMusic(String targetPath, UpdateMusicDTO updateMusicDTO, MultipartFile song) {
	// 	Optional<String> transPath = Optional.empty();
	// 	try {
	// 		transPath = writeInMusicInfo(targetPath, updateMusicDTO, song);
	// 	} catch (Exception e) {
	// 		System.out.println(e);
	// 	}
	// 	if (transPath.isPresent()) {
	// 		return transPath.get();
	// 	}
	// 	return null;
	// }
	
	private Optional<String> writeInMusicInfo(String targetPath, String filename, AddMusicDTO addMusicDTO) {
		// fileService.uploadFile(song, tempPath, filename);
		String srcPath = getTargetSrc(tempPath, filename);
		// String targetSrc = getTargetSrc(targetPath, filename);
		// String ffmpeg = Loader.load(ffmpeg.class);
		// String singersStr = "";
		// for (Singer singer : addMusicDTO.getSingerList()) {
		// 	singersStr += singer.getSingerName() + ",";
		// }
		// // ProcessBuilder transformBuilder = new ProcessBuilder(ffmpeg, "-i", srcPath, "-metadata", "album=" + addMusicDTO.getAlbum(), "-metadata", "artist=" + singersStr.substring(0, singersStr.length() - 1), "-metadata", "genre=" + addMusicDTO.getGenre(), "-metadata", "title=" + addMusicDTO.getMusicTitle(), "-y", targetSrc);
		// ProcessBuilder transformBuilder = new ProcessBuilder(ffmpeg, "-i", srcPath, "-metadata", "album=" + addMusicDTO.getAlbum(), "-metadata", "artist=" + singersStr.substring(0, singersStr.length() - 1), "-metadata", "genre=" + addMusicDTO.getGenre(), "-metadata", "title=" + addMusicDTO.getMusicTitle(), "-y", targetSrc);
		// try {
		// 	transformBuilder.inheritIO().start().waitFor();
		// 	fileService.deleteFile(tempPath, filename);
		// } catch (InterruptedException | IOException e) {
		// 	System.out.println(e);
		// 	return Optional.empty();
		// }
		return Optional.of(filename);
	}
	
	private Optional<String> writeInMusicInfo(String targetPath, String filename, UpdateMusicDTO updateMusicDTO) {
		// fileService.uploadFile(song, tempPath, filename);
		String srcPath = getTargetSrc(tempPath, filename);
		// String targetSrc = getTargetSrc(targetPath, filename);
		// String ffmpeg = Loader.load(ffmpeg.class);
		// String singersStr = "";
		// for (Singer singer : updateMusicDTO.getSingerList()) {
		// 	singersStr += singer.getSingerName() + ",";
		// }
		// ProcessBuilder transformBuilder = new ProcessBuilder(ffmpeg, "-i", srcPath, "-metadata", "album=" + updateMusicDTO.getAlbum(), "-metadata", "artist=" + singersStr.substring(0, singersStr.length() - 1), "-metadata", "genre=" + updateMusicDTO.getGenre(), "-metadata", "title=" + updateMusicDTO.getMusicTitle(), "-y", targetSrc);
		// try {
		// 	transformBuilder.inheritIO().start().waitFor();
		// 	fileService.deleteFile(tempPath, filename);
		// } catch (InterruptedException | IOException e) {
		// 	System.out.println(e);
		// 	return Optional.empty();
		// }
		return Optional.of(filename);
	}
	
	private Optional<String> writeInMusicInfo(String targetPath, String filename) {
		String srcPath = getTargetSrc(tempPath, filename);
		String targetSrc = getTargetSrc(targetPath, filename);
		String ffmpeg = Loader.load(ffmpeg.class);
		ProcessBuilder transformBuilder = new ProcessBuilder(ffmpeg, "-i", srcPath, "-y", targetSrc);
		try {
			transformBuilder.inheritIO().start().waitFor();
			fileService.deleteFile(tempPath, filename);
		} catch (InterruptedException | IOException e) {
			System.out.println(e);
			return Optional.empty();
		}
		return Optional.of(filename);
	}
	
	public Map getMusicInfo(String filePath, String filename) {
		String targetPath = getTargetSrc(filePath, filename);
		Runtime run = null;
		Process p = null;
		try {
			run = Runtime.getRuntime();
			p = run.exec("ffprobe -v quiet -show_format -show_streams -print_format json " + targetPath);
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			StringBuffer sb = new StringBuffer();
			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				sb.append(lineStr);
			}
			if (p.waitFor() != 0) {
				if (p.exitValue() == 1)
					System.out.println("执行失败");
				return null;
			}
			return (Map) JSON.parse(sb.toString());
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				p.getOutputStream().close();
				p.getInputStream().close();
				p.getErrorStream().close();
				run.freeMemory();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return null;
	}
	
	public Map getMusicInfo(MultipartFile song, String musicName) {
		fileService.uploadFile(song, tempPath, musicName);
		Map musicInfo = getMusicInfo(tempPath, musicName);
		// fileService.deleteFile(tempPath, musinName);
		return musicInfo;
		// this.saveMusic(song, tempPath, filename)
	}
	
	public String getMusicLevel(Integer br) {
		if (br <= 64000) {
			return "流畅";
		}
		if (br <= 128000) {
			return "高品";
		}
		if (br <= 640000) {
			return "超高品";
		}
		return "无损";
	};
}

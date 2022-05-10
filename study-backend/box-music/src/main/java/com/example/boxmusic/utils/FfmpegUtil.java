package com.example.boxmusic.utils;

import org.bytedeco.ffmpeg.ffmpeg;
import org.bytedeco.javacpp.Loader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

public class FfmpegUtil {
	
	private static String getTargetSrc(String target, String fileName) {
		if (target.endsWith("/")) {
			return Value.MUSIC_HOME + target + fileName;
		}
		return Value.MUSIC_HOME + target + '/' + fileName;
	}
	
	private static Optional<String> doTransform(String fromSrc, String toSrc, String bitrate) {
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
	
	public static String transformAudio(String from, String fileName, String to, String targetFileName, String bitrate) {
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
		return "转换出错";
	}
}

package cn.cslg.applysystem.utils;

// import cn.cslg.applysystem.pojo.entity.File;

import java.io.File;

/**
 * Author Admin
 * Create 2021/5/30 14:31
 */
public class FileUtils {
	
	public static boolean deleteFile(String path, String fileName) {
		File file = new File(path + fileName);
		if (file.exists()) {
			if (file.delete()) {
				return true;
			}
			return false;
		}
		return false;
	}
	
}

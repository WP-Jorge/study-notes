package cn.cslg.applysystem.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUploadUtils {

    //文件上传
    public static String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();  // 文件名

        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名

        fileName = UUID.randomUUID() + "-" + fileName; // 新文件名

        File dest = new File(path + fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
    
    public static String upload(MultipartFile file, String path, String fileName){
        
        File dest = new File(path + fileName);
        
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return fileName;
    }
    
    public static String getFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();  // 文件名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + "-" + fileName; // 新文件名
        return fileName;
    }
}

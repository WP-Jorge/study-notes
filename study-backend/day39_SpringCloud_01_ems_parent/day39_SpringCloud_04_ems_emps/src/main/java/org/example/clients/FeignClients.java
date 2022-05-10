package org.example.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient("files")
public interface FeignClients {
	// 调用文件上传服务 使用openfeign文件上传需要这样 consumes代表接收文件的类型 参数需要添加@RequestPart("photo")
	@PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	Map<String, Object> upload(@RequestPart("photo") MultipartFile photo);
}

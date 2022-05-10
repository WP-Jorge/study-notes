package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddAllApplyDTO;
import cn.cslg.applysystem.pojo.dto.UpdateAllApplyDTO;
import cn.cslg.applysystem.pojo.entity.*;
import cn.cslg.applysystem.pojo.vo.AllApplyVO;
import cn.cslg.applysystem.service.*;
import cn.cslg.applysystem.utils.FileUploadUtils;
import cn.cslg.applysystem.utils.FileUtils;
import cn.cslg.applysystem.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/allApply")
@Validated
public class AllApplyController {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private FileService fileService;
	@Autowired
	private UserApplyService userApplyService;
	@Autowired
	private InstructorApplyService instructorApplyService;

	@Autowired
	private AllApplyService allApplyService;
	private String PICPATH = System.getProperty("user.dir") + "\\src\\main\\resources\\systemImgs\\";
	private String HOST = "http://localhost:";
	@Value("${server.port}")
	private int serverPort;
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加成果")
	@PostMapping("/addApply")
	public R addApply(@Valid AddAllApplyDTO addAllApplyDTO) {
		List<Integer> studentList = addAllApplyDTO.getStudentList();
		// if (studentList == null || studentList.size() == 0) {
		// 	return R.ok("参赛成员不能为空");
		// }
		List<Integer> teacherList = addAllApplyDTO.getTeacherList();
		// if (teacherList == null || teacherList.size() == 0) {
		// 	return R.ok("指导教师不能为空");
		// }
		
		AllApply allApply = new AllApply();
		allApply.setCompeteId(addAllApplyDTO.getCompeteId());
		allApply.setDeptId(addAllApplyDTO.getDeptId());
		allApply.setAwardId(addAllApplyDTO.getAwardId());
		allApply.setAwardTime(addAllApplyDTO.getAwardTime());
		allApply.setAgencyId(addAllApplyDTO.getAgencyId());
		allApply.setUserId(addAllApplyDTO.getUserId());
		allApply.setIsGroup(addAllApplyDTO.getIsGroup());
		allApply.setDes(addAllApplyDTO.getDes());
		
		System.out.println(allApply);
		MultipartFile file = addAllApplyDTO.getFile();
		if(file == null){
			return R.ok("请上传获奖证书图片");
		}
		try {
			BufferedImage read = ImageIO.read(file.getInputStream());
			if (read == null) {
				return R.ok("图片非法，请上传正确的图片");
			}
		} catch (IOException e) {
			return R.ok("上传图片出错");
		}
		
		// TODO 对上传的文件先上传到数据库中，如果所有的数据成功都存储到数据库中，再将文件保存到本地文件中，如果保存失败则进行进行数据库的回滚
		// String fileName = FileUploadUtils.upload(file, PICPATH);
		String fileName = FileUploadUtils.getFileName(file);
		
		String filePath = fileName;
		File file1 = new File();
		file1.setFileName(fileName);
		file1.setFilePath("/allApply/pic/" + filePath);
		file1.setFileType("证书图片");
		file1.setRefCount(1);
		boolean save1 = fileService.save(file1);
		if (save1) {
			allApply.setFileId(file1.getFileId());
		}
		try {
			boolean save = allApplyService.save(allApply);
			ArrayList<UserApply> userApplies = new ArrayList<>();
			for (Integer studentId : studentList) {
				UserApply userApply = new UserApply();
				userApply.setUserId(studentId);
				userApply.setAllApplyId(allApply.getAllApplyId());
				userApplies.add(userApply);
			}
			boolean saveBatch = userApplyService.saveBatch(userApplies);
			ArrayList<InstructorApply> instructorApplies = new ArrayList<>();
			int i = 1;
			for (Integer teacherId : teacherList) {
				InstructorApply instructorApply = new InstructorApply();
				instructorApply.setInstructorId(teacherId);
				instructorApply.setApplyOrder(i++);
				instructorApply.setAllApplyId(allApply.getAllApplyId());
				instructorApplies.add(instructorApply);
			}
			boolean saveBatch1 = instructorApplyService.saveBatch(instructorApplies);
			
			// 手动清除redis中的UserMapper缓存
			redisTemplate.delete("cn.cslg.applysystem.mapper.UserMapper");
			if (save && saveBatch && saveBatch1) {
				return R.ok("申报成功");
			}
			boolean deleteFile = FileUtils.deleteFile(PICPATH, fileName);
			return R.ok("申报失败");
		} catch (DataIntegrityViolationException e) {
			boolean deleteFile = FileUtils.deleteFile(PICPATH, fileName);
			throw new DataIntegrityViolationException("数据完整性违规异常，请检查各项数据是否正确");
		}
	}
	
	@ApiOperation("获取图片")
	@GetMapping("/pic/{fileName}")
	public void getPic(HttpServletResponse response, @PathVariable("fileName") String name) throws IOException {
		String suffixName = name.substring(name.indexOf("."));
		response.setContentType("image/"+suffixName+";charset=utf-8");
		response.setHeader("Content-Disposition", "inline; filename=" + name);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(Files.readAllBytes(Paths.get(PICPATH).resolve(name)));
		outputStream.flush();
		outputStream.close();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("删除成果（物理删除）")
	@DeleteMapping("/deleteApplyTrue")
	public R deleteApplyTrue(@PathParam("allApplyId") @NotNull(message = "成果id不能为空") Integer allApplyId) {
		try {
			AllApply apply = allApplyService.getById(allApplyId);
			if (apply == null) {
				return R.ok("该申报成果不存在");
			}
			File file = fileService.getById(apply.getFileId());
			String fileName = file.getFileName();
			
			int removeApply = allApplyService.deleteAllApplyTrue(allApplyId);
			if (removeApply <= 0) {
				throw new Exception("删除成果失败");
			}
			boolean removeFile = fileService.removeById(apply.getFileId());
			if (!removeFile) {
				throw new Exception("删除成果失败");
			}
			boolean deleteFile = FileUtils.deleteFile(PICPATH, fileName);
			if (deleteFile == false) {
				throw new Exception("删除申报成果成功，文件删除失败，可能是文件不存在");
			}
			return R.ok("删除申报成果成功");
		} catch (Exception e) {
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.ok(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("删除成果（逻辑删除）")
	@DeleteMapping("/deleteApply")
	public R deleteApply(@PathParam("allApplyId") @NotNull(message = "成果id不能为空") Integer allApplyId) {
		// AllApply apply = allApplyService.getById(allApplyId);
		// if (apply == null) {
		// 	return R.ok("该申报成果不存在");
		// }
		boolean removeApply = allApplyService.removeById(allApplyId);
		// 手动清除redis中的UserMapper缓存
		redisTemplate.delete("cn.cslg.applysystem.mapper.UserMapper");
		if (removeApply) {
			return R.ok("删除申报成果成功");
		}
		return R.ok("删除申报成果失败");
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量删除成果（逻辑删除）")
	@DeleteMapping("/deleteApplies")
	public R deleteApplies(@RequestBody @NotEmpty(message = "成果id不能为空") List<Integer> allApplyIds) {
		// AllApply apply = allApplyService.getById(allApplyId);
		// if (apply == null) {
		// 	return R.ok("该申报成果不存在");
		// }
		
		boolean removeApply = allApplyService.removeByIds(allApplyIds);
		// 手动清除redis中的UserMapper缓存
		redisTemplate.delete("cn.cslg.applysystem.mapper.UserMapper");
		if (removeApply) {
			return R.ok("批量删除申报成果成功");
		}
		return R.ok("批量删除申报成果失败");
	}
	
	// @ApiOperation("批量删除成果（物理删除）")
	// @DeleteMapping("/deleteAppliesTrue")
	// public R deleteAppliesTrue(@RequestBody @NotNull(message = "成果id不能为空") List<Integer> allApplyId) {
	// 	try {
	// 		AllApply apply = allApplyService.getById(allApplyId);
	// 		if (apply == null) {
	// 			return R.ok("该申报成果不存在");
	// 		}
	// 		File file = fileService.getById(apply.getFileId());
	// 		String fileName = file.getFileName();
	//
	// 		int removeApply = allApplyService.deleteAllApplyTrue(allApplyId);
	// 		if (removeApply <= 0) {
	// 			throw new Exception("删除成果失败");
	// 		}
	// 		boolean removeFile = fileService.removeById(apply.getFileId());
	// 		if (!removeFile) {
	// 			throw new Exception("删除成果失败");
	// 		}
	// 		boolean deleteFile = FileUtils.deleteFile(PICPATH, fileName);
	// 		if (deleteFile == false) {
	// 			throw new Exception("文件删除失败");
	// 		}
	// 		return R.ok("删除申报成果成功");
	// 	} catch (Exception e) {
	// 		// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	// 		return R.ok(e.getMessage());
	// 	}
	// }
	
	@ApiOperation("获取所有的申报成果")
	@GetMapping("/getAllAppliesWithPage")
	public R getAllAppliesWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                               @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map> page = new Page<Map>(currentPage, pageSize);
		IPage<AllApplyVO> allAppliesWithPage = allApplyService.getAllAppliesWithPage(page);
		return R.ok("applies", Collections.singletonList(allAppliesWithPage));
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("修改成果")
	@PostMapping("/updateApply")
	public R updateApply(@Valid UpdateAllApplyDTO updateAllApplyDTO) {
		List<Integer> studentList = updateAllApplyDTO.getStudentList();
		// if (studentList == null || studentList.size() == 0) {
		// 	return R.ok("参赛成员不能为空");
		// }
		List<Integer> teacherList = updateAllApplyDTO.getTeacherList();
		// if (teacherList == null || teacherList.size() == 0) {
		// 	return R.ok("指导教师不能为空");
		// }
		
		AllApply allApply = new AllApply();
		allApply.setAllApplyId(updateAllApplyDTO.getAllApplyId());
		allApply.setCompeteId(updateAllApplyDTO.getCompeteId());
		allApply.setDeptId(updateAllApplyDTO.getDeptId());
		allApply.setAwardId(updateAllApplyDTO.getAwardId());
		allApply.setAwardTime(updateAllApplyDTO.getAwardTime());
		allApply.setAgencyId(updateAllApplyDTO.getAgencyId());
		allApply.setUserId(updateAllApplyDTO.getUserId());
		allApply.setIsGroup(updateAllApplyDTO.getIsGroup());
		allApply.setDes(updateAllApplyDTO.getDes());
		allApply.setFileId(updateAllApplyDTO.getFileId());
		
		System.out.println(allApply);
		MultipartFile file = updateAllApplyDTO.getFile();
		System.out.println(file + "222222222222222222222222222222222");
		if(file != null){
			try {
				BufferedImage read = ImageIO.read(file.getInputStream());
				if (read == null) {
					return R.ok("图片非法，请上传正确的图片");
				}
			} catch (IOException e) {
				return R.ok("上传图片出错");
			}
			
			File file2 = fileService.getById(updateAllApplyDTO.getFileId());
			boolean deleteFile = FileUtils.deleteFile(PICPATH, file2.getFileName());
			
			System.out.println(deleteFile + "11111111111111111111111111111");
			
			String fileName = FileUploadUtils.upload(file, PICPATH);
			
			
			String filePath = fileName;
			File file1 = new File();
			file1.setFileId(updateAllApplyDTO.getFileId());
			file1.setFileName(fileName);
			file1.setFilePath("/allApply/pic/" + filePath);
			file1.setFileType("证书图片");
			file1.setRefCount(1);
			boolean save1 = fileService.updateById(file1);
			if (save1) {
				allApply.setFileId(file1.getFileId());
			}
		}
		
		try {
			boolean save = allApplyService.updateById(allApply);
			ArrayList<UserApply> userApplies = new ArrayList<>();
			for (Integer studentId : studentList) {
				UserApply userApply = new UserApply();
				userApply.setUserId(studentId);
				userApply.setAllApplyId(allApply.getAllApplyId());
				userApplies.add(userApply);
			}
			HashMap<String, Object> columnMap = new HashMap<>();
			columnMap.put("all_apply_id", allApply.getAllApplyId());
			userApplyService.removeByMap(columnMap);
			boolean saveBatch = userApplyService.saveBatch(userApplies);
			ArrayList<InstructorApply> instructorApplies = new ArrayList<>();
			int i = 1;
			for (Integer teacherId : teacherList) {
				InstructorApply instructorApply = new InstructorApply();
				instructorApply.setInstructorId(teacherId);
				instructorApply.setApplyOrder(i++);
				instructorApply.setAllApplyId(allApply.getAllApplyId());
				instructorApplies.add(instructorApply);
			}
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("all_apply_id", allApply.getAllApplyId());
			instructorApplyService.removeByMap(columnMap);
			boolean saveBatch1 = instructorApplyService.saveBatch(instructorApplies);
			
			if (save && saveBatch && saveBatch1) {
				return R.ok("更新成果申报成功");
			}
			// deleteFile = FileUtils.deleteFile(PICPATH, fileName);
			return R.ok("更新成果申报失败");
		} catch (DataIntegrityViolationException e) {
			// deleteFile = FileUtils.deleteFile(PICPATH, fileName);
			throw new DataIntegrityViolationException("数据完整性违规异常，请检查各项数据是否正确");
		}
	}
}


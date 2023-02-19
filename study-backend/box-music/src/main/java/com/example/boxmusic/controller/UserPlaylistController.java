package com.example.boxmusic.controller;


import com.example.boxmusic.pojo.entity.UserPlaylist;
import com.example.boxmusic.pojo.vo.UserVO;
import com.example.boxmusic.service.UserPlaylistService;
import com.example.boxmusic.utils.R;
import com.example.boxmusic.utils.Value;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@RestController
@RequestMapping("/userPlaylist")
public class UserPlaylistController {
	

	
}


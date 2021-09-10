package com.jacdong.interview.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jacdong.interview.common.CommonResult;
import com.jacdong.interview.common.ResultCode;
import com.jacdong.interview.user.common.ApiVersion;
import com.jacdong.interview.user.constant.UserWanrningMessage;
import com.jacdong.interview.user.service.UserService;
import com.jacdong.interview.user.vo.UserVO;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author DongBin
 *
 */
@RestController
@RequestMapping("/{version}/user")
@ApiVersion(1)
@Api(tags = "用户管理模块") // 描述
public class UserController {

	@Autowired
	private UserService userService;

//	@GetMapping("/currentUser")
//	public LoginUserVO currentUser() {
//		return loginUserHolder.getCurrentUser();
//	}

	@ApiOperation(value = "新加用户", notes = "传入参数是UserVO对象")
	@PostMapping("add")
	@SentinelResource(value = "add", fallback = "userFallback", blockHandler = "userhandleException")
	public CommonResult<UserVO> create(
			@ApiParam(value = "用户基本信息实例", required = true) @Validated @RequestBody UserVO userVO) {

		int result = userService.create(userVO);

		if (result > 0) {
			return CommonResult.success(userVO);
		} else {
			return CommonResult.failed(ResultCode.FAILED, UserWanrningMessage.USER_ERROR_CREATE_FAIL_MSG);
		}

	}

	/**
	 * 
	 * @Title: user
	 * @Description: 通过userId 获取用户信息，包括基本信息和扩展信息 1. 查询时候，需要增加缓存实现系统的及时响应
	 * @param userId
	 * @return
	 * @author DongBin
	 * @date 2021-09-08 05:01:03
	 */
	@GetMapping("get")
	@ApiOperation(value = "查询用户", notes = "传入参数是UserVO对象")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询用户成功") })
	@SentinelResource(value = "query", fallback = "userFallback", blockHandler = "userhandleException")
	public CommonResult<UserVO> user(@RequestParam(value = "userId") String userId) {

		UserVO userVO = userService.getUser(userId);

		return CommonResult.success(userVO);

	}

	/**
	 * 
	 * @Title: user
	 * @Description: 1. 注意并发更新操作。
	 * @param userVO
	 * @return
	 * @author DongBin
	 * @date 2021-09-08 05:03:37
	 */
	@ApiOperation(value = "更新用户", notes = "传入参数是UserVO对象")
	@ApiResponses({ @ApiResponse(code = 200, message = "用户更新成功") })
	@PutMapping("update")
	@SentinelResource(value = "update", fallback = "userFallback", blockHandler = "userhandleException")
	public CommonResult<UserVO> update(
			@ApiParam(value = "用户基本信息实例", required = true) @Validated @RequestBody UserVO userVO) {

		int count = userService.update(userVO);

		if (count == 0) {
			CommonResult.failed(ResultCode.FAILED, UserWanrningMessage.USER_ERROR_DATA_CHANGE_MSG);
		}

		// 删除缓存
		return CommonResult.success(userVO);

	}

	/**
	 * 
	 * @Title: delUser
	 * @Description: TODO(描述)
	 * @param userId
	 * @return
	 * @author DongBin
	 * @throws InterruptedException
	 * @date 2021-09-08 05:39:55
	 */
	@ApiOperation(value = "删除用户", notes = "传入参数是用户的主键")
	@ApiResponses({ @ApiResponse(code = 200, message = "用户删除成功"), 
		@ApiResponse(code = 500, message = "系统发生错误") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", dataType = "string", paramType = "delete", required = true, defaultValue = "USER223590125559484416") })
	@DeleteMapping("del")
	@SentinelResource(value = "delete", fallback = "userFallback", blockHandler = "userhandleException")
	public CommonResult<String> delUser(@RequestParam(value = "userId") String userId) throws InterruptedException {

		int count = userService.delUser(userId);

		if (count == 0) {
			CommonResult.failed(ResultCode.FAILED, UserWanrningMessage.USER_ERROR_DELETE_FAIL_MSG);
		}
		return CommonResult.success(userId, UserWanrningMessage.USER_ERROR_DELETE_SUCCESS_MSG);
	}

	public CommonResult<String> userFallback() {
		return CommonResult.success("DEGRADATION", UserWanrningMessage.SERVICE_DEGRADATION_SUCCESS_MSG);
//    	return new CommonResult(200,"降级成功.","降级");
	}

	public CommonResult<String> handleException(BlockException exception) {
//		return new CommonResult(200, "限流成功.", "限流");
		return CommonResult.success("LIMITING", UserWanrningMessage.SERVICE_LIMITING_SUCCESS_MSG);
	}
}

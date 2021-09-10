package com.jacdong.interview.user.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jacdong.interview.common.id.BussinessType;
import com.jacdong.interview.common.id.SnowFlakeFactory;
import com.jacdong.interview.user.mapper.LoginUserMapper;
import com.jacdong.interview.user.mapper.UserExtInfoMapper;
import com.jacdong.interview.user.mapper.UserMapper;
import com.jacdong.interview.user.service.UserService;
import com.jacdong.interview.user.vo.LoginUserVO;
import com.jacdong.interview.user.vo.UserExtInfo;
import com.jacdong.interview.user.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
	public static final String PREFIX_USER_REDIS = "USER_";

	public static final Long USER_INFO_EXPIRED_TIME = 60L;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SnowFlakeFactory snowFlakeFactory;

	@Autowired
	private LoginUserMapper loginMapper;

	@Autowired
	private UserExtInfoMapper extInfoMapper;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(rollbackFor = { RuntimeException.class, Error.class }, isolation = Isolation.READ_COMMITTED)
	@Override
	public int create(UserVO userVo) {
		// TODO Auto-generated method stub

		// 1. 获取主键
		String userId = snowFlakeFactory.nextId(BussinessType.USER);

		userVo.setUserId(userId);

		int userCount = userMapper.create(userVo);

		// 2. 创建用户角色相关信息
		List<UserExtInfo> extInfos = buildDefaultExtInfo(userVo);

		int extCount = extInfoMapper.createExtInfo(extInfos);
		log.info("{} extend information has been built for user : {}", extCount, userVo.getUserName());

		// 2. 创建关联的登录用户信息
		LoginUserVO vo = buildDefaultLoginVO(userVo);

		int loginCount = loginMapper.create(vo);

		// 3. 将新加的用户信息保存到缓存

		String redisKey = PREFIX_USER_REDIS + userVo.getUserId();
		if (userCount > 0 && loginCount > 0 && extCount > 0) {
			redisTemplate.opsForValue().set(redisKey, userVo, Duration.ofSeconds(USER_INFO_EXPIRED_TIME));
		} else {
			log.warn(
					"There has {} user build and {} extend information saved , {} login information saved .please check ",
					userCount, extCount, loginCount);
		}

		return userCount;
	}

	private List<UserExtInfo> buildDefaultExtInfo(UserVO userVo) {

		List<UserExtInfo> extInfos = new ArrayList<UserExtInfo>();
		UserExtInfo extInfo = new UserExtInfo();
		extInfo.setUserId(userVo.getUserId());
		extInfo.setItemKey("ROLES");
		extInfo.setItemValue("TEST");
		extInfo.setComments("Default role has built with user create");
		extInfos.add(extInfo);

//		int result = extInfoMapper.createExtInfo(extInfos);
//
//		log.info("{} extend information has been built for user : {}", result, userVo.getUserName());

		return extInfos;
	}

	/**
	 * 
	 * @Title: buildDefaultLoginVO
	 * @Description: build login User information
	 * @param userVo
	 * @return
	 * @author DongBin
	 * @date 2021-09-09 06:21:16
	 */
	private LoginUserVO buildDefaultLoginVO(UserVO userVo) {
		// TODO Auto-generated method stub
		LoginUserVO loginUserVO = new LoginUserVO();

		String loginId = snowFlakeFactory.nextId(BussinessType.LOGIN);
		loginUserVO.setLoginId(loginId);
		loginUserVO.setUserId(userVo.getUserId());
		loginUserVO.setUsername(userVo.getUserName());
		loginUserVO.setPassword(passwordEncoder.encode("pass"));
//		loginUserVO.setRole("TEST");
		loginUserVO.setStatus(1);
		return loginUserVO;
	}

	@Override
	public int update(UserVO userVO) {

		// 第一步查询数据库

		UserVO orignVo = this.getUser(userVO.getUserId());

		userVO.setVer(orignVo.getVer());
		// 第二步 更新操作
		int count = userMapper.updateUserVO(userVO);

		// 第三步 删除缓存
		if (count > 0) {
			redisTemplate.delete(PREFIX_USER_REDIS + orignVo.getUserId());
		}

		return count;
	}

	@Override
	public UserVO getUser(String userId) {

		String userKey = PREFIX_USER_REDIS + userId;
//		Object object = redisTemplate.opsForValue().get(userKey);

		// 1. 先从缓存中获取数据
		UserVO vo = (UserVO) redisTemplate.opsForValue().get(userKey);

		if (vo != null) {

			// 如果存在，直接返回
			return vo;
		}

		// 2. 当缓存中没有用户信息时，从数据库中获取数据
		UserVO userVO = userMapper.getUserVO(userId);

		// 3. 将查询的用户信息保存到缓存
		if (userVO != null) {
			redisTemplate.opsForValue().set(userKey, userVO, Duration.ofSeconds(USER_INFO_EXPIRED_TIME));
		}
		// TODO Auto-generated method stub
		return userVO;
	}

	@Override
	public int delUser(String userId) throws InterruptedException {
		// TODO Auto-generated method stub

		String redisKey = PREFIX_USER_REDIS + userId;

		// 1. 删除缓存记录
		redisTemplate.delete(redisKey);

		// 2. 删除数据库记录
		int count = loginMapper.update(userId);

		// 3. 删除缓存
		Thread.sleep(200);
		redisTemplate.delete(redisKey);

		return count;
	}

}

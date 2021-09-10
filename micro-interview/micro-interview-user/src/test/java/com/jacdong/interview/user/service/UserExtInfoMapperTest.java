package com.jacdong.interview.user.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jacdong.interview.user.mapper.UserExtInfoMapper;
import com.jacdong.interview.user.vo.UserExtInfo;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserExtInfoMapperTest {
	
	@Autowired
	private UserExtInfoMapper userExtInfoMapper;
	
    
	@Test
	public void testQuery() {
		System.out.println("----------------");
	
		List<UserExtInfo> vosExtInfos  = userExtInfoMapper.userExtInfos("1");
		
		for(UserExtInfo voExtInfo : vosExtInfos) {
			System.out.println(voExtInfo.toString());
		}
	}
}

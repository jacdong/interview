package com.jacdong.interview.user.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jacdong.interview.common.id.BussinessType;
import com.jacdong.interview.common.id.SnowFlakeFactory;
import com.jacdong.interview.user.vo.UserVO;

//@MybatisTest 
//@RunWith(SpringJUnit4ClassRunner.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)   
//@Rollback(false)    //这个是默认是回滚，不会commit入数据库，改成false 则commit

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest{
	
	@Autowired
	private UserService userService;
	
    
    @Autowired
    private SnowFlakeFactory snowFlakeFactory;
    
	@Test
	public void testCreate() {
		System.out.println("----------------");
		
		String dob = "2020-10-10";
		UserVO vo1 = new UserVO(snowFlakeFactory.nextId(BussinessType.USER), "jacdong", 30, dob, "AnhuiHeFei");
		int result = userService.create(vo1);
		System.out.println("result :"+result);
	}
	
	
	@Test
	public void testQuery() {
		System.out.println("----------------");
	
		UserVO vo = userService.getUser("USER223590125559484416");
		
		System.out.println(vo.toString());
	}
	
	@Test
	public void testUpdate() {
		UserVO vo = new UserVO("USER223590125559484416", "jacdong", 31, "1989-12-20", "Hefei hi-distinct");
		int count = userService.update(vo);
		
		System.out.println("count :"+count);
	}
}
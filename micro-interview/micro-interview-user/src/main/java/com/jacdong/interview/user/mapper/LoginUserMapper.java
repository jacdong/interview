package com.jacdong.interview.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import com.jacdong.interview.user.vo.LoginUserVO;

@Mapper
public interface LoginUserMapper {
	
	@Update("update t_user_login set status = 1 where userId = #{userId}")
	public int update(String userId);
	
	@Insert("insert t_user_login (loginid , userid , username , password , status , role , createTime , lastmodifiedTime) "
			+ "values (#{loginId},#{userId},#{username} , #{password},#{status},#{role},now(),now())")
	public int create(LoginUserVO userVO);
	
	@Select("select userId , username , password ,role , status from t_user_login where username = #{username} and password = #{password}")
	public LoginUserVO user(String username,String password);
}

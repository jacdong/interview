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
	
	@Insert("insert t_user_login (loginid , userid , username , password , status , createTime , lastmodifiedTime) "
			+ "values (#{loginId},#{userId},#{username} , #{password},#{status},now(),now())")
	public int create(LoginUserVO userVO);
	
	@Select("select userId , username , password , status from t_user_login where username = #{username}")
	public LoginUserVO user(String username);
}

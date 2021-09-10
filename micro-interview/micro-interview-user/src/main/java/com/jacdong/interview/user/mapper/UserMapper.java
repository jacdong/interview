package com.jacdong.interview.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import org.mapstruct.Mapper;

import com.jacdong.interview.user.vo.UserVO;

@Mapper
public interface UserMapper {

	@Insert("insert into t_user_info (userId , username , dob,createAt,address,lastmodified) values (#{userId}, #{userName},str_to_date(#{dob},'%Y-%m-%d'),now(),#{address},now())")
	public int create(UserVO userVO);

	@Select(" SELECT * FROM t_user_info WHERE userId = #{userId}")
	@Results({ 	@Result(property = "userId", column = "userId"), 
				@Result(property = "userName", column = "userName"),
				@Result(property = "dob", column = "dob"), 
				@Result(property = "address", column = "address"),
				@Result(property = "ver" , column = "ver"),
				@Result(property = "extInfos", column = "userId", many = @Many(select = "com.jacdong.interview.user.mapper.UserExtInfoMapper.userExtInfos", fetchType = FetchType.LAZY)) })
	public UserVO getUserVO(String userId);
	
	@Update("update t_user_info set username = #{userName} , dob = str_to_date(#{dob},'%Y-%m-%d') ,address=#{address},ver=#{ver}+1 "
			+ "where userId = #{userId} and ver=#{ver}")
	public int updateUserVO(UserVO userVO);
	
}

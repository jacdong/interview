package com.jacdong.interview.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import com.jacdong.interview.user.vo.UserExtInfo;

@Mapper
public interface UserExtInfoMapper {

	@Select("SELECT userId , item_key , item_value , comments FROM t_user_ext_info WHERE userId = #{userId}")
	@Results({ @Result(property = "userId", column = "userId"),
			@Result(property = "itemKey", column = "item_key"),
			@Result(property = "itemValue", column = "item_value"), 
			@Result(property = "comments", column = "comments")
	})
	public List<UserExtInfo> userExtInfos(String userId);
}

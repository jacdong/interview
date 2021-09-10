package com.jacdong.interview.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import com.jacdong.interview.user.vo.UserExtInfo;

@Mapper
public interface UserExtInfoMapper {

	@Select("SELECT userId , item_key , item_value , comments FROM t_user_ext_info WHERE userId = #{userId}")
	@Results({ @Result(property = "userId", column = "userId"), @Result(property = "itemKey", column = "item_key"),
			@Result(property = "itemValue", column = "item_value"),
			@Result(property = "comments", column = "comments") })
	public List<UserExtInfo> userExtInfos(String userId);

	@Insert({"<script>", 
				"insert into t_user_ext_info (userId , item_key , item_value , comments) values ",
				"<foreach collection ='list' item='extInfo' separator =',' >",
					"(#{extInfo.userId},#{extInfo.itemKey},#{extInfo.itemValue},#{extInfo.comments})", 
				"</foreach>", 
			"</script>" })
	public int createExtInfo(List<UserExtInfo> extInfos);
}

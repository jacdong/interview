package com.jacdong.interview.user.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("用户登录信息")
public class LoginUserVO  implements Serializable{
	
	/**  
	 * @Fields serialVersionUID : TODO(描述)
	 * @author DongBin
	 * @date 2021-09-08 11:07:32 
	 */  
	
	private static final long serialVersionUID = -7953342243155424987L;

	@ApiModelProperty(name = "登录信息主键")
	private String loginId;
	
	@ApiModelProperty(name = "用户登录名")
	private String username;
	
	@ApiModelProperty(name = "用户ID")
	private String userId;
	
	@ApiModelProperty(name = "用户密码")
	private String password;
	
	@ApiModelProperty(name = "用户状态")
	private int status;
	
	@ApiModelProperty(name = "用户角色")
	private String role;
	
	@ApiModelProperty(name = "记录创建时间")
	private Date createTime;
	
	@ApiModelProperty(name = "记录修改时间")
	private Date lastMdofiedTime;
}

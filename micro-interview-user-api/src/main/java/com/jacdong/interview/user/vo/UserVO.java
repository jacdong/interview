package com.jacdong.interview.user.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.jacdong.interview.user.validation.annotation.DateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("用户基本信息")
public class UserVO  implements Serializable{

	/**  
	 * @Fields serialVersionUID : TODO(描述)
	 * @author DongBin
	 * @date 2021-09-08 11:06:37 
	 */  
	
	private static final long serialVersionUID = -7138613810724055486L;

	@ApiModelProperty(name = "用户ID")
	private String userId;

	@ApiModelProperty("用户姓名")
	@NotBlank(message = "用户名不能为空")
	private String userName;
	
	@ApiModelProperty("用户年龄")
	@Max(value = 150, message = "年龄最多只能是150岁")
	private int age;

	@ApiModelProperty("用户生日")
	@DateTime(format = "yyyy-MM-dd", message = "格式错误，正确格式为：YYYY-MM-DD")
	private String dob;

	@ApiModelProperty("用户地址")
	@Length(max = 128,message = "地址长度限制为150 个字符长度.")
	private String address;
	
	@ApiModelProperty("用户版本信息")
	private int ver;
	
	@ApiModelProperty("扩展属性信息")
	private List<UserExtInfo> extInfos;
	
	
	public UserVO(String userId , String userName , int age , String dob , String address) {
		this.userId = userId;
		this.userName = userName;
		this.age = age;
		this.dob = dob;
		this.address = address;
	}
}

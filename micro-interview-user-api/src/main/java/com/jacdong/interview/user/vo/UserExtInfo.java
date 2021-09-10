package com.jacdong.interview.user.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户扩展信息表")
public class UserExtInfo  implements Serializable{
	
	/**  
	 * @Fields serialVersionUID : TODO(描述)
	 * @author DongBin
	 * @date 2021-09-08 11:06:48 
	 */  
	
	private static final long serialVersionUID = -1706628543613861792L;

	/**  
	 * @Fields serialVersionUID : TODO(描述)
	 * @author DongBin
	 * @date 2021-09-08 11:06:29 
	 */  
	

	@ApiModelProperty(name = "扩展属性名称")
	private String itemKey;
	
	@ApiModelProperty(name = "扩展属性值")
	private String itemValue;
	
	
	@ApiModelProperty(name = "用户ID")
	private String userId;
	
	@ApiModelProperty(name = "属性描述")
	private String comments;
}

package com.jacdong.interview.gateway.ex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @ClassName: GlobalException
 * @Description: TODO
 * @author DongBin
 * @date 2021-09-07 10:47:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
	
	/**  
	 * @Fields serialVersionUID : TODO(描述)
	 * @author DongBin
	 * @date 2021-09-07 10:48:04 
	 */  
	
	private static final long serialVersionUID = 8983255314842301458L;
	private Integer code;// 状态码
	private String msg;// 异常信息
	
}

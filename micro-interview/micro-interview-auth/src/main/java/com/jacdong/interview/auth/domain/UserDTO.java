package com.jacdong.interview.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 
 * @ClassName: UserDTO
 * @Description: TODO
 * @author DongBin
 * @date 2021-09-06 08:41:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String username;
	private String password;
	private Integer status;
	private List<String> roles;

}

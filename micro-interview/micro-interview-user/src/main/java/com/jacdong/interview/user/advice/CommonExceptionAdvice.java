package com.jacdong.interview.user.advice;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jacdong.interview.common.CommonResult;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @ClassName: CommonExceptionAdvice
 * @Description: 参数异常拦截
 * @author DongBin
 * @date 2021-09-08 13:51:34
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class CommonExceptionAdvice {
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
	public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		log.error("参数验证失败:{}", e.getMessage());
		
		List<FieldError> errorList = e.getBindingResult().getFieldErrors();
		
		List<String> errorMessages = errorList.stream().map(x -> {
			String itemMessage = messageSource.getMessage(x.getDefaultMessage(), null, x.getDefaultMessage(),
					LocaleContextHolder.getLocale());
			return String.format("%s", itemMessage);
		}).collect(Collectors.toList());
		
		return CommonResult.validateFailed(errorMessages.toString());
	}
}
package com.jacdong.interview.user.common;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 
 * @ClassName: ApiVersion
 * @Description: API 版本控制
 * @author DongBin
 * @date 2021-09-06 03:47:39
 */
public class ApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
	private static final String VERSION_FLAG = "{version}";

	private static RequestCondition<ApiVersionCondition> createCondition(Class<?> clazz) {
		RequestMapping classRequestMapping = clazz.getAnnotation(RequestMapping.class);
		if (classRequestMapping == null) {
			return null;
		}
		StringBuilder mappingUrlBuilder = new StringBuilder();
		if (classRequestMapping.value().length > 0) {
			mappingUrlBuilder.append(classRequestMapping.value()[0]);
		}
		String mappingUrl = mappingUrlBuilder.toString();
		if (!mappingUrl.contains(VERSION_FLAG)) {
			return null;
		}
		ApiVersion apiVersion = clazz.getAnnotation(ApiVersion.class);
		return apiVersion == null ? new ApiVersionCondition(1) : new ApiVersionCondition(apiVersion.value());
	}

	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		return createCondition(method.getClass());
	}

	@Override
	protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
		return createCondition(handlerType);
	}
}

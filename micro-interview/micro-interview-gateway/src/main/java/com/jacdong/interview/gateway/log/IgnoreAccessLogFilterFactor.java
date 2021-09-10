package com.jacdong.interview.gateway.log;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class IgnoreAccessLogFilterFactor extends AbstractGatewayFilterFactory<IgnoreAccessLogFilterFactor.Config> {

	public IgnoreAccessLogFilterFactor() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return this::filter;
	}

	public Mono filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getAttributes().put(AccessLogFilter.ATTRIBUTE_IGNORE_LOG_GLOBAL_FILTER, true);
		return chain.filter(exchange);
	}

	public static class Config {

	}

	@Override
	public String name() {
		return "IgnoreAccessLogFilter";
	}
}

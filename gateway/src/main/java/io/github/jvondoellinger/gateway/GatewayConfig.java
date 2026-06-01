package io.github.jvondoellinger.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class GatewayConfig {



	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			   .route("tk", r -> r.path("/ticket/**")
					 .filters(f -> f.stripPrefix(1))
					 .uri("http://localhost:8001"))
			   .build();
	}
}

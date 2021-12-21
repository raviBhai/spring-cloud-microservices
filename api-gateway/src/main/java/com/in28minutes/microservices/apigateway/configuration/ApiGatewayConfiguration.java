package com.in28minutes.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "Value")
                        )
                        .uri("http://httpbin.org:80")
                )

                //if we add below route, then comment out below props
                //#spring.cloud.gateway.discovery.locator.enabled=true
                //#spring.cloud.gateway.discovery.locator.lower-case-service-id=true
                .route(p -> p.path("/currency-exchange/**")
                            .uri("lb://currency-exchange")
                )
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion")
                )
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion")
                )
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion")
                )

                .build();
    }
}

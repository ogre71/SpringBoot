package com.example.demo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@RestController
public class StringFluxController {

    @Operation(summary = "Get a single string Mono", description = "Returns a Mono emitting a single string.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned a string"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/string-mono", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> getStringMono() {
        return Mono.just("Hello from String Mono!");
    }

    @Operation(summary = "Get a string Flux", description = "Returns a Flux emitting strings with a 1-second delay.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned a stream of strings"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/string-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getStringFlux() {
        return Flux.just("String 1", "String 2", "String 3", "String 4")
                .delayElements(java.time.Duration.ofSeconds(1));
    }

    @Configuration
    public static class OpenApiConfig {
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("String Flux API")
                            .version("1.0")
                            .description("API returning strings using WebFlux."));
        }
    }
}
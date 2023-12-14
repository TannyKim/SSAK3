package org.ssak3.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("돈기브업 REST API")
                .description("가계부 서비스 돈기브업의 REST API입니다.")
                .version("1.0.0");
    }
}

//@RequiredArgsConstructor
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public OpenAPI openAPI() {
//
//        Info info = new Info()
//                .version("1.0.0")
//                .title("돈기브업 REST API")
//                .description("가계부 서비스 돈기브업의 REST API입니다.");
//
//        // SecuritySecheme명
//        String jwtSchemeName = "accessToken";
//        // API 요청헤더에 인증정보 포함
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
//        // SecuritySchemes 등록
//        Components components = new Components()
//                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
//                        .name(jwtSchemeName)
//                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
//                        .scheme("bearer")
//                        .bearerFormat("")); // 토큰 형식을 지정하는 임의의 문자(Optional)
//
//        return new OpenAPI()
//                .info(info)
//                .addSecurityItem(securityRequirement)
//                .components(components);
//    }
//}

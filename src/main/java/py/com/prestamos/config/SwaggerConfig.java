package py.com.prestamos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

    @OpenAPIDefinition(
            info =
            @Info(
                    title = "Alpha - Credit Cards API",
                    version = "1.0",
                    description = "API que expone consulta y pagos de préstamo"
                    ))
    @Configuration
    public class SwaggerConfig {
        @Bean
        ForwardedHeaderFilter forwardedHeaderFilter() {

            return new ForwardedHeaderFilter();

        }
    }


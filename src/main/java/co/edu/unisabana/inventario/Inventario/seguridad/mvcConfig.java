package co.edu.unisabana.inventario.Inventario.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/producto/agregar").setViewName("redirect:/producto/agregar");
        registry.addViewController("/").setViewName("redirect:/verProductoPorCategoria?categoria=default");
        registry.addViewController("/hello").setViewName("redirect:/verProductoPorCategoria?categoria=hello");
        registry.addViewController("/login").setViewName("redirect:login");
    }

}

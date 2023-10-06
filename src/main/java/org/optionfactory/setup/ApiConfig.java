package org.optionfactory.setup;

import org.optionfactory.Main;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(basePackageClasses = Main.class, includeFilters = @Filter(type = FilterType.ANNOTATION, classes = Controller.class))
@Configuration
public class ApiConfig implements WebMvcConfigurer {


}

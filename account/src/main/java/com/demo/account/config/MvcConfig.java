package com.demo.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // cấu hình CORS
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080", "http://127.0.0.1:8080", "http://10.0.2.2:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }


    // Cấu hình ResourceHandler cho các tài nguyên (ảnh, file)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("user-photos" , registry);
    }

    private void exposeDirectory(String dirName , ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if(uploadPath.contains(":")) {
            uploadPath = uploadPath.replace("\\" , "/");
        }

        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file:///" + uploadPath + "/");
    }
}

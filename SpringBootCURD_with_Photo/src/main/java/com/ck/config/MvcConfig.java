package com.ck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNullApi;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        exposeDirectory("product-photos" , registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (uploadPath.contains(":")) {  // Kiểm tra nếu là Windows
            uploadPath = uploadPath.replace("\\", "/");  // Đổi dấu `\` thành `/`
        }

        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file:///" + uploadPath + "/"); // Thêm đủ `/`
    }

}

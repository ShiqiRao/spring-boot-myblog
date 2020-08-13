package com.example.myblog;

import com.example.myblog.config.BlogProperties;
import com.example.myblog.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableConfigurationProperties({BlogProperties.class, FileStorageProperties.class})
@ServletComponentScan(basePackages = {"com.example.myblog.filter"})
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}

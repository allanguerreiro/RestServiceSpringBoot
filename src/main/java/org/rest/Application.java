package org.rest;

import com.github.jmnarloch.spring.boot.modelmapper.ModelMapperConfigurer;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.rest.mapping.SchoolMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by allan on 18/11/16.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.rest.persistence")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public ModelMapperConfigurer createModelMapperConfigurer(final SchoolMapping schoolMapping) {

        return new ModelMapperConfigurer() {
            @Override
            public void configure(ModelMapper modelMapper) {

                modelMapper.getConfiguration()
                        .setFieldMatchingEnabled(true)
                        .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
                modelMapper.addConverter(schoolMapping);
            }
        };
    }
}

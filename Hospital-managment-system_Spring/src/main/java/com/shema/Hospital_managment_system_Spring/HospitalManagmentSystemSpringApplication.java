package com.shema.Hospital_managment_system_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableSwagger2
public class HospitalManagmentSystemSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagmentSystemSpringApplication.class, args);
	}

}

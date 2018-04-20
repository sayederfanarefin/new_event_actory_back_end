package com.sweetitech.tiger.config;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


@Configuration
public class AppConfig {
    
    @Value("${spring.datasource.url}")
    private String datasourceUrl;
//    
    @Value("${spring.datasource.driverClassName}")
    private String dbDriverClassName;
    
    @Value("${spring.datasource.username}")
    private String dbUsername;
    
    @Value("${spring.datasource.password}")
    private String dbPassword;
    
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
	
    @Bean
    public DataSource dataSource() {
   
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName(dbDriverClassName);//"com.mysql.jdbc.Driver");
        dataSource.setUrl(datasourceUrl);//"jdbc:mysql://localhost:3306/test");
        dataSource.setUsername(dbUsername);//"");
        dataSource.setPassword(dbPassword);//"");
        
        return dataSource;
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    
}
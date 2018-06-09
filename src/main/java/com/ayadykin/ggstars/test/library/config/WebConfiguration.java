package com.ayadykin.ggstars.test.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement
public class WebConfiguration extends WebMvcConfigurerAdapter {

}

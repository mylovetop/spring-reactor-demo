package com.springapp.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;


import org.springframework.web.context.WebApplicationContext;


import java.util.List;

/**
 * Created by zdsoft on 14-5-7.
 */
@Configuration
@EnableReactor
@ComponentScan
public class ReactorConfig{

    @Bean(name="reactor")
    public Reactor reactor(Environment env){
//        return Reactors.reactor(env);

//        return Reactors.reactor().env(env).dispatcher(env.RING_BUFFER).get();
        Reactor reactor = Reactors.reactor(env);
        System.out.println("env=" + env);
        System.out.println("reactor=" + reactor);
        return reactor;
    }



    @Bean
    public Environment env(){
        return new Environment();
    }



}

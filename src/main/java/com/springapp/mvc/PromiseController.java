package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.composable.Deferred;
import reactor.core.composable.Promise;
import reactor.core.composable.spec.Promises;
import reactor.core.spec.Reactors;
import reactor.event.Event;
import reactor.spring.annotation.Consumer;


import java.util.List;

import static reactor.event.selector.Selectors.$;

/**
 * Created by zdsoft on 14-5-12.
 */
@Controller
public class PromiseController {

    @Autowired
    Environment env;
    @Autowired
    @Qualifier("reactor")
    Reactor reactor;


    @RequestMapping("/promise")
    @ResponseBody
    public Promise<ResponseEntity<String>> get(){

//        Environment env = new Environment();
        Deferred<ResponseEntity<String>, Promise<ResponseEntity<String>>> d = Promises.<ResponseEntity<String>>defer(env);

        reactor.notify("promiseTest1", Event.wrap(d));


        return d.compose();

    }

    @RequestMapping("/promise1")
    @ResponseBody
    public ResponseEntity<String> get1(){

//        Environment env = new Environment();
        Deferred<ResponseEntity<String>, Promise<ResponseEntity<String>>> d = Promises.<ResponseEntity<String>>defer(env);

        reactor.notify("promiseTest1", Event.wrap(d));


        return d.compose().get();

    }

    @RequestMapping("/test")
    @ResponseBody
    public Promise<String> test1(){



        Promise<String> t = testString();

        return t;
    }

    @RequestMapping("/test1")
    @ResponseBody
    public Promise<ResponseEntity<String>> testEesStr(){
          return testResponseEntity();
    }


    private Promise<String> testString(){


//        Deferred<String, Promise<String>> deferred = Promises.<String>defer()
//                .env(env)
//                .dispatcher(Environment.RING_BUFFER)
//                .get();

        System.out.println("test env=" + env);
        System.out.println("test reactor=" + reactor);
        Deferred<String, Promise<String>> deferred = Promises.<String>defer(env);

        reactor.notify("promiseTest", Event.wrap(deferred));
//        deferred.accept("Hello testString");
        return deferred.compose();
    }


    private Promise<ResponseEntity<String>> testResponseEntity(){

//        Environment env = new Environment();
//        Deferred<String, Promise<String>> deferred = Promises.<String>defer()
//                .env(env)
//                .dispatcher(Environment.RING_BUFFER)
//                .get();

        Deferred<ResponseEntity<String>, Promise<ResponseEntity<String>>> deferred = Promises.<ResponseEntity<String>>defer(env);

        reactor.notify("promiseTest", Event.wrap(deferred));



//        deferred.accept(new ResponseEntity<String>("Hello World! testResponseEntity", HttpStatus.OK));
        return deferred.compose();
    }

    @RequestMapping("/greet")//spring mvc 异步
    public DeferredResult<String> greet(){
        DeferredResult<String> result = new DeferredResult<String>();
        EventWrapper<String> wrapper = new EventWrapper<String>(result, "greet");//参数设置
        reactor.notify("greet", Event.wrap(wrapper));
        return result;
    }



}

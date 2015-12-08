package com.springapp.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.composable.Deferred;
import reactor.core.composable.Promise;
import reactor.spring.annotation.Consumer;
import reactor.spring.annotation.Selector;


/**
 * Created by zdsoft on 14-5-12.
 */
@Consumer
public class DeferredHandler {
    @Selector(value = "promiseTest1", reactor = "@reactor")
    public void test1(Deferred<ResponseEntity<String>, Promise<ResponseEntity<String>>> d) {
//        System.out.println("************调用***********"+d);
//        try{
//            System.out.println("************sleep 10秒***********");
//            Thread.sleep(10000);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("************结束***********"+d);
        d.accept(new ResponseEntity<String>("Hello World! Promise", HttpStatus.OK));
    }

    @Selector(value = "promiseTest", reactor = "@reactor")
    public void test(Deferred<String, Promise<String>> d) {
        d.accept("test");
    }

    @Selector(value = "greet", reactor = "@reactor")
    public void greet(EventWrapper<String> eventWrapper){
        eventWrapper.getDeferredResult().setResult("greet");
    }
}

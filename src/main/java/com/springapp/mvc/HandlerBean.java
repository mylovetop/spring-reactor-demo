package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.Reactor;
import reactor.event.Event;
import reactor.spring.annotation.ReplyTo;
import reactor.spring.annotation.Selector;

/**
 * Created by zdsoft on 14-5-7.
 */
//@Component
public class HandlerBean {


//    @Selector(value = "test.topic", reactor = "@rootReactor")
//    public void handleTestTopic(Event<String> evt){
//        System.out.print("这是通知\n");
//    }


//    @Autowired
//    @Qualifier("rootReactor")
//    private Reactor reactor;
//
//    @Selector("test.topic")
//    @ReplyTo("reply.topic")
//    public String handlerTestTopic(){
//        return "Hello, Reactor!";
//    }

}

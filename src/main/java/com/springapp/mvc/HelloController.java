package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.composable.Deferred;
import reactor.core.composable.Promise;
import reactor.core.composable.spec.Promises;
import reactor.core.spec.Reactors;
import reactor.event.Event;
import reactor.function.Consumer;

import static reactor.event.selector.Selectors.$;

@Controller
//@RequestMapping("/")
public class HelloController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
        r();
		return "hello";
	}



    private void r(){
        Environment env = new Environment();
        Reactor reactor = Reactors
                .reactor()
                .env(env)
                .dispatcher(Environment.EVENT_LOOP)
                .get();

        reactor.on($("parse"), new Consumer<Event<String>>() {
            @Override
            public void accept(Event<String> event) {
                System.out.println("Received event with data: " + event.getData());
            }
        });

        reactor.notify("parse", Event.wrap("data"));

    }

    private void promises(final  DeferredResult<String> deferredResult){
        Environment env = new Environment();
        Deferred<String, Promise<String>> deferred = Promises.<String>defer()
                .env(env)
                .dispatcher(Environment.RING_BUFFER)
                .get();

        deferred.compose().onSuccess(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("deferred.compose().onSuccess : " + s);
                deferredResult.setResult("HTTP response is: " + s);
            }
        });
        deferred.accept("Hello, world");



    }

    @RequestMapping(value = "/test2", method=RequestMethod.GET)
    @ResponseBody
    public DeferredResult<String> square(){
        final DeferredResult<String> deferredResult = new DeferredResult<String>();
//        runInOtherThread(deferredResult);
        promises(deferredResult);
        return deferredResult;
    }




    public void runInOtherThread(DeferredResult<String> deferredResult) {
        //seconds later in other thread...
        deferredResult.setResult("HTTP response is: 42");
    }


}
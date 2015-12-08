package com.springapp.mvc;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by admin on 15/12/8.
 */
public class EventWrapper<T> {
    private T object;
    private DeferredResult<T> deferredResult;

    public EventWrapper(DeferredResult<T> deferredResult, T object){
        this.deferredResult = deferredResult;
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public DeferredResult<T> getDeferredResult() {
        return deferredResult;
    }

    public void setDeferredResult(DeferredResult<T> deferredResult) {
        this.deferredResult = deferredResult;
    }
}

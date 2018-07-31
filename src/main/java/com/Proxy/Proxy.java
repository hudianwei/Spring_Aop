package com.Proxy;

import com.ActionImpl.ActionImpl;
import com.IAction.IAction;

public class Proxy implements IAction {
    private ActionImpl realobject;

    public Proxy(ActionImpl realobject) {
        this.realobject = realobject;
    }

    public void DoSomething() {
        System.out.println("新加功能");
        realobject.DoSomething();
    }
}

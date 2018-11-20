package com.fys.learnjava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

  Object target;

  public MyInvocationHandler(Object obj) {
    target = obj;
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    System.out.println("Method Name:"+method.getName());
    if(method.getName().equals("add"))  {
      if (args[0].equals("Apple")) {
        return false;
      }
    }
    return method.invoke(target,args);
  }
}

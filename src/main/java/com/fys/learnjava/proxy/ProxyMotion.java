package com.fys.learnjava.proxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyMotion {

  public static void main(String[] args) {
    ArrayList<String> content = new ArrayList<String>();
    MyInvocationHandler handler = new MyInvocationHandler(content);
    Object proxy = Proxy.newProxyInstance(null, new Class[]{List.class},handler);
    if(proxy instanceof List) {
      System.out.println("Proxy is List");
      List<String> mlist = (List<String>) proxy;
      mlist.add("One");
      mlist.add("Two");
      mlist.add("Apple");
      mlist.add("Three");
    }
    System.out.println("Proxy:"+ proxy.toString());
    System.out.println("Content:"+ content.toString());
  }
}

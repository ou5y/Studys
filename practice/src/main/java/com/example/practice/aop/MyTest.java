//package com.example.practice.aop;
//
//import java.util.HashMap;
//
//import com.dongnao.aop.Greeting;
//import com.dongnao.aop.cglibProxy.CGLibDynamisProxy;
//import com.dongnao.aop.cglibProxy.SayHelloImpl;
//
//public class MyTest {
//
//	public static void main(String[] args) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		CGLibDynamisProxy proxy = CGLibDynamisProxy.getInstance();
//		Greeting obj = proxy.getProxy(SayHelloImpl.class);
//		obj.sayHello("瓜袜子");
//	}
//
//}

//package com.example.practice.aop;
//
//
//import java.lang.reflect.Method;
//import org.springframework.cglib.proxy.Enhancer;
//import org.springframework.cglib.proxy.MethodInterceptor;
//import org.springframework.cglib.proxy.MethodProxy;
//
//
//public class CGLibDynamisProxy implements MethodInterceptor{
//
//	private static CGLibDynamisProxy proxy = new CGLibDynamisProxy();
//
//	private CGLibDynamisProxy(){};
//
//	public static CGLibDynamisProxy getInstance(){
//		return CGLibDynamisProxy.proxy;
//	}
//
//	@SuppressWarnings("unchecked")
//	public <T> T getProxy(Class<T> cls){
//		return (T) Enhancer.create(cls, this);
//	}
//
//	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//
//		beforeAdvice();
//
//		Object invoke = proxy.invoke(target, args);
//
//		afterAdvice();
//
//		return invoke;
//	}
//
//	private void beforeAdvice() {
//		System.out.println("代理前置方法");
//	}
//
//	private void afterAdvice() {
//		System.out.println("代理后置方法");
//	}
//
//
//}

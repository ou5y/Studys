package com.example.practice.aop;

import com.dongnao.aop.Greeting;

public class SayHelloImpl implements Greeting{

	public void sayHello(String name) {
		System.out.println("你好"+name);
	}

}

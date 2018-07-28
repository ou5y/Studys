package com.example.mt.main;

public class InheritableThreadLocalExt extends InheritableThreadLocal{

    @Override
    protected Object initialValue(){
        return "王麻子";
    }

    @Override
    protected Object childValue(Object parentValue){
        return parentValue+"~子线程内容";
    }

}
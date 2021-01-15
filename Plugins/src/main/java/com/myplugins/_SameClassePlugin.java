package com.myplugins;

public class _SameClassePlugin implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("Thi is: "+ this.getClass().getName());
    }
}

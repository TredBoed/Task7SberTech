package com.myplugins;

public class _VeryCoolThing  implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("Thi is: "+ this.getClass().getName());
    }
}

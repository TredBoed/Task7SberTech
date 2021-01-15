package com.school;

public class Main {

    public static void main(String[] args)  {
        ClassLoader pluginManager = new PluginManager(
                "/Users/super/Desktop/ClassLoader/Plugins/target/pluginName/com/myplugins",
                Main.class.getClassLoader());
        ;
        try {
            com.myplugins._ADblock pluginOne =
                    ( com.myplugins._ADblock) pluginManager.loadClass("com.myplugins._ADblock").newInstance();
            pluginOne.doUsefull();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}

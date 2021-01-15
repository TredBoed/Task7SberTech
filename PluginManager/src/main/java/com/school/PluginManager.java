package com.school;


import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class PluginManager extends ClassLoader {
    private final String pluginRootDirectory;
    private HashMap<String, Class<?>> classes = new HashMap<>();

    PluginManager(String pluginRootDirectory, ClassLoader parent) {
        super(parent);
        this.pluginRootDirectory = pluginRootDirectory;
    }


    public Class<?> loadClass(String pluginName)  throws ClassNotFoundException{
        Class<?> result = findClass(pluginName);
        if(result != null)
            return result;
        //   result = findClass(pluginName);
        //  if(result != null)
        result = super.loadClass(pluginName);
        return result;

    }

    protected Class<?> findClass(String pluginName) throws ClassNotFoundException {
        String pluginPath = pluginRootDirectory + "/" + pluginName.replace('.', '/') + ".class";
        File plugin = new File(pluginPath);
        byte[] classData;

        if (!plugin.exists()) {
            return null;
        }

        try {
            classData = loadClassData(pluginName.replace('.', '/') + ".class");
            classes.put(pluginName, defineClass(pluginName, classData, 0, classData.length));
            return classes.get(pluginName);
        } catch (IOException e) {
            return null;
        }

    }
    private byte[] loadClassData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }


}
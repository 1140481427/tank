package com.tj.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    private static PropertyMgr instance = null;

    private PropertyMgr (){
        init();
    }
    public static PropertyMgr getInstance(){
        if(instance == null){
            return new PropertyMgr();
        }
        return instance;
    }

    Properties props = null;

    public void init(){
        try {
            props = new Properties();
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public Object get(String key){
        if(props == null) return null;
        return props.get(key);
    }

}

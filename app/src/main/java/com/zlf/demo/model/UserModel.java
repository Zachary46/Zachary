package com.zlf.demo.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    public String name;
    public String age;
    public String url;
    public String thumb;


    public String getNameAndAge(){
        return "姓名："+name+" 年龄："+age;
    }
}

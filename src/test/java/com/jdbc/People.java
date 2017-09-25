package com.jdbc;

import java.sql.SQLException;

/**
 * Created by john on 2017/9/24.
 */
public class People {
    public static int check(String strage){
        int age = Integer.parseInt(strage);
        if(age < 0){
          throw new MyException("年龄不能为负数！");
        }
        return age;
    }
    public static void main(String[] args){


        SQLException S=new SQLException();



            int myage = check("-101");



    }
}
 class MyException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String name;
    public MyException(String name){
        this.name = name;
    }
    public String getMessage(){
        return this.name;
    }
}
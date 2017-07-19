package com.utm.test.annotation;

import java.lang.reflect.Method;

public class MyClass {

    @MyDeprecated
    public String sayHelloWorld()
    {
        return "Hello,World!";
    }

    public String sayHelloutm()
    {
        return "Hello,utm!";
    }


    public static void main(String[] args) {
        testMyDeclared(MyClass.class);
    }

    public static void testMyDeclared(Class<?> myClass) {
        for (Method method : myClass.getDeclaredMethods()) {

            MyDeprecated myDeprecated = method.getAnnotation(MyDeprecated.class);
            if (myDeprecated != null) {
                System.out.println(myDeprecated.description()+method.getName());
            }
        }
    }

}

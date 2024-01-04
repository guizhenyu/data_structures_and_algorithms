package com.gzy.code.heap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Test {
    int bbb = 10;
    public static void main(String[] args) {
//        Optional<Object> o = Optional.of(new Test());
//        System.out.println(o.get().toString());
//
        Runnable r = () -> System.out.println("jio");
        new Thread(r).start();
    }
//        Integer x =3;
//        Integer y = null;
//
//        List<Person> list = new ArrayList<>();
//
//        list.add(new Person(5));
//        list.add(new Person(10));
//        list.add(new Person(51));
//        list.add(new Person(15));
//        list.sort(Person::compareAge);
//        list.forEach(System.out::println);

//        byte[] c1 = {10, 20, 30, 40, 50};
//        byte[] c2 = {60, 70, 80, 90};
//        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
//        ByteArrayOutputStream b2 = new ByteArrayOutputStream(10);
//        b2.write(100);
//        System.out.println(b2.size());
//        b2.write(c1, 0, c2.length);
//        System.out.println(b2.size());
//
//        System.out.println(Stream.of("green", "yellow", "blue").max((s1, s2) -> s1.compareTo(s2)).filter(s -> s.endsWith("n")).orElse("yellow"));

        @Override
        public String toString(){
            return "Test";
        }


        public class TestInner{
           public   int a = 10;

            int get(){
               Test test = new Test();
               return test.bbb;
           }
        }

}

class Person {

    int age = 10;

    public Person(int age){
        this.age = age;
    }

    public static int compareAge(Person p1, Person p2){
        return p1.age - p2.age;
    }
    @Override
    public String toString(){
//        System.out.println(age);
        return age + "";
    }
}

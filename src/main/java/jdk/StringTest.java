package jdk;

public class StringTest {


    public static void main(String[] args) {
//        String str1 = "计算机";
//        String str2 = "计算机";
//        System.out.println(str1 == str2);
//
//
//
////常量字符串的"+"操作，编译阶段直接会合成为一个字符串。
//        String str3 = "计算" + "机"; //编译时合并成String str = "计算机";
//
////对于final字段，编译期直接进行了常量替换。
//        final String str11 = "计算";
//        final String str22 = "机";
//        String str4 = str11 + str22; //编译时直接替换成了String str3 = "计算" + "机";
//        System.out.println(str3 == str4);
//        String str1 = new String("计算机");
//        String str2 = new String("计算机");
//        System.out.println(str1 == str2);
//        String s1 = new String("1"); //常量池中创建"1"，堆中创建"1"
//        s1.intern();          //常量池中已有"1"，所以jdk6和jkd7都是返回指向常量池"1"的引用，
//        //但因为该语句没有赋值操作，所以s1仍指向堆中"1"
//        String s2 = "1";      //s2指向常量池中已存在的"1"
//        System.out.println(s1==s2);  //s1指向堆中"1"，s2指向常量池中"1"，false
        String s3 = new String("1") + new String("1"); //常量池生成一个"1"，堆生成一个"11"
        //s3指向堆中"11"
        //中间还有2个匿名的new String("1")暂不讨论
        s3.intern();             //因为常量池中不存在"11"，
        //jdk6会将堆中"11"复制到常量池中，
        //jdk7则将堆中"11"的引用添加到常量池中，
        //此时s3仍指向堆中"11"
        String s4 = "1" + "1";        //因为常量池中已存在"11"或其引用，s4指向常量池中"11"
        System.out.println(s3==s4);  //jdk6中，s4指向常量池中"11"，s3指向堆中"11"，false
        //jdk7中，s4指向常量池中指向堆中"11"的引用，true

    }
}

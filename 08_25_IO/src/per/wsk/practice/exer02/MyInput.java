package per.wsk.practice.exer02;
//题目：提供一个叫MyInput的.java文件，分别写出读取int,double,float,boolean,short,byte和
//String几种类型的方法。

// MyInput.java: Contain the methods for reading int, double, float, boolean, short,
// byte and string values from the keyboard

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyInput {

    // Read a string from the keyboard
    public static String readString() {
        /*
        下面一行的代码：
        System.in是一个字节输入流，
        new InputStreamReader(System.in)是一个转换流，把字节输入流转换为字符输入流，
        new BufferedReader(字符输入流且是转换流):
            创建了一个既是缓冲流又是字符流又是输入流的流。
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Declare and initialize the string
        String string = "";

        // Get the string from the keyboard
        try {
            string = br.readLine();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        // Return the string obtained from the keyboard
        return string;
    }

    // Read an int value from the keyboard
    public static int readInt() {
        return Integer.parseInt(readString());
    }

    // Read a double value from the keyboard
    public static double readDouble() {
        return Double.parseDouble(readString());
    }

    // Read a byte value from the keyboard
    public static double readByte() {
        return Byte.parseByte(readString());
    }

    // Read a short value from the keyboard
    public static double readShort() {
        return Short.parseShort(readString());
    }

    // Read a long value from the keyboard
    public static double readLong() {
        return Long.parseLong(readString());
    }

    // Read a float value from the keyboard
    public static double readFloat() {
        return Float.parseFloat(readString());
    }
}

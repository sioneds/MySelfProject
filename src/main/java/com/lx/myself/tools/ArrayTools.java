package com.lx.myself.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/05/08 16:20
 **/
public class ArrayTools {
    public static byte[] FlashBackBytes(byte[] originArray){
        byte[] reverseArray = new byte[originArray.length];//反转后的数组
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < originArray.length; i++) {
            arraylist.add(originArray[i]); //存放元素
        }
        Collections.reverse(arraylist); //使用方法进行逆序
        //完成逆序后,可以保存到新数组reverseArray
        for (int i = 0; i < originArray.length; i++) {
            reverseArray[i] = (byte) arraylist.get(i);
        }
        return reverseArray;
    }
}

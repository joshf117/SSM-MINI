package com.mini.test;

import com.mini.utils.MD5Util;
import org.testng.annotations.Test;

public class MyTest {
    @Test
    public void testMD5(){
        String mi = MD5Util.getMD5("123456");
        System.out.println(mi);
    }
}

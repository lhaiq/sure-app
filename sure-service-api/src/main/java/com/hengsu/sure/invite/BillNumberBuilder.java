package com.hengsu.sure.invite;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

public class BillNumberBuilder {
    private static Object locker = new Object();
      
    private static int sn = 0;
      
    public static String nextBillNumber(){
        synchronized (locker){
            if(sn == 9999999999L)
                sn = 0;
            else
                sn++;
            return DateFormatUtils.format(new Date(),"yyyyMMddHHmmss") + StringUtils.leftPad(String.valueOf(sn), 10, '0');
        }
    }
    // 防止创建类的实例
    private BillNumberBuilder(){}

    
}
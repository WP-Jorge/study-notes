package com.team001.common;

import com.team001.common.lang.Result;

public class OrderDate {

    public static boolean valid(long orderStart,long orderEnd,long start,long end){

        /*
         * 开始时间在已经预约的时间段中间
         */
        if(start<= orderStart && orderStart<=start){
            return true;
        }

        /*
         * 结束时间在已经预约的时间段中间
         */
        if(start<=orderEnd && orderEnd<=end){
            return true;
        }

        /**
         * 结束时间和开始时间包裹了已经预约的时间段
         */
        if(orderEnd<end && orderStart>start)
            return true;

        return  false;
    }
}

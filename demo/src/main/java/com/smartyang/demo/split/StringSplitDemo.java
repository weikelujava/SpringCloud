package com.smartyang.demo.split;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: StringSplitDemo
 * @description:
 * @author: lukewei
 * @date: 2020-09-08 2020/9/8
 * @remark: 修改内容
 */
public class StringSplitDemo {

    public static void main(String[] args) {
//        String ipStr = "10.2.9.152:80,10.2.9.151:80";
//        String[] ips = getIp(ipStr);
//        for (String ip : ips) {
//            System.out.println(ip);
//        }

        String str = ",22";
//        System.out.println(str.substring(1,3));
        String res = "";
        if("".equals(str)){
            str = null;
        }else {
            if(str.startsWith(",")){
                res = str.substring(str.indexOf(",")+1);
            }
        }

        System.out.println(res);
    }

    public static String[] getIp(String nginxIp){
        if(null != nginxIp){
            if(nginxIp.contains(",")){
                return nginxIp.split(",");
            }else {
                return new String[]{nginxIp};
            }
        }
        return new String[]{};
    }
}

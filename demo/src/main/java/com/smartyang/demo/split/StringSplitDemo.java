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
        String ipStr = "10.2.9.152:80,10.2.9.151:80";
        String[] ips = getIp(ipStr);
        for (String ip : ips) {
            System.out.println(ip);
        }
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

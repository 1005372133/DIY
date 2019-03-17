package io.renren.common.utils;


import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

/**
 * @Auther: Administrator
 * @Date: 2019/3/17 12:23
 * @Description:
 */
public class UUIDutils {

        public static Long randomUUID10() {
            return RandomUtils.nextLong();
        }

        public static String getUUIDPath(String uuid){
            StringBuilder builder=new StringBuilder();
            builder.append("/");
            builder.append((uuid.substring(0, 3).hashCode())%100+"").append("/");
            builder.append((uuid.substring(7,10).hashCode())%100+"").append("/");
            builder.append((uuid.substring(11,14).hashCode())%100+"").append("/");
            return builder.toString();
        }

        public static String getAttTable(){
            Random rand = new Random();
            int nextInt = rand.nextInt(10)+1;
            StringBuilder builder=new StringBuilder();
            builder.append("ATT_FILE_").append(String.format("%02d", nextInt));
            return builder.toString();
        }


}

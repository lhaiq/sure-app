package com.hengsu.sure.auth.oss;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;

/**
 * Created by haiquanli on 15/11/16.
 */
public class Image {

    public static void main(String[] args) throws Exception{

        Thumbnails.of(new File("/Users/haiquanli/Documents/aaa.jpg"))
                .size(200,300)
                .toFile("/Users/haiquanli/Documents/aab.jpg");
//
//                .toO
    }
}

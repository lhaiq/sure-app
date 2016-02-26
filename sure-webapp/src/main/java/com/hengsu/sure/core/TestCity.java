package com.hengsu.sure.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haiquanli on 16/1/15.
 */
public class TestCity {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        System.out.println(simpleDateFormat.parse("2016-01-15T11:47:37Z"));

    }


    public static String[] getLocation(String data) {
        int index = data.indexOf("|");
        data = data.substring(0, index);
        return data.split(",");
    }
}

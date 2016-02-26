package com.hengsu.sure.auth.oss;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;

import java.io.File;
import java.util.List;

/**
 * Created by haiquanli on 15/11/16.
 */
public class Sample {

    public static void main(String[] args) {
//        String accessKeyId = "3MEfGv2FB8BlxmCp";
//        String accessKeySecret = "StjrkJcpxuJPaJsaJ7WxiVk62erUiM";
        String accessKeyId = "STS.j3WGcOKMErzA9u5TqJzR";
        String accessKeySecret = "owtLxjjCIHti1YqOFeLP4RTOIL040kM1DTU6Q5dQ";
        String endpoint="http://oss-cn-hangzhou.aliyuncs.com";
        String securityToken="CAESgwMIARKAAS5oLQKv9uIzrmmW6j55Wt2ZosU51xjPySZIz4f3UXLKJEStevzZ9RkgQGSpPj+TiwY1CIJesZl9u4zjZbBVrge2t7d3cV1n9/9EiwgYNqDaqaapBpDSdh/f0WTL+9EbAFjDjkUR3Ickwfdm8cuio8ct6CUq1pwOzsdcJpvDDIG7GhhTVFMuajNXR2NPS01FcnpBOXU1VHFKelIiEjM0Nzg2ODA1MjgzNTA2NzI2MSoGc3VyZS0zMJT2vMKkKjoGUnNhTUQ1QloKATEaVQoFQWxsb3cSHwoMQWN0aW9uRXF1YWxzEgZBY3Rpb24aBwoFb3NzOioSKwoOUmVzb3VyY2VFcXVhbHMSCFJlc291cmNlGg8KDWFjczpvc3M6KjoqOipKEDE2ODU2MDQwMDY3MDM2MTFSBTI2ODQyWg9Bc3N1bWVkUm9sZVVzZXJgAGoSMzQ3ODY4MDUyODM1MDY3MjYxchthbGl5dW5vc3N0b2tlbmdlbmVyYXRvcnJvbGU=";

        OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret,securityToken);

//        PutObjectRequest putOb jectRequest = new PutObjectRequest("hengsu-sure","P60114-150144.jpg",
//                new File("/Users/haiquanli/Downloads/P60114-150144.jpg"));
//
//        client.putObject(putObjectRequest);



        // 获取指定bucket下的所有Object信息
        ObjectListing listing = client.listObjects("hengsu-sure");

        // 遍历所有Object
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey());
        }

    }
}

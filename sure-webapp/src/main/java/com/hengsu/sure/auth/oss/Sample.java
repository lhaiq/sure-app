package com.hengsu.sure.auth.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.util.List;

/**
 * Created by haiquanli on 15/11/16.
 */
public class Sample {

    public static void main(String[] args) {
        String accessKeyId = "3MEfGv2FB8BlxmCp";
        String accessKeySecret = "StjrkJcpxuJPaJsaJ7WxiVk62erUiM";
        String endpoint="http://oss-cn-hangzhou.aliyuncs.com";

        OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
//        client.u





        // 获取指定bucket下的所有Object信息
        ObjectListing listing = client.listObjects("hengsu-sure");

        // 遍历所有Object
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey());
        }

    }
}

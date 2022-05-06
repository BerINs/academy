package io.peaches.academy.service.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import io.peaches.academy.service.oss.service.FileService;
import io.peaches.academy.service.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;




    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {

        //读取配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String bucket = ossProperties.getBucketname();
        String keysecret = ossProperties.getKeysecret();

        String folder = new DateTime().toString("yyyy/MM/dd");
        String filename = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = module + "/" + folder + "/" + filename + fileExtension;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        if (!ossClient.doesBucketExist(bucket)) {
            ossClient.createBucket(bucket);
            ossClient.setBucketAcl(bucket, CannedAccessControlList.PublicRead);
        }
        try {
            // 创建PutObject请求
            ossClient.putObject(bucket, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return "https://" + bucket + "." + endpoint + "/" + objectName;
    }

    @Override
    public void removeFile(String url) {

        //读取配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String bucket = ossProperties.getBucketname();
        String keysecret = ossProperties.getKeysecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);

        // 删除文件。
        String host = "https://" + bucket + "." + endpoint + "/";
        String objectName = url.substring(host.length());
        ossClient.deleteObject(bucket, objectName);

        // 关闭OSSClient实例。
        ossClient.shutdown();
    }
}

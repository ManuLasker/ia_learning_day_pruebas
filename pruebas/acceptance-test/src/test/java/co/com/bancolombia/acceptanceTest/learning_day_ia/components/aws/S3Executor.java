package co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws;


import co.com.bancolombia.acceptanceTest.learning_day_ia.utils.ConfigurationUtils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.util.ArrayList;

public class S3Executor {
    /*
    write execution code and logic for the S3 resource
    */
    final String TAG = "s3 executer: ";
    public void uploadFile(String sceneryType, String imageWidth, String imageHeight){
        System.out.println(TAG + "TEST");
        String fileName = ConfigurationUtils.getFilePath(sceneryType, imageWidth + "x" + imageHeight);
        String objKeyInName = ConfigurationUtils.getFileInKey(sceneryType, imageWidth + "x" + imageHeight);
        System.out.println(TAG + fileName);
        System.out.println(TAG + objKeyInName);
        try {
            AmazonS3 s3 = S3Client.getS3Client();
            PutObjectRequest request = new PutObjectRequest(ConfigurationUtils.s3BucketName,
                    objKeyInName,
                    new File(fileName));
            s3.putObject(request);
        } catch (Exception error) {
            System.out.println(TAG + "ERROR" + error);
            throw new AmazonServiceException("S3 service error [uploadFileS3]: " + error);
        }
    }

    public String searchFile(String sceneryType, String imageWidth,
                             String imageHeight, String imageWidthOut,
                             String imageHeightOut){
        String objKeyOutName = ConfigurationUtils.getFileOutKey(sceneryType,
                imageWidth + "x" + imageHeight,
                imageWidthOut + "x" + imageHeightOut);
        System.out.println(TAG + "REVISION " + objKeyOutName);
        if (isInBucket(objKeyOutName, ConfigurationUtils.s3BucketName)){
            System.out.println(TAG + "REVISION " + "SUCCESS");
            return "Success";
        }
        return "Fail";
    }

    public static ArrayList<String> ListObjectsBucket (String bucketName){
        AmazonS3 s3 = S3Client.getS3Client();
        ArrayList<String> ListObjects = new ArrayList<String>();
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(10);
        ListObjectsV2Result result;
        do{
            result = s3.listObjectsV2(req);
            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                ListObjects.add(objectSummary.getKey());
            }
            String token = result.getNextContinuationToken();
            req.setContinuationToken(token);
        } while (result.isTruncated());
        return ListObjects;
    }

    public static boolean isInBucket(String fileObjKeyName, String bucketName) {
        for (String objKeyTarget: ListObjectsBucket(bucketName)){
            if (objKeyTarget.equals(fileObjKeyName)){
                return true;
            }
        }
        return false;
    }
}
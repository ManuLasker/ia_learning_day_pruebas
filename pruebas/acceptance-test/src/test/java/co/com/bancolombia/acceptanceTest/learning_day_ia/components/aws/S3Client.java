
package co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import com.amazonaws.services.lambda.model.ServiceException;

public class S3Client {
    
    public static AmazonS3 getS3Client(){
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new SystemPropertiesCredentialsProvider())
                    .withRegion(Regions.US_EAST_1)
                    .build();
            return  s3Client;
        } catch (ServiceException e) {
            System.out.println(e);
        }
        return null;
    }
    
}

package co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.BasicAWSCredentials;


import com.amazonaws.services.lambda.model.ServiceException;

public class LambdaClient {
    
        
    public static AWSLambda getLambdaClient(){
        try {
            AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(getSessionCredentials()))
                    .withRegion(Regions.US_EAST_1).build();
            return awsLambda;
        } catch (ServiceException e) {
            System.out.println(e);
        }
        return null;
    }

    private static AWSCredentials getSessionCredentials() {
        return new BasicAWSCredentials(
            System.getProperty("accessKeyIdLambda"),
                System.getProperty("secretKeyLambda")
        );
    }
        
    
}
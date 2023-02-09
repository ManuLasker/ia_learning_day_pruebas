
package co.com.bancolombia.smokeTest.learning_day_ia.components.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;


import com.amazonaws.services.lambda.model.ServiceException;

public class LambdaClient {
    
        
    public static AWSLambda getLambdaClient(){
        try {
            AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                    .withCredentials(new SystemPropertiesCredentialsProvider())
                    .withRegion(Regions.US_EAST_1).build();
            return awsLambda;
        } catch (ServiceException e) {
            throw new ServiceException("Client error [getLambdaClient]: " + e);
        }
    }
        
    
}
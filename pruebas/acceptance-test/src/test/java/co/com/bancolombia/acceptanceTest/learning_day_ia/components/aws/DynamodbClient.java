
package co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import com.amazonaws.services.lambda.model.ServiceException;

public class DynamodbClient {
    
    public static AmazonDynamoDB getDynamoClient(){
        try {
            AmazonDynamoDB dynamoClient = AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(new SystemPropertiesCredentialsProvider())
                    .withRegion(Regions.US_EAST_1).build();
            return dynamoClient;
        } catch (AmazonServiceException e){
            throw new AmazonServiceException("Client error [getDynamoClient]: " + e);
        }
    }
    
}
package co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws;

import co.com.bancolombia.acceptanceTest.learning_day_ia.utils.ConfigurationUtils;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

public class DynamodbExecutor {
    /*
    write execution code and logic for the DYNAMODB resource
    */
    public String searchItem(String sceneryType, String imageWidth,
                             String imageHeight, String imageWidthOut, String imageHeightOut){
        String objKeyOutName = ConfigurationUtils.getFileOutKey(sceneryType,
                imageWidth + "x" + imageHeight,
                imageWidthOut + "x" + imageHeightOut);
        String s3Path = "s3:://" + ConfigurationUtils.s3BucketName + "/" + objKeyOutName;
        System.out.println("DYNAMODB " + s3Path);
        try {
            DynamoDB dynamoDb = new DynamoDB(DynamodbClient.getDynamoClient());
            Table table = dynamoDb.getTable(ConfigurationUtils.tableName);
            GetItemSpec spec = new GetItemSpec().withPrimaryKey("s3_path", s3Path);
            Item item = table.getItem(spec);
            if (item == null){
                return "Failed";
            }else {
                return "Success: " + item.toString();
            }
        } catch (Exception error){
            throw new RuntimeException("ERROR");
        }
    }
}
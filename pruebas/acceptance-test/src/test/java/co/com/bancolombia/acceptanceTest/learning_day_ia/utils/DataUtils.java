package co.com.bancolombia.acceptanceTest.learning_day_ia.utils;

import co.com.bancolombia.acceptanceTest.learning_day_ia.models.ExecutionJson;
import com.amazonaws.services.lambda.model.InvokeResult;
import java.nio.charset.StandardCharsets;

public class DataUtils {
    // write all the necesary utils method that handle data, for example creating the lambda event
    // or extracting the lambda response payload

    public static String createLambdaEvent(String s3BucketName, String objKeyName,
                                           String imageWidthOut, String imageHeightOut) {
        JsonUtils.convertJsonToString(s3BucketName, objKeyName, imageWidthOut, imageHeightOut);
        return ExecutionJson.getBody();
    }

    public static String payloadExtractor(InvokeResult lambdaResult) {
        return new String(lambdaResult.getPayload().array(), StandardCharsets.UTF_8);
    }
}
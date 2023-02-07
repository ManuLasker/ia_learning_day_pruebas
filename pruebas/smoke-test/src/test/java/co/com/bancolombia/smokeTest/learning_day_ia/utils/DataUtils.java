package co.com.bancolombia.smokeTest.learning_day_ia.utils;

import co.com.bancolombia.smokeTest.learning_day_ia.models.ExecutionJson;
import com.amazonaws.services.lambda.model.InvokeResult;
import java.nio.charset.StandardCharsets;

public class DataUtils {
    // write all the necesary utils method that handle data, for example creating the lambda event
    // or extracting the lambda response payload

    public static String createLambdaEvent(String sceneryType) {
        JsonUtils.convertJsonToString(sceneryType);
        return ExecutionJson.getBody();
    }

    public static String payloadExtractor(InvokeResult lambdaResult) {
        return new String(lambdaResult.getPayload().array(), StandardCharsets.UTF_8);
    }
}
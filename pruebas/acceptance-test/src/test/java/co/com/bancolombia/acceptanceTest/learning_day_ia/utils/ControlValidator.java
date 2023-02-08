package co.com.bancolombia.acceptanceTest.learning_day_ia.utils;

import com.amazonaws.services.lambda.model.InvokeResult;

public class ControlValidator {
    public static String processFunctionalResponse(InvokeResult lambdaResult, String sceneryType, String oldSize, String newSize){
        Questions question = new Questions();
        String answer = DataUtils.payloadExtractor(lambdaResult);
        return question.totallyProcessed(answer, sceneryType, oldSize, newSize);
    }
}
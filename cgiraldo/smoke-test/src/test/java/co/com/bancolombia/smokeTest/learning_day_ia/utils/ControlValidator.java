package co.com.bancolombia.smokeTest.learning_day_ia.utils;

import com.amazonaws.services.lambda.model.InvokeResult;

public class ControlValidator {
    public static String processFunctionalResponse(InvokeResult lambdaResult){
        Questions question = new Questions();
        String answer = DataUtils.payloadExtractor(lambdaResult);
        return question.totallyProcessed(answer);
    }
}
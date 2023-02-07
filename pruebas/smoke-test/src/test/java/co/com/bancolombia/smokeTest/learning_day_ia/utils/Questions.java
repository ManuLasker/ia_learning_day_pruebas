package co.com.bancolombia.smokeTest.learning_day_ia.utils;

import com.amazonaws.services.lambda.model.InvokeResult;

public class Questions {
    public static String totallyProcessed(String response){
        
        if(response.contains("\"statusCode\": 200") && response.contains("\"ALIVE\"")){
            return  "Successful: " + response;
        }
        return "Failed: " + response;
        
    }


}
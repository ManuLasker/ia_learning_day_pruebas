package co.com.bancolombia.acceptanceTest.learning_day_ia.utils;

import com.amazonaws.services.lambda.model.InvokeResult;

public class Questions {
    public static String totallyProcessed(String response, String sceneryType,
                                          String oldSize, String newSize){
        
        // In this block of code we need to implement a switch case (normally) to handle
        // every scenario that we are going to test, and check if it successful or 
        // it is a failed scenario for the lambda response
        switch (sceneryType){
            case "jpg":
                return okProcessedImage(response, oldSize, newSize);
        }
       return "Fail";
    }


    // In this block of code we need to implement the utility methods that will be executed by
    // the previous method inside each condition to handle
    // every scenario that we are going to test, and check if it successful or 
    // it is a failed scenario for the lambda response
    public static String okProcessedImage(String response, String oldSize, String newSize){
        String result = "";
        if (
                response.contains("\"status\": \"processed\"") &&
                response.contains("\"new_shape\": \""+ newSize +"\"") &&
                        response.contains("\"old_shape\": \""+ oldSize +"\"")
        ){
            result = "Successful : Good Validation" + response + ", validation: " + oldSize + " " + newSize;
        }else{
            result = "Failed : Bad Validation" + response + ", validation: " + oldSize + " " + newSize;
        }
        return result;
    }
}
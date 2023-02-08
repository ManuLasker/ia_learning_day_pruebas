package co.com.bancolombia.acceptanceTest.learning_day_ia.utils;

import co.com.bancolombia.acceptanceTest.learning_day_ia.models.ExecutionJson;

import com.amazonaws.services.lambda.model.ServiceException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.FileReader;

public class JsonUtils {
    public static String convertJsonToString(String s3BucketName, String objKeyName,
                                             String imageWidthOut, String imageHeightOut){
        String jsonLambda="EMPTY";
        JsonObject objectJson;
        try {
            objectJson = jsonExtractor();
            JsonParser parser = new JsonParser();
            JsonObject file = (JsonObject) parser.parse(String.valueOf(objectJson));
            prepareRequest(s3BucketName, objKeyName, imageWidthOut, imageHeightOut);
            jsonLambda = file.toString();
            return jsonLambda;
        } catch (Exception e) {
            throw new ServiceException("Error on JsonUtils -> jsonExtractor" + e);
        }
    }

    public static JsonObject jsonExtractor() throws Exception {
        JsonParser jsonParser = new JsonParser();
        Object object;
        JsonObject objectJson;
        try (FileReader reader = new FileReader(ConfigurationUtils.getEventFilePath())) {
            object = jsonParser.parse(reader);
            objectJson = (JsonObject) object;
        } catch (JsonParseException e) {
            throw new ServiceException("Error on JsonUtils -> jsonExtractor" + e);
        }
        return objectJson;
    }

    public static void prepareRequest(String s3BucketName, String objKeyName,
                                      String imageWidthOut, String imageHeightOut){
        JsonObject jsonPetition = null;
        try {
            jsonPetition= JsonUtils.jsonExtractor();
        } catch (Exception e) {
            throw new ServiceException("Error on JsonUtils -> jsonExtractor" + e);
        }
        
        //in case of parametrize input for acceptance test, use the putValue method on jsonPetition
        //in this block of code
        jsonPetition = JsonUtils.putValue(jsonPetition, "<s3_bucket>", s3BucketName);
        jsonPetition = JsonUtils.putValue(jsonPetition, "<s3_key>" , objKeyName);
        jsonPetition = JsonUtils.putValue(jsonPetition, "<width_param>" , imageWidthOut);
        jsonPetition = JsonUtils.putValue(jsonPetition, "<height_param>" , imageHeightOut);
        ExecutionJson.setBody(jsonPetition.toString());
    }

    
    public static JsonObject putValue(JsonObject jsonObject, String target, String value){
        //to replace parameters on json events (used for parametrized lambda inputs)
        JsonParser parser = new JsonParser();
        String objJsonAux = jsonObject.toString().replace(target, value);
        return (JsonObject) parser.parse(objJsonAux);
    }
    
}

package co.com.bancolombia.smokeTest.learning_day_ia.utils;

import co.com.bancolombia.smokeTest.learning_day_ia.models.ExecutionJson;

import com.amazonaws.services.lambda.model.ServiceException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.FileReader;

public class JsonUtils {
    public static String convertJsonToString(String sceneryType){
        String jsonLambda="EMPTY";
        JsonObject objectJson;
        try {
            objectJson = jsonExtractor(sceneryType);
            JsonParser parser = new JsonParser();
            JsonObject file = (JsonObject) parser.parse(String.valueOf(objectJson));
            prepareRequest(sceneryType);
            jsonLambda = file.toString();
            return jsonLambda;
        } catch (Exception e) {
            throw new ServiceException("Error on JsonUtils -> jsonExtractor" + e);
        }
    }

    public static JsonObject jsonExtractor(String sceneryType) throws Exception {
        JsonParser jsonParser = new JsonParser();
        Object object;
        JsonObject objectJson;
        try (FileReader reader = new FileReader(ConfigurationUtils.getEventFilePath(sceneryType))) {
            object = jsonParser.parse(reader);
            objectJson = (JsonObject) object;
        } catch (JsonParseException e) {
            throw new ServiceException("Error on JsonUtils -> jsonExtractor" + e);
        }
        return objectJson;
    }

    public static void prepareRequest(String sceneryType){
        JsonObject jsonPetition = null;
        try {
            jsonPetition= JsonUtils.jsonExtractor(sceneryType);
        } catch (Exception e) {
            throw new ServiceException("Error on JsonUtils -> jsonExtractor" + e);
        }
        
        ExecutionJson.setBody(jsonPetition.toString());
    }

    
}

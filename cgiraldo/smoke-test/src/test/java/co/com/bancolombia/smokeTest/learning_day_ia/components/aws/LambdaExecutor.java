package co.com.bancolombia.smokeTest.learning_day_ia.components.aws;


import co.com.bancolombia.smokeTest.learning_day_ia.utils.ConfigurationUtils;
import co.com.bancolombia.smokeTest.learning_day_ia.utils.ControlValidator;
import co.com.bancolombia.smokeTest.learning_day_ia.utils.DataUtils;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;


public class LambdaExecutor {
    /*
    write execution code and logic for the LAMBDA resource
    */

    
        
    final String TAG = "learning_day_ia: ";
    public String requestLambda(String sceneryType){
        String lambdaEvent = DataUtils.createLambdaEvent(sceneryType);
        System.out.println(TAG + lambdaEvent);
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(ConfigurationUtils.lambdaName)
                .withPayload(lambdaEvent)
                .withLogType("Tail");
        return invokeLambda(invokeRequest);
    }

    public String invokeLambda(InvokeRequest invokeRequest){
        AWSLambda lambda = LambdaClient.getLambdaClient();
        InvokeResult invokeResult = null;
        try{
            invokeResult = lambda.invoke(invokeRequest);
            return ControlValidator.processFunctionalResponse(invokeResult);
        }catch (ServiceException e) {
            throw new ServiceException("Service error [invokeLambda]: " + e);
        }
    }
        
    
}
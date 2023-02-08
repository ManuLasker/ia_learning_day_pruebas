package co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws;


import co.com.bancolombia.acceptanceTest.learning_day_ia.utils.ConfigurationUtils;
import co.com.bancolombia.acceptanceTest.learning_day_ia.utils.ControlValidator;
import co.com.bancolombia.acceptanceTest.learning_day_ia.utils.DataUtils;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;

public class LambdaExecutor {
    /*
    write execution code and logic for the LAMBDA resource
    */

    final String TAG = "LambdaExecutor: ";
        
    public String requestLambda(String sceneryType, String imageWidth,
                                String imageHeight, String imageWidthOut,
                                String imageHeightOut) {
        String objKeyInName = ConfigurationUtils.getFileInKey(sceneryType, imageWidth + "x" + imageHeight);
        String lambdaEvent = DataUtils.createLambdaEvent(ConfigurationUtils.s3BucketName,
                objKeyInName, imageWidthOut, imageHeightOut);
        System.out.println(TAG + lambdaEvent);
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(ConfigurationUtils.lambdaName)
                .withPayload(lambdaEvent)
                .withLogType("Tail");
        return invokeLambda(invokeRequest, sceneryType, imageWidth + "x" + imageHeight, imageWidthOut + "x" + imageHeightOut);
    }

    public String invokeLambda(InvokeRequest invokeRequest, String sceneryType, String oldSize, String newSize) {
        AWSLambda lambda = LambdaClient.getLambdaClient();
        InvokeResult invokeResult = null;
        try{
            invokeResult = lambda.invoke(invokeRequest);
            return ControlValidator.processFunctionalResponse(invokeResult, sceneryType, oldSize, newSize);
        }catch (ServiceException e) {
            throw new ServiceException("Service error [invokeLambda]: " + e);
        }
    }
        
    
}
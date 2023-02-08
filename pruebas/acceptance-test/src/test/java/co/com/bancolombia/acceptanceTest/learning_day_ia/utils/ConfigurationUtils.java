package co.com.bancolombia.acceptanceTest.learning_day_ia.utils;

public class ConfigurationUtils {
    // AWS Configuration, write any aws configuration here
    final public static String lambdaName = "learning_day_ia";
    final public static String s3BucketName = "learning-day-ia-bucket";
    final public static String tableName = "ia_learning_day_table";
    final public static String s3PrefixKeyIn = "image_in";
    final public static String s3PrefixKeyOut = "image_out";

    // Local Configuration, write any local configuration here
    final public static String eventFilePath = "src/test/resources/files/events";

    // Write any logic for configurations, for example getting local files and mapping to s3 paths, or to dynamodb local files.
    final public static String filesDirectoryPath = "src/test/resources/files/workflow_documents";

    public static String getFilePath(String sceneryType, String size){
        // write custom code to get events path accordint to scenaryType
        return filesDirectoryPath + "/image_" + sceneryType + "_" + size + "." + sceneryType;
    }

    public static String getFileInKey(String sceneryType, String size){
        return s3PrefixKeyIn + "/image_" + sceneryType + "_" + size + "." + sceneryType;
    }

    public static String getFileOutKey(String sceneryType, String oldSize, String newSize){
        return s3PrefixKeyOut + "/image_" + sceneryType + "_" + oldSize  + "to" + newSize + "." + sceneryType;
    }

    public static String getEventFilePath(){
        return eventFilePath + "/event.json";
    }
}
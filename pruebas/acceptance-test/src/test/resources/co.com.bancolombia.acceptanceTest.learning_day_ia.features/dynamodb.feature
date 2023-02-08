
@ignore @report=false
Feature: reusable scenarios to manage learning_day_ia's dynamodb actions

  @SearchItem
  Scenario:
    * def searchItemDynamodb =
  """
  function(sceneryType, imageWidth, imageHeight, imageWidthOut, imageHeightOut) {
    var DynamodbExecutorClass = Java.type('co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws.DynamodbExecutor');
    var DynamodbExecutor = new DynamodbExecutorClass();
    return DynamodbExecutor.searchItem(sceneryType, imageWidth, imageHeight, imageWidthOut, imageHeightOut);
  }
  """
    * def searchItemDynamodbResult = searchItemDynamodb(sceneryType, imageWidth, imageHeight, imageWidthOut, imageHeightOut)

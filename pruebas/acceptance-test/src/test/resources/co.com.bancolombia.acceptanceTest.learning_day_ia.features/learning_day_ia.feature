
Feature: Test for functional validation of lambda learning_day_ia

  Background:
    * configure report = { showLog: true, showAllSteps: false }
    * def invokeLambda =
  """
  function(sceneryType, imageWidth, imageHeight, imageWidthOut, imageHeightOut) {
    var LambdaExecutorClass = Java.type('co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws.LambdaExecutor');
    var learning_day_ia = new LambdaExecutorClass();
    return learning_day_ia.requestLambda(sceneryType, imageWidth, imageHeight, imageWidthOut, imageHeightOut);
  }
  """

  # write scenarios here for acceptanceTest for learning_day_ia Lambda
  Scenario: [acceptance-jpg-1920w-1080h-to-500w-500h] - Check resize from a 1920x1080 jpg image to 500x500
    * def config = {sceneryType: 'jpg', imageWidth: '1920', imageHeight: '1080', imageWidthOut: '500', imageHeightOut: '500'}
    # upload file to s3 bucket
    Given call read("s3.feature@UploadFile") config
    # execute lambda and save in result
    When def result = invokeLambda(config.sceneryType, config.imageWidth, config.imageHeight, config.imageWidthOut, config.imageHeightOut)
    # print result
    * print result
    # review result
    Then match result contains 'Successful'
    # review files in s3 output
    Given call read("s3.feature@SearchFile") config
    * print searchFileS3Result
    Then match searchFileS3Result contains 'Success'
    # review item saved in dynamodb
    Given call read("dynamodb.feature@SearchItem") config
    * print searchItemDynamodbResult
    Then match searchItemDynamodbResult contains 'Success'


  Scenario: [acceptance-jpg-996w-747h-to-420w-420h] - Check resize from a 996x747 jpg image to 420x420
    * def config = {sceneryType: 'jpg', imageWidth: '996', imageHeight: '747', imageWidthOut: '420', imageHeightOut: '420'}
    # upload file to s3 bucket
    Given call read("s3.feature@UploadFile") config
    # execute lambda and save in result
    When def result = invokeLambda(config.sceneryType, config.imageWidth, config.imageHeight, config.imageWidthOut, config.imageHeightOut)
    # print result
    * print result
    # review result
    Then match result contains 'Successful'
    # review files in s3 output
    Given call read("s3.feature@SearchFile") config
    * print searchFileS3Result
    Then match searchFileS3Result contains 'Success'
    # review item saved in dynamodb
    Given call read("dynamodb.feature@SearchItem") config
    * print searchItemDynamodbResult
    Then match searchItemDynamodbResult contains 'Success'


@ignore @report=false
Feature: reusable scenarios to manage learning_day_ia's s3 actions

  @UploadFile
  Scenario:
    * def uploadFileS3 =
  """
  function(sceneryType, imageWidth, imageHeight) {
    var S3ExecutorClass = Java.type('co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws.S3Executor');
    var S3Executor = new S3ExecutorClass();
    return S3Executor.uploadFile(sceneryType, imageWidth, imageHeight);
  }
  """
    * uploadFileS3(sceneryType, imageWidth, imageHeight)

  @SearchFile
  Scenario:
    * def searchFileS3 =
  """
  function(sceneryType, imageWidth, imageHeight, imageWidthOut, imageHeightOut) {
    var S3ExecutorClass = Java.type('co.com.bancolombia.acceptanceTest.learning_day_ia.components.aws.S3Executor');
    var S3Executor = new S3ExecutorClass();
    return S3Executor.searchFile(sceneryType, imageWidth, imageHeight, imageWidthOut, imageHeightOut);
  }
  """
    * def searchFileS3Result = searchFileS3(sceneryType, imageWidth, imageHeight, imageWidthOut, imageHeightOut)
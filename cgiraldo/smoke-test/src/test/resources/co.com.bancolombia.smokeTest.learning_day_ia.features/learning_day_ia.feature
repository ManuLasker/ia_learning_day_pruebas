
Feature: Test for functional validation of lambda learning_day_ia

  Background:
    * configure report = { showLog: true, showAllSteps: false }
    * def invokeLambda =
  """
  function(functionName) {
    var LambdaExecutorClass = Java.type('co.com.bancolombia.smokeTest.learning_day_ia.components.aws.LambdaExecutor');
    var learning_day_ia = new LambdaExecutorClass();
    return learning_day_ia.requestLambda(functionName);
  }
  """

  # write scenarios here for smokeTest for learning_day_ia Lambda

  
  Scenario: Validate health
    When def result = call invokeLambda 'health'
    Then match result contains 'Successful'
    * print result
  

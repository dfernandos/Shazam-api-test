package com.yieldstreet.runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/yieldstreet/stepdefinitions")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "com/yieldstreet/features")
public class RunCucumberTest {
}

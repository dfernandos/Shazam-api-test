package com.yieldstreet.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/yieldstreet/features",
        glue = "com/yieldstreet/stepdefinitions"
)
public class RunCucumberTest {
}

package com.java.Test;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/LabCorp.feature",
        glue = "com.java.stepDef",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class JunitRunner {

}


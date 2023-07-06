package com.java.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class labCorpStepDef {

   private WebDriver driver;
   private WebDriverWait wait;
   private static String url = "https://www.labcorp.com";





    @Given("I open LabCorp website")
    public void i_open_lab_corp_website() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(labCorpStepDef.url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        wait=new WebDriverWait(driver, Duration.ofSeconds(60));


    }
    @When("I click on the Careers link")
    public void i_click_on_the_careers_link() {
        WebElement CareersLink=driver.findElement(By.linkText("Careers"));
        CareersLink.click();

    }
    @When("I search for the job position {string}")
    public void i_search_for_the_job_position(String JobName) {
        WebElement JobSearchBox=driver.findElement(By.xpath("(//input[@name='typehead'])[2]"));
        wait.until(ExpectedConditions.visibilityOf(JobSearchBox));
        JobSearchBox.clear();
        JobSearchBox.sendKeys(JobName);
        JobSearchBox.sendKeys(Keys.ENTER);

    }
    @When("I select and browse to the job position {string}")
    public void i_select_and_browse_to_the_job_position(String JobName) {

        WebElement JobPosition=driver.findElement(By.xpath("//span[normalize-space(text())='"+JobName+"']/ancestor::a"));
        wait.until(ExpectedConditions.visibilityOf(JobPosition));
        JobPosition.click();

    }
    @Then("I confirm the job title as {string}")
    public void i_confirm_the_job_title(String Jobtitle) {
        WebElement JobTitle=driver.findElement(By.xpath("//h1[text()='"+Jobtitle+"']"));
        wait.until(ExpectedConditions.visibilityOf(JobTitle));
        Assert.assertEquals(Jobtitle, JobTitle.getText());

    }
    @Then("I confirm the job location as {string}")
    public void i_confirm_the_job_location(String Joblocation) {
        WebElement JobLocation=driver.findElement(By.xpath("//span[text()='Location']/.."));
        wait.until(ExpectedConditions.visibilityOf(JobLocation));
        Assert.assertEquals(Joblocation, JobLocation.getText().split("\\n")[1].trim());
    }
    @Then("I confirm the job id as {string}")
    public void i_confirm_the_job_id(String Jobid) {
        WebElement JobId=driver.findElement(By.xpath("//*[text()=' Job Id : ']/.."));
        wait.until(ExpectedConditions.visibilityOf(JobId));
        Assert.assertEquals(Jobid, JobId.getText().split(":")[1].trim());
    }
    @Then("I assert additional information {string} {string} {string}")
    public void i_assert_additional_information(String JobDetail_1,String JobDetail_2,String JobDetail_3) {
        WebElement JobDetails=driver.findElement(By.xpath("//div[@class='job-description']/div[2]"));
        Assert.assertTrue(JobDetails.getText().contains(JobDetail_1));
        Assert.assertTrue(JobDetails.getText().contains(JobDetail_2));
        Assert.assertTrue(JobDetails.getText().contains(JobDetail_3));


    }
    @When("I click Apply Now")
    public void i_click_apply_now() {
        WebElement ApplyJob=driver.findElement(By.xpath("//*[text()='Apply Now']"));
       // ApplyJob.click();
    }
    @Then("I confirm the job title on the application page")
    public void i_confirm_the_job_title_on_the_application_page() {

    }
    @Then("I confirm the job location on the application page")
    public void i_confirm_the_job_location_on_the_application_page() {

    }
    @Then("I confirm the job ID on the application page")
    public void i_confirm_the_job_id_on_the_application_page() {

    }
    @Then("I assert additional information on the application page")
    public void i_assert_additional_information_on_the_application_page() {

    }
    @When("I click to Return to Job Search")
    public void i_click_to_return_to_job_search() {
        WebElement JobSearchLinkOnTop=driver.findElement(By.xpath("//div[@class='nav-right-list']//*[text()='Job Search']/parent::a"));
        JobSearchLinkOnTop.click();
    }
    @Then("I am back on the Job Search page")
    public void i_am_back_on_the_job_search_page() {
        WebElement JobSearch=driver.findElement(By.xpath("(//input[@name='typehead'])[2]"));
        wait.until(ExpectedConditions.visibilityOf(JobSearch));
        Assert.assertTrue(JobSearch.isDisplayed());
        driver.quit();
    }



}

package com.scott.seleniumRefresher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.scott.seleniumRefresher.pageSelectors.HomePage;


public class FrequencyHomePage {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/scottcardinali/Repos/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        driver.get(HomePage.frequencyHome);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFrequencyHomeTitle() {

        WebElement title = driver.findElement(By.xpath(HomePage.frequencyTitle));

        assertTrue(title.isDisplayed());
    }

    @Test
    public void testFrequencyWorkflowNavBtn() {

        WebElement topNavWorkflow = driver.findElement(By.xpath(HomePage.topNavWorkflow));
        String workflowText = topNavWorkflow.getText();

        assertEquals("Workflow", workflowText);
    }

    @Test
    public void testFrequencyDistroNavBtn() {

        WebElement topNavWorkflow = driver.findElement(By.xpath(HomePage.topNavDistro));
        String distroText = topNavWorkflow.getText();

        assertEquals("Distribution", distroText);
    }

    @Test
    public void testFrequencyCompanyNavBtn() {

        WebElement topNavWorkflow = driver.findElement(By.xpath(HomePage.topNavCompany));
        String companyText = topNavWorkflow.getText();

        assertEquals("Company", companyText);
    }

    @Test
    public void testFrequencySupportNavBtn() {

        WebElement topNavWorkflow = driver.findElement(By.xpath(HomePage.topNavSupport));
        String supportText = topNavWorkflow.getText();

        assertEquals("Support", supportText);
    }

}

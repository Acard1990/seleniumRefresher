package com.scott.seleniumRefresher;

import com.scott.seleniumRefresher.pageSelectors.Workflow;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrequencyWorkflowPage {
    private static WebDriver driver;
    private List<String> headers = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/scottcardinali/Repos/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        driver.get(Workflow.workflowURL);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public FrequencyWorkflowPage() {
        headers.add(Workflow.Ingest);
        headers.add(Workflow.Manage);
        headers.add(Workflow.Program);
        headers.add(Workflow.Schedule);
    }

    @Test
    public void validateAllHeadersOnWorkflowPage() {
        headers.forEach(header -> {
            System.out.println("Running test for header: " + header);
            WebElement head = driver.findElement(By.xpath(header));
            assertTrue(head.isDisplayed());
        });
    }
}

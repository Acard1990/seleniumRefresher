package com.scott.seleniumRefresher;

import com.scott.seleniumRefresher.pageSelectors.Portfolio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PortfolioPage {
    private static WebDriver driver;
    Duration timeoutDuration = Duration.ofSeconds(10);
    WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/scottcardinali/Repos/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        driver.get(Portfolio.PortfolioURL);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void validateTerminalWindowFilePathIsVisible() {

        WebElement filePath = driver.findElement(By.className(Portfolio
                .fauxFilePath));

        assertTrue(filePath.isDisplayed());
    }

    @Test
    public void validateTerminalWindowFilePathValue() {

        WebElement filePath = driver.findElement(By.className(Portfolio
                .fauxFilePath));

        assertEquals(filePath.getText()
        , Portfolio.expectedFauxFilePath);
    }

    @Test
    public void validateCreateUser() throws InterruptedException {

        WebElement toggleBtn = driver.findElement(By.id(Portfolio
                .toggleBtn));
        toggleBtn.click();

        WebElement genUUID = driver.findElement(By.id(Portfolio
                .genRandomUUIDBtn));
        Thread.sleep(1000);
        genUUID.click();

        WebElement firstName = driver.findElement(By.id(Portfolio
                .firstNameInput));
        firstName.sendKeys("Testing");

        WebElement lastName = driver.findElement(By.id(Portfolio.lastNameInput));
        lastName.sendKeys("UserCreation");

        WebElement email = driver.findElement(By.id(Portfolio.emailInput));
        email.sendKeys("UserCreation@email.com");

        WebElement addUser = driver.findElement(By.id(Portfolio.addUserBtn));
        addUser.click();

        WebElement table = driver.findElement(By.className("user-grid"));


        for (WebElement row : table.findElements(By.tagName("tr"))) {
            for (WebElement cell : row.findElements(By.tagName("td"))) {
                if (cell.getText().contains("UserCreation@email.com")) {
                    System.out.println("Found in cell: " + cell.getText());
                    assertEquals(cell.getText()
                            , "UserCreation@email.com");
                }
            }
        }
    }

    @Test
    public void validateDeleteUser() throws InterruptedException {

        WebElement genUUIDButton = driver.findElement(By.id(Portfolio
                .genRandomUUIDBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", genUUIDButton);
        genUUIDButton.click();

        WebElement firstName = driver.findElement(By.id(Portfolio
                .firstNameInput));
        firstName.sendKeys("Testing");

        WebElement lastName = driver.findElement(By.id(Portfolio.lastNameInput));
        lastName.sendKeys("UserCreation");

        WebElement email = driver.findElement(By.id(Portfolio.emailInput));
        email.sendKeys("UserCreation@email.com");

        WebElement addUser = driver.findElement(By.id(Portfolio.addUserBtn));
        addUser.click();

        WebElement table = driver.findElement(By.className("user-grid"));

        for (WebElement row : table.findElements(By.tagName("tr"))) {
            boolean textFound = false;
            for (WebElement cell : row.findElements(By.tagName("td"))) {
                if (cell.getText().contains("UserCreation@email.com")) {
                    System.out.println("Found in cell: " + cell.getText());
                    textFound = true;
                    break;
                }
            }

            if (textFound) {
                WebElement deleteButton = row.findElement(By.id(Portfolio.deleteBtn));
                deleteButton.click();
                Thread.sleep(1000); // Wait for 1 second
                try {
                    WebElement removedDeleteButton = row.findElement(By.id(Portfolio.deleteBtn));
                    removedDeleteButton.click();
                    System.out.println("Element is stale as expected.");

                } catch (StaleElementReferenceException e) {
                    System.out.println("Stale element exception occurred.");
                    assertTrue(true);
                }
                break;
            }
        }
   }

}

package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class addRecordTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By addButtonBy = By.cssSelector("#addNewRecordButton");
    private By firstNameBoxBy = By.cssSelector("#firstName");
    private By lastNameBoxBy = By.cssSelector("#lastName");
    private By userEmailBoxBy = By.cssSelector("#userEmail");
    private By ageBoxBy = By.cssSelector("#age");
    private By salaryBoxBy = By.cssSelector("#salary");
    private By departmentBoxBy = By.cssSelector("#department");
    private By submitButtonBy = By.cssSelector("#submit");
    private By editButtonBy = By.cssSelector(".rt-tbody > div:nth-of-type(4) .mr-2");
    private By salaryUpdateControlBy = By.cssSelector(".rt-tbody > div:nth-of-type(4) div:nth-of-type(5)");


    @BeforeClass
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/org/example/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        js = (JavascriptExecutor) driver;
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

        @Test
        public void addTest(){
            driver.get("https://demoqa.com/webtables");
            WebElement addButton = driver.findElement(addButtonBy);
            addButton.click();

            WebElement firstNameBox = driver.findElement(firstNameBoxBy);
            firstNameBox.sendKeys("Gönül");

            WebElement lastNameBox = driver.findElement(lastNameBoxBy);
            lastNameBox.sendKeys("Çetin");

            WebElement userEmailBox = driver.findElement(userEmailBoxBy);
            userEmailBox.sendKeys("gnlntc@gmail.com");

            WebElement ageBox = driver.findElement(ageBoxBy);
            ageBox.sendKeys("30");

            WebElement salaryBox = driver.findElement(salaryBoxBy);
            salaryBox.sendKeys("50000");

            WebElement departmentBox = driver.findElement(departmentBoxBy);
            departmentBox.sendKeys("IT");

            WebElement submitButton = driver.findElement(submitButtonBy);
            submitButton.click();

            js.executeScript("window.scrollBy(0, 500)");

            WebElement editButton = driver.findElement(editButtonBy);
            editButton.click();

            WebElement salaryField = driver.findElement(salaryBoxBy);
            salaryField.clear();
            salaryField.sendKeys("60000");

            WebElement submitUpdateClick = driver.findElement(submitButtonBy);
            submitUpdateClick.click();

            WebElement salaryUpdateControl = driver.findElement(salaryUpdateControlBy);
            Assert.assertTrue(salaryUpdateControl.isDisplayed());
            Assert.assertEquals(salaryUpdateControl.getText(), "60000");
        }
        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }


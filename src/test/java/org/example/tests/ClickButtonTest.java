package org.example.tests;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ClickButtonTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeClass
    public void Setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        js = (JavascriptExecutor) driver;
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void clickMeButtonTest(){
        driver.get("https://demoqa.com/elements");
        WebElement buttonOption =driver.findElement(By.cssSelector("#item-4"));
        buttonOption.click();
        js.executeScript("window.scrollBy(0, 500)");
        WebElement meButton= driver.findElement(By.cssSelector(".col-md-6 div:nth-of-type(3) > .btn"));
        meButton.click();
        WebElement confirmationMessage = driver.findElement(By.cssSelector("#dynamicClickMessage"));
        Assert.assertEquals(confirmationMessage.getText(), "You have done a dynamic click");

    }

    @Test
    public void addRecordTest(){
        driver.get("https://demoqa.com/webtables");
        WebElement addButton =driver.findElement(By.cssSelector("#addNewRecordButton"));
        addButton.click();
        //js.executeScript("window.scrollBy(0, 500)");
        WebElement firstNameBox= driver.findElement(By.cssSelector("#firstName"));
        firstNameBox.sendKeys("Gönül");
        WebElement lastNameBox= driver.findElement(By.cssSelector("#lastName"));
        lastNameBox.sendKeys("Çetin");
        WebElement userEmailBox= driver.findElement(By.cssSelector("#userEmail"));
        userEmailBox.sendKeys("gnlntc@gmail.com");
        WebElement ageBox= driver.findElement(By.cssSelector("#age"));
        ageBox.sendKeys("30");
        WebElement salaryBox= driver.findElement(By.cssSelector("#salary"));
        salaryBox.sendKeys("50000");
        WebElement departmentBox= driver.findElement(By.cssSelector("#department"));
        departmentBox.sendKeys("IT");
        WebElement submitButton= driver.findElement(By.cssSelector("#submit"));
        submitButton.click();
        js.executeScript("window.scrollBy(0, 500)");
        WebElement editButton = driver.findElement(By.cssSelector(".rt-tbody > div:nth-of-type(4) .mr-2"));
        editButton.click();
        WebElement salaryField = driver.findElement(By.cssSelector("#salary"));
        salaryField.clear();
        salaryField.sendKeys("60000");
        WebElement submitUpdateClick = driver.findElement(By.cssSelector("#submit"));
        submitUpdateClick.click();
        WebElement SalaryUpdateControl = driver.findElement(By.cssSelector(".rt-tbody > div:nth-of-type(4) div:nth-of-type(5)"));
        Assert.assertTrue(SalaryUpdateControl.isDisplayed());
        Assert.assertEquals(SalaryUpdateControl.getText(),"60000");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package love.moon.selenium.key;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class KeysTest {

    @Test
    public void keyDown() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");

        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("a")
                .perform();

        WebElement textField = driver.findElement(By.id("textInput"));
        Assert.assertEquals("A", textField.getAttribute("value"));
    }

    @Test
    public void keyDown2() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");

        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("s")
                .perform();

        WebElement textField = driver.findElement(By.id("textInput"));
        System.out.println(textField.getText());
    }

    @Test
    public void copyAndPaste() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");

        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        WebElement textField = driver.findElement(By.id("textInput"));
        new Actions(driver)
                .sendKeys(textField, "Selenium!")
                .sendKeys(Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("xvv")
                .keyUp(cmdCtrl)
                .perform();

        Assert.assertEquals("SeleniumSelenium!", textField.getAttribute("value"));
    }

    /**
     * 鼠标中间向下滑动
     */
    @Test
    public void moveByOffset() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://wenku.csdn.net/answer/2e3wt1r0dt");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

            for (int i = 0; i < 1; i++) {
                new Actions(driver)
                        .moveByOffset(0, 1000)
                        .perform();
            }
        } finally {
//            driver.quit();
        }
    }

    @Test
    public void moveToElement() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://wenku.csdn.net/answer/2e3wt1r0dt");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
            WebElement webElement = driver.findElement(By.cssSelector(".recommend-list"));
            new Actions(driver)
                    .moveToElement(webElement)
                    .perform();

        } finally {
            driver.quit();
        }
    }
   @Test
    public void moveToElement2() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://baike.weixin.qq.com/v188754.htm");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
            WebElement webElement = driver.findElement(By.cssSelector("li:last-child"));
            webElement.click();
            Thread.sleep(4000l);
        } finally {
            driver.quit();
        }
    }

}

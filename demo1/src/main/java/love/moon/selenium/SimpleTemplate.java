package love.moon.selenium;


import love.moon.selenium.demo.Constants;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SimpleTemplate {

    String url = Constants.url;

    @Test
    public void first() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        System.out.println("title:" + title);
        // 等待加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        Thread.sleep(1000L);
        textBox.sendKeys("Selenium");
        Thread.sleep(1000L);
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        //获取元素信息
        String text = message.getText();
        System.out.println("text: " + text);
        Thread.sleep(1000L);
        //关闭浏览器，结束会话
        driver.quit();
    }




}

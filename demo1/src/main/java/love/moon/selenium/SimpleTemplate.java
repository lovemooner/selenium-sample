package love.moon.selenium;


import love.moon.selenium.demo.AbsTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class SimpleTemplate  extends AbsTest {

    String url = "https://www.selenium.dev/selenium/web/web-form.html";

    @Test
    public void first() throws InterruptedException {
        // 等待加载
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        System.out.println("title:" + title);


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

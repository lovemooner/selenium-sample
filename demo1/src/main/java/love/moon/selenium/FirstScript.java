package love.moon.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstScript {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

       String title= driver.getTitle();
        System.out.println("title:"+title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        Thread.sleep(1000L);
        textBox.sendKeys("Selenium");
        Thread.sleep(1000L);

        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        //获取元素信息
        String text= message.getText();
        System.out.println("text: "+text);

        Thread.sleep(1000L);
        //结束会话
        driver.quit();
    }
}

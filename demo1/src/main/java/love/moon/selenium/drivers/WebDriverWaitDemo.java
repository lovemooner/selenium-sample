package love.moon.selenium.drivers;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverWaitDemo {


    /**
     * 隐式等待
     */
    @Test
    public void implicit() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            // 隐式等待加载，等待指定时间
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        } finally {
            driver.quit();
        }
    }

    /**
     * 显示等待
     */
    @Test
    public void Explicit1() {
        //2.显示等待
        WebDriver driver = new ChromeDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(300))
                    .until(drive -> ((JavascriptExecutor) drive)
                            .executeScript("return document.readyState").equals("complete"));
        } finally {
            driver.quit();
        }
    }

    /**
     * 显示等待
     */
    @Test
    public void Explicit2() {
        //2.显示等待
        WebDriver driver = new ChromeDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("submit_btn")));

        } finally {
            driver.quit();
        }
    }

}

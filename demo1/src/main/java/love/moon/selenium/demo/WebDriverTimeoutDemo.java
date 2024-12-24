package love.moon.selenium.demo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * 显示等待/隐式等待 使用场景：页面元素异步加载
 */
public class WebDriverTimeoutDemo extends AbsTest{



    private final String url = "http://localhost:8088/seleunimList.html";

    @Test
    public void pageLoadTimeout() {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(1000)); // 页面加载超时
            long start = System.currentTimeMillis();
            System.out.println("driver.get start");
            driver.get(url);
            System.out.println("driver.get end,elapsed " + (System.currentTimeMillis() - start) / 1000 + "s");

        } catch (TimeoutException e) {
            System.err.println("页面加载超时异常");
        } finally {
            driver.quit();
        }
    }


    @Test
    public void implicitlyWait() {
        long start = System.currentTimeMillis();
        try {
            // 隐式等待加载，在设置时间范围内，只要条件成立，马上结束等待
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            System.out.println("driver.get start");
            driver.get(url);
            System.out.println("driver.get,elapsed " + (System.currentTimeMillis() - start) / 1000 + "s");
            WebElement element = driver.findElement(By.id("abc"));
            System.out.printf("elem: %s\n", element.getTagName());
        } catch (NoSuchElementException e) {
            System.err.println("NoSuchElementException");
        } finally {
            driver.quit();
            long time = (System.currentTimeMillis() - start) / 1000;
            System.out.println("elapsed " + time);
        }
    }

    /**
     * 显示等待
     * 是针对某一个元素进行相关等待判定.
     */
    @Test
    public void Explicit() {
        long start = System.currentTimeMillis();
        try {
            System.out.println("driver.get start");
            driver.get(url);
            System.out.println("driver.get end,elapsed " + (System.currentTimeMillis() - start) / 1000 + "s");

            new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("abc")));

            System.out.println("end");
        } catch (TimeoutException timeoutException) {
            System.err.println("显示等待-超时异常");
        } finally {
            driver.quit();
            long time = (System.currentTimeMillis() - start) / 1000;
            System.out.println("elapsed " + time);
        }
    }


    @Test
    public void setScriptTimeout() {
        long start = System.currentTimeMillis();
        try {
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(1)); // 加载超时
            System.out.println("driver.get start");
            driver.get(url);
            System.out.println("driver.get end,elapsed " + (System.currentTimeMillis() - start) / 1000 + "s");
            WebElement element = driver.findElement(By.cssSelector(".layui-table tr:nth-child(2)"));
            System.out.printf("elem: %s\n", element.getTagName());
        } catch (TimeoutException e) {
            System.err.println("页面加载超时异常");
        } finally {
            driver.quit();
        }
    }


}

package love.moon.selenium.demo;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ScrollDemo {

    @Test
    public void scroll() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://cloud.tencent.com/developer/article/1406356");
        // 等待加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        //滚动加载
        JavascriptExecutor executor = ((JavascriptExecutor) driver);
        //下拉到页面底部
        executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(1000);
        //上拉到页面顶端
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");

        Thread.sleep(200);
        driver.quit();
    }

    @Test
    public void scroll2() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
//        driver.get("https://baike.weixin.qq.com/v188754.htm");
        driver.get("https://cloud.tencent.com/developer/article/1406356");
        // 等待加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        // 有的时候必须点击一下，下拉才能生效（有的网站是这样）
//        WebElement webElement = driver.findElement(By.cssSelector("body"));
//        webElement.click();
        //滚动加载
        JavascriptExecutor executor = ((JavascriptExecutor) driver);
        Long all_height1 = (Long) executor.executeScript("return document.body.scrollHeight");
//        all_height1=10000l;
        int all_height = all_height1.intValue();
        int scroll_height = 1000;
        int time = all_height / scroll_height;
        for (int i = 0; i <= time; i++) {
            executor.executeScript("window.scrollTo(0," + i * scroll_height + " )");
            Thread.sleep(200);
            System.out.println("第" + i + "次，下拉高度" + scroll_height);
        }
        driver.quit();
    }

    @Test
    public void scroll3() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            String url="https://cloud.tencent.com/developer/article/1406356";
             url="https://baike.weixin.qq.com/v188754.htm";
            driver.get(url);
            // 等待加载
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
            WebElement webElement = driver.findElement(By.cssSelector("body"));
            for (int i = 0; i < 10; i++) {
                Thread.sleep(200);

                ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
                //小幅度下拉
//                webElement.sendKeys(Keys.PAGE_DOWN);
                //或者直接下拉到底
                webElement.sendKeys(Keys.END);
                System.out.println("第" + i + "次，下拉");
            }
        } finally {
            driver.quit();
        }
    }
}

package love.moon.selenium.demo;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverOptionsTest {
    String url="https://www.selenium.dev/selenium/web/web-form.html";

    @Test
    public void headless(){
        // 设置浏览器驱动程序路径
//        System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 无头模式，不显示浏览器界面
        WebDriver driver = new ChromeDriver(options);
        // 打开目标网页
        driver.get(url);
        String title= driver.getTitle();
        System.out.println("title:"+title);

        // 关闭浏览器窗口
        driver.quit();
    }
}

package love.moon.selenium.demo;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AbsTest {

   public WebDriver driver;

    @Before
    public void initEnv() {
        String path = "D:\\yy\\南京地理环境\\部署\\driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 无头模式，不显示浏览器界面
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
}

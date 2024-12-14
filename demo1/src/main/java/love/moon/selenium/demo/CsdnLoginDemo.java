package love.moon.selenium.demo;


import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CsdnLoginDemo {
    public static void main(String[] args) throws Exception {
        // 初始化参数据
//        System.setProperty("webdriver.chrome.driver", "C:/bin/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://passport.csdn.net/account/login";
        // 加载url
        driver.get(baseUrl);
        // 等待加载完成
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        String notes=  driver.findElement(By.cssSelector(".notes")).getText();
        System.out.println("notes:"+notes);
        driver.findElement(By.cssSelector(".login-box-tabs-items")).getText();
        WebElement submitButton =driver.findElements(By.cssSelector(".login-box-tabs-items span")).get(3);
        submitButton.click();
        // 获取页面元素
        List<WebElement> loginInputs =driver.findElements(By.cssSelector(".login-form-item .base-input-text"));
        WebElement elemUsername = loginInputs.get(0);
        WebElement elemPassword = loginInputs.get(1);
        WebElement btn = driver.findElement(By.cssSelector(".login-form-item button"));
        WebElement rememberMe = driver.findElement(By.cssSelector(".login-inform .icon-nocheck"));
        // 操作页面元素
        elemUsername.clear();
        elemPassword.clear();
        elemUsername.sendKeys("ndong211@163.com");
        elemPassword.sendKeys("ccx1155665ccx");
        rememberMe.click();
        btn.click();
        Thread.sleep(1000);
        driver.get("https://i.csdn.net/#/msg/like");
        Thread.sleep(2000);
        // 获取cookies
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("cookies Size: " + cookies.size());

        CookieStore cookieStore = new BasicCookieStore();
        for (Cookie cookie : cookies) {
            BasicClientCookie bcco = new BasicClientCookie(cookie.getName(), cookie.getValue());
            bcco.setDomain(cookie.getDomain());
            bcco.setPath(cookie.getPath());
            cookieStore.addCookie(bcco);
        }
    }
}
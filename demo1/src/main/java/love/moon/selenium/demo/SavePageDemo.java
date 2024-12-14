package love.moon.selenium.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

@Slf4j
public class SavePageDemo {

    @Test
    public void executeScript() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://cloud.tencent.com/developer/article/1406356");
        // 等待加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        //滚动加载
        JavascriptExecutor executor = ((JavascriptExecutor) driver);
        Thread.sleep(1000);

        List resources = (List) executor.executeScript("return window.performance.getEntriesByType('resource')");
        resources.forEach(e->{
            System.out.println(e);
        });
        Thread.sleep(200);
        driver.quit();
    }

    @Test
    public void htmlToImage() throws InterruptedException, IOException {
//        System.setProperty("webdriver.chrome.driver",
//                "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        // 将窗口最大化
        driver.manage().window().maximize();
        // 等待页面加载完毕
        new WebDriverWait(driver, Duration.ofSeconds(300)).until(drive -> ((JavascriptExecutor) drive)
                .executeScript("return document.readyState").equals("complete"));
        driver.get("http://www.baidu.com/");

        //找到百度上面的输入框、放入输入内容‘鹿晗人妖’
        driver.findElement(By.id("kw")).sendKeys("鹿晗人妖");
        //点击百度旁边的搜索按钮
        driver.findElement(By.id("su")).click();
        //暂停两秒，让他加载搜索出来的数据
        Thread.sleep(2000);
        //对整个网页截图
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //把截图保存到桌面
        FileUtils.copyFile(srcFile, new File("d:\\tmp\\screen1.png"));
        driver.quit();

    }

    @Test
    public void savePage() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 无头模式，不显示浏览器界面
        WebDriver driver = new ChromeDriver(options);
        String str = "https://blog.csdn.net/foreverling/article/details/51385128";
        URL url = new URL(str);
        driver.get(str);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        //下拉到页面底部
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");

        String pageSource = driver.getPageSource();
        String title = driver.getTitle()
                .replace(" ", "")
                .replace("|", "");
        String path = "C:\\Users\\d\\Desktop\\tmp";
        String filesPath = path + File.separator + title + "_files";
        File files = new File(filesPath);
        files.deleteOnExit();
        files.mkdir();

        Document doc = Jsoup.parse(pageSource);
        Elements elements = doc.select("script");
        for (Element element : elements) {
            element.remove();
        }

        elements = doc.select("link[rel='stylesheet']");
        elements.addAll(doc.select("link[rel='icon']"));
        for (Element element : elements) {
            String href = element.attr("href");
            if (href == null || href.length() == 0) {
                continue;
            }
            href = appendUrl(url, href);
            String fileName = href.substring(href.lastIndexOf("/"));
            File newFile = new File(filesPath + fileName);
            download(href, newFile);
            element.attr("href", ".." + fileName);
        }
        elements = doc.select("link[rel='preload']");
        for (Element element : elements) {
            element.attr("href", "" );
        }

        elements = doc.select("img");
        for (Element element : elements) {
            String href = element.attr("img");
            if (href == null || href.length() == 0) {
                continue;
            }
            href = appendUrl(url, href);
            String fileName = href.substring(href.lastIndexOf("/"), href.lastIndexOf("css") + 3);
            File newFile = new File(filesPath + fileName);
            download(href, newFile);
            element.attr("src", ".." + fileName);
        }

        String html = doc.html();
        System.out.println(html);
        File dest = new File(path + File.separator + title + ".html");
        FileUtils.writeByteArrayToFile(dest, html.getBytes());

        driver.quit();
    }


    private String appendUrl(URL url, String path) {
        String protocol=url.getProtocol();
        if (path.startsWith("//")) {
            path = protocol+":" + path;
        } else if (path.startsWith("/")) {
            String domain = url.getProtocol() + "://" + url.getHost();
            path = domain + path;
        }
        return path;
    }

    void download(String url, File newFile) {
        try {
            FileUtils.copyURLToFile(new URL(url), newFile);
            System.out.println("下载文件: " + url);
//            log.info("下载文件 {}" , url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package love.moon.selenium.drivers;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class ChromeDriverProvider implements DriverProvider {

    private String chromeDriverPath="xxx";

    @Override
    public String support() {
        return chromeDriverPath != null ? "chrome" : null;
    }

    @Override
    public WebDriver getWebDriver(JSONObject param) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        String userAgent = param.getString(USER_AGENT);
        //无头模式
        if("1".equals(param.getString(HEADLESS))){
            options.addArguments("--headless", "--disable-gpu");
        }
        //禁用沙盒模式
        if(!"1".equals(param.getString(SANDBOX))){
            options.addArguments("--no-sandbox");
        }
        //隐身模式
        if("1".equals(param.getString(INCOGNITO))){
            options.addArguments("--incognito");
        }
        //设置User-Agent
        if(userAgent!=null&&userAgent.length()>0){
            options.addArguments("user-agent=\"" + userAgent +"\"");
        }
        //禁用JS
        if("1".equals(param.getString(JAVASCRIPT_DISABLED))){
            options.addArguments("–-disable-javascript");
        }
        //禁用加载图片
        if("1".equals(param.getString(IMAGE_DISABLED))){
            options.addArguments("blink-settings=imagesEnabled=false");
        }
        //隐藏滚动条
        if("1".equals(param.getString(HIDE_SCROLLBAR))){
            options.addArguments("--hide-scrollbars");
        }
        //禁用插件
        if("1".equals(param.getString(PLUGIN_DISABLE))){
            options.addArguments("--disable-plugins");
        }
        //禁用Java
        if("1".equals(param.getString(JAVA_DISABLE))){
            options.addArguments("--disable-java");
        }
        //设置窗口大小
        String windowSize = param.getString(WINDOW_SIZE);
        if(StringUtils.isNotBlank(windowSize)){
            options.addArguments("--window-size=" + windowSize);
        }
        //最大化
        if("1".equals(param.getString(MAXIMIZED))){
            options.addArguments("--start-maximized");
        }
        //设置其他参数
        String arguments = param.getString(ARGUMENTS);
        if(StringUtils.isNotBlank(arguments)){
            options.addArguments(Arrays.asList(arguments.split("\r\n")));
        }
        //设置代理
        String proxyStr=param.getString("proxy");
        if (StringUtils.isNotBlank(proxyStr)) {
            Proxy proxy = new Proxy();
            proxy.setHttpProxy(proxyStr);
            options.setProxy(proxy);
        }
        return new ChromeDriver(options);
    }
}

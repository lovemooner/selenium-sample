package love.moon.selenium.drivers;

import com.alibaba.fastjson2.JSONObject;
import org.openqa.selenium.WebDriver;

public interface DriverProvider {

     String JAVASCRIPT_DISABLED = "javascript-disabled";

     String USER_AGENT = "user-agent";

     String HEADLESS = "headless";

     String IMAGE_DISABLED = "image-disabled";

     String HIDE_SCROLLBAR = "hide-scrollbar";

     String PLUGIN_DISABLE = "plugin-disable";

     String JAVA_DISABLE = "java-disable";

     String INCOGNITO = "incognito";

     String SANDBOX = "sandbox";

     String WINDOW_SIZE = "window-size";

     String MAXIMIZED = "maximized";

     String ARGUMENTS = "arguments";

     String support();

     WebDriver getWebDriver(JSONObject node);
}

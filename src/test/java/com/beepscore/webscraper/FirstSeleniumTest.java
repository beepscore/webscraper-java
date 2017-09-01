package com.beepscore.webscraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;

// chrome driver stuff
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

// Reference
// CodeSolid tutorials, SeleniumStarterGradle
// https://github.com/CodeSolid/tutorials/tree/master/SeleniumStarterGradle
public class FirstSeleniumTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {

        baseUrl = "https://github.com";
        // baseUrl = "http://beepscore.com";
        loadFirefoxDriver();
        // loadChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private void loadFirefoxDriver() {
        driver = new FirefoxDriver();
    }

//    private void loadChromeDriver() throws MalformedURLException {
//        // To remove message "You are using an unsupported command-line flag: --ignore-certificate-errors.
//        // Stability and security will suffer."
//        // Add an argument 'test-type'
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("test-type");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        driver = new RemoteWebDriver(new URL("http://localhost:9515"), capabilities);
//    }

    @Test
    public void testFirstSelenium() throws Exception {
        driver.get(baseUrl + "/codesolid");
        assertEquals("CodeSolid Software Development · GitHub", driver.findElement(By.cssSelector("html title")).getText());

        // driver.findElement(By.linkText("tutorials")).click();
        // assertEquals("Welcome to the CodeSolid Tutorials", driver.findElement(By.cssSelector("html body.logged_out.env-production.windows.vis-public div.wrapper div.site div.container div.repository-with-sidebar.repo-container.new-discussion-timeline.js-new-discussion-timeline.with-full-navigation div#js-repo-pjax-container.repository-content.context-loader-container div#readme.clearfix.announce.instapaper_body.md article.markdown-body.entry-content h1")).getText());
        // change logged_out to logged-out
        // assertEquals("Welcome to the CodeSolid Tutorials", driver.findElement(By.cssSelector("html body.logged-out.vis-public div.wrapper div.site div.container div.repository-with-sidebar.repo-container.new-discussion-timeline.js-new-discussion-timeline.with-full-navigation div#js-repo-pjax-container.repository-content.context-loader-container div#readme.clearfix.announce.instapaper_body.md article.markdown-body.entry-content h1")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

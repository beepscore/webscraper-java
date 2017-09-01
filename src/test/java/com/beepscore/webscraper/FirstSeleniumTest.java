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

        baseUrl = "http://beepscore.com";
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
    public void testTitle() throws Exception {
        driver.get(baseUrl + "/unit-testing");
        assertEquals("Beepscore", driver.findElement(By.cssSelector("html title")).getText());
    }

    @Test
    public void testH1() throws Exception {
        driver.get(baseUrl + "/unit-testing");
        String expected = "Unit Testing";
        assertEquals(expected, driver.findElement(By.cssSelector("html h1")).getText());
    }

    @Test
    public void testH3() throws Exception {
        driver.get(baseUrl + "/unit-testing");
        String expected = "Jenkins iOS Unit Test Results";
        assertEquals(expected, driver.findElement(By.cssSelector("html h3")).getText());
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

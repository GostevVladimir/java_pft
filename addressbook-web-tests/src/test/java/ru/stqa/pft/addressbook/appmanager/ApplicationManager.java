package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  private NavigatioHelper navigatioHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private String browser;
  private Properties properties;
  private DbHalper dbHalper;
  private GoogleMainPage googleMainPage;
  private WikipediaPage wikipediaPage;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "remote");
    //String target = System.getProperty("target", "remote");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

//    dbHalper = new DbHalper();

    if("".equals(properties.getProperty("selenium.server"))) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
    }else{
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "ubuntu")));
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }

    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigatioHelper = new NavigatioHelper(wd);
    googleMainPage = new GoogleMainPage(wd);
    sessionHelper = new SessionHelper(wd);
    wikipediaPage = new WikipediaPage(wd);
//    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

  }



  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigatioHelper goTo() {
    return navigatioHelper;
  }
  public ContactHelper contact(){
    return contactHelper;
  }
  public DbHalper db(){
    return dbHalper;
  }

  public GoogleMainPage getGoogleMainPage() {
    return googleMainPage;
  }
  public WikipediaPage getWikipediaPage() {
    return wikipediaPage;
  }

}

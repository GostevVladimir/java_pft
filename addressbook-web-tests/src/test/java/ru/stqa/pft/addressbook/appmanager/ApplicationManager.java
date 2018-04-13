package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  FirefoxDriver wd;
  private NavigatioHelper navigatioHelper;
  private  GroupHelper groupHelper;
  private  ContactHelper contactHelper;
  private  SessionHelper sessionHelper;


  public void init() {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigatioHelper = new NavigatioHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }



  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigatioHelper getNavigatioHelper() {
    return navigatioHelper;
  }
  public ContactHelper getContactHelper(){
    return contactHelper;
  }
}

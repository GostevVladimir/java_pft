package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.ArrayList;

public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if(text != null){
      String existingText = wd.findElement(locator).getAttribute("value");
      if(! text.equals(existingText)){
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }
  protected void attach(By locator, File file) {
    if(file != null){
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
      }
  }

  public  boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex){
      return false;
    }
  }

  protected void switchDriverToSecondTabOfBrowser() {
    ArrayList<String> windows = new ArrayList<>(wd.getWindowHandles());
    windows.forEach(s -> System.out.println());
    String secondTabName = new ArrayList<>(wd.getWindowHandles()).get(1);
    wd.switchTo().window(secondTabName);
    System.setProperty("current.window.handle", secondTabName);
  }
}

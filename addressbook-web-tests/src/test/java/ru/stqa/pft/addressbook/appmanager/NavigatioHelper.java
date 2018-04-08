package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigatioHelper {
  private FirefoxDriver wd;

  public NavigatioHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
  }
}

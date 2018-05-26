package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NavigationHelper extends HelperBase{

  private WebDriver wd;
  private WebDriverWait wait;

  public NavigationHelper(ApplicationManager app) {
    super(app);
    wd = app.getDriver();
    wait = new WebDriverWait(wd, 5);
  }

  public void goToLoginPage(){
    wd.get ( app.getProperty("web.baseUrl") +  "/login.php" );
    wd.manage().window().maximize();
    type (By.name("username"),app.getProperty("web.adminLogin"));
    type (By.name("password"),app.getProperty("web.adminPassword"));
    wd.findElement(By.cssSelector("input[type=submit]")).click();
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home page"));
  }

  public void goToManageUsers(){
    click(By.cssSelector(String.format("a[href=\"/mantisbt-1.2.19/manage_user_page.php\"]")));
  }

  public void goToManagePage(){
    click(By.cssSelector(String.format("a[href=\"/mantisbt-1.2.19/manage_overview_page.php\"]")));
  }
}

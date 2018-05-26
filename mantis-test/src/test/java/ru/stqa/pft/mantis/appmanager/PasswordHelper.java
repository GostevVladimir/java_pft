package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordHelper extends HelperBase{

  public PasswordHelper(ApplicationManager app){
    super(app);
    wd = app.getDriver();
  }
  public void selectUsers(String user) {
    click(By.linkText(user));
  }


  public void reset() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public void submitReset(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//form[@action='account_update.php']//input[@type='submit']"));
  }
}

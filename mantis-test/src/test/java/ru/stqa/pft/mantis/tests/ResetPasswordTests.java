package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ResetPasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws InterruptedException, IOException {
    app.goTo().goToLoginPage();
    app.goTo().goToManagePage();
    app.goTo().goToManageUsers();

    String users = app.db().getUserName();
    app.resetUserPassword().selectUsers(users);
    app.resetUserPassword().reset();

    //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000);
    String email = users + "@localhost";
    String confirmationLink = findConfirmationLink(mailMessages, email);

    String newPassword = String.valueOf(System.currentTimeMillis());
    app.resetUserPassword().submitReset(confirmationLink, newPassword);
    assertTrue(app.newSession().login(users, newPassword));
  }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
  }



  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigatioHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("FirstNameTest1-1", "LastNameTest2-2", "Penza",
            "252525", "test1@mail.com", null), false);
    app.getContactHelper().submitContactModificatio();
    app.getContactHelper().returnToHomePage();
  }
}

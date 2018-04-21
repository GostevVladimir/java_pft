package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigatioHelper().gotoHomePage();
    int before = app.getContactHelper().getConactCount();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("FirstNameTest1", "LastNameTest2", "test address",
              "222333", "test@mail.com", "test1"), true);
    }
    app.getContactHelper().initContactModification(before - 1);
    app.getContactHelper().fillContactForm(new ContactData("FirstNameTest1-1", "LastNameTest2-2", "Penza",
            "252525", "test1@mail.com", null), false);
    app.getContactHelper().submitContactModificatio();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getConactCount();
    Assert.assertEquals(after, before);
  }
}

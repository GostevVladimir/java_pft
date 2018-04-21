package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    app.getNavigatioHelper().gotoHomePage();
    int before = app.getContactHelper().getConactCount();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("FirstNameTest1", "LastNameTest2", "test address",
              "222333", "test@mail.com", "test1"), true);
    }
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlert();
    int after = app.getContactHelper().getConactCount();
    Assert.assertEquals(after, before - 1);
  }
}

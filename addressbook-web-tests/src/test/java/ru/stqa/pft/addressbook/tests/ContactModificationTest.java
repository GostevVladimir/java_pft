package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigatioHelper().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("FirstNameTest1", "LastNameTest2", "test address",
              "222333", "test@mail.com", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "FirstNameTest1-1", "LastNameTest2-2", "Penza",
            "252525", "test1@mail.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModificatio();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

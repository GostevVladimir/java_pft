package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("FirstNameTest1").withLastName("LastNameTest2").withAddress("test address").
              withHomePhoneNumber("222333").withEmail("test@mail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstName("FirstNameTest1-1").withLastName("LastNameTest2-2").
            withAddress("Penza").withHomePhoneNumber("252252").withEmail("test1@mail.com");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());

    before.remove(modifyContact);
    before.add(contact);
    assertEquals(before, after);
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
  }


}

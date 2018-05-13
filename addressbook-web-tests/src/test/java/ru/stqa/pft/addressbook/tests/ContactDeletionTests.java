package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstName("FirstNameTest12").withLastName("LastNameTest23").withAddress("test address").
              withHomePhoneNumber("222333").withMobilePhone("88005553535").withWorkPhone("84959602424").withEmail("test@mail.com").
              withEmail2("test2@mail.com").withEmail3("test3@mail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(deletedContact)));
    verifyContactListInUI();
  }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    Groups groups = app.db().groups();
    app.goTo().homePage();
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("FirstNameTest12").withLastName("LastNameTest23").withAddress("test address").
              withHomePhoneNumber("222333").withMobilePhone("88005553535").withWorkPhone("84959602424").withEmail("test@mail.com").
              withEmail2("test2@mail.com").withEmail3("test3@mail.com").inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactEmail(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));

  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email){
    return email.replaceAll("\\s","")
            .replaceAll("[#$:;+=()%]","");
  }
}


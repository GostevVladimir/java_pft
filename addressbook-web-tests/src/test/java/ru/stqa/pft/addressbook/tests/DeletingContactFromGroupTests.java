package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeletingContactFromGroupTests extends TestBase{
  boolean ct;
  boolean gp;
  ContactData contact = new ContactData();
  GroupData group = new GroupData();

  @BeforeMethod
  public void ensurePrecondition(){


    app.goTo().homePage();
    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstName("FirstNameTest1").withLastName("LastNameTest2").withAddress("test address").
              withHomePhoneNumber("222333").withEmail("test@mail.com").withPhoto(new File("src/test/resources/stru.png")), true);
      contact = app.db().contacts().iterator().next();

    }
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
      group = app.db().groups().iterator().next();
    }

    if(!(ct && gp)){
      for (ContactData c : app.db().contacts()){
          if(c.getGroups().size() != 0){
            contact = c;
            group = c.getGroups().iterator().next();
            return;
          }
        }
      }
      contact = app.db().contacts().iterator().next();
      group = app.db().groups().iterator().next();
      //group = new GroupData().withName(String.format("test %s", Math.random()));
      app.goTo().homePage();
      app.contact().addContactInGroup(contact.getId(), group.getName());
    }

    @Test
  public void testDeletingContactFromGroup(){
      Contacts before = app.db().contacts();
      ContactData delContact = contact.deleteGroup(group);

      app.goTo().homePage();
      app.contact().deleteContactInGroup(contact.getId(), group.getName());

      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.withOut(contact).withAdded(delContact)));
    }
  }


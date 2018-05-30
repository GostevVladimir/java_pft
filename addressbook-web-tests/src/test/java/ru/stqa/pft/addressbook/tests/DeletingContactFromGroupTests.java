package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeletingContactFromGroupTests extends TestBase{
  private  boolean ct;
  private  boolean gp;
  private  ContactData contact = new ContactData();
  private  GroupData group = new GroupData();

  @BeforeMethod
  public void ensurePrecondition(){


    app.goTo().homePage();
    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstName("FirstNameTest1").withLastName("LastNameTest2").withAddress("test address").
              withHomePhoneNumber("222333").withEmail("test@mail.com").withPhoto(new File("src/test/resources/stru.png")), true);
      contact = app.db().contacts().iterator().next();
      ct = true;
    }
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
      gp = true;
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
      app.goTo().homePage();
      app.contact().addContactInGroup(contact.getId(), group.getName());
    }

    @Test
  public void testDeletingContactFromGroup(){
      Groups before = contact.getGroups();
      app.goTo().homePage();
      app.contact().deleteContactInGroup(contact.getId(), group.getName());

      Groups after = contact.getGroups();
      assertThat(after.size(), equalTo(before.size()));
    }
  }


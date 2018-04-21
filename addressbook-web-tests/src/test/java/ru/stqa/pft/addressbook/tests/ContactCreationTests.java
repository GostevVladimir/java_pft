package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigatioHelper().gotoHomePage();
        int before = app.getContactHelper().getConactCount();
        app.getContactHelper().createContact(new ContactData("FirstNameTest1", "LastNameTest2", "test address",
                "222333", "test@mail.com", "test1"), true);
        int after = app.getContactHelper().getConactCount();
        Assert.assertEquals(after, before + 1);
    }
}

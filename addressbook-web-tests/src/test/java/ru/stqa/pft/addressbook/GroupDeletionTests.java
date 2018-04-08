package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class GroupDeletionTests extends TestBase{


    @Test
    public void testGroupDelition() {
        app.getNavigatioHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}

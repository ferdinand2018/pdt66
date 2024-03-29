package ru.stqa.pft.addressbook.tests.fluent;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.fluent.GroupData;
import ru.stqa.pft.addressbook.model.fluent.Groups;
import ru.stqa.pft.addressbook.tests.fluent.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupodification() {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        Groups after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }
}
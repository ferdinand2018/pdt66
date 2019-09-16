package ru.stqa.pft.addressbook.tests.fluent;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.fluent.ContactData;
import ru.stqa.pft.addressbook.model.fluent.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("Иван").withLastname("Иванов")
                    .withMiddlename("Иванович").withMobile("+79772222333")
                    .withWork("ivanov@mail.com").withGroup(null), true);
        }
    }

    @Test
    public void testContactDelition(){
        Contacts before = app.contact().all();
        ContactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.withOut(deleteContact)));
    }
}
package ru.stqa.pft.addressbook.tests.fluent;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.fluent.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EmailTests extends TestBase{
    @Test
    public void testsEmail(){
        app.goTo().homePage();
        ContactData contact = app.contact().allMerge().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }
    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream()
                .filter((s) -> ! s.equals(""))
                .map(EmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email){
        return email.replaceAll("\\s","").replaceAll("[-()]]","");
    }
}

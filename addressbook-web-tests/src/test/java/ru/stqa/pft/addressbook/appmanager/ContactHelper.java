package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.xpath("//input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation){
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());

        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else{
            Assert.assertFalse(isElementPresent((By.name("new_group"))));
        }
    }

    public void initContactCreation(){
        click(By.linkText("add new"));
    }

    public void deleteSelectedContacts(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void deletedAlert(){
        wd.switchTo().alert().accept();
    }

    public void selectContact(int index){
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.name("selected[]"));
    }

    public int initContactModification(int i) {
        //click(By.xpath("//img[@alt='Edit']"));   НЕ АКТУАЛЬНЫЙ!
        click(By.xpath("(//img[@alt='Edit'])[" + i + "]"));
        return i;
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact, boolean creation){
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement element : elements){
            //int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            /* СЛИШКОМ ДЛИНЫЕ ЛОКАТОРЫ */
            /*-----------------------------------------------------------------*/
            //String firstname = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[3]")).getText();
            //String lastname = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[2]")).getText();
            /*-----------------------------------------------------------------*/
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            ContactData contact = new ContactData(lastname, firstname, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}

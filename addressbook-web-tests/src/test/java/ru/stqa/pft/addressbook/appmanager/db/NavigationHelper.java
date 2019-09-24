package ru.stqa.pft.addressbook.appmanager.db;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.appmanager.HelperBase;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))){
            return;
        }else{
            click(By.linkText("groups"));
        }
    }

    public void gotoContactCreationPage() {
        click(By.linkText("add new"));
    }

    public void gotoHomePage(){
        if(isElementPresent(By.name("maintable"))){
            return;
        }else{
            click(By.linkText("home"));
        }
    }
}

package ru.stqa.pft.addressbook.tests.fluent;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.fluent.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite //(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite //(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }
}

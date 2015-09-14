package com.barclaycard.collections;

import com.barclaycard.collections.utils.WebBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class HelloWorldTest {

    @Before
    public void setup() {
        WebBrowser.start();
    }

    @After
    public void tearDown() {
        WebBrowser.closeBrowser();
    }

    @Test
    public void helloWorld() {
        WebBrowser.navigateTo("/sayHello");

        assertThat(WebBrowser.findElement(cssSelector("[data-qa-greeting]")).getText(), is("Yo Joe"));

        WebBrowser.navigateTo("/sayHello?name=Jim");

        assertThat(WebBrowser.findElement(cssSelector("[data-qa-greeting]")).getText(), is("Yo Jim"));
    }

}
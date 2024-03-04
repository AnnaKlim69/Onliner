package onliner.pageObject.page;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

import static framework.Browser.getDriver;


public class HomePage extends BaseOnlinerPage {

    private static final String PAGE_LOCATOR = "//img[@class='onliner_logo']";
    private static final String NAV_CATALOGUE = "//span[@class='b-main-navigation__text' and text()='Каталог']";
    private static String item;

    public HomePage() {
        super(By.xpath(PAGE_LOCATOR), "'Onliner' Page");
    }

    public void clicCatalogue(String item) {
        Label lblCatalogueSelection = new Label(By.xpath(String.format(NAV_CATALOGUE, item)));
        lblCatalogueSelection.click();
    }
}
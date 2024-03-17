package onliner.pageObject.page;

import framework.elements.Label;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

public class CatalogPage extends BaseOnlinerPage {
    private static final String PAGE_LOCATOR = "//div[@class='catalog-navigation__title' and contains(text(),'Каталог')]";
    private static final String NAV_CATALOG_ITEM = "//span[@class='catalog-navigation-classifier__item-title-wrapper' " +
            "and contains(text(),'Электроника')]";
    private static final String NAV_CATALOGUE_SUBITEM = "//div[@class='catalog-navigation-list__aside-title' " +
            "and contains(text(),'Телевидение')]";
    private static final String NAV_SUBITEM_GROUP = "//span[text()='Телевизоры']";

    public CatalogPage(String pageTitle) {super(By.xpath(String.format(PAGE_LOCATOR, pageTitle)), pageTitle);
    }

    public void catalogueNavigation(String item) {
        Label lblCatalogueSelection = new Label(By.xpath(String.format(NAV_CATALOG_ITEM, item)));
        lblCatalogueSelection.click();
    }

    @Step("Clic on item group")
    public void clicOnItemGroup(String itmGroup) {
        Label labelItemGroup = new Label(By.xpath(String.format(NAV_CATALOGUE_SUBITEM, itmGroup)));
        labelItemGroup.click();
    }

    @Step("Clic on subItem")
    public void clicOnSubItemGroup(String subItmGroup) {
        Label labeSublItemGroup = new Label(By.xpath(String.format(NAV_SUBITEM_GROUP, subItmGroup)));
        labeSublItemGroup.click();
    }
}
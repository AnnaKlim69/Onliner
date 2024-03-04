package onliner.pageObject.page;

import framework.elements.Label;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

public class CatalogPage extends BaseOnlinerPage {
    private static final String PAGE_LOCATOR = "//div[@class='catalog-navigation__title' and text()='Каталог']";
    private static final String NAV_CATALOG_ITEM = "//li[@data-id='1']";
//    private static final String NAV_CATALOGUE_SUBITEM = "//span[@class='catalog-navigation-list__aside-item' " +
//            "and text()=' Телевидение и видео']";
//    private static final String NAV_SUBITEM_GROUP = "//div[@date-id='1']//span[@class='catalog-navigation-list__" +
//            "dropdown-title' and contains('Телевидение и видео')]";


    //    private static final String NAV_CATALOG_ITEM = "//span[@class='catalog-navigation-classifier__item-title-wrapper'" +
//            " and text()='%s']";
    public CatalogPage(String title) {
        super(By.xpath(String.format(PAGE_LOCATOR, title)), "'Catalog' Page");
    }

    public void catalogueNavigation(String item) {
        Label lblCatalogueSelection = new Label(By.xpath(String.format(NAV_CATALOG_ITEM, item)));
        lblCatalogueSelection.click();

    }

//    @Step("Clic on item group")
//    public void clicOnItemGroup(String itmGroup) {
//        Label labelItemGroup = new Label(By.xpath(String.format(NAV_CATALOGUE_SUBITEM, itmGroup)));
//        labelItemGroup.click();
//    }
//
//    @Step("Clic on subItem")
//    public void clicOnSubItemGroup(String subItmGroup) {
//        Label labeSublItemGroup = new Label(By.xpath(String.format(NAV_CATALOGUE_SUBITEM, subItmGroup)));
//        labeSublItemGroup.click();
//    }
}

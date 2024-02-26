package onliner.pageObject.page;

import framework.elements.Label;
import org.openqa.selenium.By;

public class CatalogPage extends BaseOnlinerPage {
    private static final String PAGE_LOCATOR = "//div[@class='catalog-navigation__title' and text()='%s']";
    private static final String NAV_CATALOGUE_SUBITEM = "//span[@class='catalog-navigation-list__aside-item' " +
            "and contains(text(), '%s')]";
    private static final String NAV_SUBITEM_GROUP = "//div[@date-id='1']//span[@class='catalog-navigation-list__" +
            "dropdown-title' and contains(., '%s')]";
    private static final String NAV_CATALOG_ITEM = "//span[@class='catalog-navigation-classifier__item-title-wrapper'" +
            " and text()='%s']";

    public CatalogPage(String title) {
        super(By.xpath(String.format(PAGE_LOCATOR, title)), "'Catalog' Page");
    }
    public  void  catalogueNavigation(String item){
        Label lblCatalogueSelection = new Label(By.xpath(String.format(NAV_CATALOG_ITEM, item)));
        lblCatalogueSelection.clickViaJS();

    }

    //    @Step("Clic on item group")
    public void clicOnItemGroup(String itmGroup) {
        Label labelItemGroup = new Label(By.xpath(String.format(NAV_CATALOGUE_SUBITEM, itmGroup)));
        labelItemGroup.clickViaJS();
    }

//    @Step("Clic on subItem")
    public void clicOnSubItemGroup(String subItmGroup) {
        Label labeSublItemGroup = new Label(By.xpath(String.format(NAV_CATALOGUE_SUBITEM, subItmGroup)));
        labeSublItemGroup.clickViaJS();
    }
}

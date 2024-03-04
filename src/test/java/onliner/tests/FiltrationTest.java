package onliner.tests;

import framework.BaseTest;
import jdk.jfr.Description;
import onliner.pageObject.page.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FiltrationTest extends BaseTest {
    @Test
    @Description("Test description")
    @Parameters({"manufacture", "resolution", "priceTo", "diagonalFrom", "diagonalTo"})
    public void checkFiltration(String manufacture, String resolution, double priceTo, double diagonalFrom,
                                double diagonalTo) {
        HomePage homePage = new HomePage();
        homePage.header.mainMenuNovigation("Каталог");

        CatalogPage catalogPage = new CatalogPage("Каталог");
        catalogPage.catalogueNavigation("Электроника");
//        catalogPage.clicOnItemGroup("Телевидение");
//        catalogPage.clicOnSubItemGroup("Телевизоры");
//
//        TVPage tvPage = new TVPage("Телевизоры");
//        tvPage.selectManufacture(manufacture);
//        tvPage.setPriceTo(priceTo);
//        tvPage.selectResolution(resolution);
//        tvPage.selectFromDiagonal(500);
//        tvPage.selectToDiagonal(400);
    }
}
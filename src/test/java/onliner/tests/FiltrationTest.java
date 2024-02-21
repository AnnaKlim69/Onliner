package onliner.tests;

import framework.BaseTest;
import onliner.pageObject.page.CatalogPage;
import onliner.pageObject.page.HomePage;
import org.testng.annotations.Test;

public class FiltrationTest extends BaseTest {
    @Test
    public void  checkFiltration(){
        HomePage homePage = new HomePage();
        homePage.header.mainMenuNovigation("Каталог");//????
        CatalogPage catalogPage = new CatalogPage();

    }
}

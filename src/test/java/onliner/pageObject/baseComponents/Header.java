package onliner.pageObject.baseComponents;

import org.openqa.selenium.By;

public class Header {
    private static final String NAV_MENU_ITEM = "//span[@class='b-main-navigation__text' and text()='%s']";
    public void mainMenuNovigation(String item){
        Label lblNavMenuSection = new Label(By.xpath(String.format(NAV_MENU_ITEM, item)));
    }
}
package onliner.pageObject.page;

import onliner.pageObject.baseComponents.Header;
import org.openqa.selenium.By;

public class BaseOnlinerPage extends BasePage {
    private  static  final  String PAGE_LOCATOR = "//img[@class='onliner_logo']";
    public Header header = new Header();
    public BaseOnlinerPage(By locator, String pageTitle){
        super(locator, pageTitle);
    }
}

package onliner.pageObject.page;

import framework.elements.CheckBox;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class TVPage extends BaseOnlinerPage {

    private static final String PAGE_LOCATOR = "//hi[@class='catalog-form__title catalog-form__title_big-alter' " +
            "and contains('text', '%s')]";
    private static final String MANUFACTURE = "//div[@class='catalog-form__checkbox-sing' and text()='%s']/../../input";
    private static final String CHECK_RESOLUTION = "//div[@class='catalog-form__checkbox-sing' and text()='%s']/../../input";
    private static final TextBox SET_PRICE_TO = new TextBox(By.xpath("//input[@type='text' and @placeholder='до']"));
    private static final Label SCROLL_TO_RESOLUTION_FILTER = new Label(By.xpath("//div[@class='catalog-form__label-title' " +
            "and text()='%s']"));//???
    private static final Label SCROLL_TO_DIAGONAL_FILTER = new Label(By.xpath("//div[@class='catalog-form__label-title' " +
            "and text()='%s']"));//???
    private static final CheckBox SELECT_FROM_DIAGONAL = new CheckBox(By.xpath("//div[2]/div/div[1]/div/select"));
    private static final CheckBox SELECT_TO_DIAGONAL = new CheckBox(By.xpath("//div[2]/div/select"));

    public TVPage(String pageTitle) {
        super(By.xpath(String.format(PAGE_LOCATOR, pageTitle)), pageTitle);
    }

    //    @Step("Check manufacturer checkbox")
    public void selectManufacture(String manufacture) {
        CheckBox cbManufacture = new CheckBox(By.xpath(String.format(MANUFACTURE, manufacture)));
        cbManufacture.clickViaJS();
        cbManufacture.isSelected();
    }

    //    @Step("Set price")
    public void setPriceTo(double priceTo) {
        SET_PRICE_TO.sendKeys(String.valueOf(priceTo));
    }

    //    @Step("Check resolution checkbox")
    public void selectResolution(String resolution) {
        CheckBox cbResolution = new CheckBox(By.xpath(String.format(CHECK_RESOLUTION, resolution)));
        cbResolution.clickViaJS();
        cbResolution.isSelected();
    }

    //    @Step("Set diagonal From")
    public void selectFromDiagonal(double diagonalFrom) {
        SELECT_FROM_DIAGONAL.selectByValue(String.valueOf(diagonalFrom));
    }

    public void selectToDiagonal(double diagonalTo) {
        SELECT_TO_DIAGONAL.selectByValue(String.valueOf(diagonalTo));
    }
}
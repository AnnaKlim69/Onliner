package onliner.pageObject.page;

import framework.elements.CheckBox;
import framework.elements.Label;
import framework.elements.TextBox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static framework.Browser.*;

public class TVPage extends BaseOnlinerPage {
    SoftAssert softAssert = new SoftAssert();
    private static final String PAGE_LOCATOR = "//h1[@class='catalog-form__title catalog-form__title_big-alter' " +
            "and contains(., '%s')]";
    private static final String MANUFACTURE = "//div[@class='catalog-form__checkbox-sign' and text()='%s']";
    private static final String CHECK_RESOLUTION = "//div[@class='catalog-form__checkbox-sign' and text()='%s']";
    public static final TextBox SET_PRICE_TO = new TextBox(By.xpath("//input[@type='text' and @placeholder='до']"));
    private static final TextBox CB_DIAGONAL_FROM = new TextBox(By.xpath("//div[@class='input-style " +
            "input-style_primary input-style_small input-style_arrow_bottom catalog-form__input " +
            "catalog-form__input_width_full input-style_placeholder']//select[@class='input-style__real']"));
    private static final TextBox TXB_DIAGONAL_TO = new TextBox(By.xpath("//div[2 and @class='input-style " +
            "input-style_primary input-style_small input-style_arrow_bottom catalog-form__input " +
            "catalog-form__input_width_full input-style_placeholder']//select[@class='input-style__real']"));
    private static final Label SCROLL_TO_DIAGONAL_FILTER = new Label(By.xpath(
            "//div[@class='catalog-form__label-title'and contains(., 'Диагональ')]"));
    private static final TextBox APPLIED_FILTER_MANUFACTURE = new TextBox(By.xpath(
            "//div[@class='catalog-form__tag-list']/div[1]"));
    private static final TextBox APPLIED_FILTER_RESOLUTION = new TextBox(By.xpath(
            "//div[@class='catalog-form__tag-list']/div[2]"));
    private static final List<WebElement> FILTERED_DIAGONAL_AND_RESOLUTION = getDriver().findElements(By.xpath(
            "//div[@class='catalog-form__parameter-part catalog-form__parameter-part_1']/div[1]"));
    private static final List<WebElement> PRICES = getDriver().findElements(By.xpath(
            "//div[@class='catalog-form__description catalog-form__description_huge-additional " +
                    "catalog-form__description_font-weight_bold catalog-form__description_condensed-other " +
                    "catalog-form__description_primary']"));
    private static final List<String> PRODUCTS_LIST = getDriver().findElements(By.xpath
                    ("//div[@class='catalog-form__filter-part catalog-form__filter-part_2']"))
            .stream().map(e -> e.getText()).collect(Collectors.toList());
    private static final TextBox SPECIAL_PRICE_OFFER = new TextBox(By.xpath("//div[@class='catalog-form__description " +
            "catalog-form__description_huge-additional " +
            "catalog-form__description_font-weight_bold catalog-form__description_condensed-other " +
            "catalog-form__description_error-alter']//span[2]"));
    private static final TextBox TXT_ITEM = new TextBox(By.xpath(
            "//div[contains(@class,'catalog-form__description_base-additional')]/a"));

    public WebElement productItem;
    public static String productName;
    public static double price;

    public TVPage(String pageTitle) {
        super(By.xpath(String.format(PAGE_LOCATOR, pageTitle)), "Телевизоры");
    }

    @Step("Check manufacturer checkbox")
    public void selectManufacture(String manufacture) {
        CheckBox checkFilter = new CheckBox(By.xpath(String.format(MANUFACTURE, manufacture)));
        checkFilter.scrollIntoView();
        checkFilter.clickViaJS();
        checkFilter.isSelected();
    }

    @Step("Set price TO")
    public void setPriceTo(double priceTo) {
        SET_PRICE_TO.sendKeys(String.valueOf(priceTo));
    }

    @Step("Check resolution checkbox")
    public void selectResolution(String resolution) {
        CheckBox chbResolution = new CheckBox(By.xpath(String.format(CHECK_RESOLUTION, resolution)));
        chbResolution.scrollIntoView();
        chbResolution.clickViaJS();
        chbResolution.isSelected();
    }

    @Step("Set diagonal From")
    public void selectFromDiagonal(String diagonalFrom) {
        SCROLL_TO_DIAGONAL_FILTER.scrollIntoView();
        CB_DIAGONAL_FROM.sendKeys(diagonalFrom);
    }

    @Step("Set diagonal to")
    public void selectToDiagonal(String diagonalTo) {
        TXB_DIAGONAL_TO.sendKeys(diagonalTo);
    }

    @Step("Check if manufacturer filter applied")
    public void checkManufacturerFilterApplied(String manufacture) {
        softAssert.assertEquals(APPLIED_FILTER_MANUFACTURE.getText(), manufacture,
                "Expected result: " + manufacture + ". Actual result: " + APPLIED_FILTER_MANUFACTURE.getText());
    }

    @Step("Check if resolution filter applied")
    private void checkResolutionFilterApplied(String resolution) {
        softAssert.assertEquals(APPLIED_FILTER_RESOLUTION.getText(), resolution,
                "Expected result: " + resolution + ". Actual result: " + APPLIED_FILTER_RESOLUTION.getText());
    }

    @Step("Assertion: manufacturer is equal to parameter")
    private void checkIfManufacturerMatch(String manufacturer) {
        List<WebElement> listOFItems = TXT_ITEM.getElements();
        for (WebElement w : listOFItems) {
            softAssert.assertTrue(w.getText().contains(manufacturer),
                    "Expected result: " + manufacturer + ". Actual result: " + APPLIED_FILTER_MANUFACTURE.getText());
        }
    }

    @Step("Assertion: Resolution is equal to parameter")
    private void checkResolution(String resolution) {
        for (WebElement w : FILTERED_DIAGONAL_AND_RESOLUTION) {
            System.out.println(w.getText());
            softAssert.assertTrue(w.getText().contains(resolution),
                    "Expected result: " + resolution + ". Actual result: " + APPLIED_FILTER_RESOLUTION.getText());
        }
    }

    @Step("Assertion: Diagonal is equal to parameter")
    private void checkInches(String diagonalFrom, String diagonalTo) {
        for (WebElement w : FILTERED_DIAGONAL_AND_RESOLUTION) {
            double diagonal = Double.parseDouble(w.getText().substring(0, 2));
            System.out.println(diagonal);
            double dDTo = Double.parseDouble(diagonalTo);
            double dDFrom = Double.parseDouble(diagonalFrom);
            softAssert.assertTrue(diagonal >= dDFrom & diagonal <= dDTo);
        }
    }

    @Step("Assertion: check special offer price")
    private void checkSpecialOfferPrice(double priceTo) {
        double specialOfferPrice = Double.parseDouble(SPECIAL_PRICE_OFFER.getText().replaceAll("[\\s.а-я]", "")
                .replaceAll(",", "."));
        softAssert.assertTrue(specialOfferPrice <= priceTo);
    }

    @Step("Assertion: check prices")
    private void checkPrices(double priceTo) {
        for (WebElement w : PRICES) {
            double prices = Double.parseDouble(w.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
            System.out.println(prices);
            softAssert.assertTrue(prices <= priceTo, "Expected result: " + priceTo + ". Actual result: " + prices);
        }
    }

    public void validationOfAllFilters(String manufacture, String resolution, double price, String diagonalFrom,
                                       String diagonalTo) {
        checkManufacturerFilterApplied(manufacture);
        checkResolutionFilterApplied(resolution);
        checkIfManufacturerMatch(manufacture);
        checkResolution(resolution);
        checkPrices(price);
        checkInches(diagonalFrom, diagonalTo);
        softAssert.assertAll();
    }
}
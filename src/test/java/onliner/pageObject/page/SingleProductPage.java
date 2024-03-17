package onliner.pageObject.page;

import framework.elements.Button;
import org.openqa.selenium.By;

import java.util.Objects;

import static framework.Browser.getDriver;

public class SingleProductPage extends BaseOnlinerPage {
    private static final String PAGE_LOCATOR = "//h1[@class='catalog-masthead__title js-nav-header']";
    private static final String PRODUCT_PRICE = "//div[@class='offers-description__price " +
            "offers-description__price_secondary']";
    private static final String PRODUCT_ITEM = "//p[text()='1920x1080 (Full HD), частота матрицы 60 Гц, " +
            "Smart TV (Samsung Tizen), AirPlay, Wi-Fi']";
    private static final Button IN_THE_BASKET_BUTTON = new Button(By.xpath(
            "//a[@class='button-style button-style_base-alter offers-list__button offers-list__button_cart " +
                    "button-style_another']"));

    public static String productItem;
    public static String productName;
    public static double price;

    public SingleProductPage() {
        super(By.xpath(PAGE_LOCATOR), "Single Product Page");
    }

    public String getProductItem() {
        return productItem = PRODUCT_ITEM;
    }

    public double getSingleProductPrice() {
        return price = Double.parseDouble(getDriver().findElement(By.xpath(String.format(PRODUCT_PRICE, productName)))
                .getText().replace("$", ""));
    }

    public void itemValidation() {
        if (productItem.equals(TVPage.productName)) {
            System.out.println("Items are equal");
        } else {
            System.out.println("Items are not equal");
        }
    }

    public void itemPriceValidation() {
        if (!Objects.equals(price, TVPage.price)) {
            System.out.println("Items are not equal");
        } else {
            System.out.println("Items are equal");
        }
    }

    public void clickInTheBasketButton() {
        IN_THE_BASKET_BUTTON.click();
    }
}
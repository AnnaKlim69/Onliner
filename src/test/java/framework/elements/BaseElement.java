package framework.elements;

import framework.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static framework.Browser.getDriver;
import static framework.Browser.waitForPageLoad;
import static framework.PropertyReader.getIntProperty;

public abstract class BaseElement {
    protected WebElement element;
    protected List<WebElement> elements;
    protected By by;
    private String name;
    private WebDriverWait wait;
    private int invaLidImageCount = 0;

    List<String> broKenImgList = new ArrayList<>();

    public static String getLoc(final String key) {//получить лок???
        return getProperty(key);
    }

    public WebElement getElement() {//получить элемент
        isElementPresent();
        return element;
    }

    public BaseElement(By by) {//Базовый элемент
        this.by = by;
    }

    public BaseElement(By by, String name) {//Базовый элемент
        this.by = by;
        this.name = name;
    }

    protected abstract String getElementType();//получить тип элемента

    public boolean waitForIsElementPressent() {//ждать, пока элемент появится

        return isElementPresent();
    }

    public boolean waitForIsElementsArePressent() {//???? ждать, пока элементы загрузятся
        waitForIsElementPressent();// ожидаем указанное количество времени
        return false;
    }

    public List<WebElement> getElements() {//получить все элементы
        isElementPresent();
        return elements;
    }

    protected List<? extends BaseElement> returnElements;//???

    public boolean isElementPresent() {//присутствует ли элемент
        try {
            getDriver().manage().timeouts().implicitlyWait(new PropertyReader("config.properties").
                    getIntProperty("timeout"), TimeUnit.SECONDS);
            element = getDriver().findElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println(getElementType() + ":" + by + "is not present. Exception - " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static String getProperty(String key) {//получить свойство
        return key;
    }

    public boolean isElementClickable() {//кликабельен ли элемент
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(getIntProperty("element.timeout")))
                    .until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element isn't clickable" + getElementType() + by);
            return false;
        }
    }

    public String sendKeys(String senKeys) {//отправить ключи
        isElementPresent();
        getElement().sendKeys(senKeys);
        return senKeys;
    }

    public boolean isSelected() {//выбор
        isElementPresent();
        System.out.println((getProperty("log.select") + element.getText()));
        return element.isSelected();
    }

    public boolean isDisplayed() {//отображение
        isElementPresent();
        if (element.isDisplayed()) {
            System.out.println(getElementType() + ":" + by + "is displeted");
            return true;
        } else {
            System.out.println(getElementType() + ":" + by + "is not displeted");
            return false;
        }
    }

    public void click() {//клик
        isElementPresent();
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid orange'", element);
            isElementClickable();
            getElement().click();
        }
        System.out.println(getProperty("log.click") + " : " + getElementType() + " : " + by);
    }

    public void clickAndWait() {//??? клик по элементу и ожидание
        isElementPresent();
        element.click();// клик по элементу
        waitForPageLoad();// ожидаем указанное количество времени
        System.out.println(getProperty("log.click") + " : " + getElementType() + " : " + by);
    }

    public void clickViaJS() {//!!! JavascriptExecutor для выполнения клика на элементе
        isElementPresent();
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();// выполняет клик через JavascriptExecutor
        executor.executeScript("arguments[0].click();", element);
    }

    public void scrollIntoView() {//!!! прокручивает страницу до элемента с помощью scrollIntoView
        isElementPresent();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void moveAndClickByActions() {//!!! перемещение курсора мыши к элементу на веб-странице
        // и выполнения клика на этом элементе
        isElementPresent();
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).click().build().perform();
    }

    public void moveByActions() {//!!! перемещение курсора мыши к элементу на веб-странице
        // и потом на определенное расстояние от данного местоположения
        isElementPresent();
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).moveByOffset(getIntProperty("x"), getIntProperty("y")).perform();
    }

    public void moveToElement() {//!!! перемещение курсора мыши к элементу на веб-странице и нажатия на элемент
        isElementPresent();
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
        element.click();
    }

    public String getAttribute(String attribute) {//!!!получить атрибут элемента веб-страницы
        isElementPresent();
        return element.getAttribute(attribute);
    }

    public String getText() {//получить текст
        isElementPresent();
        System.out.println(element.getText());
        return element.getText();
    }
}
package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static Browser instance;
    private static WebDriver driver;

    public static Browser getInstance() {
        if (instance == null) {
            driver = DriverFactory.getDriver();
            driver.manage().timeouts().implicitlyWait(PropertyReader.getInProperty("timeout"),
                    TimeUnit.SECONDS);//ожидание 10 сек
        } else {
            System.out.println("Driver does not instance!");//если браузер уже запущен
        }
        return instance = new Browser();
    }

    public static void windowMaxsimize() {//открыть окно максимально
        driver.manage().window().maximize();
    }

    public static void navigateTo(String url) {
        driver.get(url);//делаем так, что бы небыло точного значения
    }

    public static void quit() {//закрытие браузера и драйвера
        driver.quit();
        instance = null;// для закрытия браузера чтобы не забыть закрыть браузер
        System.out.println("Browser has been closed.");
    }

    public static void waitForPageLoad() {//ожидание загрузки страницы
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyReader
                .getInProperty("page.load.timeout"))); // ожидание какого то действия
        wait.until(driver -> executor.executeScript("return document.readyState").equals("complete"));//каманда для системы
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
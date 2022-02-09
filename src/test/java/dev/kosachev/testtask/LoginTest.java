package dev.kosachev.testtask;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class LoginTest {

    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static MailPage mailPage;

    public static WebDriver driver;

    @BeforeAll
    public static void setup() {


        //определение пути до драйвера и его настройка
//        System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("firefoxdriver"));
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));

        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        mailPage = new MailPage(driver);
    }

    @AfterAll
    public static void tearDown() {
        mailPage.entryMenu();
        mailPage.userLogout();
        driver.quit();
    }

    @Test
    public void loginTest() {
        //значение login/password берутся из файла настроек по аналогии с chromedriver
//и loginpage
//вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();

        //получаем отображаемый логин
        String user = profilePage.getUserName();
        //и сравниваем его с логином из файла настроек
        Assertions.assertEquals(ConfProperties.getProperty("login"), user);
        profilePage.entryMenu();
        profilePage.entryMail();
        mailPage.search("Simbirsoft Тестовое задание");
        String searchResult = mailPage.getResult();
        mailPage.sendMail("kosachdmi@yandex.ru","Simbirsoft Тестовое задание. Косачёв",searchResult);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}

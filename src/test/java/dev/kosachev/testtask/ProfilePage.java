package dev.kosachev.testtask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * определение локатора меню пользователя
     */
    @FindBy(xpath = "//*[contains(@class, 'personal-info-login__text personal-info-login__text_decorated')]")
    private WebElement userLogin;

    @FindBy(xpath = "//*[contains(@class, 'legouser legouser_fetch-accounts_yes legouser_hidden_yes i-bem')]")
    private WebElement userIcon;

    @FindBy(xpath = "//*[contains(@class, 'menu__item menu__item_type_link legouser__menu-item legouser__menu-item_action_mail')]")
    private WebElement mailBtn;

    /**
     * метод для получения имени пользователя из меню пользователя
     */
    public String getUserName() {
        String userName = userLogin.getText();
        return userName;
    }

    /**
     * метод для нажатия кнопки меню пользователя
     */
    public void entryMenu() {
        userIcon.click();
    }

    public void entryMail() {
        mailBtn.click();
    }
}
package dev.kosachev.testtask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@class, 'textinput__control')]")
    private WebElement search;

    @FindBy(xpath = "//*[contains(@class, 'control button2 button2_view_default button2_tone_mail-suggest-themed button2_size_n button2_theme_normal button2_pin_clear-round button2_type_submit search-input__form-button')]")
    private WebElement searchBtn;

    @FindBy(xpath = "//*[contains(@class, 'mail-MessagesSearchInfo-Title_misc nb-with-xs-left-gap')]")
    private WebElement searchResult;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[7]/div/div[3]/div[2]/div[1]/div/div/div/a")
    private WebElement writeMailBtn;

    @FindBy(xpath = "//*[contains(@class, 'composeYabbles')]")
    private WebElement address;

    @FindBy(xpath = "//*[@id=\"js-apps-container\"]/div[2]/div[10]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[1]/div[1]/div[3]/div/div/input")
    private WebElement emailTopic;

    @FindBy(xpath = "//*[contains(text(), 'Себе')]/..")
    private WebElement contact;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[10]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[3]/div[2]/div[2]/div[1]/div/div/div")
    private WebElement emailText;

    @FindBy(xpath = "//*[contains(@class, 'Button2 Button2_pin_circle-circle Button2_view_default Button2_size_l')]")
    private WebElement sendBtn;

    @FindBy(xpath = "//*[contains(@class, 'PSHeader-User')]")
    private WebElement userIcon;

    @FindBy(xpath = "//*[contains(@class, 'menu__item menu__item_type_link count-me legouser__menu-item legouser__menu-item_action_exit legouser__menu-item legouser__menu-item_action_exit')]")
    private WebElement logoutBtn;

    public void search(String searchText) {
        search.sendKeys(searchText);
        searchBtn.click();
    }

    public String getResult() {
        return searchResult.getText();
    }

    public void sendMail(String sendAddress, String mailTopic, String mailText) {
        writeMailBtn.click();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        address.sendKeys(sendAddress);
        contact.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailTopic.sendKeys(mailTopic);
        emailText.sendKeys(mailText);
        sendBtn.click();


    }

    public void entryMenu() {
        userIcon.click();
    }

    public void userLogout() {
        logoutBtn.click();
    }
}

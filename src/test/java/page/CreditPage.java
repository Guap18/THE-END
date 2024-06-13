package page;

import com.codeborne.selenide.Condition;
import locator.CreditPageLocators;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

//без изменений
public class CreditPage {


    public void clickBuyCreditButton() {
        $x(CreditPageLocators.BUY_CREDIT_BUTTON).click();
    }

    public void clickBuyButton() {
        $(CreditPageLocators.BUTTON_BUTTON).find(byText("Купить")).click();
    }

    public void enterCardNumber(String cardNumber) {
        $x(CreditPageLocators.INPUT_CARD_NUMBER).setValue(cardNumber);
    }

    public void enterExpiryMonth(String month) {
        $x(CreditPageLocators.INPUT_MONTH).setValue(month);
    }

    public void enterExpiryYear(String year) {
        $x(CreditPageLocators.INPUT_EXPIRY_YEAR).setValue(year);
    }

    public void enterCardHolderName(String cardHolderName) {
        $x(CreditPageLocators.INPUT_HOLDER_NAME).setValue(cardHolderName);
    }

    public void enterCVV(String cvv) {
        $x(CreditPageLocators.INPUT_CVV).setValue(cvv);
    }

    public void clickSubmitButton() {
        $x(CreditPageLocators.BUTTON_SUBMIT).click();
    }

    public void waitForNotification(String expectedText) {
        $(CreditPageLocators.NOTIFICATION)
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    public void cardNumberFormat(String expectedText) {
        $x(CreditPageLocators.CARD_NUMBER)
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void expiryMonthFormat(String expectedText) {
        $x(CreditPageLocators.EPIRY_MONTH_FORMAT)
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void expiryYearFormat(String expectedText) {
        $x(CreditPageLocators.EXPIRY_YEAR_FORMAT)
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void cardNameSure(String expectedText) {
        $x(CreditPageLocators.CARD_NAME_SURE)
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    public void cvvFormat(String expectedText) {
        $x(CreditPageLocators.CVV_FORMAT)
                .shouldHave(Condition.exactText("Неверный формат"));
    }
    public void cardNameFormat(String expectedText) {
        $x(CreditPageLocators.CARD_FORMAT)
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void yearExpired(String expectedText) {
        $x(CreditPageLocators.YEAR_EXPIRED)
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Истёк срок действия карты"));
    }

    public void yearNotFormat(String expectedText) {
        $x(CreditPageLocators.YEAR_NOT_FORMAT)
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    public void monthNotFormat(String expectedText) {
        $x(CreditPageLocators.MONTH_NOT_FORMAT)
                .shouldHave(Condition.exactText(expectedText));
    }

}

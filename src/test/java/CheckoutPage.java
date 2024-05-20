import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byText;

public class CheckoutPage {

    public void clickBuyButton() {
        $("button.button").find(byText("Купить")).click();
    }

    public void enterCardNumber(String cardNumber) {
        $x("//div[1]/span/span/span[2]/input").setValue(cardNumber);
    }

    public void enterExpiryMonth(String month) {
        $x("//div[2]/span/span[1]//input").setValue(month);
    }

    public void enterExpiryYear(String year) {
        $x("//div[2]/span/span[2]//input").setValue(year);
    }

    public void enterCardHolderName(String cardHolderName) {
        $x("//div[3]/span/span[1]//input").setValue(cardHolderName);
    }

    public void enterCVV(String cvv) {
        $x("//div[3]/span/span[2]//input").setValue(cvv);
    }

    public void clickSubmitButton() {
        $x("//div[4]/button").click();
    }

    public void waitForNotification(String expectedText) {
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    public void cardNumberFormat(String expectedText) {
        $x("//div[1]/span//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void expiryMonthFormat(String expectedText) {
        $x("//div[2]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void expiryYearFormat(String expectedText) {
        $x("//div[2]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void cardNameSure(String expectedText) {
        $x("//div[3]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    public void cvvFormat(String expectedText) {
        $x("//div[3]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void cardNameFormat(String expectedText) {
        $x("//div[3]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    public void yearExpired(String expectedText) {
        $x("//div[2]/span/span[2]//span[3]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Истёк срок действия карты"));
    }

    public void yearNotFormat(String expectedText) {
        $x("//div[2]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    public void monthNotFormat(String expectedText) {
        $x("//div[2]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }
}

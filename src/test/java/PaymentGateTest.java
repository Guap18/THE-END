import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


import static com.codeborne.selenide.Selenide.open;

public class PaymentGateTest {
    @BeforeAll
    static void setAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("All valid")
    public void allValid() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.waitForNotification("Операция одобрена Банком.");

    }

    @Test
    @DisplayName("Invalid")
    public void inValid() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("");
        checkoutPage.enterExpiryMonth("");
        checkoutPage.enterExpiryYear("");
        checkoutPage.enterCardHolderName("");
        checkoutPage.enterCVV("");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNumberFormat("Неверный формат");
        checkoutPage.expiryMonthFormat("Неверный формат");
        checkoutPage.expiryYearFormat("Неверный формат");
        checkoutPage.cardNameSure("Поле обязательно для заполнения");
        checkoutPage.cvvFormat("Неверный формат");

    }

    @Test
    @DisplayName("Card15Digit")
    public void card15DigitInvalid() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 444");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("Card17Digit")
    public void card17DigitInvalid() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 44415");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("CardLetters")
    public void cardLettersInvalid() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("qwerty");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("Card Symbol")
    public void cardSymbolInvalid() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("///???");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("OneDigitMonth")
    public void oneDigitMonth() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("7");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.expiryMonthFormat("Неверный формат");
    }

    @Test
    @DisplayName("ThreeDigitMonth")
    public void ThreeDigitMonth() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("155");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.monthNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("ThirteenMonth")
    public void thirteenMonth() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("13");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.monthNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Letters Month")
    public void lettersMonth() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("qwerty");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNumberFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbols Month")
    public void symbolsMonth() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("///???");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNumberFormat("Неверный формат");
    }

    @Test
    @DisplayName("OneDigitYear")
    public void oneDigitYear() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("2");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("ThreeDigitYear")
    public void ThreeDigitYear() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("345");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.yearNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("PreviousYear")
    public void previousYear() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("23");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.yearExpired("Истёк срок действия карты");

    }

    @Test
    @DisplayName("Letters Year")
    public void lettersYear() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("qwerty");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbol Year")
    public void symbolYear() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("///???");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("DigitHolder")
    public void digitHolder() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("12345");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNameFormat("Неверный формат");

    }

    @Test
    @DisplayName("OneLetterHolder")
    public void oneLetterHolder() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("V");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("RuHolder")
    public void ruHolder() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Владимирова");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNameFormat("Неверный формат");

    }

    @Test
    @DisplayName("SymbolsHolder")
    public void symbolsHolder() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("///???");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("Two CVC")
    public void twoCVC() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("11");
        checkoutPage.clickSubmitButton();
        checkoutPage.cvvFormat("Неверный формат");
    }

    @Test
    @DisplayName("Letters CVC")
    public void lettersCVC() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("qwe");
        checkoutPage.clickSubmitButton();
        checkoutPage.cvvFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbols CVC")
    public void symbolsCVC() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("///");
        checkoutPage.clickSubmitButton();
        checkoutPage.cvvFormat("Неверный формат");
    }
}



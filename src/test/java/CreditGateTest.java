import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


import static com.codeborne.selenide.Selenide.open;

public class CreditGateTest {
    private final ConnectionMysqlConfig connectionMysqlConfig = new ConnectionMysqlConfig();

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
        connectionMysqlConfig
                .connect()
                .executePrepareStatement("truncate table payment_entity")
                .executePrepareStatement("truncate table order_entity")
                .executePrepareStatement("truncate table credit_request_entity");
    }

    @AfterEach
    void tearDown() {
        connectionMysqlConfig.connectionClose();
    }

    @Test
    @DisplayName("All Credit valid")
    void allCreditValid() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.waitForNotification("Операция одобрена Банком.");

        String actual = connectionMysqlConfig.executeQueryAndGetValue("credit");

        Assertions.assertEquals("APPROVED", actual);
    }

    @Test
    @DisplayName("InvalidCredit")
    void inValid() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("");
        poCredit.enterExpiryMonth("");
        poCredit.enterExpiryYear("");
        poCredit.enterCardHolderName("");
        poCredit.enterCVV("");
        poCredit.clickSubmitButton();
        poCredit.cardNumberFormat("Неверный формат");
        poCredit.expiryMonthFormat("Неверный формат");
        poCredit.expiryYearFormat("Неверный формат");
        poCredit.cardNameSure("Поле обязательно для заполнения");
        poCredit.cvvFormat("Неверный формат");


    }

    @Test
    @DisplayName("Card15DigitCredit")
    void card15DigitInvalid() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 444");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("Card17DigitCredit")
    void card17DigitInvalid() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 44415");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("CardLetters")
    void cardLettersInvalid() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("qwerty");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("Card Symbol Credit")
    void cardSymbolInvalid() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("///???");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("OneDigitMonthCreditCredit")
    void oneDigitMonth() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("7");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.expiryMonthFormat("Неверный формат");
    }

    @Test
    @DisplayName("ThreeDigitMonthCredit")
    void ThreeDigitMonth() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("155");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.monthNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("ThirteenMonthCredit")
    void thirteenMonth() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("13");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.monthNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Letters Month Credit")
    void lettersMonth() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("qwerty");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.expiryMonthFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbols Month Credit")
    void symbolsMonth() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("///???");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.expiryMonthFormat("Неверный формат");
    }

    @Test
    @DisplayName("OneDigitYearCredit")
    void oneDigitYear() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("2");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("ThreeDigitYearCredit")
    void ThreeDigitYear() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("345");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.yearNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("PreviousYearCredit")
    void previousYear() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("23");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.yearExpired("Истёк срок действия карты");

    }

    @Test
    @DisplayName("Letters Year Credit")
    void lettersYear() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("qwerty");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbol Year Credit")
    void symbolYear() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("///???");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("DigitHolderCredit")
    void digitHolder() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("12345");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("OneLetterHolderCredit")
    void oneLetterHolder() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("V");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("RuHolderCredit")
    void ruHolder() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Владимирова");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("SymbolsHolderCredit")
    void symbolsHolder() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("///???");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("Two CVC Credit")
    void twoCVC() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("11");
        poCredit.clickSubmitButton();
        poCredit.cvvFormat("Неверный формат");
    }

    @Test
    @DisplayName("Letters CVC Credit")
    void lettersCVC() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("qwe");
        poCredit.clickSubmitButton();
        poCredit.cvvFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbols CVC Credit")
    void symbolsCVC() {
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();

        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("///");
        poCredit.clickSubmitButton();
        poCredit.cvvFormat("Неверный формат");
    }

}

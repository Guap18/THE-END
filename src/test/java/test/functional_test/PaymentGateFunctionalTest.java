package test.functional_test;

import config.DatabaseConnector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.CreditPage;
import test.BaseTest;
import testcontainers.MysqlTestContainersEnvironment;
import testcontainers.PostgresqlTestContainersEnvironment;

public class PaymentGateFunctionalTest extends BaseTest {

    @BeforeAll
    static void setUp() {
        PostgresqlTestContainersEnvironment.environmentWithPostgresql.stop();
        MysqlTestContainersEnvironment.startContainers();
        DatabaseConnector.getConnection("mysql.url");
    }

    //Так же проверяет интеграцию с mysql
    @Test
    @DisplayName("All valid")
    public void allValid() {
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.waitForNotification("Операция одобрена Банком.");
    }

    @Test
    @DisplayName("Invalid")
    public void inValid() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("");
        creditPage.enterExpiryMonth("");
        creditPage.enterExpiryYear("");
        creditPage.enterCardHolderName("");
        creditPage.enterCVV("");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");
        creditPage.expiryMonthFormat("Неверный формат");
        creditPage.expiryYearFormat("Неверный формат");
        creditPage.cardNameSure("Поле обязательно для заполнения");
        creditPage.cvvFormat("Неверный формат");

    }

    @Test
    @DisplayName("Card15Digit")
    public void card15DigitInvalid() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 444");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("Card17Digit")
    public void card17DigitInvalid() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 44415");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("CardLetters")
    public void cardLettersInvalid() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("qwerty");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("Card Symbol")
    public void cardSymbolInvalid() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("///???");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("OneDigitMonth")
    public void oneDigitMonth() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("7");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryMonthFormat("Неверный формат");
    }

    @Test
    @DisplayName("ThreeDigitMonth")
    public void ThreeDigitMonth() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("155");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.monthNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("ThirteenMonth")
    public void thirteenMonth() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("13");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.monthNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Letters Month")
    public void lettersMonth() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("qwerty");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbols Month")
    public void symbolsMonth() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("///???");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");
    }

    @Test
    @DisplayName("OneDigitYear")
    public void oneDigitYear() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("2");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("ThreeDigitYear")
    public void ThreeDigitYear() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("345");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.yearNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("PreviousYear")
    public void previousYear() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("23");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.yearExpired("Истёк срок действия карты");

    }

    @Test
    @DisplayName("Letters Year")
    public void lettersYear() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("qwerty");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbol Year")
    public void symbolYear() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("///???");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("DigitHolder")
    public void digitHolder() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("12345");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNameFormat("Неверный формат");

    }

    @Test
    @DisplayName("OneLetterHolder")
    public void oneLetterHolder() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("V");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("RuHolder")
    public void ruHolder() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Владимирова");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNameFormat("Неверный формат");

    }

    @Test
    @DisplayName("SymbolsHolder")
    public void symbolsHolder() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("///???");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("Two CVC")
    public void twoCVC() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("11");
        creditPage.clickSubmitButton();
        creditPage.cvvFormat("Неверный формат");
    }

    @Test
    @DisplayName("Letters CVC")
    public void lettersCVC() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("qwe");
        creditPage.clickSubmitButton();
        creditPage.cvvFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbols CVC")
    public void symbolsCVC() {
        CreditPage creditPage = new CreditPage();
        creditPage.clickBuyButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("///");
        creditPage.clickSubmitButton();
        creditPage.cvvFormat("Неверный формат");
    }
}



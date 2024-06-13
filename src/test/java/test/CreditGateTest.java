package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreditGateTest extends BaseTest {

    @Test
    @DisplayName("All Credit valid")
    void allCreditValid() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.waitForNotification("Операция одобрена Банком.");

        String actual = connectionMysqlConfig.executeQueryAndGetValue("credit");

        Assertions.assertEquals("APPROVED", actual);
    }

    @Test
    @DisplayName("InvalidCredit")
    void inValid() {
        creditPage.clickBuyCreditButton();
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
    @DisplayName("Card15DigitCredit")
    void card15DigitInvalid() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 444");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");
    }

    @Test
    @DisplayName("Card17DigitCredit")
    void card17DigitInvalid() {
        creditPage.clickBuyCreditButton();
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
    void cardLettersInvalid() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("qwerty");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");
    }

    @Test
    @DisplayName("Card Symbol Credit")
    void cardSymbolInvalid() {

        creditPage.clickBuyCreditButton();

        creditPage.enterCardNumber("///???");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNumberFormat("Неверный формат");

    }

    @Test
    @DisplayName("OneDigitMonthCreditCredit")
    void oneDigitMonth() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("7");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryMonthFormat("Неверный формат");
    }

    @Test
    @DisplayName("ThreeDigitMonthCredit")
    void ThreeDigitMonth() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("155");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.monthNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("ThirteenMonthCredit")
    void thirteenMonth() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("13");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.monthNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Letters Month Credit")
    void lettersMonth() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("qwerty");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryMonthFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbols Month Credit")
    void symbolsMonth() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("///???");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryMonthFormat("Неверный формат");
    }

    @Test
    @DisplayName("OneDigitYearCredit")
    void oneDigitYear() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("2");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("ThreeDigitYearCredit")
    void ThreeDigitYear() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("345");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.yearNotFormat("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("PreviousYearCredit")
    void previousYear() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("23");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.yearExpired("Истёк срок действия карты");

    }

    @Test
    @DisplayName("Letters Year Credit")
    void lettersYear() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("qwerty");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbol Year Credit")
    void symbolYear() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("///???");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.expiryYearFormat("Неверный формат");
    }

    @Test
    @DisplayName("DigitHolderCredit")
    void digitHolder() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("12345");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("OneLetterHolderCredit")
    void oneLetterHolder() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("V");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("RuHolderCredit")
    void ruHolder() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Владимирова");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("SymbolsHolderCredit")
    void symbolsHolder() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("///???");
        creditPage.enterCVV("111");
        creditPage.clickSubmitButton();
        creditPage.cardNameFormat("Неверный формат");
    }

    @Test
    @DisplayName("Two CVC Credit")
    void twoCVC() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("11");
        creditPage.clickSubmitButton();
        creditPage.cvvFormat("Неверный формат");
    }

    @Test
    @DisplayName("Letters CVC Credit")
    void lettersCVC() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("qwe");
        creditPage.clickSubmitButton();
        creditPage.cvvFormat("Неверный формат");
    }

    @Test
    @DisplayName("Symbols CVC Credit")
    void symbolsCVC() {
        creditPage.clickBuyCreditButton();
        creditPage.enterCardNumber("4444 4444 4444 4441");
        creditPage.enterExpiryMonth("07");
        creditPage.enterExpiryYear("27");
        creditPage.enterCardHolderName("Vladimirova");
        creditPage.enterCVV("///");
        creditPage.clickSubmitButton();
        creditPage.cvvFormat("Неверный формат");
    }
}

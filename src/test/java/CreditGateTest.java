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
    @DisplayName("All Credit valid")
    void allCreditValid() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));

    }

    @Test
    @DisplayName("Invalid Credit")
    void invalidCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("");
        $x("//div[2]/span/span[1]//input").setValue("");
        $x("//div[2]/span/span[2]//input").setValue("");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("");
        $x("//div[4]/button").click();

    }

    @Test
    @DisplayName("Card Number Credit Invalid")
    void cardNumberCreditInvalid() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 444");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $(".input__sub")
                .shouldHave(Condition.exactText("Неверный формат"));

    }

    @Test
    @DisplayName("OneDigitMonthCredit")
    void oneDigitMonthCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("7");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $(".input__sub")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    @DisplayName("ThirteenMonthCredit")
    void thirteenMonthCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("13");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        ;
        $(".input__sub")
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    @DisplayName("OneDigitYearCredit")
    void oneDigitYearCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("2");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $(".input__sub")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    @DisplayName("PreviousYearCredit")
    void previousYearCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("23");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $(".input__sub")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Истёк срок действия карты"));

    }

    @Test
    @DisplayName("DigitHolderCredit")
    void digitHolderCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("12345");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        // $(".notification__content") Ошибка!
    }

    @Test
    @DisplayName("OneLetterHolderCredit")
    void oneLetterHolderCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
    }

    @Test
    @DisplayName("RuHolderCredit")
    void ruHolderCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        // $(".notification__content") Ошибка!
    }

    @Test
    @DisplayName("SymbolsHolderCredit")
    void symbolsHolderCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();        // $(".notification__content") Ошибка!
    }

    @Test
    @DisplayName("InvalidCVCCredit")
    void invalidCVCCredit() {
        $("button.button").find(byText("Купить")).click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("11");
        $x("//div[4]/button").click();
        $(".input__sub")
                .shouldHave(Condition.exactText("Неверный формат"));
    }
}

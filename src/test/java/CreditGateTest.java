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
    @DisplayName("InvalidCredit")
    void inValid() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("");
        $x("//div[2]/span/span[1]//input").setValue("");
        $x("//div[2]/span/span[2]//input").setValue("");
        $x("//div[3]/span/span[1]//input").setValue("");
        $x("//div[3]/span/span[2]//input").setValue("");
        $x("//div[4]/button").click();
        $x("//div[1]/span//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
        $x("//div[2]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
        $x("//div[2]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
        $x("//div[3]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        $x("//div[3]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));


    }

    @Test
    @DisplayName("Card15DigitCredit")
    void card15DigitInvalid() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 444");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[1]/span//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));

    }

    @Test
    @DisplayName("Card17DigitCredit")
    void card17DigitInvalid() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 44415");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[1]/span//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));

    }

    @Test
    @DisplayName("CardLetters")
    void cardLettersInvalid() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("qwerty");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[1]/span//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));

    }

    @Test
    @DisplayName("Card Symbol Credit")
    void cardSymbolInvalid() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("///???");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[1]/span//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));

    }

    @Test
    @DisplayName("OneDigitMonthCreditCredit")
    void oneDigitMonth() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("7");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }
    @Test
    @DisplayName("ThreeDigitMonthCredit")
    void ThreeDigitMonth() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("155");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }
    @Test
    @DisplayName("ThirteenMonthCredit")
    void thirteenMonth() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("13");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    @DisplayName("Letters Month Credit")
    void lettersMonth() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("qwerty");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }
    @Test
    @DisplayName("Symbols Month Credit")
    void symbolsMonth() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("///???");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    @DisplayName("OneDigitYearCredit")
    void oneDigitYear() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("2");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }
    @Test
    @DisplayName("ThreeDigitYearCredit")
    void ThreeDigitYear() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("345");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    @DisplayName("PreviousYearCredit")
    void previousYear() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("23");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[2]//span[3]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Истёк срок действия карты"));

    }
    @Test
    @DisplayName("Letters Year Credit")
    void lettersYear() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("qwerty");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    @DisplayName("Symbol Year Credit")
    void symbolYear() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("///???");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[2]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    @DisplayName("DigitHolderCredit")
    void digitHolder() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("12345");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[3]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));

    }

    @Test
    @DisplayName("OneLetterHolderCredit")
    void oneLetterHolder() {
        $x("//div/button[2]").click();
        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("V");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[3]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    @DisplayName("RuHolderCredit")
    void ruHolder() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Владимирова");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[3]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));

    }

    @Test
    @DisplayName("SymbolsHolderCredit")
    void symbolsHolder() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("///???");
        $x("//div[3]/span/span[2]//input").setValue("111");
        $x("//div[4]/button").click();
        $x("//div[3]/span/span[1]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    @DisplayName("Two CVC Credit")
    void twoCVC() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("11");
        $x("//div[4]/button").click();
        $x("//div[3]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }
    @Test
    @DisplayName("Letters CVC Credit")
    void lettersCVC() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("qwe");
        $x("//div[4]/button").click();
        $x("//div[3]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }
    @Test
    @DisplayName("Symbols CVC Credit")
    void symbolsCVC() {
        $x("//div/button[2]").click();

        $x("//div[1]/span/span/span[2]/input").setValue("4444 4444 4444 4441");
        $x("//div[2]/span/span[1]//input").setValue("07");
        $x("//div[2]/span/span[2]//input").setValue("27");
        $x("//div[3]/span/span[1]//input").setValue("Vladimirova");
        $x("//div[3]/span/span[2]//input").setValue("///");
        $x("//div[4]/button").click();
        $x("//div[3]/span/span[2]//span[3]")
                .shouldHave(Condition.exactText("Неверный формат"));
    }

}

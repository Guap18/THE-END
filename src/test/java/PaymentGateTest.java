import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

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
    void allValid() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Invalid")
    void inValid() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Card15Digit")
    void card15DigitInvalid() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Card17Digit")
    void card17DigitInvalid() {
        $("button.button").find(byText("Купить")).click();

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
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Card Symbol")
    void cardSymbolInvalid() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("OneDigitMonth")
    void oneDigitMonth() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("ThreeDigitMonth")
    void ThreeDigitMonth() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("ThirteenMonth")
    void thirteenMonth() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Letters Month")
    void lettersMonth() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Symbols Month")
    void symbolsMonth() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("OneDigitYear")
    void oneDigitYear() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("ThreeDigitYear")
    void ThreeDigitYear() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("PreviousYear")
    void previousYear() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Letters Year")
    void lettersYear() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Symbol Year")
    void symbolYear() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("DigitHolder")
    void digitHolder() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("OneLetterHolder")
    void oneLetterHolder() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("RuHolder")
    void ruHolder() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("SymbolsHolder")
    void symbolsHolder() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Two CVC")
    void twoCVC() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Letters CVC")
    void lettersCVC() {
        $("button.button").find(byText("Купить")).click();

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
    @DisplayName("Symbols CVC")
    void symbolsCVC() {
        $("button.button").find(byText("Купить")).click();

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



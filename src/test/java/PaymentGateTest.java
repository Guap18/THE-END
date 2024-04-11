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

public class PaymentGateTest {
    @BeforeAll
    static void setAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){
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

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//div[3]//span[1]/span/span/span[2]/input").setValue("Vladimirova");
        $(".input__control[maxlength='3']").setValue("111");
        $x("//div[4]/button").click();




    }
    @Test
    @DisplayName("Invalid")
    void inValid() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("");
        $(".input__control[placeholder='08']").setValue("");
        $(".input__control[placeholder='22']").setValue("");
        $x("//*[text()='Владелец']").setValue("");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("");
        $("button.button__text:contains('Продолжить')").click();
        //$(".notification__content") неверный формат полей
        //  .shouldBe(Condition.visible, Duration.ofSeconds(15))
        //    .shouldHave(Condition.exactText(""));
    }
    @Test
    @DisplayName("CardNumberInvalid")
    void cardNumberInvalid() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 444");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//*[text()='Владелец']").setValue("Vladimirova");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content") Неверный формат
    }
    @Test
    @DisplayName("OneDigitMonth")
    void oneDigitMonth() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("7");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//*[text()='Владелец']").setValue("Vladimirova");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content") Неверный формат
    }
    @Test
    @DisplayName("ThirteenMonth")
    void thirteenMonth() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("13");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//*[text()='Владелец']").setValue("Vladimirova");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content")(Неверно указан срок действия карты)
    }
    @Test
    @DisplayName("OneDigitYear")
    void oneDigitYear() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("2");
        $x("//*[text()='Владелец']").setValue("Vladimirova");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content")(Неверный формат)
    }
    @Test
    @DisplayName("PreviousYear")
    void previousYear() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("23");
        $x("//*[text()='Владелец']").setValue("Vladimirova");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content")(Истёк срок действия карты)
    }
    @Test
    @DisplayName("DigitHolder")
    void digitHolder() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//*[text()='Владелец']").setValue("12345");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content") Ошибка!
    }
    @Test
    @DisplayName("OneLetterHolder")
    void oneLetterHolder() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//*[text()='Владелец']").setValue("V");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content") Ошибка!
    }
    @Test
    @DisplayName("RuHolder")
    void ruHolder() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//*[text()='Владелец']").setValue("Владимирова");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content") Ошибка!
    }
    @Test
    @DisplayName("SymbolsHolder")
    void symbolsHolder() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//*[text()='Владелец']").setValue("////???");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("111");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content") Ошибка!
    }

    @Test
    @DisplayName("InvalidCVC")
    void invalidCVC() {
        $("button.button").find(byText("Купить")).click();

        $(".input__control[maxlength='19']").setValue("4444 4444 4444 4441");
        $(".input__control[placeholder='08']").setValue("07");
        $(".input__control[placeholder='22']").setValue("27");
        $x("//*[text()='Владелец']").setValue("VLADIMIROVA");
        //$x("//button[contains(text(),'Владелец']").setValue("Vladimirova");

        $(".input__control[maxlength='3']").setValue("11");
        $("button.button__text:contains('Продолжить')").click();
        // $(".notification__content") (Неверный формат)
    }
}



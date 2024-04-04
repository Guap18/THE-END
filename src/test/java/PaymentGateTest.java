import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;


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
        $(".input__top").find(byText("Владелец")).setValue("Vladimirova");
        $(".input__top").find(byText("CVC/CVV")).setValue("111");
        $(".button__text").find(withText("Продолжить")).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));


    }
}

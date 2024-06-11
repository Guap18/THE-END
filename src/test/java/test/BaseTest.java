package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConnectionMysqlConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import page.CreditPage;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected final ConnectionMysqlConfig connectionMysqlConfig = new ConnectionMysqlConfig();
    protected CreditPage creditPage = new CreditPage();

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
        connectionMysqlConfig
                .connect()
                .executePrepareStatement("truncate table payment_entity")
                .executePrepareStatement("truncate table order_entity")
                .executePrepareStatement("truncate table credit_request_entity");

        open("http://localhost:8080");
    }

    @AfterEach
    void tearDown() {
        connectionMysqlConfig.connectionClose();
    }
}

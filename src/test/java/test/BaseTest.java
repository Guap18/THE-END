package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.DatabaseConnector;
import database.DatabaseQueryExecutor;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import page.CreditPage;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

  protected CreditPage creditPage = new CreditPage();

  protected final DatabaseQueryExecutor databaseQueryExecutor = new DatabaseQueryExecutor();


  @BeforeEach
  void setup() {
    databaseQueryExecutor.deleteAll();
    Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless"));
    open("http://localhost:8080");
  }

  @BeforeAll
  static void setAll() {
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @AfterAll
  @SneakyThrows
  static void tearDownAll() {
    SelenideLogger.removeListener("allure");
    DatabaseConnector.connection.close();
  }
}

package test.database_test;

import config.DatabaseConnector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import testcontainers.MysqlTestContainersEnvironment;
import testcontainers.PostgresqlTestContainersEnvironment;

public class CreditGatePostgresTest extends BaseTest {

    @BeforeAll
    static void setUp() {
        MysqlTestContainersEnvironment.environmentWithMysql.stop();
        PostgresqlTestContainersEnvironment.startContainers();
        DatabaseConnector.getConnection("postgres.url");
    }

    //Так же проверяет интеграцию с postgres
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

        String actual = databaseQueryExecutor.executeQueryAndGetValue("credit");

        Assertions.assertEquals("APPROVED", actual);
    }
}

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;


public class DBTest {

    private final ConnectionMysqlConfig connectionMysqlConfig = new ConnectionMysqlConfig();

    @BeforeEach
    void setUp() {
        connectionMysqlConfig
                .connect()
                .executePrepareStatement("truncate table payment_entity")
                .executePrepareStatement("truncate table order_entity")
                .executePrepareStatement("truncate table credit_request_entity");
//        var dataSql = "insert into user(login, password) values (?, ?);";
//        var truncatePaymentEntity = "truncate table payment_entity";
//        var truncateOrderEntity = "truncate table order_entity";
//        var truncateCredit_request_entityEntity = "truncate table credit_request_entity";
//
//        try (
//            var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
//            var dataStmt = conn.prepareStatement(truncatePaymentEntity);
//            var dataStmt2 = conn.prepareStatement(truncateOrderEntity);
//            var dataStmt3 = conn.prepareStatement(truncateCredit_request_entityEntity);
//        ) {
//            dataStmt.executeUpdate();
//            dataStmt2.executeUpdate();
//            dataStmt3.executeUpdate();
//        }
    }

    @AfterEach
    void tearDown() {
        connectionMysqlConfig.connectionClose();
    }

    @Test
    @DisplayName("All Credit valid")
    void allCreditValid() {
        open("http://localhost:8080");
        POCredit poCredit = new POCredit();
        poCredit.clickBuyButton();
        poCredit.enterCardNumber("4444 4444 4444 4441");
        poCredit.enterExpiryMonth("07");
        poCredit.enterExpiryYear("27");
        poCredit.enterCardHolderName("Vladimirova");
        poCredit.enterCVV("111");
        poCredit.clickSubmitButton();
        poCredit.waitForNotification("Операция одобрена Банком.");

        connectionMysqlConfig.executeQueryAndPrint("SELECT * FROM credit_request_entity;");

    }
}

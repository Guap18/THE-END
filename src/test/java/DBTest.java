import org.junit.jupiter.api.*;

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

        String actualCreditValue = connectionMysqlConfig.executeQueryAndGetValue("credit");

        Assertions.assertEquals("APPROVED", actualCreditValue);

    }

    @Test
    @DisplayName("All valid")
    public void allValid() {
        open("http://localhost:8080");
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.clickBuyButton();
        checkoutPage.enterCardNumber("4444 4444 4444 4441");
        checkoutPage.enterExpiryMonth("07");
        checkoutPage.enterExpiryYear("27");
        checkoutPage.enterCardHolderName("Vladimirova");
        checkoutPage.enterCVV("111");
        checkoutPage.clickSubmitButton();
        checkoutPage.waitForNotification("Операция одобрена Банком.");
        String actualPaymentValue = connectionMysqlConfig.executeQueryAndGetValue("payment");

        Assertions.assertEquals("APPROVED", actualPaymentValue);
    }

    }

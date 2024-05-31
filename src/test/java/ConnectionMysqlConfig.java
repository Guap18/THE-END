import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConnectionMysqlConfig {

    private Connection conn;

    @SneakyThrows
    public ConnectionMysqlConfig connect() {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        return this;
    }

    @SneakyThrows
    public ConnectionMysqlConfig executePrepareStatement(String sqlStatement) {
        conn.prepareStatement(sqlStatement).executeUpdate();
        return this;
    }

    @SneakyThrows
    public void executeQueryAndPrint(String sqlStatement) {
        ResultSet resultSet = conn.prepareStatement(sqlStatement).executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.get());
            //select ord.id, pa.status as payment, cr.status as credit
            //from app.order_entity ord
            //left join app.payment_entity pa on ord.payment_id = pa.transaction_id
            //left join app.credit_request_entity cr on ord.payment_id = cr.bank_id
        }
    }

    @SneakyThrows
    public void connectionClose() {
        conn.close();
    }
}

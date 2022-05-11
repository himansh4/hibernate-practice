import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:h2:mem:test",
                "sa",
                ""
        )) {
            System.out.println("Connection successful!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

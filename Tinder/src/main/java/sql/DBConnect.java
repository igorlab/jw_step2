package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class DBConnect {

    public static Optional<Connection> get(
            String url,
            String username,
            String password
    ) {
        try {
            return Optional.of(DriverManager.getConnection(url, username, password));
        } catch (SQLException ex) {
            return null;

        }
    }

    public static Optional<Connection> get() { // перегруженный конструктор
        return get(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "admin"
        );
    }
}


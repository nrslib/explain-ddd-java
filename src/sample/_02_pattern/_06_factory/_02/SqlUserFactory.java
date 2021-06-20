package sample._02_pattern._06_factory._02;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class SqlUserFactory implements UserFactory {
    @Override
    public User create(UserName name) {
        Objects.requireNonNull(name);

        try (var connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "user", "pass")) {
            try (var seqNoUpdate = connection
                    .prepareStatement("UPDATE seq_numbers SET seq_no = seq_no+1 WHERE seq_key = 'users'")) {
                seqNoUpdate.executeUpdate();
            }
            try (var getCurrentSeq = connection
                    .prepareStatement("SELECT seq_no FROM seq_numbers WHERE seq_key = 'users'")) {
                var rs = getCurrentSeq.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException();
                }

                var idValue = rs.getString(1);

                return new User(new UserId(idValue), name);
            }
        } catch (SQLException e) {
            throw new SqlFactoryException(e);
        }
    }
}

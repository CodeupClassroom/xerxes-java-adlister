import java.sql.*;

import com.mysql.cj.jdbc.Driver;

import java.util.ArrayList;
import java.util.List;

public class MySQLBooksDao implements Books{
    private Connection connection = null;

    public MySQLBooksDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("error connecting to database", e);
        }
    }



    @Override
    public List<Book> all() {
        List<Book> books = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books_example.books");
            while (rs.next()) {
                books.add(new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author")
                ));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package demodb;

import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
	    new Main().run();
    }

    private void run() {
        Properties properties = new Properties();
        properties.setProperty("user", "eugeny");
        properties.setProperty("password", "123");

        try {
            // 2
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/demo1", properties);
            // 3
            PreparedStatement ps = connection.prepareStatement("select * from student");
            // 4
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double rating = rs.getDouble("rating");
                System.out.println(id + " | " + name + " | " + age + " | " + rating);
            }
            // 5
            rs.close();
            ps.close();

//            PreparedStatement ps1 = connection.prepareStatement("insert into student (name, age, rating) values (?, ?, ?)");
//            ps1.setString(1, "Сеня");
//            ps1.setInt(2, 38);
//            ps1.setDouble(3, 59);
//            ps1.executeUpdate();

            connection.close(); // только если БД более не нужна
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

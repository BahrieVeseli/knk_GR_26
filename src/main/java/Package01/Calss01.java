package Package01;
import Database.DBConnector;
import java.sql.*;


public class Calss01 {
    public static void main(String[] args) throws SQLException {

       Connection connection= DBConnector.getConnection();

        String query= """
                INSERT INTO USERS (name, email, age)
                VALUES ('Filan' ,'Fisteku', 25)
                """;
        Statement statement=connection.createStatement();
        statement.execute(query);
        statement.execute("DELETE FROM USERS WHERE id=1");

        query= """
    SELECT * FROM USERS
""";

        ResultSet results=statement.executeQuery(query);
        while(results.next()){
            int id=results.getInt("id");
            String name=results.getString("name");
            String email=results.getString("email");
            int age=results.getInt("age");
            System.out.println("id" +id+" "+"name: "+name+" "+"email"+email+" age: "+age);
            System.out.println("----------------------------");
        }

    }
}

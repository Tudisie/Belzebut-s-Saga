package Database;

import state.HighscoreState;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseOperations {
    public static void insertRecord(String tableName,String name, int points){
        Connection connection = null;
        Statement stmt = null;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:highscores.db");
            connection.setAutoCommit(true);
            stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * from '" + tableName + "' WHERE Name = '" + name + "' ");
            if(rs.next() == false){ //if this player played for the first time
                stmt.executeUpdate("INSERT INTO '" + tableName + "' (Name, Points) VALUES('" + name+ "', " + points+")");
            }
            else //this player already exists in the database
            {
                int oldPoints = rs.getInt("Points");
                stmt.executeUpdate("UPDATE '" + tableName + "' set Points = " + points + "" +
                        "  WHERE Name = '" + name + "' AND " + points + " > " + oldPoints +";");
            }

            stmt.close();
            connection.close();

        }catch ( Exception e ) {
            System.out.println("Database Error");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public static void getTable(String tableName){ //sorted
        Connection connection = null;
        Statement stmt = null;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:highscores.db");
            connection.setAutoCommit(true);
            stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * from " + tableName + " ORDER BY CAST(Points as INT) DESC;");

            if(tableName.endsWith("1")){
                HighscoreState.clearScores(1);
                while(rs.next()){

                    HighscoreState.addScore(HighscoreState.getScores(1), new Score(rs.getString("Name"), rs.getInt("Points")));
                }
            }

            if(tableName.endsWith("2")){
                HighscoreState.clearScores(2);
                while(rs.next()){

                    HighscoreState.addScore(HighscoreState.getScores(2), new Score(rs.getString("Name"), rs.getInt("Points")));
                }
            }

            if(tableName.endsWith("3")){
                HighscoreState.clearScores(3);
                while(rs.next()){
                    HighscoreState.addScore(HighscoreState.getScores(3), new Score(rs.getString("Name"), rs.getInt("Points")));
                }
            }


            rs.close();
            stmt.close();
            connection.close();

        }catch ( Exception e ) {
            System.out.println("Database Error");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}

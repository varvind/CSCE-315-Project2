/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2.pkg315;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arvin
 */
public class Question2 {
    private List<String> args;
    private String queryInput;
    private Connection conn;
    private String sqlStatement;
    
    public Question2(String queryInput, Connection conn) {
        args = new ArrayList<>();
        this.conn = conn;
        this.queryInput = queryInput;
        sqlStatement = formatQuery();
    }
    
    /**
     * Get Users review statistics
     * @return 
     */
    public String getUserStats() {
        String output;
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //send statement to DBMS
            if(queryInput.equals("")) {
               return "Invalid Input : Please enter a User";
            } else {
                ResultSet result = stmt.executeQuery(sqlStatement);

                output = getFormattedQueryOutput(result, args);
            }   
                        
        } catch (SQLException e) {
            output = "Error: executing query, please try again.";
        }
        return output;
    }
    
    /**
     * Formats SQL statement that will be executed
     * @return 
     */
    private String formatQuery() {
        args.add("name");
        args.add("avg_stars");
        args.add("stars");
        args.add("funny");
        args.add("cool");
        args.add("useful");
        args.add("text");
        String query = "SELECT NAME, avg_stars, r.text, r.stars, "
                + "r.funny, r.cool, r.useful FROM "
                + "(SELECT user_id, NAME, average_stars "
                + "AS avg_stars FROM users WHERE  user_id = " + 
                "\'" + queryInput + "\'" + ") a "
                + "INNER JOIN review AS r ON a.user_id = r.user_id"; 
        return query;
    }
    
    /**
     * Takes results from execution of SQL query and formats data for user output
     * @param result
     * @param args
     * @return 
     */
    private String getFormattedQueryOutput(ResultSet result, List<String> args) {
        String output = "";
        int size = 0;
        for(int i = 0; i < args.size(); i++) {
            output += String.format("%-50s", args.get(i));
        }
        try {
            output += "\n\n";
            if(result != null) {
                result.last();
                size = result.getRow();
            }
            if(size == 0) {
                return "";
            }
            result.first();
            if(result != null) {
                while(result.next()) {
                   for(int i = 0; i < args.size(); i++) {
                       String arg;
                       output += String.format("%-50s", result.getString(args.get(i)));
                   }
                   output += "\n\n";
                }
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            output = "Error: in Query, Please try again";
        }
        
        return output;
    }
}

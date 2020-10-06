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
public class Question4 {
    private List<String> args;
    private String queryInput;
    private Connection conn;
    private String sqlStatement;
    public Question4(String queryInput, Connection conn) {
        args = new ArrayList<>();
        this.conn = conn;
        this.queryInput = queryInput;
        sqlStatement = formatQuery();
    }
    
    public String getBestLocalRestuarants() {
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
    
    private String formatQuery() {
        args.add("name");
            args.add("text");

            String query = "SELECT mostTip.name, mostTip.count, "
                    + "tip.text FROM (SELECT nonFran.name, "
                    + "nonFran.business_id, Count(name) "
                    + "FROM (SELECT a.name, b.city, b.business_id "
                    + "FROM (SELECT name, Count(name) FROM business "
                    + "GROUP BY name HAVING Count(name) = 1) a INNER "
                    + "JOIN business AS b ON b.name = a.name WHERE "
                    + "city = " + "\'" + queryInput + "\'" + ") "
                    + "nonFran INNER JOIN tip AS t ON "
                    + "nonFran.business_id = t.business_id GROUP "
                    + "BY name, nonFran.business_id ORDER BY count "
                    + "DESC LIMIT 1) mostTip INNER JOIN tip ON "
                    + "mostTip.business_id = tip.business_id"; 
        return query;
    }
    
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

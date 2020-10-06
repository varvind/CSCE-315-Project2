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
public class Question3 {
    private List<String> args;
    private String queryInput;
    private Connection conn;
    private String sqlStatement;
    public Question3(String queryInput, Connection conn) {
        args = new ArrayList<>();
        this.conn = conn;
        this.queryInput = queryInput;
        sqlStatement = formatQuery();
    }
    
    public String getSpread() {
        String output;
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //send statement to DBMS
            if(queryInput.equals("")) {
               return "Error Invalid Input : Please enter a User";
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
        args.add("countinstate");
        args.add("avgstars");
        String query = "SELECT franCntInState.name, "
                + "franCntInState.countinstate, "
                + "rate.avgstars FROM "
                + "(SELECT Count(franInState.name) AS countInState,";
        query += "franInState.name FROM (SELECT b.name, "
                + "b.business_id FROM   (SELECT name, Count(name) "
                + "FROM   business GROUP  BY name "
                + "HAVING Count(name) > 1) fran "
                + "INNER JOIN business AS b ON b.name = "
                + "fran.name WHERE  b.state = \'" + queryInput 
                + "\' ORDER  BY name) franInState GROUP  BY "
                + "franInState.name  ORDER  BY countinstate DESC) "
                + "franCntInState INNER JOIN (SELECT name, "
                + "Avg(stars) AS avgStars FROM business GROUP  "
                + "BY name) rate ON rate.name = franCntInState.name "
                + "WHERE  avgstars >= 3.5 ORDER  BY countinstate "
                + "DESC LIMIT 5 ";  
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

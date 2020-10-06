package project2.pkg315;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arvin
 */
class Node {
    private Node parent;
    private String current_id;
    private String user_id;
    public Node(Node parent, String current_id, String user_id) {
        this.parent = parent;
        this.current_id = current_id;
        this.user_id = user_id;
    }

    public Node getParent() {
        return parent;
    }

    public String getCurrent_id() {
        return current_id;
    }

    public String getUser_id() {
        return user_id;
    }
    
}

public class Question1 {
    private String b2Id;
    private String b1Id;
    private Connection conn;
    private Map<String, List<String>> userToBusiness = new HashMap<>();
    private Map<String, List<String>> businessToUser = new HashMap<>();
    private Queue<Node> queue = new LinkedList<>();
    private Set<String> visited = new HashSet<>();
    
    public Question1(String business1, String business2, Connection conn) {
        this.conn = conn;
        b2Id = getBusinessId(business2);
        b1Id = getBusinessId(business1);
    }
    public String getChain() {
        String chain = "";
        getReviews(userToBusiness, businessToUser);
        Node node = new Node(null, b1Id, "");
        Node output = null;
        queue.add(node);
        visited.add(b1Id);
        while(!queue.isEmpty()) {
            node = queue.remove();
            if(node.getCurrent_id().equals(b2Id)) {
                output = node;
                break;
            } else {
                List<String> users = businessToUser.get(node.getCurrent_id());
                if(users == null) {
                    users = new ArrayList<>();
                }
                for(String user : users) {
                    List<String> businesses = userToBusiness.get(user);
                    if(businesses == null) {
                        businesses = new ArrayList<>();
                    }
                    for(String business: businesses) {
                        if(visited.add(business)) {
                            Node newNode = new Node(node, business, user);
                            queue.add(newNode);
                        }
                    }
                }
            }
           
        }
        if(!node.getCurrent_id().equals(b2Id)) {
            chain = "There is no path between business 1 and business 2";
        } else {
            Stack<String> userOutput = new Stack<>();
            while(output != null) {
                System.out.println(output.getUser_id());
                String name = getUserName(output.getUser_id());
                if(name != "") {
                    userOutput.push(name);
                }
                output = output.getParent();
            }

            chain += "Shortest Chain : ";
            while(!userOutput.empty()) {
                chain += userOutput.pop() + " to ";
            }
            chain = chain.substring(0, chain.length() - 4);
            
        }
        return chain;
        
    }
    private void getReviews(Map<String, List<String>> userToBusiness, Map<String, List<String>> businessToUser) {
        String query = "Select user_id, business_id FROM review WHERE stars > 2";     
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next()) {
                String user_id = result.getString("user_id");
                String business_id = result.getString("business_id");
                
                if(userToBusiness.get(user_id) == null) {
                    List<String> bus_ids = new ArrayList<>();
                    bus_ids.add(business_id);
                    userToBusiness.put(user_id, bus_ids);
                } else {
                    userToBusiness.get(user_id).add(business_id);
                }
                
                if(businessToUser.get(business_id) == null) {
                    List<String> user_ids = new ArrayList<>();
                    user_ids.add(user_id);
                    businessToUser.put(business_id, user_ids);
                } else {
                    businessToUser.get(business_id).add(user_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private String getBusinessId(String business) {
        String query = "SELECT business_id FROM business WHERE name = " + "\'" + business + "\'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next()) {
                String business_id = result.getString("business_id");
                return business_id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    private String getUserName(String id){
        String query = "SELECT name FROM users WHERE user_id = \'" + id + "\'";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next()) {
                String name = result.getString("name");
                return name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}

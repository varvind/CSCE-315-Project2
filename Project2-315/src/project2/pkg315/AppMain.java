/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2.pkg315;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;  
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 *
 * @author arvin
 */
class WindowEventHandler extends WindowAdapter {
  @Override
  public void windowClosing(WindowEvent evt) {
      try {
          AppMain.closeConnection();
      } catch (SQLException ex) {
          Logger.getLogger(WindowEventHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
}

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
public class AppMain extends javax.swing.JFrame {
    private final static String Q_1 = "Find Shortest Chain Between Two Restuarants";
    private final static String Q_2 = "Get Review Statistics For a User";
    private final static String Q_3 = "Find the Most Spread of Franchises in a State";
    private final static String Q_4 = "Find the Best Local Restuarant in a City";
    private static Connection conn;
    private final static String DB_STRING =
            "jdbc:postgresql://csce-315-db.engr.tamu.edu/db908_group18_project2";
    private static boolean isLoggedIn = false;
    
    /**
     * Creates new form AppMain
     */
    public AppMain() {
        initComponents();
        Search.setEnabled(false);
    }
    
    public static void closeConnection() throws SQLException{
        if(conn != null){
            conn.close();
        }      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginDialog = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        userPassword = new javax.swing.JPasswordField();
        loginMessage = new javax.swing.JLabel();
        userUserName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        loginUser = new javax.swing.JButton();
        outputDialog = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultsText = new javax.swing.JTextArea();
        exportFile = new javax.swing.JButton();
        outputMessage = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        connectionLabel = new javax.swing.JLabel();
        query = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Search = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        queryErrorMessage = new javax.swing.JLabel();
        questionBox = new javax.swing.JComboBox<>();
        selectQuestion = new javax.swing.JButton();
        query2 = new javax.swing.JTextField();

        loginDialog.setMinimumSize(new java.awt.Dimension(466, 300));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Database Login");

        userPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userPasswordActionPerformed(evt);
            }
        });

        userUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userUserNameActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Username");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Password");

        loginUser.setText("Login");
        loginUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginDialogLayout = new javax.swing.GroupLayout(loginDialog.getContentPane());
        loginDialog.getContentPane().setLayout(loginDialogLayout);
        loginDialogLayout.setHorizontalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(loginUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginDialogLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(146, 146, 146))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginDialogLayout.createSequentialGroup()
                        .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(loginDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(userPassword))
                                .addGroup(loginDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(userUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(loginDialogLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(loginMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        loginDialogLayout.setVerticalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(67, 67, 67)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(userUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginUser)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        outputDialog.setMinimumSize(new java.awt.Dimension(613, 425));

        resultsText.setColumns(20);
        resultsText.setRows(5);
        jScrollPane1.setViewportView(resultsText);

        exportFile.setText("Export to File");
        exportFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportFileActionPerformed(evt);
            }
        });

        outputMessage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        outputMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout outputDialogLayout = new javax.swing.GroupLayout(outputDialog.getContentPane());
        outputDialog.getContentPane().setLayout(outputDialogLayout);
        outputDialogLayout.setHorizontalGroup(
            outputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputDialogLayout.createSequentialGroup()
                .addGroup(outputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outputDialogLayout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(exportFile)
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addGroup(outputDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(outputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        outputDialogLayout.setVerticalGroup(
            outputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(outputMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTextField1.setText("jTextField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Team M.A.S.K Presents");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel2.setText("Yelp Business Query");

        connectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectionLabel.setText("Status: Not Connected to Database");

        query.setEditable(false);
        query.setText("Please Login Before Searching");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        queryErrorMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        questionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Find Shortest Chain Between Two Restuarants", "Get Review Statistics For a User", "Find the Most Spread of Franchises in a State", "Find the Best Local Restuarant in a City" }));
        questionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionBoxActionPerformed(evt);
            }
        });

        selectQuestion.setText("Select Question");
        selectQuestion.setEnabled(false);
        selectQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectQuestionActionPerformed(evt);
            }
        });

        query2.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(queryErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(query, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(connectionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(Search))
                    .addComponent(query2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(loginButton)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(questionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(selectQuestion)
                        .addGap(269, 269, 269))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(loginButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(connectionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(questionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectQuestion)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(queryErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(query, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(query2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(Search)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userPasswordActionPerformed

    private void userUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userUserNameActionPerformed

    private void loginUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginUserActionPerformed
        // TODO add your handling code here:
        String userName = userUserName.getText();
        String passCode = new String(userPassword.getPassword());
        if(userPassword.getPassword().length == 0 || userName.equals("")) {
            loginMessage.setText("Error: Missing Fields");
        } else {
            try {
                loginMessage.setText("Logging in....");
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(DB_STRING, userName, passCode);
                isLoggedIn = true;
                connectionLabel.setText("Status: Connected");
                connectionLabel.setForeground(new Color(76, 175, 80));
            } catch (SQLException | ClassNotFoundException e) {
                loginMessage.setText("Error connecting to database, please try again");
            }
        }
        if(isLoggedIn) {
            Search.setEnabled(true);
            query.setText("");
            query.setEditable(true);
            query2.setEnabled(true);
            loginButton.setVisible(false);
            loginDialog.setVisible(false);
            selectQuestion.setEnabled(true);
            queryErrorMessage.setText("Enter 2 Businesses In Both of the Fields Below");
        }
    }//GEN-LAST:event_loginUserActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        loginDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        loginDialog.setVisible(true);
    }//GEN-LAST:event_loginButtonActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        Statement stmt;
        try {
            List<String> args = new ArrayList<>();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             //send statement to DBMS
            String sqlStatement = "";
            System.out.println(query.getText());
            if(query.getText().equals("")) {
               queryErrorMessage.setText("Invalid Input : Please enter a User");
            } else {
                if(questionBox.getSelectedItem().equals(Q_1) ){
                    runQuestionOne();          
                } else {
                    sqlStatement = createQuery(args);

                    ResultSet result = stmt.executeQuery(sqlStatement);

                    queryErrorMessage.setText("Searching....");
                    String output = getFormattedQueryOutput(result, args);
                    if(output.equals("")) {
                        resultsText.setText("No Results Found");
                        exportFile.setEnabled(false);
                    } else {
                       resultsText.setText(output);
                    }
                    outputDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    outputDialog.setVisible(true);
                }   
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SearchActionPerformed
    
    private String createQuery(List<String> args) {
        String sqlStatement = "";
        if(questionBox.getSelectedItem().equals(Q_2)){
            args.add("name");
            args.add("avg_stars");
            args.add("stars");
            args.add("funny");
            args.add("cool");
            args.add("useful");
            args.add("text");
            sqlStatement += "SELECT NAME, avg_stars, r.text, r.stars, "
                    + "r.funny, r.cool, r.useful FROM "
                    + "(SELECT user_id, NAME, average_stars "
                    + "AS avg_stars FROM users WHERE  user_id = " + 
                    "\'" + query.getText() + "\'" + ") a "
                    + "INNER JOIN review AS r ON a.user_id = r.user_id"; 

        } else if(questionBox.getSelectedItem().equals(Q_3)){
            args.add("name");
            args.add("countinstate");
            args.add("avgstars");
            sqlStatement += "SELECT franCntInState.name, "
                    + "franCntInState.countinstate, "
                    + "rate.avgstars FROM "
                    + "(SELECT Count(franInState.name) AS countInState,";
            sqlStatement += "franInState.name FROM (SELECT b.name, "
                    + "b.business_id FROM   (SELECT name, Count(name) "
                    + "FROM   business GROUP  BY name "
                    + "HAVING Count(name) > 1) fran "
                    + "INNER JOIN business AS b ON b.name = "
                    + "fran.name WHERE  b.state = \'" + query.getText() 
                    + "\' ORDER  BY name) franInState GROUP  BY "
                    + "franInState.name  ORDER  BY countinstate DESC) "
                    + "franCntInState INNER JOIN (SELECT name, "
                    + "Avg(stars) AS avgStars FROM business GROUP  "
                    + "BY name) rate ON rate.name = franCntInState.name "
                    + "WHERE  avgstars >= 3.5 ORDER  BY countinstate "
                    + "DESC LIMIT 5 ";    
        } else {
            args.add("name");
            args.add("text");

            sqlStatement += "SELECT mostTip.name, mostTip.count, "
                    + "tip.text FROM (SELECT nonFran.name, "
                    + "nonFran.business_id, Count(name) "
                    + "FROM (SELECT a.name, b.city, b.business_id "
                    + "FROM (SELECT name, Count(name) FROM business "
                    + "GROUP BY name HAVING Count(name) = 1) a INNER "
                    + "JOIN business AS b ON b.name = a.name WHERE "
                    + "city = " + "\'" + query.getText() + "\'" + ") "
                    + "nonFran INNER JOIN tip AS t ON "
                    + "nonFran.business_id = t.business_id GROUP "
                    + "BY name, nonFran.business_id ORDER BY count "
                    + "DESC LIMIT 1) mostTip INNER JOIN tip ON "
                    + "mostTip.business_id = tip.business_id";
        }
        return sqlStatement;
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
            queryErrorMessage.setText("Error in Query, Please try again");
        }
        
        return output;
    }
    
    private void runQuestionOne() {
        String business1 = query.getText();
        String business2 = query2.getText();
        if(business1.contains("\'")) {
            business1 = business1.substring(0, business1.indexOf("\'")) + "\'" +
                    business1.substring(business1.indexOf("\'"));
        }
        if(business2.contains("\'")) {
            business2 = business2.substring(0, business2.indexOf("\'")) + "\'" +
                    business2.substring(business2.indexOf("\'"));
        }
        System.out.println(business1);
        System.out.println(business2);
        String b2Id = getBusinessId(business2);
        String b1Id = getBusinessId(business1);
        System.out.println(b2Id);
        System.out.println(b1Id);
        Map<String, List<String>> userToBusiness = new HashMap<>();
        Map<String, List<String>> businessToUser = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        getReviews(userToBusiness, businessToUser);
        Set<String> visited = new HashSet<>();
        
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
                List<String> users = userToBusiness.get(node.getCurrent_id());
                if(users == null) {
                    users = new ArrayList<>();
                }
                for(String user : users) {
                    List<String> businesses = businessToUser.get(user);
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
            System.out.println("There is no path between business 1 and business 2");
        }
        while(output != null) {
            System.out.println(output.getUser_id());
            output = output.getParent();
        }
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
    private void exportFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportFileActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");  
        Date date = new Date();
        String fileName = formatter.format(date) + ".txt";
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(resultsText.getText());
            file.close();
            String outputMessageText = "Successfully exported to file: " 
                    + fileName;
            outputMessage.setText(outputMessageText);
        } catch (IOException ex) {
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
            outputMessage.setText("Error outputting to file, please try again.");
        }
    }//GEN-LAST:event_exportFileActionPerformed

    private void questionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionButtonActionPerformed
        
    }//GEN-LAST:event_questionButtonActionPerformed
    
    private void queryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryActionPerformed
        
    }//GEN-LAST:event_queryActionPerformed

    private void questionBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_questionBoxActionPerformed

    private void selectQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectQuestionActionPerformed
        // TODO add your handling code here:
        if(questionBox.getSelectedItem().equals(Q_1)){
            query2.setVisible(true);
            query2.setEnabled(true);
            queryErrorMessage.setText("Enter 2 Businesses In Both of the Fields Below");
        } else if(questionBox.getSelectedItem().equals(Q_2)){
            query2.setVisible(false);
            query2.setEnabled(false);
            queryErrorMessage.setText("Enter a User ID");
        } else if(questionBox.getSelectedItem().equals(Q_3)){
            query2.setVisible(false);
            query2.setEnabled(false);
            queryErrorMessage.setText("Enter a US State (Two letter Abreviation , i.e. TX for Texas)");
        } else {
            query2.setVisible(false);
            query2.setEnabled(false);
            queryErrorMessage.setText("Enter a US City");
        }
    }//GEN-LAST:event_selectQuestionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AppMain main = new AppMain();
                main.setVisible(true);
                main.addWindowListener(new WindowEventHandler());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Search;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JButton exportFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton loginButton;
    private javax.swing.JDialog loginDialog;
    private javax.swing.JLabel loginMessage;
    private javax.swing.JButton loginUser;
    private javax.swing.JDialog outputDialog;
    private javax.swing.JLabel outputMessage;
    private javax.swing.JTextField query;
    private javax.swing.JTextField query2;
    private javax.swing.JLabel queryErrorMessage;
    private javax.swing.JComboBox<String> questionBox;
    private javax.swing.JTextArea resultsText;
    private javax.swing.JButton selectQuestion;
    private javax.swing.JPasswordField userPassword;
    private javax.swing.JTextField userUserName;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381milestone2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Cash
 */
public class DBConnection {
    //Set the fully qualified name of Apache Cerby Embedded JDBC driver class
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
     //JDBC URL used to connect to an Apache Derby database.
    private static final String JDBC_URL = "jdbc:derby:NetbeansDB;create=true";
     //Set connection object to connect to Apache Derby databse
    Connection con;
    
    public DBConnection()
    {
        
    }
     //Create a connect to database method
    public void connect() throws ClassNotFoundException
    {
        try
        { //Dynamically load JDBC driver class at runtime
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(JDBC_URL);
            if(this.con != null)
            {
               System.out.println("Connected to database");
            
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }          
    }
    
    //Creating the DB tables for each form:
    
    public void createFeedbackTable()
    {
        try
        {
            //Statement stmt = con.createStatement();   
            //stmt.executeUpdate("DROP TABLE FeedBackTB");
            String query = "Create Table FeedBackTB("+
                "student VARCHAR(100)," +
                "rating INTEGER CHECK (rating >=1 AND rating <=5)," +
                "comments VARCHAR(500)" +
                ")";
            this.con.createStatement().execute(query);
            System.out.println("Succesfully created FeadbackTable");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.out.println("Table already exists.");
        } 
    }
    
     public void createAppointmentsTable(){
        try{
            String query = "CREATE TABLE AppointmentsTB (" +
                       "studentName VARCHAR(20), " +
                       "counselorName VARCHAR(20), " +
                       "apptDate DATE, " +
                       "apptTime VARCHAR(20), " +
                       "status VARCHAR(20))";
            
            this.con.createStatement().execute(query);
            System.out.println("Table created successfully.");
        }catch (SQLException ex){
        ex.printStackTrace();
        }
    }
     
    public void createCounselorsTable()
    {
        try
        {
           //Statement stmt = con.createStatement();   
           //stmt.executeUpdate("DROP TABLE Counselors");
            String query = "CREATE TABLE Counselors ("+
                    "Counselor VARCHAR(100) PRIMARY KEY,"+
                    "Specialization VARCHAR(100),"+
                    "Availability VARCHAR(100))";
            this.con.createStatement().execute(query);
            JOptionPane.showMessageDialog(null, "Table created successfully.");
        }
        catch(SQLException ex)
        {
          System.out.println("Table already exists.");
        }
    }
    
    // Creating the Arraylist for each form:
    
    public ArrayList<Object[]> Feedbackview()
    {
        ArrayList<Object[]> datalist = new ArrayList<>();
        try
        {
            String query = "Select * FROM FeedBackTB";
            ResultSet table = this.con.createStatement().executeQuery(query);
             while(table.next())
             {
                 String student = table.getString("student");
                 Integer rating = table.getInt("rating");
                 String comment = table.getString("comments");
                 
                 Object[] row = {student,rating,comment};
                 datalist.add(row);
             }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return datalist;
    }
    
    public ArrayList<String[]>AppointmentView()
    {
        ArrayList<String[]> dataList = new ArrayList();
        try{
            String query =" SELECT * FROM AppointmentsTB";
            ResultSet table = this.con.createStatement().executeQuery(query);
            
            while(table.next())
            {
                String studentName = table.getString("studentName");
                String counselorName = table.getString("counselorName");
                String apptDate = String.valueOf(table.getDate("apptDate"));
                String apptTime = table.getString("apptTime");
                String status = table.getString("status");
                
                String[] row = {studentName, counselorName, apptDate, apptTime, status};
                dataList.add(row);
            }   
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return dataList;
    }
    
    public ArrayList<String[]> viewCounselors()
    {
        ArrayList<String[]> dataList = new ArrayList<>();
        try
        {
            String query = "SELECT * FROM Counselors";
            ResultSet table = this.con.createStatement().executeQuery(query);
            while(table.next())
            {
                String counselor = table.getString("Counselor");
                String specialization = table.getString("Specialization");
                String availability = table.getString("Availability");
                
                String [] row = {counselor,specialization,availability};
                dataList.add(row);
            } 
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return dataList;
    }
    
    //Creating the ADDbtn for each form:
    
    public void addFB(String student, Integer rating, String comments)
    {
       String query = "INSERT INTO FeedBackTB (student, rating, comments) VALUES (?, ?, ?)";
       try (PreparedStatement pst = con.prepareStatement(query)) 
       {
          pst.setString(1, student);
          pst.setInt(2, rating);
          pst.setString(3, comments);
          pst.executeUpdate();
           JOptionPane.showMessageDialog(null, "Feedback added.");
          pst.close();
       } 
       catch (SQLException ex) 
       {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: "+ ex.getMessage());
       }
    }
    public void addAP(String studentName, String counselorName, Date apptDate, String apptTime, String status)
    { 
        String query = "INSERT INTO AppointmentsTB( studentName, counselorName, apptDate, apptTime, status) VALUES(?, ?, ?, ?, ?)";
        try(PreparedStatement pst = con.prepareStatement(query)){
            
            pst.setString(1,studentName);
            pst.setString(2,counselorName);
            pst.setDate(3,apptDate);
            pst.setString(4,apptTime);
            pst.setString(5,status);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Appointment added.");
            pst.close();
        } catch (SQLException ex){
            ex.printStackTrace();
             JOptionPane.showMessageDialog(null, "Appiontment not added.");
        }  
    }
    
    public void addCounselor(String counselor, String specialization, String availability)
    {
        String query = "INSERT INTO Counselors VALUES (?, ?, ?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, counselor);
            ps.setString(2, specialization);
            ps.setString(3, availability);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Counselor added.");
            ps.close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error: "+ ex.getMessage());
        }
    }
    
    // Creating the Updatebtn for each form:
    
    public void UpdateFB(String student, int rating, String comment)
    {
        try
        {
            String query = "UPDATE FeedBackTB SET rating =?,comments = ? WHERE student = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, rating);
            pst.setString(2,comment);
            pst.setString(3,student);
            int rows = pst.executeUpdate();
            if(rows > 0)
            {
                JOptionPane.showMessageDialog(null, "Feedback updated!");
            }
            else
            {
                 JOptionPane.showMessageDialog(null, "Feedback update Failed!");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: !"+ex.getMessage());
        }  
    }
    
    public void updateAppointment(String studentName, String counselorName, Date apptDate, String apptTime, String status) {
       
        try {
             String query = "UPDATE AppointmentsTB SET counselorName = ?, apptDate = ?, apptTime = ?,status= ? WHERE studentName = ?";
             PreparedStatement pst = con.prepareStatement(query);
             pst.setString(1,counselorName);
             pst.setDate(2,apptDate);
             pst.setString(3,apptTime);
             pst.setString(4,status);
             pst.setString(5,studentName);
             int rows = pst.executeUpdate();
            if(rows > 0)
            {
                JOptionPane.showMessageDialog(null, "Appointment updated!");
            }
            else
            {
                 JOptionPane.showMessageDialog(null, "Failed to update appointment!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Update failed.");
        }
    }
    
    public void updateCounselor(String counselor, String specialization, String availability) 
    {
        try 
        {
            String query = "UPDATE Counselors SET Specialization=?, Availability=? WHERE counselor=?";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, specialization);
            ps.setString(2, availability);
            ps.setString(3, counselor);
            ps.executeUpdate();
            System.out.println("Updated: " + counselor + " to Specialization: " + specialization + ", Availability: " + availability);
            JOptionPane.showMessageDialog(null, "Counselor Updated Successfully");
        } 
        catch (SQLException ex) 
        {
             JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
    
    //Creating the deletebtns for the forms
    
    public int DeleteFB(String student, String rating, String comments)
    {
        String query = "DELETE FROM FeedBackTB WHERE student= ? AND rating = ? AND comments= ?";
        int rowsAffected=0;
        try(PreparedStatement pst = con.prepareStatement(query))
           {
            pst.setString(1,student);
            pst.setInt(2,Integer.parseInt(rating));
            pst.setString(3,comments);
            rowsAffected= pst.executeUpdate();
            pst.close();  
           }catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
           } 
         return rowsAffected; 
    }  
    
    public void deleteAppointment(String studentName) {
        try {
            String query = "DELETE FROM AppointmentsTB WHERE studentName = '" + studentName + "'";
            this.con.createStatement().execute(query);
             JOptionPane.showMessageDialog(null, "Appointment deleted!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to delete Appointment!");
        }
    }

    public void removeCounselor(String counselor) 
    {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Delete Counselor?",
            "Confirm!",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            try 
            {
                String query = "DELETE FROM Counselors WHERE Counselor=?";
                PreparedStatement ps = this.con.prepareStatement(query);
                ps.setString(1, counselor);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Counselor removed!");
            } 
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }    
}
    
  

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prg381milestone2;
import static prg381milestone2.MainFrame.DB;

/**
 *
 * @author Cash
 */
public class PRG381Milestone2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new MainFrame().setVisible(true);
         try
            {
                DB.connect();
                //DB.createFeedbackTable();
            }catch(ClassNotFoundException ex){
                ex.printStackTrace();
            }
    }
    
}

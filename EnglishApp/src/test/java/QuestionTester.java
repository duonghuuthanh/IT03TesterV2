
import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.JdbcUtils;
import com.dht.services.QuestionService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.junit.jupiter.api.*;
import java.sql.*;

/**
 *
 * @author admin
 */
public class QuestionTester {
    // Q1 --> content != null
    // Q2 --> category != null & category dung trong dsd dang co
    // Q3 --> co dung 4 phuong an, chi 1 phuong an dung
    private static Connection conn ;
    private static QuestionService s;
    
//    static {
//        try {
//            conn = JdbcUtils.getConn();
//        } catch (SQLException ex) {
//            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s = new QuestionService();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void testAddSuccessful() {
        Question q = new Question("ABC", 1);
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice("A", true, q.getId()));
        choices.add(new Choice("B", false, q.getId()));
        choices.add(new Choice("C", false, q.getId()));
        choices.add(new Choice("D", false, q.getId()));
        
        QuestionService s = new QuestionService();
        try {
            boolean actual = s.addQuestion(q, choices);
            Assertions.assertTrue(actual);
            
            PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE id=?");
            stm.setString(1, q.getId());
            
            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals("ABC", rs.getString("name"));
            Assertions.assertEquals(1, rs.getInt("category_id"));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testSearch() {
        try {
            List<Question> questions = s.getQuestions("you");
            Assertions.assertEquals(3, questions.size());
            for (Question q: questions)
                Assertions.assertTrue(q.getContent().toLowerCase().contains("you"));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @Test
    public void testDelete() {
        String id = "62c27e6f-fef3-4bb8-8ea4-46dba746e37b";
        // backup database
        // fixtures
        try {
            boolean actual = s.deleteQuestion(id);
            Assertions.assertTrue(actual);
            
            PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE id=?");
            stm.setString(1, id);
            
            ResultSet rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
            
            stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
            stm.setString(1, id);
            rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

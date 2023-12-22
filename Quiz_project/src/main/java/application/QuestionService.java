package application;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface QuestionService {

    // connnection


    int addQ(Question que);
    int updtaeQ(String que,int id);
    int removeQ (int id);
    List<Question> displayAll();
    List<Question> getQuestionById(int qid);

    int updtaeOp(Question q);

    void clossconn() throws SQLException;

    boolean chekPass(String pass);
}

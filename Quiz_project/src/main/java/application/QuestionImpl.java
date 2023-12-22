package application;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionImpl implements QuestionService{
    static  Connection conn;
    static {
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/questiondb","root","tiger");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Override
    public int addQ(Question q) {
        int n=0;
        String quryadd="insert into question_info (question,option1,option2,option3,answer) values(?,?,?,?,?)";
        try {
            PreparedStatement prst=conn.prepareStatement(quryadd);
            prst.setString(1,q.getQue());
            prst.setString(2,q.getOption1());
            prst.setString(3,q.getOption2());
            prst.setString(4,q.getOption3());
            prst.setString(5,q.getAns());
             n= prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return n;

    }

    @Override
    public int updtaeQ(String que,int id) {
        String updateQ="update question_info set question=? where question_id=?";
        try {
            PreparedStatement pstmt= conn.prepareStatement(updateQ);
            pstmt.setString(1,que);
            pstmt.setInt(2,id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int removeQ(int id) {
        int n=0;
        String qDelet="delete from question_info where question_id=?";
        try {
            PreparedStatement prst= conn.prepareStatement(qDelet);
            prst.setInt(1,id);
            n= prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return n;
    }

    @Override
    public List<Question> displayAll() {
       String displayAllQ="Select * from question_info";
        List<Question> q= new ArrayList<>();
        try {
          Statement  stmt = conn.createStatement();
           ResultSet rs= stmt.executeQuery(displayAllQ);
            while (rs.next()){
                Question all=new Question(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                q.add(all);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return q;

    }

    @Override
    public List<Question> getQuestionById(int qid) {
        String Query="select * from question_info where  question_id=?";
        List<Question> p=new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(Query)) {
            pstmt.setInt(1,qid);

            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                Question q= new Question(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                p.add(q);
            }

        }catch (SQLException e)
        {
            System.out.println(e);

        }
        return p;
    }

    @Override
    public int updtaeOp(Question q) {
        String QuaryUpdateOp="update question_info set option1=?, option2=?, option3=?, answer=? where question_id=?";
        int n=0;
        try {
            PreparedStatement pstmt= conn.prepareStatement(QuaryUpdateOp);
            pstmt.setString(1,q.getOption1());
            pstmt.setString(2,q.getOption2());
            pstmt.setString(3,q.getOption3());
            pstmt.setString(4,q.getAns());
            pstmt.setInt(5,q.getUid());
             n=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return n;
    }

    public void clossconn() throws SQLException {
        conn.close();
    }

    @Override
    public boolean chekPass(String pass) {
//        List<Question> passa= new ArrayList<>();
        String quary="select pass from main_pass";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(quary);
            rs.next();
            String acpass= rs.getString(1);
            if (pass.equals(acpass)){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return false;
    }
}

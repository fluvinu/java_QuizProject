package application;

public class Question {
    private String Que;

    private int Uid;
    private String option1;
    private String option2;
    private String option3;
    private String ans;

    public Question(String que, String option1, String option2, String option3, String ans) {
        Que = que;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.ans = ans;
    }

    public Question(int uid, String que,  String option1, String option2, String option3, String ans) {
        Que = que;
        Uid = uid;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.ans = ans;
    }

    public Question(String option1, String option2, String option3, String ans ,int uid) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.ans = ans;
        Uid=uid;
    }

    public int getUid(){
        return Uid;
    }
    public String getQue() {
        return Que;
    }

    public void setQue(String que) {
        Que = que;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}

package application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static Scanner sc= new Scanner(System.in);
    static QuestionService service=new QuestionImpl();
    public static void main(String[] args)  {
        System.out.println("1 add question");
        System.out.println("2 remove question ");
        System.out.println("3 update qutio ");
        System.out.println("4 diplay all question");
        System.out.println("5 take test ");
        System.out.println("6 exit");

        int ch= sc.nextInt();

        if(ch<5){
            System.out.println("enter pasword");
            String pass=sc.next();
            boolean s=service.chekPass(pass);
            if(!s){
                System.out.println("wrong password");
                System.exit(0);
            }
        }

        switch (ch){
            case 1:
                 addQuestion();
                break;
            case 2:
                 removeQuestion();
                break;
            case 3:
                updateQuetion();
                break;
            case 4:
                displayAllQuestion();
                break;
            case 5:
                 takeTest();
                break;
            case 6:
                System.out.println("conntion is cloosing");
                try {
                    service.clossconn();
                } catch (SQLException e) {
                    System.out.println(e);
                    return;
                }
                System.exit(0);

        }



        main(args);
    }
   public static void addQuestion(){
       System.out.println("enter the quetion");
       String ques= sc.nextLine();
       ques=sc.nextLine();
       System.out.println("enter option 1 dont use space");
       String op1=sc.nextLine();
       System.out.println("enter option 2 dont use space");
       String op2=sc.nextLine();
       System.out.println("enter option 3 dont use space");
       String op3=sc.nextLine();
       System.out.println("which option is corect");
       String ans=sc.nextLine();


       Question q=new Question(ques,op1,op2,op3,ans);
        int n =service.addQ(q);
       System.out.println(n+" row added");
   }
   public static void removeQuestion(){
       System.out.println("enter question id");
       int id = sc.nextInt();
       int n =service.removeQ(id);
       System.out.println(n+"  row deleted");
   }

   public static void updateQuetion(){
       System.out.println("1 modify question ");
       System.out.println("2 modify answer");
       System.out.println("3 back");

       int ch= sc.nextInt();
       switch (ch){
           case 1:
                modifyQ();
               break;
           case 2:
                modifyOp();
               break;
           case 3:
               return;
           default:
               System.out.println("somtig wrong in updaet method");
       }
       updateQuetion();
   }

   public static void modifyQ(){
       System.out.println("enterr qid");
       int qid= sc.nextInt();
       System.out.println("enter question");
       String question= sc.nextLine();
       question= sc.nextLine();
      int n=  service.updtaeQ(question,qid);
       System.out.println(n+" qution updated");
   }

   public static void modifyOp(){
       System.out.println("enter question id");
       int qid= sc.nextInt();
       List<Question> que=service.getQuestionById(qid);

       for(Question q:que){
           System.out.println(q.getUid()+" "+q.getQue()+" "+q.getOption1()+" "+q.getOption2()+" "+q.getOption3()+" "+q.getAns());

       }
       System.out.println("enter modify option one");
       String uop1=sc.nextLine();
       uop1=sc.nextLine();
       System.out.println("enter modify option 2");
       String uop2=sc.nextLine();
       System.out.println("enter modify option 3");
       String uop3= sc.nextLine();
       System.out.println("enter modify ans ");
       String uans=sc.nextLine();
       Question Uop=new Question(uop1,uop2,uop3,uans,qid);
       int n =service.updtaeOp(Uop);
       System.out.println(n+" row updated");
   }

   public static void displayAllQuestion(){
       List<Question> alQ= service.displayAll();
       for (Question q:alQ){
           System.out.println("Q "+q.getUid()+". "+q.getQue());
           System.out.println(" 1 "+q.getOption1());
           System.out.println(" 2 "+q.getOption2());
           System.out.println(" 3 "+q.getOption3());
           System.out.println("----->"+q.getAns());

           System.out.println("\n=================================================\n");
       }
   }
   public static void takeTest(){
        List<Question> queTest=service.displayAll();
        int marks=0;
       System.out.println("ready for test ");
       String ans=sc.nextLine();
       for(Question q:queTest){
           System.out.println("Q "+q.getUid()+". "+q.getQue());
           System.out.println(" 1 "+q.getOption1());
           System.out.println(" 2 "+q.getOption2());
           System.out.println(" 3 "+q.getOption3());
           ans= sc.nextLine();
           String actualans=q.getAns();
           if (ans.equals(actualans)){
               marks=marks+5;
           }else {
               marks=marks-2;
           }
           System.out.println("===========================");
       }

       System.out.println("\n\n++++++++++++++++++++++++");
       System.out.println("yor total mark si  "+marks);

       if (marks>20){
           System.out.println("status passed");
       }else {
           System.out.println("you failed");
       }
       System.exit(0);
   }
}

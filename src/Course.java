//Name: Zheng Chuanyang ID:student 17251311
//course need course code,name,time
//need some methods.
//Chang String time to Real time
//some get methods
// deal with time is not incorrect
import java.util.Scanner;
import java.io.*;
public class Course {
    private String couseName;
    private String t1;
    private String t2;
    private String t3;
    private String fullName;
    private boolean hasTheCourse=false;
    public static void main(String[] args)
    {

    }
    public Course(String couseName,String[] courseInformation)
    {

           for(int i=0;i<courseInformation.length;i++) {
               if (getString(courseInformation[i], 1).equals(couseName)) {
                   hasTheCourse = true;
                   Scanner inx = new Scanner(courseInformation[i]);
                   this.couseName = inx.next();//get codename
                   t1 = inx.next();//T1
                   t2 = inx.next();//T2
                   t3 = inx.next();//T3
                   fullName = inx.nextLine();
                   ;
                   break;
               }
           }

           if(hasTheCourse==false)
               System.out.println("Do not find "+couseName);


    }

    public String getCouseName()
    {
        return couseName;
    }

    public String getT1()
    {
        return t1;
    }

    public String getT2()
    {
        return t2;
    }

    public String getT3()
    {
        return t3;
    }

    public String getFullName()
    {
        return fullName;
    }
    //return int
    //give time,such as M11
    public int couseWeek(String coursetime)
    {
        switch (coursetime.charAt(0))
        {
            case 'M':
                return 0;
            case 'T':
                return 1;
            case 'W':
                return 2;

            case 'R':
                return 3;
            case 'F':
                return 4;
        }

        System.out.println(coursetime+": "+coursetime.charAt(0)+" is incorrect. The computer set the day as Monday");
        return 0;
    }

    //return int
    // give time such as M11,M12,T12,W12,R13,F15
    //parase last two char into Int
    public int couseTime(String coursetime)
    {
       String immediateString="";
       immediateString=immediateString+coursetime.charAt(1)+coursetime.charAt(2);//get time
       int immediateInt=Integer.parseInt(immediateString);// get int
        if(immediateInt<8 || immediateInt>17)
        {
            System.out.println(couseName+": "+immediateInt+" is incorrect. The computer set it as 8");
            immediateInt=8;
        }
       return immediateInt-8;//return time
    }

    public String toString()
    {
        return this.couseName+"["+this.getT1()+", "+this.t2+", "+this.t3+"] "+this.fullName;
    }




    //give a part of string
    // String Target
    String getString(String s,int target)
    {
        int count=0;//count
        boolean judgePrevious=true;//for i =0
        String word="";//will return this one
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==' ')
                judgePrevious=true;
            else
            {
                if(judgePrevious)
                {
                    count++;
                    judgePrevious=false;
                }
                if(count==target)
                    word=word+s.charAt(i);
            }
        }

        return word;

    }


    //give String
    //return int
    int getStringNumber(String s)
    {

        int count=0;//count
        boolean judgePrevious=true;//for i =0
        String word="";//will return this one
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==' ')
                judgePrevious=true;
            else
            {
                if(judgePrevious)
                {
                    count++;
                    judgePrevious=false;
                }
            }
        }

        return count;

    }

    public boolean getJudgeTheCourse()
    {
        return hasTheCourse;
    }

}

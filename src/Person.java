//Name:Zheng Chuanyang ID:1751311
//First,a Person need Name,ID,Gender,maxLoad,nowCourse,Titable
//Then, build a constructor. This constructor need has the Peron Information
//There should be some method. enroll, unenroll,,print timetable

import sun.plugin.dom.core.CoreConstants;

import java.awt.*;
import java.util.Scanner;
import java.io.*;
public class Person {
    private String[][] timeTable=new String[10][5];//time table
    private String[] information=new String[3];// student information
    private String[] nowCourse={};//now course have
    private String gender;//gender
    private String maxLoad;
    private String name;
    private String ID;
    private String inputCouse="Courses.db";
    private String inputInformation="Students.db";
    private String outputName="Enrollments.db";

    public static void main(String[] args) throws Exception
    {
        new Person().runApp();
    }

    Person (){}

    void runApp() throws Exception
    {

//
//        p.unenrollCourse("comp105");
//        p.printTimeTable();
//        String x="THis is my dog";
//        String x2=getString(x,4);
//                System.out.println(x2);
    }

    public Person(String studentIformation) {
//        this.ID = ID;
//        this.inputInformation=inputInformation;
//        this.outputName=outputName;
//        for(int i=0;i<this.timeTable.length;i++)
//        {
//            for(int j=0;j<this.timeTable[i].length;j++)
//                this.timeTable[i][j]="";
//        }
//        System.out.println(timeTable[0][0]);
//        File inputFileStudent = new File(inputInformation);
//        try {
//            Scanner inStudent = new Scanner(inputFileStudent);// input Student File
////            int count = 0;
////            String line;
//            while (inStudent.hasNext()) {
//                if (inStudent.next().equals(this.ID)) {
//                    information[0]=inStudent.next();
//                    gender=information[0];
//                    information[1]=inStudent.next();
//                    maxLoad=information[1];
//                    information[2]=inStudent.nextLine();// give student information
//                    name= information[2];
//                    if(information[2].equals(""))
//                        System.out.println("Load "+this.name+" wrong");
//                    break;
//                }
//            }


//            File inputFileCourse=new File(outputName);//see course enrolled
//            try{
//                Scanner inCourse=new Scanner(inputFileCourse);//input Course
//                while(inCourse.hasNext())
//                {
//                    String immediateID=inCourse.next();//get student number;
//                    if(immediateID.equals(ID))//it is ID
//                    {
//                        String immediateCourse=inCourse.next();// get course
//                        for(int i=0;i<nowCourse.length;i++)
//                        {
//
//                            if(!(immediateCourse.equals(nowCourse[i])))
//                                nowCourse=addString(nowCourse,immediateCourse);
//                        }
//                        if(nowCourse.length==0)// at begin
//                        {
//                            nowCourse = addString(nowCourse, immediateCourse);
//                            Course immediateCourseObject=new Course(immediateCourse);
//                            timeTable[immediateCourseObject.couseTime(immediateCourseObject.getT1())][immediateCourseObject.couseWeek(immediateCourseObject.getT1())]=immediateCourse;
//                            timeTable[immediateCourseObject.couseTime(immediateCourseObject.getT2())][immediateCourseObject.couseWeek(immediateCourseObject.getT2())]=immediateCourse;
//                            timeTable[immediateCourseObject.couseTime(immediateCourseObject.getT3())][immediateCourseObject.couseWeek(immediateCourseObject.getT3())]=immediateCourse;
//                        }
//
//                    }
//
//                    String dump=inCourse.nextLine();// go to next Line
//
//                }
//            }
//            catch (FileNotFoundException e)
//            {
//                System.out.println("Do not Find "+outputName);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Do not Find " + inputInformation);
//            ;
//        }

        this.ID=getString(studentIformation,1);//get ID
        this.gender=getString(studentIformation,2);//get gender


        this.maxLoad=getString(studentIformation,3);//get maxload
        Scanner in=new Scanner(studentIformation);

        String dump=in.next();// no id
        dump=in.next();//no gender
        dump=in.next();// no maxload
        this.name=in.nextLine();// name
        if(gender.compareToIgnoreCase("M")!=0&&gender.compareToIgnoreCase("F")!=0)
            System.out.println("Please check the gender of "+this.name+"("+this.ID+")");
        for(int i=0;i<timeTable.length;i++)
        {
            for(int j=0;j<timeTable[j].length;j++)
                timeTable[i][j]="";// giva value
        }

    }


    //return gender
    public String getGender()
    {
        return gender;
    }

    //return maxload
    public String getMaxLoad()
    {
        return maxLoad;
    }

    //return name
    public String getName()
    {
        return name;
    }

    //return ID
    public String getID()
    {
        return ID;
    }

    //return timeTable;
    public String[][] getTimeTable()
    {
        return timeTable;
    }

    //return now Course
    public String[] getNowCourse()
    {
        return nowCourse;
    }

    //return nothing
    //give timetable nowcourse,added course
    public boolean enrollCourse(String course,String[] coursesInformation) throws Exception {
        boolean judgeEnrollment=true;
        if (nowCourse.length + 1 <= Integer.parseInt(maxLoad)) {//if the student can do more
            Course addTheCourse = new Course(course,coursesInformation);
            int t1row = addTheCourse.couseTime(addTheCourse.getT1());
            int t1col = addTheCourse.couseWeek(addTheCourse.getT1());
            int t2row = addTheCourse.couseTime(addTheCourse.getT2());
            int t2col = addTheCourse.couseWeek(addTheCourse.getT2());
            int t3row = addTheCourse.couseTime(addTheCourse.getT3());
            int t3col = addTheCourse.couseWeek(addTheCourse.getT3());//get the course time

            if (this.timeTable[t1row][t1col].equals("") && this.timeTable[t2row][t2col].equals("") && this.timeTable[t3row][t3col].equals(""))// there are empty
            {
                this.nowCourse = addString(this.nowCourse, course);//add the course,add the length
                this.timeTable[t1row][t1col]=course;
                this.timeTable[t2row][t2col]=course;
                this.timeTable[t3row][t3col]=course;// timetable over
            }
            else {
                //else we find which one is occupy the stance
                judgeEnrollment=false;
                System.out.println("Fail to add course");// say we can not do that
                if (!(timeTable[t1row][t1col].equals(""))) {
                    System.out.println(this.name+" ("+this.ID+") Fail to add " + course + " because it is conflict with " + timeTable[t1row][t1col]);//say the course we have now
                }
                if (!(timeTable[t2row][t2col].equals(""))) {
                    System.out.println(this.name+" ("+this.ID+") Fail to add " + course + " because it is conflict with " + timeTable[t2row][t2col]);
                }
                if (!(timeTable[t3row][t3col].equals(""))) {
                    System.out.println(this.name+" ("+this.ID+") Fail to add " + course + " because it is conflict with " + timeTable[t3row][t3col]);
                }
            }

        }
        else {
            System.out.println(this.name + " (" + this.ID + ") Can not register more");//maxload
            judgeEnrollment = false;
        }

        return judgeEnrollment;//can we enroll ?
    }

   //delete a course
    //give course, courseInformation
    //find the course ,delete it
    public void unenrollCourse(String course,String[] courseInformation) throws Exception
    {
//        boolean noDouble=true;// have the course or not
//        for(int i=0;i<nowCourse.length;i++) {
//            if (nowCourse[i].equals(course)) {
//                System.out.println("Already have the " + course);
//                noDouble = false;//do not have the course
//            }
//        }
//        if(noDouble) {
            String[] immediateString ={};//store all information in this person

                this.timeTable = deleteTimeTable(this.timeTable, course,courseInformation);// no this course
                this.nowCourse = deleteString(this.nowCourse, course);
                for(int i=0;i<courseInformation.length;i++){
                    String deleteTimeTableString = courseInformation[i];//no this course
                    if (getString(deleteTimeTableString, 1).equals(this.ID) && getString(deleteTimeTableString, 2).equals(course)) {
                        //do nothing
                    } else
                        immediateString = addString(immediateString, deleteTimeTableString);//store data
                }


    }

    // print all course by time
    public void printTimeTable()
    {
        System.out.print(" ");
        System.out.printf("%4s%3s ","","M");
        System.out.printf("%4s%3s ","","T");
        System.out.printf("%4s%3s ","","W");
        System.out.printf("%4s%3s ","","R");
        System.out.printf("%4s%3s ","","F");//print
        System.out.println();
        for(int x=0;x<40;x++)
        {
            if(x==0) {
                for (int c = 0; c < 3; c++)
                    System.out.print(" ");
            }
            System.out.print("_");
        }
        System.out.println();
        for(int i=0;i<this.timeTable.length;i++)//for week
        {
            System.out.printf("%3d",i+8);//print time
            for(int j=0;j<this.timeTable[i].length;j++)
            {
                if(this.timeTable[i][j]==null)
                    System.out.printf("|%7"," ");
                else
                    System.out.printf("|%7s",this.timeTable[i][j]);
            }
            System.out.print("| "+(i+8));//print time
            System.out.println();//next line
            System.out.println();
            for(int x=0;x<40;x++)
            {
                if(x==0) {
                    for (int c = 0; c < 3; c++)
                        System.out.print(" ");
                }
                System.out.print("_");
            }
            System.out.println();
        }
    }

    // String s, new course
    // return String
    // add a course
    public String[] addString(String []s,String course)
    {
        String[] news=new String[s.length+1];
        for(int i=0;i<s.length;i++)
        {
            news[i]=s[i];//give value
        }

        news[news.length-1]=course;//add new

        return news;

    }

    //give a part of string
    // String Target
    //Give String[], String course
    //delete course. create a new course[], give all old array value to it excluding deleted course
    //return String[]
    public String[] deleteString(String[] s,String course)
    {
        String[] news=new String[s.length-1];//new String[]
        int count=0;//count
        for(int i=0;i<s.length;i++)
        {
            boolean judge=true;//judge
            if(getStringNumber(s[i])!=getStringNumber(course))// the number of sub string is not equal
                judge=false;
            for(int j=0;j<getStringNumber(s[i]);j++)
            {
                if(getString(s[i],j).compareToIgnoreCase(getString(course,j))!=0)// sub string is not equal
                    judge=false;//judge false
            }
            if(judge) {
                news[count] = s[i];//give string
                count++;//count+1
            }
        }
        return news;
    }

    public String[][] deleteTimeTable(String[][] timeTable,String course,String[]courseInformation)
    {
        String[][] newTimeTable=new String[10][5];
        Course deleteTheCourse = new Course(course,courseInformation);
        int t1row = deleteTheCourse.couseTime(deleteTheCourse.getT1());
        int t1col = deleteTheCourse.couseWeek(deleteTheCourse.getT1());
        int t2row = deleteTheCourse.couseTime(deleteTheCourse.getT2());
        int t2col = deleteTheCourse.couseWeek(deleteTheCourse.getT2());
        int t3row = deleteTheCourse.couseTime(deleteTheCourse.getT3());
        int t3col = deleteTheCourse.couseWeek(deleteTheCourse.getT3());//get time
        for(int i=0;i<newTimeTable.length;i++)
        {
            for(int j=0;j<timeTable[i].length;j++)
            {
                if((i==t1row&&j==t1col) || (i==t2row&&j==t2col) || (i==t3row&&j==t3col))
                {
                    newTimeTable[i][j]="";//give value
                }
                else
                {
                    newTimeTable[i][j]=timeTable[i][j];
                }
            }
        }

        return newTimeTable;

    }



    //Give String
    //Get how many sub String of a String. just count one byone
    //Return int
    int getStringNumber(String s)
    {

        int count=0;//count
        boolean judgePrevious=true;//for i =0
        String word="";//will return this one
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==' ')
                judgePrevious=true;//last one is " "
            else
            {
                if(judgePrevious)//last one is " "
                {
                    count++;//count++
                    judgePrevious=false;//last one is not " "
                }
            }
        }

        return count;// how many sub string

    }

    //give a targeted part of string
    // String Target
    //return String
    String getString(String s,int target)
    {
        int count=0;//count
        boolean judgePrevious=true;//for i =0
        String word="";//will return this one
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==' ')
                judgePrevious=true;//previous one is " "
            else
            {
                if(judgePrevious)//previous is " "
                {
                    count++;//count+1
                    judgePrevious=false;
                }
                if(count==target)//get target
                    word=word+s.charAt(i);
            }
        }

        return word;

    }





}

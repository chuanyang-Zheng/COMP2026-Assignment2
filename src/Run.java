//Name:Zheng Chuanyang ID:17251311
//should have student[],instructor[],course[],assignment[],enrollment[],courseInformation[]
//first,determine it is load,save,enroll,unenroll,assign,unassign,quit,status,list
//load (save): it is course ? student? instructor? assignment ? enrollment? What is the file
//enroll(assign):find the person, add course
//unenroll(unassign): find the person, delete course
//status: find the course, give status of the course
//list : it is course? student? instructor? list all
//quit: quit the program
import java.util.Scanner;
import java.io.*;
public class Run {
    Person[] studentPersonArray={};//student
    Person[] instructorPersonArray={};//instrutor
    Course[] courseArray={};//course Array
    String[] assignmentArray={};//assigment Array
    String[] enrollmentsArray={};//enrollment Array
    String[] courseInformation={};//courseInformation Array
    String[] commandArray={};//record command from command file
    String inputCommand;// inputcommand

    //do nothong
    boolean updateStudent1=false;
    boolean updateStudent2=false;
    boolean updateStudent3=false;
    boolean updateInstructor1=false;
    boolean updateInstructor2=false;
    boolean updateInstructor3=false;
    //do nothing
    public static void main(String[] args) throws Exception
    {
        new Run().rumApp(args);
    }

    Run(){}

    void rumApp(String[] args) throws Exception {


//        System.out.println(test);
        boolean quit = false;
        if (args.length > 0) {
            File commandFile = new File(args[0]);
            if (commandFile.exists()) {
                Scanner commandInstruction = new Scanner(commandFile);
                while (quit == false && commandInstruction.hasNextLine()) {//can scanner
                    String cmdLine = commandInstruction.nextLine();//command
                    if (processCmd(cmdLine) == false) {
                        quit = true;
                    }
//                    commandArray = addString(commandArray, commandInstruction.nextLine());
                }
            } else
                System.out.println("Do not find " + args[0]);
        }

        if (quit == false) {
            Scanner in = new Scanner(System.in);

            int countCommand = 0;
            while (true) {

                    inputCommand = in.nextLine();
                    if (processCmd(inputCommand) == false) {//quit
                        break;
                    }
                }
            }
        }


    public boolean processCmd(String inputCommand) throws Exception {
        boolean result = true;
        try {
            if (getString(inputCommand, 1).equals("load") && getStringNumber(inputCommand) == 3) {
                if (getString(inputCommand, 2).equals("courses")) {
                    int count=0;
                    courseInformation = new String[0];//new String Array
                    courseArray = new Course[0];//new course Array
                    File inputCoursesFile = new File(getString(inputCommand, 3));
                    try {
                        Scanner inputCourseFileScanner = new Scanner(inputCoursesFile);//input
                        while (inputCourseFileScanner.hasNextLine()) {
                            String immediate = inputCourseFileScanner.nextLine();//get courseInformation
                            if (getStringNumber(immediate) >= 5) {
                                courseInformation = addString(courseInformation, immediate);
                                count++;
                            }
                            else
                                System.out.println("Incorrect Format： \""+immediate+"\"");
                        }
                        for (int i = 0; i < courseInformation.length; i++) {
                            Course loadCourse = new Course(getString(courseInformation[i], 1), courseInformation);
                            courseArray = addCourseArray(courseArray, loadCourse);//add course
                        }
                        System.out.println(count+" "+getString(inputCommand,2)+"  records sucessfully from "+"\""+getString(inputCommand,3)+"\"");
                        updateStudent3 = true;
                        updateInstructor3 = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Fail to open " + getString(inputCommand, 3));
                    }
                } else if (getString(inputCommand, 2).equals("students")) {
                    studentPersonArray = new Person[0];//new student
                    File inputStudentFile = new File(getString(inputCommand, 3));//
                    try {
                        int count=0;
                        Scanner inputStudentScanner = new Scanner(inputStudentFile);//scanner
                        while (inputStudentScanner.hasNextLine()) {
                            String immediate = inputStudentScanner.nextLine();
                            if (getStringNumber(immediate) >= 4) {//input Ok
                                Person immediatePerson = new Person(immediate);//creat person
                                studentPersonArray = addPerson(studentPersonArray, immediatePerson);//add peorson
                                count++;
                                if (getString(immediate, 1).length() != 5)//ID check
                                    System.out.println("Please check the ID length of " + getString(immediate, 1) + " " + getString(immediate, 1));
                            } else
                                System.out.println("Incorrect Format： \""+immediate+"\"");
                        }
                        System.out.println(count+" "+getString(inputCommand,2)+"  records sucessfully from "+"\""+getString(inputCommand,3)+"\"");
                        updateStudent2 = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Fail to open " + getString(inputCommand, 3));
                    }
                } else if (getString(inputCommand, 2).equals("instructors")) {//check
                    int count=0;
                    instructorPersonArray = new Person[0];//new instructor
                    File inputInstructorFile = new File(getString(inputCommand, 3));
                    try {
                        Scanner inputStudentScanner = new Scanner(inputInstructorFile);
                        while (inputStudentScanner.hasNextLine()) {//check
                            String immediate = inputStudentScanner.nextLine();//get instructors
                            if (getStringNumber(immediate) >= 4) {//check input format
                                Person immediatePerson = new Person(immediate);
                                instructorPersonArray = addPerson(instructorPersonArray, immediatePerson);//add instructor
                                count++;
                                if (getString(immediate, 1).length() != 4)//check ID
                                    System.out.println("Please check the ID length of " + getString(immediate, 1) + " " + getString(immediate, 2));
                            }
                        }
                        System.out.println(count+" "+getString(inputCommand,2)+"  records sucessfully from "+"\""+getString(inputCommand,3)+"\"");
                        updateInstructor2 = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Fail to open " + getString(inputCommand, 3));
                    }
                } else if (getString(inputCommand, 2).equals("enrollments")) {
                    enrollmentsArray = new String[0];//new enrollments Array
                    File inputEnrollmentFile = new File(getString(inputCommand, 3));
                    try {
                        Scanner loadScanner = new Scanner(inputEnrollmentFile);
                        while (loadScanner.hasNextLine()) {
                            String immediate = loadScanner.nextLine();
                            if (getStringNumber(immediate) == 2) {//check format
                                enrollmentsArray = addString(enrollmentsArray, immediate);//enroll
                            } else
                                System.out.println("Incorrect Format： \""+immediate+"\"");
//                            else
//                                System.out.println("Invalid Input");
                        }
                        int count = 0;//how many load sucessful
                        for (int j = 0; j < enrollmentsArray.length; j++) {
                            boolean find = false;//no find now
                            for (int i = 0; i < studentPersonArray.length; i++) {
                                String immediateID = studentPersonArray[i].getID();
                                if (studentPersonArray[i] != null && studentPersonArray[i].getID().equals(getString(enrollmentsArray[j], 1))) {
                                    if (studentPersonArray[i].enrollCourse(getString(enrollmentsArray[j], 2), courseInformation)) {
                                        count = count + 1;//one load sucessfully, count+1
                                    }
                                    find = true;// find this one
                                }
                            }
                            if (find == false)//do not find this student
                                System.out.println("Do not find student " + getString(enrollmentsArray[j], 1));
                        }

                        System.out.println(count + " " + getString(inputCommand, 2) + "  records sucessfully from " + "\"" + getString(inputCommand, 3) + "\"");
                        enrollmentsArray = new String[0];//new enrollmentArray
                        for (int i = 0; i < studentPersonArray.length; i++) {
                            String[] immediate = studentPersonArray[i].getNowCourse();//now Course
                            for (int j = 0; j < immediate.length; j++) {
                                String x = returnString(studentPersonArray[i].getID(), immediate[j]);
                                enrollmentsArray = addString(enrollmentsArray, x);// add this enrollment
                            }
                        }
                        updateStudent1 = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Fail to open " + getString(inputCommand, 3));
                    }
                } else if (getString(inputCommand, 2).equals("assignments")) {// it is assignment
                    assignmentArray = new String[0];//new assignment
                    File inputAssignmentFile = new File(getString(inputCommand, 3));
                    try {
                        Scanner loadScanner = new Scanner(inputAssignmentFile);
                        while (loadScanner.hasNextLine()) {
                            String immediate = loadScanner.nextLine();//get assignments information
                            if (getStringNumber(immediate) == 2) {// check format
                                assignmentArray = addString(assignmentArray, immediate);
                            } else
                                System.out.println("Incorrect Format： \""+immediate+"\"");
                        }

                        int count = 0;//count how many load sucessfully
                        for (int j = 0; j < assignmentArray.length; j++) {
                            boolean find = false;// not find now
                            for (int i = 0; i < instructorPersonArray.length; i++) {
                                String immediateID = instructorPersonArray[i].getID();// get ID
                                if (instructorPersonArray[i] != null && instructorPersonArray[i].getID().equals(getString(assignmentArray[j], 1))) {
                                    if (instructorPersonArray[i].enrollCourse(getString(assignmentArray[j], 2), courseInformation)) {
                                    }
                                    find = true;// find this instructor
                                    count++;// count+1

                                }
                            }
                            if (find == false)// do not this instructor
                                System.out.println("Do not find instructor " + getString(assignmentArray[j], 1));
                        }


                        System.out.println(count + " " + getString(inputCommand, 2) + "  records sucessfully from " + "\"" + getString(inputCommand, 3) + "\"");
                        assignmentArray = new String[0];// new assignment Array
                        for (int i = 0; i < instructorPersonArray.length; i++) {
                            String[] immediate = instructorPersonArray[i].getNowCourse();//get course
                            for (int j = 0; j < immediate.length; j++) {
                                String x = returnString(instructorPersonArray[i].getID(), immediate[j]);
                                assignmentArray = addString(assignmentArray, x);//give assignment to Assignment arry
                            }
                        }


                        updateInstructor1 = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Fail to open " + getString(inputCommand, 3));
                    }
                } else
                    System.out.println("Invalid Input");
            } else if (getString(inputCommand, 1).equals("save") && getStringNumber(inputCommand) == 3) {
                try {
                    PrintWriter out = new PrintWriter(getString(inputCommand, 3));//determine print to which file
                    if (getString(inputCommand, 2).equals("courses")) {

                        int count = 0;// how many records
                        for (int i = 0; i < courseArray.length; i++) {
                            out.println(courseArray[i].getCouseName() + " " + courseArray[i].getT1() + " " + courseArray[i].getT2() + " " + courseArray[i].getT3() + " " + courseArray[i].getFullName());// print all course
                            System.out.println("Saving: " + courseArray[i]);//tell user i am doing
                            count++;//count+1
                        }
                        ;
                        System.out.println("Saved " + count + " course records to " + getString(inputCommand, 3));//write result
                    } else if (getString(inputCommand, 2).equals("students")) {
                        int count = 0;//how many records
                        for (int i = 0; i < studentPersonArray.length; i++) {
                            out.println(studentPersonArray[i].getID() + " " + studentPersonArray[i].getGender() + " " + studentPersonArray[i].getMaxLoad() + " " + studentPersonArray[i].getName());//prit all students
                            System.out.println("Saving " + studentPersonArray[i].getName() + " " + studentPersonArray[i].getID());// I am doing
                            count++;//cout+1
                        }
                        System.out.println("Saved " + count + " student records to " + getString(inputCommand, 3));//tell result
                    } else if (getString(inputCommand, 2).equals("instructors")) {
                        int count = 0;//count how many records
                        for (int i = 0; i < instructorPersonArray.length; i++) {

                            out.println(instructorPersonArray[i].getID() + " " + instructorPersonArray[i].getGender() + " " + instructorPersonArray[i].getMaxLoad() + " " + instructorPersonArray[i].getName());//print all instructors
                            System.out.println("Saving " + instructorPersonArray[i].getName() + " " + instructorPersonArray[i].getID());// Iam doing
                            count++;                // cout+1
                        }
                        System.out.println("Saved " + count + " instructos records to " + getString(inputCommand, 3));
                    } else if (getString(inputCommand, 2).equals("enrollments")) {
                        int count = 0;//count how many redors
                        for (int i = 0; i < enrollmentsArray.length; i++) {
                            out.println(enrollmentsArray[i]);//print all enrollments
                            System.out.println("Saving " + enrollmentsArray[i]);// I am doing
                            count++;// count+1
                        }
                        System.out.println("Saved " + count + " enrollments records to " + getString(inputCommand, 3));// tell results
                    } else if (getString(inputCommand, 2).equals("assignments")) {
                        int count = 0;// count how many records
                        for (int i = 0; i < assignmentArray.length; i++) {
                            out.println(assignmentArray[i]);//print all assignment
                            System.out.println("Saving " + assignmentArray[i]);/// Doing
                            count++;//count +1
                        }

                        System.out.println("Saved " + count + " enrollments records to " + getString(inputCommand, 3));//tell result
                    } else
                        System.out.println("Invalid Input");// Wrong
                    out.close();//close out
                } catch (FileNotFoundException e) {
                    System.out.println("Fail opening " + getString(inputCommand, 3) + " for saving");
                }
            } else if (getString(inputCommand, 1).equals("list") && getStringNumber(inputCommand) == 2) {
                if (getString(inputCommand, 2).equals("courses")) {// should list course
                    for (int i = 0; i < courseArray.length; i++) {
                        System.out.println(courseArray[i].getCouseName() + " [" + courseArray[i].getT1() + " " + courseArray[i].getT2() + " " + courseArray[i].getT3() + "] " + courseArray[i].getFullName());//list all course
                    }
                } else if (getString(inputCommand, 2).equals("students")) {//list students
                    for (int i = 0; i < studentPersonArray.length; i++) {
                        System.out.println(studentPersonArray[i].getName() + "(" + studentPersonArray[i].getID() + ")");//print students
                    }
                } else if (getString(inputCommand, 2).equals("instructors")) {//should print instructors
                    for (int i = 0; i < instructorPersonArray.length; i++) {
                        System.out.println(instructorPersonArray[i].getName() + "(" + instructorPersonArray[i].getID() + ")");//print instructors
                    }
                } else
                    System.out.println("Invalid Input");// Wrong
            } else if (getString(inputCommand, 1).equals("assign") && getStringNumber(inputCommand) == 3) {//assign command
                instructorAssign(inputCommand);// assign a course
            } else if (getString(inputCommand, 1).equals("unassign") && getStringNumber(inputCommand) == 3) {
                boolean find = false;// not find this one
                for (int i = 0; i < instructorPersonArray.length; i++) {
                    String immediateID = instructorPersonArray[i].getID();
                    if (instructorPersonArray[i] != null && instructorPersonArray[i].getID().equals(getString(inputCommand, 2))) {//find this one
                        instructorPersonArray[i].unenrollCourse(getString(inputCommand, 3), courseInformation);//delete this course
                        String immediate = returnString(instructorPersonArray[i].getID(), getString(inputCommand, 3));//get Correct Format
                        assignmentArray = deleteString(assignmentArray, immediate);//add assignment
                        find = true;//find this one
                    }
                }
                if (find == false)//do not find this instructor
                    System.out.println("Do not find instructor " + getString(inputCommand, 3));
            } else if (getString(inputCommand, 1).equals("enroll") && getStringNumber(inputCommand) == 3) {// enroll and Check Command Format
                studentEnroll(inputCommand);// enroll this course
            } else if (getString(inputCommand, 1).equals("unenroll") && getStringNumber(inputCommand) == 3) {// unenroll this course
                boolean find = false;// find or not?

                for (int i = 0; i < studentPersonArray.length; i++) {
                    String immediateID = studentPersonArray[i].getID();// get ID
                    if (studentPersonArray[i] != null && studentPersonArray[i].getID().equals(getString(inputCommand, 2))) {// check this one has this ID or not
                        studentPersonArray[i].unenrollCourse(getString(inputCommand, 3), courseInformation);// unenroll
                        String immediate = returnString(studentPersonArray[i].getID(), getString(inputCommand, 3));//Get
                        enrollmentsArray = deleteString(enrollmentsArray, immediate);//add enrollment
                        find = true;
                    }
                }
                if (find == false)// do not find
                    System.out.println("Do not find student " + getString(inputCommand, 3));//say not find
            } else if (getString(inputCommand, 1).equals("status") && getStringNumber(inputCommand) == 3) {//get Command Status

                if (getString(inputCommand, 2).equals("student")) {//status student
                    statusPerson(inputCommand, studentPersonArray);//give status student
                } else if (getString(inputCommand, 2).equals("instructor")) {//status instructor
                    statusPerson(inputCommand, instructorPersonArray);//give status instructor
                } else if (getString(inputCommand, 2).equals("course")) {//status course
                    statusCourse(getString(inputCommand, 3));//give status course
                } else
                    System.out.println("Invalid Input");//Wrong
            } else if (getString(inputCommand, 1).equals("quit")) {
                result = false;//quit
                System.out.println("Goodbye!");
            } else
                System.out.println("Invalid Input");//Wrong

        }
        catch (NumberFormatException e)
        {
            System.out.println();
            System.out.println("Find data format may not correct when using coomand: "+inputCommand);
            System.out.println("Please fix the errors. Then run the program from begin.");
            System.out.println("That means: fix the errors, Load All Commands again.");
            e.printStackTrace();
        }


        System.out.println();







        return result;
    }


    //add a String to String Array
    //Give String[] ,String
    //return course
    public String[] addString(String []s,String course)
    {
        String[] news=new String[s.length+1];
        for(int i=0;i<s.length;i++)
        {
            news[i]=s[i];//add
        }

        news[news.length-1]=course;//new one

        return news;

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

    //give command
    // get how many record in a file, just count how many Line
    //return int
    void load(String inputCommand,String inputFileName) throws Exception
    {
        int count=0;
            File loadCoursesFile=new File(inputFileName);//loadCoursesFile
            if(loadCoursesFile.exists())//has this one
            {
                Scanner loadCorsesScanner=new Scanner(loadCoursesFile);//load
                while(loadCorsesScanner.hasNextLine())
                {

                    String nothing=loadCorsesScanner.nextLine();//scanner
                    count++;//count line


                }
                System.out.println(count+" "+getString(inputCommand,2)+"  records sucessfully from "+"\""+inputFileName+"\"");

            }
            else
                System.out.println("Fail to load "+inputFileName);
    }



    //get how many file Line in a FIle. Just count how many Line
    // return int
    //give a file
    int countFileLine(File inputFile) throws Exception
    {
        Scanner countLine=new Scanner(inputFile);//scanner
        int count=0;//count
        while(countLine.hasNextLine())
        {
            count++;//count+1
            String nothing=countLine.nextLine();//go to nextLine
        }
        return count;
    }



//    String statusString(String s)
//    {
//        Scanner immediate=new Scanner(s);
//    }

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


    //GIve Course[], Course
    //addCourse, create a new course, give it all old array value. add new course to new Array
    // return Course
    Course[] addCourseArray(Course[] courArray,Course newCourse)
    {
        Course[] newcourArray=new Course[courArray.length+1];//new courseArray
        for(int i=0;i<courArray.length;i++)//for loop
            newcourArray[i]=courArray[i];//give valuse
        newcourArray[newcourArray.length-1]=newCourse;//add new course
        return newcourArray;//return new Array
    }

    //GIve Course[], Person
    //addPerson,create a new Person array, give it all old array value, add new Person to it
    //return Person[]
    Person[] addPerson(Person[] personArray,Person newPerson)
    {
        Person[] newPersonArray=new Person[personArray.length+1];//new Person[]
        for(int i=0;i<personArray.length;i++)
        {
            newPersonArray[i]=personArray[i];//give value
        }

        newPersonArray[newPersonArray.length-1]=newPerson;//add new Person
        return newPersonArray;//return Person[]
    }


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

    //Give inputCommand, Person[]
    //find the person,just print the status of a Person
    //
    void statusPerson(String inputCommand,Person[] personArray)
    {
        boolean find=false;//do not find this one

            for (int i = 0; i < personArray.length; i++) {
                if(personArray[i]!=null&&personArray[i].getID().equals(getString(inputCommand,3)))// this one hsa the ID
                {
                    find=true;//tind this one
                    String[] statusImmediateNowCourse=personArray[i].getNowCourse();//give this one's courses
                    System.out.println("UID: " + personArray[i].getID());//Print UID
                    System.out.println("Name: " + personArray[i].getName());//Print Name
                    System.out.println("Gender: " + personArray[i].getGender());//Print Gender
                    System.out.println("Maxlod: " + personArray[i].getMaxLoad());//Print Maxload
                    for(int j=0;j<statusImmediateNowCourse.length;j++)
                    {
                        Course statusImmediateCourse=new Course(statusImmediateNowCourse[j],courseInformation);
                        System.out.println(statusImmediateCourse);//print each course
                    }
                    System.out.println("TimeTable: ");
                    personArray[i].printTimeTable();//print timetable
                }
            }

        if(find==false)//do not find this one
            System.out.println("Do not find "+ getString(inputCommand,3));
    }

    //Give String
    //find the course, print its information
    void statusCourse(String courseName)
    {
        int count=0;//count how many students
        for(int i=0;i<studentPersonArray.length;i++)
        {
            String[] immediate=studentPersonArray[i].getNowCourse();
            for(int j=0;j<immediate.length;j++)
            {
                if(immediate[j].equalsIgnoreCase(courseName))//this person has the course
                    count++;// count+1
            }
        }
        Course immediateCourse=new Course(courseName,courseInformation);//create new Course Object
        System.out.println("Code: "+courseName);//print course code
        System.out.println("Name: "+immediateCourse.getFullName());//print course name
        System.out.println("Timeslots: ["+immediateCourse.getT1()+", "+immediateCourse.getT2()+", "+immediateCourse.getT3()+"]");//print course time
        boolean findInstructor=false;//do not find instructor notw
        System.out.print("Instructor: ");
        for(int i=0;i<instructorPersonArray.length;i++)
        {
            String[] immediate=instructorPersonArray[i].getNowCourse();
            for(int j=0;j<immediate.length;j++)
            {
                if(immediate[j].equalsIgnoreCase(courseName))//this instructor has this course
                {
                    System.out.print(instructorPersonArray[i].getName()+" ("+instructorPersonArray[i].getID()+")");
                    findInstructor=true;//find instructor
                }
            }
        }

        if(findInstructor==false)// do not find instrutor
        {
            System.out.print("UNASSIGNED!");
        }

        System.out.println();

        System.out.println("Students ("+count+" students) ：");// how many student have this course
        for(int i=0;i<studentPersonArray.length;i++)
        {
            String[] immediate=studentPersonArray[i].getNowCourse();
            for(int j=0;j<immediate.length;j++)
            {
                if(immediate[j]!=null&&immediate[j].equalsIgnoreCase(courseName))//this student have this course
                   System.out.println(studentPersonArray[i].getName()+" ("+studentPersonArray[i].getID()+")");//print this student information
            }
        }
    }

    //Give String
    //enroll a course. Find the student, enroll the course
    public void studentEnroll(String inputCommand) throws Exception {
        boolean find = false;// find the student
        for (int i = 0; i < studentPersonArray.length; i++) {
            String immediateID = studentPersonArray[i].getID();//get this student ID
            if (studentPersonArray[i] != null && studentPersonArray[i].getID().equals(getString(inputCommand, 2))) {// check whether is this student
                if (studentPersonArray[i].enrollCourse(getString(inputCommand, 3), courseInformation)) {//Can this student enroll?
                    String x = returnString(studentPersonArray[i].getID(), getString(inputCommand, 3));//get Oorrect Format
                    enrollmentsArray = addString(enrollmentsArray, x);//add enrollment
                }
                find = true;// find this student
            }
        }
        if (find == false)// do not find this student
            System.out.println("Do not find student " + getString(inputCommand, 2));
    }



    //GIve String
    //assign a course. Find the instructor, assign the course
    public void instructorAssign(String inputCommand) throws Exception
    {
        boolean find = false;//check find the instructor or not
        for (int i = 0; i < instructorPersonArray.length; i++) {
            String immediateID = instructorPersonArray[i].getID();//get ID
            if (instructorPersonArray[i] != null && instructorPersonArray[i].getID().equals(getString(inputCommand, 2))) {// check whether is this instructor
                if (instructorPersonArray[i].enrollCourse(getString(inputCommand, 3), courseInformation)) {//check whether can assign
                    String immediate = returnString(instructorPersonArray[i].getID(),getString(inputCommand, 3));//get Correct Format
                    assignmentArray = addString(assignmentArray, immediate);//add assignment

                }

                find = true;//fin the instructor
            }
        }
            if (find == false)//do not find the instructor
                System.out.println("Do not find instructor " + getString(inputCommand, 2));
    }

    //Give String ,String
    // give correct Format. String+" "+String
    //return String
    public String returnString(String s1,String s2)
    {
        return s1+" "+s2;//give correct FOrmat
    }
}


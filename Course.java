public class Course {

    private String courseName;
    private Student[] roster;
    private Student[] waitlist;
    private int enrolled;
    private int waitlisted;

    public Course(String newCourseName, int rosterCapacity, int waitlistCapacity){
        this.courseName = newCourseName;
        this.roster = new Student[rosterCapacity];
        this.waitlist = new Student[waitlistCapacity];
    }

    public String getCourseName(){
        return courseName;
    }

    public int getRosterLength(){
        return roster.length;
    }

    public int getWaitlistLength(){
        return waitlist.length;
    }

//setters not included because arrays are fixed in size and course name shouldn't be changed by user

    public String toString(){
        return "Course name: " + courseName + "\nNumber enrolled: " + checkEnrolled() + "\nMax class size: " + roster.length +
                "\nTotal waitlisted: " + checkWaitlisted() + "\nMax waitlist: " + waitlist.length;
    }

    public int checkEnrolled(){
        enrolled = 0;
        for(int i = 0; i < roster.length; i++){
            if(roster[i] != null){
                enrolled ++;
            }
        }
        return enrolled;
    }

    public int checkWaitlisted(){
        waitlisted = 0;
        for(int i = 0; i < waitlist.length; i++){
            if(waitlist[i] != null){
                waitlisted ++;
            }
        }
        return waitlisted;
    }

    public boolean addStudent(Student student){

        if(student.isTuitionPaid()){ //is the student tuition paid
            for (Student item : roster) {
                if (item != null && item.equals(student)) { //is the student already in the class
                    return false;
                }
            }

            if(roster.length > enrolled){ //if not enrolled and there is room in class
                for(int i = 0; i < roster.length; i++){
                    if(roster[i] == null){
                        roster[i] = student; //add student and return
                        return true;
                    }
                }
            }

            if (waitlist.length > waitlisted){ //if not enrolled and roster is full
                for (Student value : waitlist) {
                    if (value != null && value.equals(student)) { //is the student already on the waitlist
                        return false;
                    }
                }

                for(int i = 0; i < waitlist.length; i++){
                    if(waitlist[i] == null){
                        waitlist[i] = student; //add student to waitlist and return
                        return true;
                    }
                }

            }

        }

        return false;

    }

}
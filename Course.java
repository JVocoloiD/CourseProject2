import java.util.Arrays;

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
        return "Course name: " + courseName +
                "\nNumber enrolled: " + checkEnrolled() + "\n" + Arrays.toString(roster) +
                "\nMax class size: " + roster.length +
                "\nNumber waitlisted: " + checkWaitlisted() + "\n" + Arrays.toString(waitlist) +
                "\nMax waitlist: " + waitlist.length;
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
                for (Student item : waitlist) {
                    if (item != null && item.equals(student)) { //is the student already on the waitlist
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


    public boolean checkInClass(Student[] array, Student student){
        for (Student item : array) {
            if (item == student) {
                return true;
            }
        }
        return false;
    }

    public int whereInClass(Student[] array, Student student){
        for (int i = 0; i < array.length; i++){
            if (array[i] == student){
                return i;
            }
        }
        return -1;
    }

    public boolean dropStudent(Student student){

        if (checkInClass(waitlist, student)) {
            Student[] newWaitlist = new Student[waitlist.length];
            int index = whereInClass(waitlist, student);

            for (int i = 0, k = 0; i < waitlist.length; i++) {
                if (i == index) {
                    continue;
                }
                newWaitlist[k++] = waitlist[i];
            }

            waitlist = newWaitlist;
            return true;
        }
        else if (checkInClass(roster, student)) {
            Student[] newWaitlist = new Student[waitlist.length];
            Student[] newRoster = new Student[roster.length];
            int index = whereInClass(roster, student);

            for (int i = 0, k = 0; i < roster.length; i++) {
                if (i == index) {
                    continue;
                }
                newRoster[k++] = roster[i];
            }

            newRoster[newRoster.length - 1] = waitlist[0];
            for (int i = 1, k = 0; i < waitlist.length; i++) {
                newWaitlist[k++] = waitlist[i];
            }

            roster = newRoster;
            waitlist = newWaitlist;
            return true;
        }
        else {
            return false;
        }

    }

}
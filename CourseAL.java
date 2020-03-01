import java.util.ArrayList;

public class CourseAL {

    private String courseName;
    private ArrayList<Student> roster = new ArrayList<>();
    private ArrayList<Student> waitlist = new ArrayList<>();
    private int rosterCapacity;
    private int waitlistCapacity;


    public CourseAL(String newCourseName, int rosterCapacity, int waitlistCapacity){
        this.courseName = newCourseName;
        this.rosterCapacity = rosterCapacity;
        this.waitlistCapacity = waitlistCapacity;
    }

    public String getCourseName(){
        return courseName;
    }
    public int getRosterSize(){
        return roster.size();
    }
    public int getWaitlistSize(){
        return waitlist.size();
    }

//setters not included because arraylists are more flexible than arrays and course name shouldn't be changed by user

    public String toString(){
        return "Course name: " + courseName +
                "\nNumber enrolled: " + roster.size() + "\n" + roster +
                "\nMax class size: " + roster.size() +
                "\nNumber waitlisted: " + waitlist.size() + "\n" + waitlist +
                "\nMax waitlist: " + waitlist.size();
    }

    public boolean addStudent(Student student){

        if(student.isTuitionPaid()){ //is the student tuition paid
            if (roster.contains(student)){
                return false;
            }

            if(roster.size() < rosterCapacity){ //if not enrolled and there is room in class
                roster.add(student);
                return true;
            }
            else if (waitlist.size() < waitlistCapacity){ //if not enrolled and roster is full
                if (waitlist.contains(student)){
                    return false;
                }

                waitlist.add(student);
                return true;
            }

        }

        return false;

    }


    public boolean dropStudent(Student student){

        if (waitlist.contains(student)) {
            waitlist.remove(student);
            return true;
        }
        else if (roster.contains(student)) {
            roster.remove(student);

            if (waitlist.size() != 0) {
                roster.add(waitlist.get(0));
                waitlist.remove(0);
            }
            return true;
        }
        else {
            return false;
        }

    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Person;
import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.Faculty.FacultyDirectory;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.CourseSchedule.SeatAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kal bugrara
 */
public class Info5001UniversityExample {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        Department department = new Department("Information Systems");
//        CourseCatalog coursecatalog = department.getCourseCatalog();
//        
//        Course course = coursecatalog.newCourse("app eng", "info 5100", 4);
//        
//        CourseSchedule courseschedule = department.newCourseSchedule("Fall2020");
//
//        CourseOffer courseoffer = courseschedule.newCourseOffer("info 5100");
//        if (courseoffer==null)return;
//        courseoffer.generatSeats(10);
//        PersonDirectory pd = department.getPersonDirectory();
//        Person person = pd.newPerson("0112303");
//        StudentDirectory sd = department.getStudentDirectory();
//        StudentProfile student = sd.newStudentProfile(person);
//        CourseLoad courseload = student.newCourseLoad("Fall2020"); 
////        
//        courseload.newSeatAssignment(courseoffer); //register student in class
//        
//        int total = department.calculateRevenuesBySemester("Fall2020");
//        System.out.print("Total: " + total);
//
//    }

         public static void main(String[] args) {
        // Step 1: Initialize the department and course catalog
        Department department = new Department("Information Systems");

        // Step 2: Populate the course catalog with core and elective courses
        populateCourseCatalog(department);

        // Step 3: Create a course schedule for the semester "Fall2024"
        CourseSchedule fallSchedule = department.newCourseSchedule("Fall2024");

        // Step 4: Create students and professors
        populateStudentsAndProfessors(department);

        // Step 5: Create course offers and assign teachers to them
        populateCourseOffersAndAssignTeachers(department, fallSchedule);

        // Step 6: Register students in courses
        registerStudentsInCourses(department, fallSchedule);

        // Step 7: Generate and print the report for "Fall2024"
        generateReport(department, "Fall2024");
    }

    // Method to populate the course catalog with core and elective courses
    private static void populateCourseCatalog(Department department) {
        CourseCatalog catalog = department.getCourseCatalog();
        catalog.newCourse("Core Subject", "IS5100", 4);  // Core course
        catalog.newCourse("Elective 1", "IS5200", 3);    // Elective courses
        catalog.newCourse("Elective 2", "IS5300", 3);
        catalog.newCourse("Elective 3", "IS5400", 3);
        catalog.newCourse("Elective 4", "IS5500", 3);
        catalog.newCourse("Elective 5", "IS5600", 3);
        catalog.newCourse("Elective 6", "IS5700", 3);
        catalog.newCourse("Elective 7", "IS5800", 3);
        catalog.newCourse("Elective 8", "IS5900", 3);
        catalog.newCourse("Elective 9", "IS6000", 3);
    }

    // Method to create students and professors
    private static void populateStudentsAndProfessors(Department department) {
        // Create 10 students
        for (int i = 1; i <= 10; i++) {
            String studentId = "S00" + i;
            Person studentPerson = department.getPersonDirectory().newPerson(studentId);
            department.getStudentDirectory().newStudentProfile(studentPerson);
        }

        // Create 5 professors
        FacultyDirectory facultyDirectory = department.getFacultyDirectory();
        for (int i = 1; i <= 5; i++) {
            String professorId = "T00" + i;
            Person professorPerson = department.getPersonDirectory().newPerson(professorId);
            facultyDirectory.newFacultyProfile(professorPerson);
        }
    }

    private static void populateCourseOffersAndAssignTeachers(Department department, CourseSchedule schedule) {
    // Adding 10 course offers
    ArrayList<CourseOffer> offers = new ArrayList<>();
    for (Course course : department.getCourseCatalog().getCourseList()) {
        CourseOffer offer = schedule.newCourseOffer(course.getCOurseNumber());
        offer.generatSeats(10); // Generate seats for each course offer
        offers.add(offer);
    }

    // Assign professors to the course offers
    ArrayList<FacultyProfile> professors = department.getFacultyDirectory().getTeacherList();
    for (int i = 0; i < offers.size(); i++) {
        CourseOffer offer = offers.get(i);
        offer.AssignAsTeacher(professors.get(i % professors.size())); // Cycle professors for each course
    }
}

// Method to register students for courses
    private static void registerStudentsInCourses(Department department, CourseSchedule schedule) {
        ArrayList<StudentProfile> students = department.getStudentDirectory().getStudentList();

        // Register each student in multiple courses
        for (StudentProfile student : students) {
            CourseLoad fallLoad = student.newCourseLoad("Fall2024");
            int coursesRegistered = 0;

            // Register the student in 2-3 random courses
            for (CourseOffer offer : schedule.getSchedule()) {
                if (Math.random() > 0.5 || coursesRegistered < 2) {  // Ensure each student registers for at least 2 courses
                    SeatAssignment seatAssignment = fallLoad.newSeatAssignment(offer);
                    if (seatAssignment != null) {
                        ArrayList<Float> grades = new ArrayList<>(Arrays.asList(4.0f, 3.7f, 3.3f, 3.0f, 2.7f));
                        seatAssignment.setGrade(grades.get((int) (Math.random() * grades.size())));
                        coursesRegistered++;
                    }
                }
            }
        }
    }
    // Method to generate and print the student report
    private static void generateReport(Department department, String semester) {
        System.out.println("Student Report for " + semester + ":");
        for (StudentProfile student : department.getStudentDirectory().getStudentList()) {
            CourseLoad courseLoad = student.getCourseLoadBySemester(semester);
            float totalCredits = 0;
            float totalScore = 0;
            int tuition = 0;

            System.out.println("Student ID: " + student.getPerson().getPersonId());
            for (SeatAssignment seatAssignment : courseLoad.getSeatAssignments()) {
                Course course = seatAssignment.getAssociatedCourse();
                String professor = seatAssignment.getCourseOffer().getFacultyProfile().getPerson().getPersonId();
                float grade = seatAssignment.GetCourseStudentScore() / seatAssignment.getCreditHours();
                int courseTuition = course.getCoursePrice();

                // 输出课程详情
                System.out.println("  Course: " + course.getCOurseNumber() + " | Professor: " + professor + " | Grade: " + grade);
                
                // 累加学费和学分
                tuition += courseTuition;
                totalCredits += seatAssignment.getCreditHours();
                totalScore += seatAssignment.GetCourseStudentScore();
            }

            // 计算GPA
            float gpa = totalCredits > 0 ? totalScore / totalCredits : 0;
            System.out.println("  GPA: " + gpa + " | Total Tuition: $" + tuition);
            System.out.println("------------------------------------");
        }
    }
}

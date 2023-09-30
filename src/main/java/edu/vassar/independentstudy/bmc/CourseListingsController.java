package edu.vassar.independentstudy.bmc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class CourseListingsController {
    CourseListings classes = new CourseListings();
    StudentList students = new StudentList();

    @GetMapping("/course")
    public String courseForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("coursesList", classes);
        return "course";
    }

    @PostMapping("/course")
    //public String courseSubmit(@ModelAttribute Course course, @ModelAttribute CourseListings coursesList, Model model) {
    public String courseSubmit(@ModelAttribute Course course, Model model){
        model.addAttribute("course", course);
        model.addAttribute("coursesList", classes);
        classes.addCourse(course);
        return "result";
    }

    @GetMapping("/remove")
    public String courseRemoveForm(Model model){
        model.addAttribute("remove", new Course());
        model.addAttribute("coursesList", classes);
        return "remove";
    }

    @PostMapping("/remove")
    public String removeCourse(@ModelAttribute Course course, Model model){
        model.addAttribute("remove", course);
        model.addAttribute("coursesList", classes);
        Course x = classes.findCourse(course.getClassName());
        classes.removeCourse(x);
        return "removeresult";
    }

    @GetMapping("/addedcourses")
    public String showClasses(Model model){
        model.addAttribute("coursesList", classes);
        return "addedcourses";
    }
    @PostMapping("/addedcourses")
    public String showClassesResult(Model model){
        model.addAttribute("coursesList", classes);
        return "addedcourses";
    }

    @GetMapping("/coaches")
    public String makeAppForm(Model model){
        ArrayList<Course> selections = classes.getCoursesCopy();
        System.out.println(Student.courseArrayToString(selections));
        Student student = new Student();
        student.setClassSelection(selections);
        model.addAttribute("student", student);
        return "coaches";
    }

    @PostMapping("/coaches")
    public String submittedApp(Model model, @ModelAttribute Student student, @ModelAttribute ArrayList<Course> selections){
        model.addAttribute("student", student);
        //model.addAttribute("selections", selections);
        //System.out.println(selections.size());
        //student.setClassSelection(selections);
        System.out.println(student.getClassSelection().size());
        System.out.println(student.getClassSelection() == selections);
        students.addStudent(student);
        System.out.println(student.toString());
        return "coachessubmitted";
    }

    @GetMapping("/faculty")
    public String facultySubmissions(Model model){
        model.addAttribute("faculty", new Faculty());
        return "faculty";
    }

    @PostMapping("/faculty")
    public String facultyNameSubmitted(Model model, Faculty faculty){
        ArrayList<Student> availableStudents = students.filteredList(faculty.getName());
        faculty.setStudentChoices(availableStudents);
        model.addAttribute("faculty", faculty);
        return "faculty2";
    }
}
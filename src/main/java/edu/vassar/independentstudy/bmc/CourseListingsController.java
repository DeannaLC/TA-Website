package edu.vassar.independentstudy.bmc;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseListingsController {
    CourseListings classes = new CourseListings();
    StudentList students = new StudentList();
    FacultyList facultyMembers = new FacultyList();

    boolean coursesAdded = false;
    boolean studentsAdded = false;
    boolean facultyAdded = false;

    @Autowired
    StudentRepository studentRepo;
    @Autowired
    CourseRepository courseRepo;
    @Autowired
    FacultyRepository facultyRepo;
    String curName;

    @ModelAttribute("coursesList")
    public CourseListings loadClasses(){
        if (courseRepo != null && !(coursesAdded)) {
            this.coursesAdded = true;
            List<Course> courseLoading = courseRepo.findAll();
            for (int i = 0; i < courseLoading.size(); i = i + 1) {
                classes.addCourse(courseLoading.get(i));
            }
        }
        return this.classes;
    }

    @ModelAttribute("facultyList")
    public FacultyList loadFaculty(){
        if ((this.facultyRepo != null) && !facultyAdded){
            this.facultyAdded = true;
            List<Faculty> facultyLoading = facultyRepo.findAll();
            for (int i = 0; i < facultyLoading.size(); i = i + 1){
                facultyMembers.addFaculty(facultyLoading.get(i));
            }
        }
        return this.facultyMembers;
    }

    @ModelAttribute("studentsList")
    public StudentList loadStudents(){
        if ((this.studentRepo != null) && !studentsAdded){
            this.studentsAdded = true;
            List<Student> studentLoading = studentRepo.findAll();
            for (int i = 0; i < studentLoading.size(); i = i + 1){
                students.addStudent(studentLoading.get(i));
            }
        }
        return this.students;
    }

    @GetMapping("/course")
    public String courseForm(Model model) {
        model.addAttribute("course", new Course());
        //model.addAttribute("coursesList", classes);
        return "course";
    }

    @PostMapping("/course")
    //public String courseSubmit(@ModelAttribute Course course, @ModelAttribute CourseListings coursesList, Model model) {
    public String courseSubmit(@ModelAttribute Course course, Model model){//, @ModelAttribute("coursesList") CourseListings coursesList){
        //model.addAttribute("course", course);
        if (classes.findCourse(course.getClassName(), course.getSection()) != null){
            return "failedadd";
        }
        classes.addCourse(course);
        courseRepo.save(course);
        model.addAttribute("coursesList", classes);
        return "result";
    }

    @GetMapping("/allstudents")
    public String allStudents(Model model){
        model.addAttribute("studentList", students);
        return "allstudents";
    }

    @PostMapping("/allstudents")
    public String allStudents2(Model model){
        model.addAttribute("studentList", students);
        return "allstudents";
    }

    @GetMapping("/remove")
    public String courseRemoveForm(Model model){
        model.addAttribute("remove", new Course());
        //model.addAttribute("coursesList", classes);
        return "remove";
    }

    @PostMapping("/remove")
    public String removeCourse(@ModelAttribute Course course, Model model){
        model.addAttribute("remove", course);
        //model.addAttribute("coursesList", classes);
        Course x = classes.findCourse(course.getClassName(), course.getSection());
        if (x != null)
            classes.removeCourse(x);
        List<Course> y = courseRepo.findByClassName(course.getClassName());
        Course del = null;
        for (int i = 0; i < y.size(); i = i + 1){
            if (y.get(i).getSection() == course.getSection())
                del = y.get(i);
        }
        if (del != null)
            courseRepo.delete(del);
        if (x == null && del == null){
            return "failedremove";
        }
        return "removeresult";
    }

    @GetMapping("/addedcourses")
    public String showClasses(Model model){
        //model.addAttribute("coursesList", classes);
        return "addedcourses";
    }
    @PostMapping("/addedcourses")
    public String showClassesResult(Model model){
        //model.addAttribute("coursesList", classes);
        return "addedcourses";
    }

    @GetMapping("/coaches")
    public String makeAppForm(Model model){
        ArrayList<Course> selections = classes.getCoursesCopy();
        //System.out.println(Student.courseArrayToString(selections));
        Student student = new Student();
        student.setClassSelection(selections);
        model.addAttribute("student", student);
        return "coaches";
    }

    @PostMapping("/coaches")
    public String submittedApp(Model model, @ModelAttribute Student student){
        for(int i = 0; i < student.getClassSelection().size(); i = i + 1){
            student.getClassSelection().get(i).setClassName(classes.getCourses().get(i).getClassName());
            student.getClassSelection().get(i).setProfessor(classes.getCourses().get(i).getProfessor());
            student.getClassSelection().get(i).setLabDay(classes.getCourses().get(i).getLabDay());
            student.getClassSelection().get(i).setLabStart(classes.getCourses().get(i).getLabStart());
            student.getClassSelection().get(i).setLabEnd(classes.getCourses().get(i).getLabEnd());
        }
        model.addAttribute("student", student);
        students.addStudent(student);
        studentRepo.save(student);
        return "coachessubmitted";
    }

    @GetMapping("/faculty")
    public String facultySubmissions(Model model){
        model.addAttribute("faculty", new Faculty());
        return "faculty";
    }

    @PostMapping("/faculty")
    public String facultyNameSubmitted(Model model, @ModelAttribute Faculty faculty){
        System.out.println(faculty.getName());
        System.out.println(students.toString());
        curName = faculty.getName();
        model.addAttribute("facultyName", faculty.getName());
        ArrayList<Student> availableStudents = students.studentsCopy();
        System.out.println(Student.arrayToString(availableStudents));
        faculty.setStudentChoices(availableStudents);
        model.addAttribute("faculty", faculty);
        facultyMembers.addFaculty(faculty);
        facultyRepo.save(faculty);
        return "faculty2";
    }

    @GetMapping("/facultysubmitted")
    public String facultySubmitted(Model model, @ModelAttribute Faculty faculty){
        model.addAttribute("faculty", faculty);
        return "facultysubmitted";
    }

    @PostMapping("/facultysubmitted")
    public String facultySubmitted2(Model model, @ModelAttribute Faculty faculty){
        faculty.setName(curName);
        ArrayList<Student> compare = students.studentsCopy();
        Student student;
        for(int k = 0; k < faculty.getStudentChoices().size(); k = k + 1) {
            student = faculty.getStudentChoices().get(k);
            student.setName(compare.get(k).getName());
            student.setComments(compare.get(k).getComments());
            student.setVassarID(compare.get(k).getVassarID());
            student.setClassSelection(compare.get(k).getClassSelection());
        }
        model.addAttribute("faculty", faculty);
        return "facultysubmitted";
    }

    @GetMapping("/clearall")
    public String clear(Model model){
        model.addAttribute("remove", new Removal());
        return "clear";
    }

    @PostMapping("/clearall")
    public String clearAll(Model model, @ModelAttribute Removal remove){
        //if (remove.getStudentClear())
            this.studentRepo.deleteAll();
        //if (remove.getCourseClear())
            this.courseRepo.deleteAll();
        //if (remove.getFacultyClear())
            this.facultyRepo.deleteAll();
        return "deleted";
    }

    @GetMapping("/allfaculty")
    public String allFaculty(Model model){
        model.addAttribute("facultyList", this.facultyMembers);
        return "allfaculty";
    }

    @PostMapping("/allfaculty")
    public String allFacultyPost(Model model){
        model.addAttribute("facultyList", this.facultyMembers);
        return "allfaculty";
    }

    @GetMapping("/tabletest")
    public String tableTest(Model model){
       // System.out.println(classes.toString());
       // System.out.println(classes.getCourses().size());
        ArrayList<Student> extra = students.studentsCopy();
        StudentList studentList = new StudentList();
        studentList.setStudentList(extra);
        //System.out.println(studentList.toString());
        System.out.println(studentList.getStudentList().size());
        for(int i = 0; i < studentList.getStudentList().size(); i = i + 1){
            System.out.println(studentList.getStudentList().get(i).toString());
        }
        model.addAttribute("studentList", studentList);
        model.addAttribute("coursesList", classes);
        return "tabletest";
    }

    @PostMapping("/tabletest")
    public String tableTest2(Model model, @ModelAttribute StudentList studentList){
        Student student;
        ArrayList<Student> compare = students.getStudentList();
        int savePref;
        Course curCourse;
        for(int k = 0; k < studentList.getStudentList().size(); k = k + 1) {
            student = studentList.getStudentList().get(k);
            student.setName(compare.get(k).getName());
            student.setComments(compare.get(k).getComments());
            student.setVassarID(compare.get(k).getVassarID());
            for (int i = 0; i < student.getClassSelection().size(); i = i + 1) {
                curCourse = student.getClassSelection().get(i);
                curCourse.setClassName(classes.getCourses().get(i).getClassName());
                curCourse.setProfessor(classes.getCourses().get(i).getProfessor());
                curCourse.setLabDay(classes.getCourses().get(i).getLabDay());
                curCourse.setLabStart(classes.getCourses().get(i).getLabStart());
                curCourse.setLabEnd(classes.getCourses().get(i).getLabEnd());
                curCourse.setGrade(compare.get(k).getClassSelection().get(i).getGrade());
                curCourse.setClassPreference(compare.get(k).getClassSelection().get(i).getClassPreference());
                curCourse.setAvailable(compare.get(k).getClassSelection().get(i).getAvailable());
            }
        }
        this.students.setStudentList(studentList.getStudentList());
        model.addAttribute("studentList", studentList);
        return "tabletest2";
    }
}
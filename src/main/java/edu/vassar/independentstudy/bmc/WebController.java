package edu.vassar.independentstudy.bmc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class WebController {
    CourseList courses = new CourseList();
    StudentList students = new StudentList();
    @Autowired
    CourseRepository courseRepo;
    @Autowired
    StudentRepository studentRepo;
    boolean coursesAdded = false;
    boolean studentsAdded = false;
    Course curCourse;
    Student curStudent;

    //for loading previous data
    @ModelAttribute("courseList")
    public CourseList loadClasses(){
        if (courseRepo != null && !(coursesAdded)) {
            this.coursesAdded = true;
            List<Course> courseLoading = courseRepo.findAll();
            for (int i = 0; i < courseLoading.size(); i = i + 1) {
                courses.addCourse(courseLoading.get(i));
            }
        }
        return this.courses;
    }

    @ModelAttribute("studentList")
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

    public static boolean checkNumberSequence(int[] vals){
        int min;
        int idx = -1;
        int[] check;
        if (vals != null){
            check = new int[vals.length];
            for (int i = 0; i < vals.length; i = i + 1){
                min = Integer.MAX_VALUE;
                for (int k = 0; k < vals.length; k = k + 1){
                    if (vals[k] < min){
                        min = vals[k];
                        idx = k;
                    }
                }
                check[i] = min;
                vals[idx] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < vals.length - 1; i = i + 1){
                if (check[i] + 1 != check[i + 1]){
                    return false;
                }
            }
        }
        return true;
    }

    public void updateCourseRepo(){
        courseRepo.deleteAll();
        Course add;
        for (int i = 0; i < this.courses.getCourses().size(); i = i + 1){
            add = this.courses.getCourses().get(i);
            courseRepo.save(add);
        }
    }

    //add courses
    @GetMapping("/course")
    public String courseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course";
    }

    @PostMapping("/course")
    public String courseSubmit(@ModelAttribute Course course, Model model){
        if (courses.findCourse(course.getName(), course.getSection()) != null){
            Course found = courses.findCourse(course.getName(), course.getSection());
            this.curCourse = course.courseCopy();
            model.addAttribute("course", course);
            model.addAttribute("foundCourse", found);
            System.out.println(course.toString());
            System.out.println(found.toString());
            return "failedadd";
        }
        course.transformTime();
        courses.addCourse(course);
        courseRepo.save(course);
        System.out.println(course.getName());
        model.addAttribute("course", course);
        model.addAttribute("courseList", courses);
        return "coursesubmit";
    }

    @GetMapping("/courseoverride")
    public String overrideCourse(Model model, @ModelAttribute Course course){
        return "courseoverride";
    }

    @PostMapping("/courseoverride")
    public String overrideCourse2(@ModelAttribute Course course, Model model){
        courses.removeByNameSection(curCourse.getName(), curCourse.getSection());
        courses.addCourse(curCourse);
        System.out.println("3" + courses.toString());
        this.updateCourseRepo();
        return "courseoverride";
    }

    @GetMapping("/addedcourses")
    public String showClasses(Model model){
        model.addAttribute("courseList", courses);
        return "addedcourses";
    }
    @PostMapping("/addedcourses")
    public String showClassesResult(Model model){
        return "addedcourses";
    }

    @GetMapping("/remove")
    public String courseRemoveForm(Model model){
        model.addAttribute("remove", new Course());
        return "courseremove";
    }

    @PostMapping("/remove")
    public String removeCourse(@ModelAttribute Course course, Model model){
        model.addAttribute("remove", course);
        Course x = courses.findCourse(course.getName(), course.getSection());
        if (x != null) {
            courses.removeCourse(x);
            this.updateCourseRepo();
            return "courseremoveresult";
        }
        return "failedcourseremove";
    }

    //coach apps
    @GetMapping("/coaches")
    public String coachApp(Model model){
        Student student = new Student();
        CourseList modify = courses.courseListCopy();
        modify.setAllCurPrefs();
        model.addAttribute("courseList", modify);
        model.addAttribute("student", student);
        return "coachapp";
    }

    @PostMapping("/coaches")
    public String submittedApp(Model model, @ModelAttribute CourseList modify, @ModelAttribute Student student){
        if (students.findByNameId(student.getName(), student.getVassarID()) != null){
            /**Student found = students.findByNameId(student.getName(), student.getVassarID());
            //CourseList filtered = this.courses.filteredCopy(found.getName(), found.getVassarID());
            modify.fixAllNames(this.courses);
            this.curStudent = student.studentCopy();
            System.out.println("HI" + modify.toString());
            System.out.println("HI AGAIN" + this.courses.toString());
            model.addAttribute("filteredNew", modify);
            model.addAttribute("student", student);
            model.addAttribute("foundStudent", found);
            model.addAttribute("filteredOld", this.courses);
            return "failedcoachapp";**/

            return "failedcoachtemp";
        }
        modify.setStudents(student);
        modify.addCurPrefs();
        this.courses.transferAllStuPrefs(modify);
        modify = this.courses;
        students.addStudent(student);
        studentRepo.save(student);
        this.updateCourseRepo();
        model.addAttribute("courseList", modify);
        model.addAttribute("student", student);
        return "submittedcoachapp";
    }

    @GetMapping("/allstudents")
    public String allStudents(Model model){
        model.addAttribute("studentList", this.students);
        return "allstudents";
    }

    @PostMapping("/allstudents")
    public String allStudentsPost(Model model){
        return "allstudents";
    }

    //faculty submissions
    @GetMapping("/faculty")
    public String facultyTableGenerate(Model model){
        this.courses.setAllFacPrefBackups();
        CourseList editable = this.courses.courseListCopy();
        model.addAttribute("courseList", editable);
        model.addAttribute("studentList", students);
        return "facultytable";
    }

    @PostMapping("/faculty")
    public String facultyPost(Model model, @ModelAttribute CourseList editable){
        courses.transferAllFacPrefs(editable);
        courses.changeAllFacPrefs();
        this.updateCourseRepo();
        model.addAttribute("courseList", courses);
        model.addAttribute("studentList", students);
        return "submittedfacultytable";
    }

    //clear all data
    @GetMapping("/clearall")
    public String clear(Model model){
        return "clear";
    }

    @PostMapping("/clearall")
    public String clearAll(Model model){
        this.courses = new CourseList();
        this.students = new StudentList();
        this.studentRepo.deleteAll();
        this.courseRepo.deleteAll();
        return "deleted";
    }

    @GetMapping("/assign")
    public String assign(Model model){
        return "assign";
    }

    @PostMapping("/assign")
    public String assignDone(Model model){
        courses.assignAll();
        this.updateCourseRepo();
        System.out.println("hi " + courses.toString());
        model.addAttribute("courseList", courses.courseListCopy());
        return "assignDone";
    }

    @GetMapping("/assignments")
    public String viewAssignments(Model model){
        model.addAttribute("assigned", this.courses);
        return "assignments";
    }

    @PostMapping("/assignments")
    public String viewAssignmentsPost(Model model){
        return "assignments";
    }
}

package edu.vassar.independentstudy.bmc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseListingsController {
    CourseListings courses = new CourseListings();
    Course remove = new Course();

    @GetMapping("/course")
    public String courseForm(Model model) {
        model.addAttribute("course", new Course());
        //if (!(model.containsAttribute("coursesListing")))
           // model.addAttribute(courses);
        model.addAttribute("coursesList", courses);
        return "course";
    }

    @PostMapping("/course")
    //public String courseSubmit(@ModelAttribute Course course, @ModelAttribute CourseListings coursesList, Model model) {
    public String courseSubmit(@ModelAttribute Course course, Model model){
        model.addAttribute("course", course);
        //if (!(model.containsAttribute("coursesListing")))
        //    model.addAttribute("coursesList", coursesList);
        model.addAttribute("coursesList", courses);
        courses.addCourse(course);
        return "result";
    }

    @GetMapping("/remove")
    public String courseRemoveForm(Model model){
        model.addAttribute("removing", remove);
        model.addAttribute("coursesList", courses);
        return "remove";
    }
}
package edu.vassar.independentstudy.bmc;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String>{

    public Course findByClassName(String name);
    
}

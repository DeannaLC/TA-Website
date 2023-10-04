package edu.vassar.independentstudy.bmc;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String>{
    public List<Course> findByClassName(String className);

    //public List<Course> findAll(String className);

    //public List<Course> findAll();

    //public List<Course> findAll(String name);
    
}

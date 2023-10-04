package edu.vassar.independentstudy.bmc;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
    public Student findByVassarID(int vassarID);
}

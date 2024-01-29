package sit.int204.classicmodelsservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.models.Student;

@Service
public class StudentService {
    public Student calculateGrade(Student student) {
        if (student.getScore() > 100 || student.getScore() < 0 || student.getScore().isNaN()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid Score");
        }
        Double score = student.getScore();
        if (score <= 49) {
            student.setGrade('F');
            return student;
        }
        if (score >= 50 && score <= 59) {
            student.setGrade('D');
            return student;
        }
        if (score >= 60 && score <= 69) {
            student.setGrade('C');
            return student;
        }
        if (score >= 70 && score <= 79) {
            student.setGrade('B');
            return student;
        }
        student.setGrade('A');
        return student;
    }
}

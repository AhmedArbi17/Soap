package de.tekup.soap.models.Service;

import de.tekup.soap.models.whitetest.*;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ExamService {

    public Student findStudent(List<Student> studentList, int id) {
        Student student = null;
        for (Student s : studentList) {
            if (s.getId() == id) {
                student = s;
                break;
            }
        }
        return student;
    }

    public Exam findExam(List<Exam> examList, String code) {
        Exam exam = null;
        for (Exam e : examList) {
            if (e.getCode().equalsIgnoreCase(code)) {
                exam = e;
                break;
            }
        }
        return exam;
    }

    public List<Student> addStudent() {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("ARBI Ahmed");
        s1.setAddress("Tunis");
        Student s2 = new Student();
        s2.setId(2);
        s2.setName("Foulen ben Foulen");
        s2.setAddress("Tunis");
        Student s3 = new Student();
        s3.setId(3);
        s3.setName("x y");
        s3.setAddress("Tunis");
        //adding student to the list
        students.add(s1);
        students.add(s2);
        students.add(s3);
        return students;
    }

    public List<Exam> addExam() {
        List<Exam> exams = new ArrayList<>();
        Exam exam1 = new Exam();
        exam1.setCode("CLF-C01 ");
        exam1.setName("AWS CCP");
        Exam exam2 = new Exam();
        exam2.setCode("1ZO-808");
        exam2.setName("OCA JAVA 8");
        exams.add(exam1);
        exams.add(exam2);
        return exams;

    }

    public WhiteTestResponse getExam(StudentRequest request) throws Exception {
        WhiteTestResponse response = new ObjectFactory().createWhiteTestResponse();
        List<Student> students = this.addStudent();
        List<Exam> exams = this.addExam();
        List<String> messageList = response.getMessage();

        if (findExam(exams, request.getExamCode()) == null)
            messageList.add("Exam Not found with code =" + request.getExamCode());
        if (findStudent(students, request.getStudentId()) == null)
            messageList.add("Student not found with id =" + request.getStudentId());

        if (messageList.isEmpty() || messageList == null) {
            response.setStudent(findStudent(students, request.getStudentId()));
            response.setExam(findExam(exams, request.getExamCode()));
            ZoneId zone = ZoneId.of("Europe/Rome");
            ZonedDateTime zdt = LocalDateTime.now().atZone(zone);
            GregorianCalendar cal = GregorianCalendar.from(zdt);
            XMLGregorianCalendar xmlgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            response.setDate(xmlgcal);
            messageList.add("Data fetched successfully");
        }
        return response;
    }

    public ExamResponse findAllExams(ExamRequest request) {
        ExamResponse response= new ExamResponse();
        List<Exam> exams = response.getExams();
        Exam exam = findExam(addExam(), request.getExamCode());
        if (exam ==null || request.getExamCode() ==null)
            exams.addAll(addExam());
        else
            exams.add(exam);
        return response;
    }


}

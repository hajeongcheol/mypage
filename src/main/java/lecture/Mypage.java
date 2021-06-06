package lecture;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Mypage_table")
public class Mypage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long courseId;
    private String courseName;
    private String teacher;
    private Long fee;
    private Integer studentCount;
    private Boolean openYn;
    private String textBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Boolean getOpenYn() {
        return openYn;
    }

    public void setOpenYn(Boolean openYn) {
        this.openYn = openYn;
    }

    public String getTextBook() {
        return textBook;
    }

    public void setTextBook(String textBook) {
        this.textBook = textBook;
    }

}

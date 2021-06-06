package lecture;

import lecture.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {

    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCourseRegistered_then_CREATE_1(@Payload CourseRegistered courseRegistered) {
        try {

            if (!courseRegistered.validate())
                return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setCourseId(courseRegistered.getId());
            mypage.setCourseName(courseRegistered.getName());
            mypage.setTeacher(courseRegistered.getTeacher());
            mypage.setFee(courseRegistered.getFee());
            mypage.setOpenYn(false);
            mypage.setTextBook(courseRegistered.getTextBook());
            // view 레파지 토리에 save
            mypageRepository.save(mypage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCourseModified_then_UPDATE_1(@Payload CourseModified courseModified) {
        try {
            if (!courseModified.validate())
                return;
            // view 객체 조회
            List<Mypage> mypageList = mypageRepository.findByCourseId(courseModified.getId());
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setCourseName(courseModified.getName());
                mypage.setTeacher(courseModified.getTeacher());
                mypage.setFee(courseModified.getFee());
                mypage.setTextBook(courseModified.getTextBook());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCourseScheduleModified_then_UPDATE_2(@Payload CourseScheduleModified courseScheduleModified) {
        try {
            if (!courseScheduleModified.validate())
                return;
            // view 객체 조회
            List<Mypage> mypageList = mypageRepository.findByCourseId(courseScheduleModified.getCourseId());
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setStudentCount(courseScheduleModified.getStudentCount());
                mypage.setOpenYn(courseScheduleModified.getOpenYn());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCourseDeleted_then_DELETE_1(@Payload CourseDeleted courseDeleted) {
        try {
            if (!courseDeleted.validate())
                return;
            // view 레파지 토리에 삭제 쿼리
            mypageRepository.deleteByCourseId(courseDeleted.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
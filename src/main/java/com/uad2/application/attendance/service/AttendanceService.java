package com.uad2.application.attendance.service;
/*
 * @USER JungHyun
 * @DATE 2019-10-05
 * @DESCRIPTION
 */

import com.uad2.application.attendance.entity.Attendance;
import com.uad2.application.attendance.repository.AttendanceRepository;
import com.uad2.application.matching.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance getAttendance(int memberSeq,String date){
        return attendanceRepository.findByMemberSeqAndAvailableDate(memberSeq,date);
    }

    public List<Attendance> getAttendanceAndMemberListByDateAndTime(String date, String time) {
        return attendanceRepository.findByAvailableDateAndTime(date, time);
    }

    public void deleteAttendance(Attendance attendance){
        attendanceRepository.delete(attendance);
    }

    public Attendance saveAttendance(Attendance attendance){
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendanceList(String date){
        return attendanceRepository.findByAvailableDate(date);
    }


}

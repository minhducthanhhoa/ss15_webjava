package com.data.sesson15_webjava.controller;

import com.data.sesson15_webjava.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {
    @GetMapping("/students")
    public String showStudentList(Model model) {
        List<Student> students = Arrays.asList(
                new Student("SV001", "Nguyễn Văn A", 20, "12A1", "a@gmail.com", "Hà Nội", "0912345678"),
                new Student("SV002", "Trần Thị B", 21, "12A2", "b@gmail.com", "TP.HCM", "0987654321"),
                new Student("SV003", "Lê Văn C", 19, "12A3", "c@gmail.com", "Đà Nẵng", "0909090909")
        );

        model.addAttribute("students", students);
        return "studentList";
    }
}

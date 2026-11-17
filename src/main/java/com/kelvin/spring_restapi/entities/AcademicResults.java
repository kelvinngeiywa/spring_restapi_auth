package com.kelvin.spring_restapi.entities;

import com.kelvin.spring_restapi.entities.enums.ExamTypeEnum;
import com.kelvin.spring_restapi.entities.enums.TermEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

@Entity
@Table(name = "academic_results",
       uniqueConstraints = @UniqueConstraint(columnNames = {"student_id","subject_id","academic_year","term",
       "exam_type"}))
public class AcademicResults {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "recorded_by")
    private Teacher teacher;
    @Column(nullable = false)
    private int academicYear;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 15)
    private TermEnum term;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "exam_type",length = 15)
    private ExamTypeEnum examType;
    @Column(nullable = false)
    private double score;
    @Column(name = "max_score",nullable = false)
    private double maxScore;
    private String remarks;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public AcademicResults(){

    }
    public AcademicResults(UUID id,Student student,Subject subject,Teacher teacher,int academicYear,
                           TermEnum term,ExamTypeEnum examType,double score,double maxScore,String remarks,
                           Instant createdAt, Instant updatedAt)
    {
        this.id=id;
        this.student=student;
        this.subject=subject;
        this.teacher=teacher;
        this.academicYear=academicYear;
        this.term=term;
        this.examType=examType;
        this.score=score;
        this.maxScore=maxScore;
        this.remarks=remarks;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public UUID getId(){
        return this.id;
    }

    public Student getStudent(){
        return this.student;
    }

    public void setStudent(Student student){
        this.student=student;
    }

    public Subject getSubject(){
        return this.subject;
    }

    public void setSubject(Subject subject){
        this.subject=subject;
    }

    public Teacher getTeacher(){
        return this.teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher=teacher;
    }

    public void setAcademicYear(int academicYear){
        this.academicYear=academicYear;
    }

    public int getAcademicYear(){
        return this.academicYear;
    }

    public TermEnum getTerm(){
        return this.term;
    }

    public void setTerm(TermEnum term){
        this.term=term;
    }

    public ExamTypeEnum getExamType(){
        return this.examType;
    }

    public void setExamType(ExamTypeEnum examType){
        this.examType=examType;
    }

    public double getScore(){
        return this.score;
    }

    public void setScore(double score){
        this.score=score;
    }

    public double getMaxScore(){
        return this.maxScore;
    }

    public void setMaxScore(double maxScore){
        this.maxScore=maxScore;
    }

    public String getRemarks(){
        return this.remarks;
    }

    public void setRemarks(String remarks){
        this.remarks=remarks;
    }

    public Instant getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedAt(Instant createdAt){
        this.createdAt=createdAt;
    }


    public Instant getUpdatedAt(){
        return this.updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt){
        this.updatedAt=updatedAt;
    }


}

package com.Perseveres.mapper;

import com.Perseveres.entity.Student;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface StudentMapper {
    /**
     * 多条件复杂查询
     * 规则：
     * 1. 当姓名不为空时，只根据姓名查询
     * 2. 当姓名为空而专业不为空时，只根据专业查询
     * 3. 当姓名和专业都为空时，查询所有学号不为空的学生
     */
    List<Student> selectStudentsByConditions(@Param("name") String name,
                                             @Param("major") String major);

    /**
     * 单条件查询：查询id在指定列表中的学生信息
     */
    List<Student> selectStudentsByIds(@Param("ids") List<Long> ids);

    /**
     * 插入学生信息
     */
    int insertStudent(Student student);

    /**
     * 查询所有学生（用于测试）
     */
    List<Student> selectAllStudents();
}
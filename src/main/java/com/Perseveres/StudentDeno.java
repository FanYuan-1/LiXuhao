package com.Perseveres;

import com.Perseveres.entity.Student;
import com.Perseveres.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class StudentDeno {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 创建学生表（测试用）

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            // 插入测试数据
            System.out.println("=== 插入测试数据 ===");
            insertTestData(mapper, sqlSession);

            // 测试1：多条件查询 - 根据姓名查询
            System.out.println("\n=== 测试1：根据姓名'李'查询 ===");
            List<Student> studentsByName = mapper.selectStudentsByConditions("李", null);
            studentsByName.forEach(System.out::println);

            // 测试2：多条件查询 - 根据专业查询
            System.out.println("\n=== 测试2：根据专业'计算机'查询 ===");
            List<Student> studentsByMajor = mapper.selectStudentsByConditions(null, "计算机");
            studentsByMajor.forEach(System.out::println);

            // 测试3：多条件查询 - 姓名和专业都为空
            System.out.println("\n=== 测试3：姓名和专业都为空（查询所有学号不为空的学生）===");
            List<Student> allStudents = mapper.selectStudentsByConditions(null, null);
            allStudents.forEach(System.out::println);

            // 测试4：单条件查询 - 查询id为3,5,7的学生
            System.out.println("\n=== 测试4：查询id为3,5,7的学生 ===");
            List<Long> ids = Arrays.asList(3L, 5L, 7L);
            List<Student> studentsByIds = mapper.selectStudentsByIds(ids);
            studentsByIds.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 创建学生表


    // 插入测试数据
    private static void insertTestData(StudentMapper mapper, SqlSession sqlSession) {
        // 清空表
        try {
            sqlSession.getConnection().createStatement().execute("DELETE FROM student");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 插入测试数据
        List<Student> testStudents = Arrays.asList(
                new Student("S001", "李绪昊", "计算机科学", 20, "男"),
                new Student("S002", "李四", "软件工程", 21, "男"),
                new Student("S003", "王五", "计算机科学", 22, "男"),
                new Student("S004", "赵六", "电子信息", 19, "女"),
                new Student("S005", "钱七", "软件工程", 20, "女"),
                new Student("S006", "孙八", "计算机科学", 21, "男"),
                new Student("S007", "周九", "机械工程", 23, "男"),
                new Student("S008", "吴十", "软件工程", 20, "女")
        );

        for (Student student : testStudents) {
            mapper.insertStudent(student);
        }
        sqlSession.commit();
        System.out.println("插入了 " + testStudents.size() + " 条测试数据");
    }
}
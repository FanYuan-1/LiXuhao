package com.Perseveres;

import com.Perseveres.entity.Employee;
import com.Perseveres.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmployeeDemo{
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

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 1. 新增员工信息
            System.out.println("=== 新增员工信息 ===");
            Employee emp1 = new Employee("李绪昊", 22, "软件工程师");
            Employee emp2 = new Employee("李四", 30, "项目经理");
            Employee emp3 = new Employee("张三", 28, "测试工程师");

            mapper.insertEmployee(emp1);
            mapper.insertEmployee(emp2);
            mapper.insertEmployee(emp3);
            sqlSession.commit();
            System.out.println("新增员工成功！");

            // 2. 根据姓名查询信息
            System.out.println("\n=== 根据姓名'李绪昊'查询信息 ===");
            List<Employee> zhangsanList = mapper.selectEmployeesByName("李绪昊");
            zhangsanList.forEach(System.out::println);

            // 3. 根据姓名更新age、position
            System.out.println("\n=== 更新韩一帆的信息 ===");
            Employee updateEmp = new Employee("李绪昊", 22, "高级软件工程师");
            int updateCount = mapper.updateEmployeeByName(updateEmp);
            sqlSession.commit();
            System.out.println("更新了 " + updateCount + " 条记录");

            // 查询更新后的结果
            System.out.println("更新后的李绪昊信息：");
            mapper.selectEmployeesByName("李绪昊").forEach(System.out::println);

            // 4. 根据年龄删除信息
            System.out.println("\n=== 删除年龄为30的员工 ===");
            int deleteCount = mapper.deleteEmployeesByAge(30);
            sqlSession.commit();
            System.out.println("删除了 " + deleteCount + " 条记录");

            // 查询所有员工
            System.out.println("\n=== 当前所有员工信息 ===");
            List<Employee> allEmployees = mapper.selectAllEmployees();
            allEmployees.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 创建员工表（用于测试）

    private static void createEmployeeTable() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.getConnection().createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS employee (" +
                            "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(50) NOT NULL, " +
                            "age INT, " +
                            "position VARCHAR(100)" +
                            ")"
            );
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("表已存在或创建失败: " + e.getMessage());
        }
    }*/


}
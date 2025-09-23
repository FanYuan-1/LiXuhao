package com.Perseveres.mapper;

import com.Perseveres.entity.Employee;
import java.util.List;

public interface EmployeeMapper {
    /**
     * 1. 新增员工信息
     */
    int insertEmployee(Employee employee);

    /**
     * 2. 根据姓名查询信息
     */
    List<Employee> selectEmployeesByName(String name);

    /**
     * 3. 根据姓名更新age、position
     */
    int updateEmployeeByName(Employee employee);

    /**
     * 4. 根据年龄删除信息
     */
    int deleteEmployeesByAge(Integer age);

    /**
     * 查询所有员工（用于测试）
     */
    List<Employee> selectAllEmployees();
}
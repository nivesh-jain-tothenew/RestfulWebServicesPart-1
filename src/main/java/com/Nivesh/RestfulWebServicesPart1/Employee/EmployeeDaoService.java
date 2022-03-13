package com.Nivesh.RestfulWebServicesPart1.Employee;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeeDaoService {

    private static List<EmployeeBean> employeeBeanList = new ArrayList<EmployeeBean>();

    int count = 3;
    static
    {
        employeeBeanList.add(new EmployeeBean(1, "Nivesh", 23));
        employeeBeanList.add(new EmployeeBean(2, "Parit", 22));
        employeeBeanList.add(new EmployeeBean(3, "parth", 22));
    }

    List<EmployeeBean> findAll()
    {
        return employeeBeanList;
    }

    public EmployeeBean save(EmployeeBean users) {
        if (users.getId() == 0)
            users.setId(++count);
        employeeBeanList.add(users);
        return users;
    }

    public EmployeeBean findOne(int id)
    {
        for(EmployeeBean employeeBean : employeeBeanList)
        {
            if(employeeBean.getId() == id)
                return employeeBean;
        }
        return null;

    }

    public EmployeeBean deleteById(int id)
    {
        Iterator iterator = employeeBeanList.iterator();
        while(iterator.hasNext())
        {
            EmployeeBean employeeBean = (EmployeeBean) iterator.next();

            if(employeeBean.getId() == id) {
                iterator.remove();
                return employeeBean;
            }

        }
        return null;

    }


    public EmployeeBean updateNameById(int id, String name) {
        Iterator iterator = employeeBeanList.iterator();
        EmployeeBean employeeBean = null;
        while (iterator.hasNext()) {
            employeeBean = (EmployeeBean) iterator.next();
            if (employeeBean.getId() == id) {
                employeeBean.setName(name);
            }
        }
        return employeeBean;
    }
}

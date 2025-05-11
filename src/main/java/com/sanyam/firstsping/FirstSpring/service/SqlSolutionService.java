package com.sanyam.firstsping.FirstSpring.service;

import org.springframework.stereotype.Service;

@Service
public class SqlSolutionService {

    public String solveSqlProblem(String regNo) {
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastDigits;
        
        try {
            lastDigits = Integer.parseInt(lastTwoDigits);
        } catch (NumberFormatException e) {
            lastDigits = Integer.parseInt(lastTwoDigits.replaceAll("[^0-9]", ""));
        }
        
        boolean isOdd = lastDigits % 2 != 0;
        
        if (isOdd) {
            return solveProblem1();
        } else {
            return solveProblem2();
        }
    }
    
    private String solveProblem1() {
        return "SELECT " +
               "p.AMOUNT AS SALARY, " +
               "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, " +
               "TIMESTAMPDIFF(YEAR, e.DOB, CURRENT_DATE()) AS AGE, " +
               "d.DEPARTMENT_NAME " +
               "FROM PAYMENTS p " +
               "JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID " +
               "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
               "WHERE DAY(p.PAYMENT_TIME) != 1 " +
               "ORDER BY p.AMOUNT DESC " +
               "LIMIT 1";
    }
    
    private String solveProblem2() {
        return "SELECT " +
                "e1.EMP_ID, " +
                "e1.FIRST_NAME, " +
                "e1.LAST_NAME, " +
                "d.DEPARTMENT_NAME, " +
                "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT " +
                "FROM EMPLOYEE e1 " +
                "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID " +
                "LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT " +
                "AND e1.DOB < e2.DOB " +
                "AND e1.EMP_ID != e2.EMP_ID " +
                "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME " +
                "ORDER BY e1.EMP_ID DESC";
    }

} 
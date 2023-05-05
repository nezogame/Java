package edu.dnu.fpm.pz;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static void demo() throws MyException {
        EmployerDAO employerDAO = new EmployerDAO();
        VacancyDAO vacancyDAO = new VacancyDAO();
        System.out.println("Додаємо співробітників");
/*
        List<Employer> employers = new ArrayList<>();
*/
        List<Employer> employers = new ArrayList<>();

        employers.add(new Employer("Volodimr", "Zelenski", "+380677848066"));
        employers.add(new Employer("Serhii", "Sharlet", "0663322244"));
        employers.add(new Employer("Roman", "Hribov", "0987848066"));
        employerDAO.create(employers);
        employers = employerDAO.read();
        for (Employer current : employers) {
            System.out.println(current);
        }
        System.out.println("Змінюємо номер телефону першого співробітника");
        System.out.println("Було:");
        Employer employer = employerDAO.read().get(0);
        System.out.println(employer);
        employer.setPhone("380957848066");
        employerDAO.update(employer);
        System.out.println("Стало:");
        employer = employerDAO.read().get(0);
        System.out.println(employer);

        System.out.println("Додаємо вакансії");
        List<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(new Vacancy(employers.get(0), "Ukrainian President",
                "", 500000, Date.valueOf("2019-05-20")));
        vacancies.add(new Vacancy(employers.get(1),
                "Minister of education and science in Ukraine",
                "", 60000, Date.valueOf("2020-12-17")));
        vacancyDAO.create(vacancies);
        vacancies = vacancyDAO.read();
        for (Vacancy current : vacancies) {
            System.out.println(current);
        }
        vacancies = vacancyDAO.read();
        for (Vacancy current : vacancies) {
            vacancyDAO.delete(current.getId());
        }
        employers = employerDAO.read();
        for (Employer current : employers) {
            employerDAO.delete(current.getId());
        }
    }

    public static void main(String[] args) {
        try {
            demo();
        } catch (MyException e) {
            e.toLog();
        }
    }
}

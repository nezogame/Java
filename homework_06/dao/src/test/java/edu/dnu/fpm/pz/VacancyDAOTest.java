package edu.dnu.fpm.pz;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VacancyDAOTest {
    private final static InterfaceDAO<Vacancy> vacancyDAO = new VacancyDAO();
    private final static InterfaceDAO<Employer> employerDAO = new EmployerDAO();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void prepare() throws MyException {
        Employer employer = new Employer("John", "Doe", "+380961234567");
        employerDAO.create(employer);
        List<Employer> employers = employerDAO.read();
        int employerId = employers.get(employers.size() - 1).getId();
        employer.setId(employerId);
        Vacancy vacancy = new Vacancy(employer, "teacher",
                "teacher", 12000, Date.valueOf("2023-01-25"));
        vacancyDAO.create(vacancy);
    }

    @Test
    public void createVacancyExceptionTestBadSalary() throws Exception {
//GIVEN
        Employer employer = employerDAO.read().get(0);
        Vacancy vacancy = new Vacancy(employer, "teacher",
                "teacher", 1000000000, Date.valueOf("2023-01-25"));
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect salary: " + vacancy.getSalary());
//WHEN
        vacancyDAO.create(vacancy);
    }

    @Test
    public void createVacancyTest() throws Exception {
//GIVEN
        Employer employer = employerDAO.read().get(0);
        Vacancy expected = new Vacancy(employer, "teacher",
                "teacher", 20000, Date.valueOf("2023-01-20"));
//WHEN
        vacancyDAO.create(expected);
        Vacancy result = vacancyDAO.read().get(vacancyDAO.read().size() - 1);
//THEN
        assertEquals(expected, result);
    }

    @Test
    public void createVacanciesTest() throws Exception {
//GIVEN
        Employer employer = employerDAO.read().get(0);
        List<Vacancy> expected = new ArrayList<>();
        expected.add(new Vacancy(employer, "Minister of Education and Science of Ukraine",
                "Economist", 25000, Date.valueOf("2023-01-22")));
        expected.add(new Vacancy(employer, "Border guard",
                "Saying forever", 21000, Date.valueOf("2023-01-15")));
//WHEN
        vacancyDAO.create(expected);
        List<Vacancy> result = new ArrayList<>();
        result.add(vacancyDAO.read().get(vacancyDAO.read().size() - 2));
        result.add(vacancyDAO.read().get(vacancyDAO.read().size() - 1));
//THEN
        assertEquals(expected, result);
    }

    @Test
    public void deleteVacancyTest() throws Exception {
//GIVEN
        int expected = vacancyDAO.read().get(vacancyDAO.read().size() - 1).getId();
        Employer employer = employerDAO.read().get(0);
        Vacancy vacancy = new Vacancy(employer, "Head of the Office of the President of Ukraine",
                "Lawyer", 20000, Date.valueOf("2023-01-18"));
        vacancyDAO.create(vacancy);
//WHEN
        vacancyDAO.delete(vacancyDAO.read().get(
                vacancyDAO.read().size() - 1).getId());
        int result = vacancyDAO.read().get(vacancyDAO.read().size() - 1).getId();
//THEN
        assertEquals(expected, result);
    }

    @Test
    public void updateVacancyExceptionBadSalaryTest() throws Exception {
//GIVEN
        Employer employer = employerDAO.read().get(0);
        int lastId = vacancyDAO.read().get(vacancyDAO.read().size() - 1).getId();
        Vacancy vacancy = new Vacancy(lastId, employer,
                "Prime Minister of Great Britain",
                "In the past, a comedian", 999, Date.valueOf("2023-01-03"));
//WHEN
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect salary: " + "999");
        vacancyDAO.update(vacancy);
    }

    @Test
    public void updateVacancyTest() throws Exception {
//GIVEN
        Employer employer = employerDAO.read().get(0);
        int lastId = vacancyDAO.read().get(vacancyDAO.read().size() - 1).getId();
        Vacancy expected = new Vacancy(lastId, employer,
                "Prime Minister of Great Britain",
                "In the past, a comedian", 99000, Date.valueOf("2023-01-03"));
//WHEN
        vacancyDAO.update(expected);
        Vacancy result = vacancyDAO.read().get(vacancyDAO.read().size() - 1);
//THEN
        assertEquals(expected, result);
    }
}
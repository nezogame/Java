package edu.dnu.fpm.pz;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployerDAOTest {
    private final InterfaceDAO<Employer> employerDAO = new EmployerDAO();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createEmployerTest() throws Exception {
//GIVEN
        Employer employer = new Employer("John", "Smith", "+380967819240");
//WHEN
        employerDAO.create(employer);
        Employer result = employerDAO.read().get(employerDAO.read().size() - 1);
//THEN
        assertEquals(employer, result);
    }

    @Test
    public void createEmployerTestBadPhone() throws Exception {
//GIVEN
        Employer employer = new Employer("John", "Doe", "+380");
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect phone number: " +
                employer.getPhone());
//WHEN
        employerDAO.create(employer);
    }

    @Test
    public void createEmployersTestBadSurname() throws Exception {
//GIVEN
        List<Employer> employers = new ArrayList<>();
        employers.add(new Employer("Peter", "Pan", "+380687872334"));
        employers.add(new Employer("Joanne", "", "+380962345678"));
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect surname: " + "");
//WHEN
        employerDAO.create(employers);
    }

    @Test
    public void createEmployersTestBadName() throws Exception {
//GIVEN
        Employer employers = new Employer("", "Pan", "+380687872334");
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect name: " + "");
//WHEN
        employerDAO.create(employers);
    }

    @Test
    public void deleteEmployerTest() throws Exception {
//GIVEN
        Employer employer = new Employer("Dmytro", "Hordienko", "+380967812520");
        employerDAO.create(employer);
        int currentSize = employerDAO.read().size();
//WHEN
        employerDAO.delete(employerDAO.read().get(currentSize - 1).getId());
        int resultSize = employerDAO.read().size();
//THEN
        assertEquals(currentSize - 1, resultSize);
    }

    @Test
    public void deleteEmployerExceptionOutOfBoundsTest() throws Exception {
//GIVEN
        int currentSize = employerDAO.read().size();
//WHEN
        expectedException.expect(java.lang.IndexOutOfBoundsException.class);
        employerDAO.delete(employerDAO.read().get(currentSize).getId());
//THEN
    }

    @Test
    public void updateEmployerTest() throws Exception {
//GIVEN
        Employer employer = new Employer("Dmytro", "Hordienko", "+380967812520");
        employerDAO.create(employer);
        int currentSize = employerDAO.read().size();
        int lastId = employerDAO.read().get(currentSize - 1).getId();
        Employer expected = new Employer(lastId, " Dmytro", "Matvienko",
                "+380967812520");
//WHEN
        employerDAO.update(expected);
        Employer result = employerDAO.read().get(currentSize - 1);
//THEN
        assertEquals(expected, result);
    }

    @Test
    public void updateEmployerBadSurnameTest() throws Exception {
//GIVEN
        Employer employer = new Employer("Dmytro", "Hordienko", "+380967812520");
        employerDAO.create(employer);
        int currentSize = employerDAO.read().size();
        int lastId = employerDAO.read().get(currentSize-1).getId();
        Employer expected = new Employer(lastId, " Dmytro", "",
                "+380967812520");
//WHEN
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect surname: "+"");
        employerDAO.update(expected);
    }
}
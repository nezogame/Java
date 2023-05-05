package edu.dnu.fpm.pz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacancyDAO implements InterfaceDAO<Vacancy> {
    private void validateVacancy(Vacancy entity) throws MyException {
        Validator.validatePosition(entity);
        Validator.validateSalary(entity);
    }

    final private String CREATE_SQL =
            "INSERT INTO " +
                    "vacancies(employer_id, position, description, salary, publication_date) " +
                    "VALUES (?, ?, ?, ?, ?)";

    public void create(Vacancy entity) throws MyException {
        validateVacancy(entity);
        try (Connection connection = Service.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_SQL);
            preparedStatement.setInt(1, entity.getEmployer().getId());
            preparedStatement.setString(2, entity.getPosition());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setInt(4, entity.getSalary());
            preparedStatement.setDate(5, entity.getPublicationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyException(
                    "class VacancyDAO method create(Vacancy entity)", e);
        }
    }

    public void create(List<Vacancy> entities) throws MyException {
        try (Connection connection = Service.getConnection()) {
            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_SQL);
            for (Vacancy vacancy : entities) {
                validateVacancy(vacancy);
                preparedStatement.setInt(1, vacancy.getEmployer().getId());
                preparedStatement.setString(2, vacancy.getPosition());
                preparedStatement.setString(3, vacancy.getDescription());
                preparedStatement.setInt(4, vacancy.getSalary());
                preparedStatement.setDate(5, vacancy.getPublicationDate());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new MyException(
                    "class VacancyDAO method create(List<Vacancy> entities)", e);
        }
    }

    public List<Vacancy> read() throws MyException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = Service.getConnection()) {
            Statement statement = connection.createStatement();
            String READ_SQL =
                    "SELECT * FROM vacancies " +
                            "LEFT JOIN employers ON vacancies.employer_id = employers.id";
            ResultSet resultSet = statement.executeQuery(READ_SQL);
            while (resultSet.next()) {
                Vacancy vacancy = new Vacancy();
                Employer employer = new Employer();
                employer.setId(resultSet.getInt("employers.id"));
                employer.setName(resultSet.getString("employers.name"));
                employer.setSurname(resultSet.getString("employers.surname"));
                employer.setPhone(resultSet.getString("employers.phone"));
                vacancy.setId(resultSet.getInt("vacancies.id"));
                vacancy.setEmployer(employer);
                vacancy.setPosition(resultSet.getString("vacancies.position"));
                vacancy.setDescription(resultSet.getString("vacancies.description"));
                vacancy.setSalary(resultSet.getInt("vacancies.salary"));
                vacancy.setPublicationDate(resultSet.getDate(
                        "vacancies.publication_date"));
                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            throw new MyException("class VacancyDAO method read()", e);
        }
        return vacancies;
    }

    public void update(Vacancy entity) throws MyException {
        validateVacancy(entity);
        try (Connection connection = Service.getConnection()) {
            String UPDATE_SQL =
                    "UPDATE vacancies SET employer_id=?, position=?, " +
                            "description=?, salary=?, publication_date=?" +
                            "WHERE id=?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setInt(1, entity.getEmployer().getId());
            preparedStatement.setString(2, entity.getPosition());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setInt(4, entity.getSalary());
            preparedStatement.setDate(5, entity.getPublicationDate());
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyException(
                    "class VacancyDAO method update(Vacancy entity)", e);
        }
    }

    public void update(List<Vacancy> entities) throws MyException {
        for (Vacancy vacancy : entities) {
            update(vacancy);
        }
    }

    public void delete(int entityId) throws MyException {
        try (Connection connection = Service.getConnection()) {
            String DELETE_SQL = "DELETE FROM vacancies WHERE id=?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, entityId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyException("class VacancyDAO method delete(int entityId)", e);
        }
    }
}

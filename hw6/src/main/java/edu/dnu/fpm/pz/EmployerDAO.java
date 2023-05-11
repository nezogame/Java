package edu.dnu.fpm.pz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerDAO implements InterfaceDAO<Employer> {
    private void validateEmployer(Employer entity) throws MyException {
        Validator.validateNumber(entity);
        Validator.validateName(entity);
        Validator.validateSurname(entity);
    }

    final private String CREATE_SQL =
            "INSERT INTO employment.employers(name, surname, phone) " +
                    "VALUES (?, ?, ?)";

    public void create(Employer entity) throws MyException {
        validateEmployer(entity);
        try (Connection connection = Service.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_SQL);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyException(
                    "class EmployerDAO method create(Employer entity)", e);
        }
    }

    public void create(List<Employer> entities) throws MyException {
        try (Connection connection = Service.getConnection()) {
            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_SQL);
            for (Employer employer : entities) {
                validateEmployer(employer);
                preparedStatement.setString(1, employer.getName());
                preparedStatement.setString(2, employer.getSurname());
                preparedStatement.setString(3, employer.getPhone());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new MyException(
                    "class EmployerDAO method create(List<Employer> entities)", e);
        }
    }

    public List<Employer> read() throws MyException {
        List<Employer> employers = new ArrayList<>();
        try (Connection connection = Service.getConnection()) {
            String READ_SQL = "SELECT * FROM employment.employers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(READ_SQL);
            while (resultSet.next()) {
                Employer employer = new Employer();
                employer.setId(resultSet.getInt("id"));
                employer.setName(resultSet.getString("name"));
                employer.setSurname(resultSet.getString("surname"));
                employer.setPhone(resultSet.getString("phone"));
                employers.add(employer);
            }
        } catch (SQLException e) {
            throw new MyException("class EmployerDAO method read()", e);
        }
        return employers;
    }
    public Employer getById(int id) throws MyException {
        Employer employer = new Employer();
        try (Connection connection = Service.getConnection()) {
            String READ_SQL = "select * FROM employment.employers WHERE id=?";
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(READ_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employer.setId(resultSet.getInt("id"));
                employer.setName(resultSet.getString("name"));
                employer.setSurname(resultSet.getString("surname"));
                employer.setPhone(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            throw new MyException(
                    "class EmployerDAO method getById(int id)", e);
        }
        return employer;
    }
    public void update(Employer entity) throws MyException {
        validateEmployer(entity);
        try (Connection connection = Service.getConnection()) {
            String UPDATE_SQL =
                    "UPDATE employment.employers SET name=?, surname=?, phone=?" +

                            "WHERE id=?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyException(
                    "class EmployerDAO method update(Employer entity)", e);
        }
    }

    public void update(List<Employer> entities) throws MyException {
        for (Employer employer : entities) {
            update(employer);
        }
    }

    public void delete(int entityId) throws MyException {
        try (Connection connection = Service.getConnection()) {
            String DELETE_SQL = "DELETE FROM employment.employers WHERE id=?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, entityId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyException(
                    "class EmployerDAO method delete(int entityId)", e);
        }
    }
}

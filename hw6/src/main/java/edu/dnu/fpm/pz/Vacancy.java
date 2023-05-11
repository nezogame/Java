package edu.dnu.fpm.pz;

import java.sql.Date;
import java.util.Objects;

public class Vacancy {
    private int id;
    private Employer employer;
    private int employerId;
    private String position;
    private String description;
    private int salary;
    private Date publicationDate;

    Vacancy() {
        id = 0;
        employerId = 0;
        employer = new Employer();
        position = "";
        description = "";
        salary = 0;
        publicationDate = new Date(System.currentTimeMillis());
    }

    Vacancy(Employer employer, String position, String description,
            int salary, Date date) {
        this.id = 0;
        this.employer = employer;
        this.employerId = employer.getId();
        this.position = position;
        this.description = description;
        this.salary = salary;
        this.publicationDate = date;
    }

    Vacancy(int id, Employer employer, String position, String description,
            int salary, Date date) {
        this.id = id;
        this.employer = employer;
        this.employerId = employer.getId();
        this.position = position;
        this.description = description;
        this.salary = salary;
        this.publicationDate = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", employer=" + employer.toString() +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", publicationDate=" + publicationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return salary == vacancy.salary &&
                employer.equals(vacancy.employer) &&
                position.equals(vacancy.position) &&
                description.equals(vacancy.description) &&
                publicationDate.equals(vacancy.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employer, position, description, salary,
                publicationDate);
    }
}

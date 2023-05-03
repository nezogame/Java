package edu.dnu.fpm.pz;

import java.util.Objects;

public class Employer {
    private int id;
    private String name;
    private String surname;
    private String phone;

    Employer() {
        id = 0;
        name = "";
        surname = "";
        phone = "";
    }

    Employer(String name, String surname, String phone) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    Employer(int id, String name, String surname, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
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
        Employer employer = (Employer) o;
        return name.equals(employer.name) &&
                surname.equals(employer.surname) &&
                phone.equals(employer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone);
    }
}

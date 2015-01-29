package com.salm.domain;

import com.salm.enums.PersonStatus;
import com.sun.istack.internal.NotNull;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ysalmin on 06.08.2014.
 */
@Document
public class Person extends BaseEntity {

    @NotNull
    private String name;

    @DateTimeFormat(style = "M-")
    private Date birthday;

    private String email;

    private String workPhone;

    private String homePhone;

    private String cellPhone;

    private String department;

    private PersonStatus status;

    private String photo;

    private String login;

    private List<Person> personsGoodWorkWith;

    private List<Task> tasks;

    private List<Project> projects;

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setStatus(PersonStatus status) {
        this.status = status;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPersonsGoodWorkWith(List<Person> personsGoodWorkWith) {
        this.personsGoodWorkWith = personsGoodWorkWith;
    }

    public String getName() {
        return name;
    }

    public List<Person> getPersonsGoodWorkWith() {
        return personsGoodWorkWith;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }


}

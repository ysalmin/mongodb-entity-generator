package com.salm.domain;

import com.salm.enums.ProjectStatus;
import com.sun.istack.internal.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by ysalmin on 06.08.2014.
 */
@Document
public class Project extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private Person manager;

    @NotNull
    @DateTimeFormat(style = "M-")
    private Date starts;

    @DateTimeFormat(style = "M-")
    private Date ends;

    private ProjectStatus status;

    @NotNull
    private List<Person> involvedPersons;

    @NotNull
    private List<Task> tasks;

    private Double budget;

    public void setName(String name) {
        this.name = name;
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }

    public void setEnds(Date ends) {
        this.ends = ends;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public void setInvolvedPersons(List<Person> involvedPersons) {
        this.involvedPersons = involvedPersons;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<Person> getInvolvedPersons() {
        return involvedPersons;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


}

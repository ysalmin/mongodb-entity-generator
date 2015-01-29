package com.salm.domain;

import com.salm.enums.TaskStatus;
import com.sun.istack.internal.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by ysalmin on 06.08.2014.
 */
@Document
public class Task extends BaseEntity {
    @NotNull
    private String name;

    @NotNull
    @DateTimeFormat(style = "M-")
    private Date starts;

    @DateTimeFormat(style = "M-")
    private Date ends;

    private TaskStatus status;

    private String description;

    @NotNull
    private Project project;

    private List<Record> records;

    @NotNull
    private Person responsiblePerson;

    public void setName(String name) {
        this.name = name;
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }

    public void setEnds(Date ends) {
        this.ends = ends;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Person responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}

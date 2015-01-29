package com.salm.repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.salm.domain.Person;
import com.salm.domain.Project;
import com.salm.domain.Record;
import com.salm.domain.Task;
import com.salm.helpers.Randomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class PmRepository {
    static final Logger logger = LoggerFactory.getLogger(PmRepository.class);

    @Autowired
    MongoTemplate mongoTemplate;

    private void fillPersons(int num) throws ParseException {
        for (int i = 0; i < num; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName(Randomizer.getRandomName() + " " + Randomizer.getRandomSurname());
            person.setBirthday(Randomizer.getRandomDate());
            person.setCellPhone(Randomizer.getRandomPhone());
            person.setEmail(person.getName().replace(" ", ".").toLowerCase() + "@mail.com");
            person.setDepartment(Randomizer.getRandomDepartment());
            person.setHomePhone(Randomizer.getRandomPhone());
            person.setLogin(person.getName().substring(person.getName().indexOf(" ") + 1).toLowerCase());
            person.setPhoto(person.getName().replace(" ", "_").toLowerCase() + ".jpg");
            person.setWorkPhone(Randomizer.getRandomPhone());
            person.setStatus(Randomizer.getRandomPersonStatus());
            person.setPersonsGoodWorkWith(new ArrayList<Person>());
            person.setProjects(new ArrayList<Project>());
            mongoTemplate.save(person);
        }
        for (int i = 0; i < num; i++) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(i));

            Person person = mongoTemplate.findOne(query, Person.class);
            Random random = new Random();

            for (int j = 0; j < random.nextInt(3); j++) {
                Query q = new Query();
                if (i < 2) {
                    q.addCriteria(Criteria.where("_id").is(random.nextInt(i + 1)));
                } else {
                    q.addCriteria(Criteria.where("_id").is(random.nextInt(i - 1)));
                }
                Person friend = mongoTemplate.findOne(q, Person.class);
                List<Person> persons = person.getPersonsGoodWorkWith();
                persons.add(friend);
                update("personsGoodWorkWith", persons, query, Person.class);
            }
        }
    }

    private void update(String collectionName, Object collection, Query query, Class<?> entityClass) {
        Update update = new Update();
        update.set(collectionName, collection);
        mongoTemplate.updateFirst(query, update, entityClass);
    }

    private void fillProjects(int num) throws ParseException {
        for (int i = 0; i < num; i++) {
            Project project = new Project();
            project.setId(i);
            project.setBudget(Randomizer.getRandomBudget());
            project.setName(Randomizer.getRandomProjectName());
            project.setInvolvedPersons(new ArrayList<Person>());
            project.setStatus(Randomizer.getRandomProjectStatus());
            Date startDate = Randomizer.getRandomDate();
            project.setStarts(startDate);
            project.setEnds(Randomizer.getRandomEndDate(startDate));
            project.setTasks(new ArrayList<Task>());
            mongoTemplate.save(project);
        }
        for (int i = 0; i < num; i++) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(i));

            Project project = mongoTemplate.findOne(query, Project.class);

            for (int j = 0; j < new Random().nextInt(2) + 1; j++) {
                Query q1 = new Query();
                q1.addCriteria(Criteria.where("_id").is(new Random().nextInt(99999)));
                Person person = mongoTemplate.findOne(q1, Person.class);

                List<Project> projects = new ArrayList<Project>();
                projects.add(project);
                update("projects", projects, q1, Person.class);

                List<Person> persons = project.getInvolvedPersons();
                persons.add(person);
                update("involvedPersons", persons, query, Project.class);
            }
            Query q1 = new Query();
            q1.addCriteria(Criteria.where("_id").is(new Random().nextInt(99999)));
            Person person = mongoTemplate.findOne(q1, Person.class);
            update("manager", person, query, Project.class);
        }
    }

    private void fillTasks(int num) throws ParseException {
        for (int i = 0; i < num; i++) {
            Task task = new Task();
            task.setId(i);
            task.setName(Randomizer.getRandomTaskName());
            task.setDescription(Randomizer.getRandomTaskDescription());
            Date startDate = Randomizer.getRandomDate();
            task.setStarts(startDate);
            task.setEnds(Randomizer.getRandomEndDate(startDate));
            task.setStatus(Randomizer.getRandomTaskStatus());
            task.setRecords(new ArrayList<Record>());
            mongoTemplate.save(task);
        }

        for (int i = 0; i < num; i++) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(i));
            Task task = mongoTemplate.findOne(query, Task.class);

            Random random = new Random();

            Query q = new Query();
            q.addCriteria(Criteria.where("_id").is(random.nextInt(99999)));
            Person responsiblePerson = mongoTemplate.findOne(q, Person.class);

            Query q1 = new Query();
            q1.addCriteria(Criteria.where("_id").is(random.nextInt(9999)));
            Project project = mongoTemplate.findOne(q1, Project.class);

            update("responsiblePerson", responsiblePerson, query, Task.class);
            update("project", project, query, Task.class);

            List<Task> tasks = project.getTasks();
            tasks.add(task);
            update("tasks", tasks, q1, Project.class);
        }
    }

    private void fillRecords(int num) throws ParseException {
        for (int i = 0; i < num; i++) {
            Record record = new Record();
            record.setId(i);
            record.setDescription(Randomizer.getRandomRecordDescription());
            record.setDate(Randomizer.getRandomDate());
            record.setHoursSpent(new Random().nextInt(1000));
            mongoTemplate.save(record);
        }

        for (int i = 0; i < num; i++) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(i));
            Record record = mongoTemplate.findOne(query, Record.class);

            Random random = new Random();
            int personId = random.nextInt(99999);
            update("personId", personId, query, Record.class);

            int projectId = random.nextInt(9999);
            update("projectId", projectId, query, Record.class);

            Query q = new Query();
            int taskId = random.nextInt(199999);
            q.addCriteria(Criteria.where("_id").is(taskId));
            Task task = mongoTemplate.findOne(q, Task.class);
            update("taskId", taskId, query, Record.class);

            List<Record> records = task.getRecords();
            records.add(record);
            update("records", records, q, Task.class);
        }
    }

    public void fillMongo() throws ParseException {
        fillPersons(100000);
        fillProjects(10000);
        fillTasks(200000);
        fillRecords(1000000);
    }


}
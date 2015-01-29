package com.salm.helpers;

import com.salm.domain.Project;
import com.salm.enums.PersonStatus;
import com.salm.enums.ProjectStatus;
import com.salm.enums.TaskStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ysalmin on 07.08.2014.
 */
public class Randomizer {

    public static String getRandomName() {
        return RandomInfo.name[new Random().nextInt(RandomInfo.name.length - 1)];
    }

    public static String getRandomSurname() {
        return RandomInfo.surname[new Random().nextInt(RandomInfo.surname.length - 1)];
    }

    public static String getRandomTaskDescription() {
        return RandomInfo.taskDescription[new Random().nextInt(RandomInfo.taskDescription.length - 1)];
    }

    public static String getRandomRecordDescription() {
        return RandomInfo.recordDescription[new Random().nextInt(RandomInfo.recordDescription.length - 1)];
    }

    public static String getRandomDepartment() {
        return RandomInfo.department[new Random().nextInt(RandomInfo.department.length - 1)];
    }

    public static String getRandomTaskName() {
        return RandomInfo.taskName[new Random().nextInt(RandomInfo.taskName.length - 1)];
    }

    public static String getRandomProjectName() {
        return RandomInfo.projectName[new Random().nextInt(RandomInfo.projectName.length - 1)];
    }

    public static PersonStatus getRandomPersonStatus() {
        return PersonStatus.values()[new Random().nextInt(PersonStatus.values().length)];
    }

    public static ProjectStatus getRandomProjectStatus() {

        return ProjectStatus.values()[new Random().nextInt(ProjectStatus.values().length)];
    }

    public static TaskStatus getRandomTaskStatus() {

        return TaskStatus.values()[new Random().nextInt(TaskStatus.values().length)];
    }

    public static Double getRandomBudget() {
        return new Random().nextDouble() * 1000000;

    }

    public static Date getRandomEndDate(Date startDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, new Random().nextInt(60) + 5);
        return c.getTime();
    }

    public static Date getRandomDate() throws ParseException {
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1971, 1996);
        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        return df.parse(gc.get(gc.DAY_OF_MONTH) + "-" + gc.get(gc.MONTH) + "-" + gc.get(gc.YEAR));
    }
    public static String getRandomPhone() throws ParseException {
        return "+380" + String.format("%d", new Random().nextInt(89999999) + 10000000);
    }

    private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}

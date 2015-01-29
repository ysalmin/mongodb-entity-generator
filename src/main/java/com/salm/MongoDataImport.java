package com.salm;

import com.salm.repository.PmRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;

public class MongoDataImport {
	public static void main(String[] args) throws ParseException {

        ApplicationContext context = new AnnotationConfigApplicationContext(MongoConfiguration.class);

        PmRepository pmRepository = context.getBean(PmRepository.class);

        pmRepository.fillMongo();
    }
}

package com.sillibus.web;

import com.mysql.jdbc.Driver;
import com.sillibus.web.model.*;
import com.sillibus.web.model.dto.UserType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.ArrayList;

@RestController
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan
@EnableJpaRepositories
@SpringBootApplication
@PropertySource ({ "classpath:local.properties", "classpath:global.properties" })
public class Application extends SpringBootServletInitializer {
	@Autowired AssignmentRepository assignmentRepository;
	@Autowired CourseRepository     courseRepository;
	String databaseDriver = Driver.class.getName();
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired UserRepository userRepository;

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory () {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPersistenceUnitName("jpaPersistenceUnit");
		entityManagerFactory.setPackagesToScan("com.sillibus.web", "com.sillibus.web.model");
		return entityManagerFactory;
	}

	@Bean
	public DataSource dataSource () {
		/*logger.info("databaseUrl: " + databaseUrl);
		logger.info("databaseUsername: " + databaseUsername);
		logger.info("databasePassword: " + databasePassword);
		logger.info("databaseDriver: " + databaseDriver);*/
		/*return DataSourceBuilder.create()
								.url(env.getProperty("prayforme.database.url"))
								.username(env.getProperty("prayforme.database.username"))
								.password(env.getProperty("prayforme.database.password"))
								.driverClassName(databaseDriver)
								.build();*/
		return DataSourceBuilder.create()
								.url("jdbc:mysql://localhost:3306/sillibus")
								.username("root")
								.password("root")
								.driverClassName(databaseDriver)
								.build();
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation () {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	public static void main (String[] args) {
		SpringApplication.run(Application.class);
	}

	@RequestMapping ("/makeFakeData")
	public void makeFakeData () {
		User professor = userRepository.save(new User(1, "anemail@adomain.com", "Maureen", "Burkhart", "1234", UserType.Professor, "prof1"));
		User professor2 = userRepository.save(new User(2, "anemail@adomain.com", "Bill", "Murray", "1234", UserType.Professor, "prof2"));
		User professor3 = userRepository.save(new User(3, "anemail@adomain.com", "Will", "Smith", "1234", UserType.Professor, "prof3"));
		User professor4 = userRepository.save(new User(4, "anemail@adomain.com", "Bill", "Gates", "1234", UserType.Professor, "prof4"));
		User professor5 = userRepository.save(new User(5, "anemail@adomain.com", "Fred", "Smith", "1234", UserType.Professor, "prof5"));
		User professor6 = userRepository.save(new User(6, "anemail@adomain.com", "Bob", "Chambers", "1234", UserType.Professor, "prof6"));
	}

/*
	@Bean
	public InternalResourceViewResolver viewResolver () {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("");
		resolver.setSuffix(".jsp");
		return resolver;
	}
*/

	@RequestMapping ("/makeFakeData2")
	public void makeFakeData2 () {
		int asdf = 7;
		User student1 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Maureen", "Burkhart", "1234", UserType.Student, "student1"));
		User student2 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bill", "Murray", "1234", UserType.Student, "student2"));
		User student3 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Will", "Smith", "1234", UserType.Student, "student3"));
		User student4 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bill", "Gates", "1234", UserType.Student, "student4"));
		User student5 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Fred", "Smith", "1234", UserType.Student, "student5"));
		User student6 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bob", "Chambers", "1234", UserType.Student, "student6"));
		User student7 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Maureen", "Burkhart", "1234", UserType.Student, "student7"));
		User student8 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bill", "Murray", "1234", UserType.Student, "student8"));
		User student9 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Will", "Smith", "1234", UserType.Student, "student9"));
		User student10 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bill", "Gates", "1234", UserType.Student, "student10"));
		User student11 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Fred", "Smith", "1234", UserType.Student, "student11"));
		User student12 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bob", "Chambers", "1234", UserType.Student, "student12"));
		User student13 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Maureen", "Burkhart", "1234", UserType.Student, "student13"));
		User student14 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bill", "Murray", "1234", UserType.Student, "student14"));
		User student15 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Will", "Smith", "1234", UserType.Student, "student15"));
		User student16 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bill", "Gates", "1234", UserType.Student, "student16"));
		User student17 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Fred", "Smith", "1234", UserType.Student, "student17"));
		User student18 = userRepository.save(new User(asdf++, "anemail@adomain.com", "Bob", "Chambers", "1234", UserType.Student, "student18"));

		ArrayList<User> studentsGroup1 = new ArrayList<>();
		studentsGroup1.add(student1);
		studentsGroup1.add(student2);
		studentsGroup1.add(student3);
		studentsGroup1.add(student4);
		studentsGroup1.add(student5);
		studentsGroup1.add(student6);
		studentsGroup1.add(student7);
		studentsGroup1.add(student8);
		studentsGroup1.add(student9);
		studentsGroup1.add(student10);
		studentsGroup1.add(student11);
		studentsGroup1.add(student12);
		studentsGroup1.add(student13);
		studentsGroup1.add(student14);
		studentsGroup1.add(student15);

		ArrayList<User> studentsGroup2 = new ArrayList<>();
		studentsGroup2.add(student1);
		studentsGroup2.add(student7);
		studentsGroup2.add(student8);
		studentsGroup2.add(student9);
		studentsGroup2.add(student10);
		studentsGroup2.add(student11);
		studentsGroup2.add(student12);
		studentsGroup2.add(student13);
		studentsGroup2.add(student14);
		studentsGroup2.add(student15);

		ArrayList<User> studentsGroup3 = new ArrayList<>();
		studentsGroup3.add(student1);
		studentsGroup3.add(student4);
		studentsGroup3.add(student5);
		studentsGroup3.add(student6);
		studentsGroup3.add(student7);
		studentsGroup3.add(student8);
		studentsGroup3.add(student16);
		studentsGroup3.add(student17);
		studentsGroup3.add(student18);

		User professor = userRepository.findOne(1L);
		User professor2 = userRepository.findOne(2L);
		User professor3 = userRepository.findOne(3L);
		User professor4 = userRepository.findOne(4L);
		User professor5 = userRepository.findOne(5L);
		User professor6 = userRepository.findOne(6L);

		Course course1 = courseRepository.save(new Course(123423, asdf++, "Phys 2212", professor, studentsGroup1));
		Course course2 = courseRepository.save(new Course(567884, asdf++, "Chem 1112", professor2, studentsGroup2));
		Course course3 = courseRepository.save(new Course(234543, asdf++, "Engl 1101", professor3, studentsGroup3));
		Course course4 = courseRepository.save(new Course(756887, asdf++, "Calc 2212", professor4, studentsGroup2));
		Course course5 = courseRepository.save(new Course(425345, asdf++, "Span 1000", professor5, studentsGroup1));
		Course course6 = courseRepository.save(new Course(103852, asdf++, "Psyc 1101", professor6, studentsGroup1));
		Course course7 = courseRepository.save(new Course(883846, asdf++, "Hons 1000", professor5, studentsGroup3));
		Course course8 = courseRepository.save(new Course(234543, asdf++, "Pols 1101", professor4, studentsGroup3));
		Course course9 = courseRepository.save(new Course(834720, asdf++, "Gsu 1010", professor3, studentsGroup2));
		Course course10 = courseRepository.save(new Course(345234, asdf++, "CSC 2310", professor2, studentsGroup3));
		Course course11 = courseRepository.save(new Course(284057, asdf++, "CSC 2010", professor, studentsGroup1));

		Assignment assignment1 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", 0, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment2 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment3 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment4 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment5 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment6 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment7 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment8 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment9 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment10 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment11 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));

		ArrayList<Assignment> assignments = new ArrayList<>();
		assignments.add(assignment1);
		ArrayList<Assignment> assignments2 = new ArrayList<>();
		assignments2.add(assignment2);
		ArrayList<Assignment> assignments3 = new ArrayList<>();
		assignments3.add(assignment3);
		ArrayList<Assignment> assignments4 = new ArrayList<>();
		assignments4.add(assignment4);
		ArrayList<Assignment> assignments5 = new ArrayList<>();
		assignments5.add(assignment5);
		ArrayList<Assignment> assignments6 = new ArrayList<>();
		assignments6.add(assignment6);
		ArrayList<Assignment> assignments7 = new ArrayList<>();
		assignments7.add(assignment7);
		ArrayList<Assignment> assignments8 = new ArrayList<>();
		assignments8.add(assignment8);
		ArrayList<Assignment> assignments9 = new ArrayList<>();
		assignments9.add(assignment9);
		ArrayList<Assignment> assignments10 = new ArrayList<>();
		assignments10.add(assignment10);
		ArrayList<Assignment> assignments11 = new ArrayList<>();
		assignments11.add(assignment11);

		course1.setAssignments(assignments);
		course2.setAssignments(assignments2);
		course3.setAssignments(assignments3);
		course4.setAssignments(assignments4);
		course5.setAssignments(assignments5);
		course6.setAssignments(assignments6);
		course7.setAssignments(assignments7);
		course8.setAssignments(assignments8);
		course9.setAssignments(assignments9);
		course10.setAssignments(assignments10);
		course11.setAssignments(assignments11);

		courseRepository.save(course1);
		courseRepository.save(course2);
		courseRepository.save(course3);
		courseRepository.save(course4);
		courseRepository.save(course5);
		courseRepository.save(course6);
		courseRepository.save(course7);
		courseRepository.save(course8);
		courseRepository.save(course9);
		courseRepository.save(course10);
		courseRepository.save(course11);
	}

	@RequestMapping ("/makeFakeData3")
	public void makeFakeData3 () {
		User professor = userRepository.findOne(1L);
		ArrayList<User> studentsGroup1 = new ArrayList<>();
		studentsGroup1.add(userRepository.findOne(14L));
		Course course1 = courseRepository.save(new Course(123423, 0, "Phys 2212", professor, studentsGroup1));
		Course course2 = courseRepository.save(new Course(567884, 1, "Chem 1112", professor, studentsGroup1));
		Course course3 = courseRepository.save(new Course(234543, 2, "Engl 1101", professor, studentsGroup1));
		Course course4 = courseRepository.save(new Course(756887, 3, "Calc 2212", professor, studentsGroup1));
		Course course5 = courseRepository.save(new Course(425345, 4, "Span 1000", professor, studentsGroup1));
		Course course6 = courseRepository.save(new Course(103852, 5, "Psyc 1101", professor, studentsGroup1));
	}

	@RequestMapping ("/makeFakeData4")
	public void makeFakeData4 () {
		User professor = userRepository.findOne(1L);
		ArrayList<User> studentsGroup1 = new ArrayList<>();
		studentsGroup1.add(userRepository.findOne(14L));
		Course course1 = courseRepository.save(new Course(123423, 0, "Phys 2212", professor, studentsGroup1));
		Course course2 = courseRepository.save(new Course(567884, 1, "Chem 1112", professor, studentsGroup1));
		Course course3 = courseRepository.save(new Course(234543, 2, "Engl 1101", professor, studentsGroup1));
		Course course4 = courseRepository.save(new Course(756887, 3, "Calc 2212", professor, studentsGroup1));
		Course course5 = courseRepository.save(new Course(425345, 4, "Span 1000", professor, studentsGroup1));
		Course course6 = courseRepository.save(new Course(103852, 5, "Psyc 1101", professor, studentsGroup1));

		Assignment assignment1 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "ssdf", 0, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment2 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "ssdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment3 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "ssdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment4 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "ssdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment5 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "ssdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));
		Assignment assignment6 = assignmentRepository.save(new Assignment(Assignment.AssignmentType.Homework, "sdfsdf", -1, Assignment.ImportanceLevel.High, "NOTES! YEA!", "This is the topic"));

		ArrayList<Assignment> assignments = new ArrayList<>();

		assignments.add(assignment1);
		assignments.add(assignment2);
		assignments.add(assignment3);
		assignments.add(assignment4);
		assignments.add(assignment5);
		assignments.add(assignment6);

		course1.setAssignments(assignments);
		course2.setAssignments(assignments);
		course3.setAssignments(assignments);
		course4.setAssignments(assignments);
		course5.setAssignments(assignments);
		course6.setAssignments(assignments);

		courseRepository.save(course1);
		courseRepository.save(course2);
		courseRepository.save(course3);
		courseRepository.save(course4);
		courseRepository.save(course5);
		courseRepository.save(course6);
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter () {
		return new MappingJackson2HttpMessageConverter();
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter () {
		return new StringHttpMessageConverter();
	}
}
package com.sillibus.web;

import com.google.gson.Gson;
import com.sillibus.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 Created by joshua on 1/16/16.
 */
@RestController
public class PDFParsingController {
	@Autowired AssignmentRepository assignmentRepository;
	@Autowired CourseRepository     courseRepository;
	@Autowired UserRepository       userRepository;

	@RequestMapping (value = "/upload", method = RequestMethod.POST)
	public
	@ResponseBody
	String handleFileUpload (@RequestParam ("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				Pair<ArrayList<SyllabusDate>, Pair<String, String>> stuff = MyPDFParser.parsePdf(file.getInputStream());
				ArrayList<SyllabusDate> syllabusDates = stuff.getLeft();
				Pair<String, String> courseNameAndEmailPair = stuff.getRight();
				String courseName = courseNameAndEmailPair.getLeft();
				String email = courseNameAndEmailPair.getRight();

				/*User professor = new User(0, email, null, null, null, UserType.Professor, null);
userRepository.*/
				Course course = new Course(0, 0, courseName, null, null);
				course = courseRepository.save(course);
				syllabusDates.stream()
							 .map(syllabusDate -> new Assignment(Assignment.AssignmentType.valueOf(syllabusDate.type), syllabusDate.date, 0L, Assignment.ImportanceLevel.Unknown, "No note", "No topic"))
							 .forEach(assignment -> assignmentRepository.save(assignment));

				Gson gson = new Gson();
				String syllabusDatesJson = gson.toJson(syllabusDates);
				String js = "var professorEmail='" + email + "';var courseName='" + courseName + "';var syllabusDates=JSON.parse('" + syllabusDatesJson + "');";

				return js;
			} catch (Exception e) {
				return "You failed to upload  => " + e.getMessage();
			}
		} else {
			return "You failed to upload your mom because the file was empty.";
		}
	}
}

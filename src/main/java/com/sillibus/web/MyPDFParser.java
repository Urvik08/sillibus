package com.sillibus.web;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyPDFParser {
	public static Pair<ArrayList<SyllabusDate>, Pair<String, String>> parsePdf (InputStream inputStream) {
		ArrayList<SyllabusDate> dateList = new ArrayList();
		String sexyText = pdfExtraction(inputStream);
		ArrayList<SyllabusDate> newDateList = getTimes(sexyText, dateList);

		ArrayList<SyllabusDate> newerDateList = cleanDates(newDateList);

		for (SyllabusDate date : newerDateList) {
			String newDateType = checkType(sexyText, date.getNewStart(), date.getNewEnd());
			date.setType(newDateType);
		}
		newerDateList.forEach(System.out::print);
		return new Pair<>(newerDateList, new Pair<>(getCourseName(sexyText), getEmail(sexyText)));
	}

	public static String pdfExtraction (InputStream fileIS) {
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		try {
			PDFParser parser = new PDFParser(fileIS);
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(10);
			return pdfStripper.getText(pdDoc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static ArrayList<SyllabusDate> getTimes (String pdfText, ArrayList<SyllabusDate> dateList) {
		Pattern p = Pattern.compile("((\\d+(st|nd|rd|th)\\s)?(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sept|Oct|Nov|Dec)(\\s|(uary|ruary|ch|il|e|y|ust|ember|ober))*(,*\\s?\\d)+)|((1)?[1-9]{1}(/|-)\\\\d{1,2}((/|-)\\\\d{1,4})*)");
		Matcher m = p.matcher(pdfText);

//        List<String> dates = new ArrayList<String>();
		while (m.find()) {
//            System.out.println("Found date: " + m.group());
			dateList.add(new SyllabusDate(m.group(), m.start(), m.end()));
//            dateList.add(m.group());
		}
		return dateList;
	}

	public static ArrayList<SyllabusDate> cleanDates (ArrayList<SyllabusDate> dateList) {
		Set<SyllabusDate> justDates = dateList.stream().collect(Collectors.toSet());
		return justDates.stream().collect(Collectors.toCollection(ArrayList::new));
	}

	public static String checkType (String text, int startIndex, int endIndex) {
		if (0 > startIndex) {
			startIndex = 0;
		} else if (text.length() - 1 < endIndex) {
			endIndex = text.length() - 1;
		}
//        System.out.println(startIndex);
		String subStr = text.substring(startIndex, endIndex);
		Pattern p = Pattern.compile("(Test|test|Homework|homework|Quiz|quiz|Project|project|Important|important|Exam|exam|Due|due|Paper|paper|Essay|essay)");
		Matcher m = p.matcher(subStr);
		if (m.find()) {
			String output = m.group();
			output = output.toLowerCase();
			if (output.equals("due") || output.equals("essay") || output.equals("paper")) {
				output = "Important";
			}
			return output.substring(0, 1).toUpperCase() + output.substring(1); //makes sure that new string is capitalized
		} else {
			return "Unknown";
		}
	}

	public static String getCourseName (String text) {
		Pattern p = Pattern.compile("(?!Spring)[A-Z]+[A-Za-z]+\\s*(?!2016)(\\d{4})");
		Matcher m = p.matcher(text);
		m.find();
		String courseName = m.group();
		if (courseName.length() == 0) {
			courseName = "Course Name not Found";
		}
		return courseName;
	}

	public static String getEmail (String text) {
		Pattern p = Pattern.compile("[A-Za-z\\d\\.\\_]+@[A-Za-z\\d\\.\\_]+\\.[A-Za-z]{2,4}");
		Matcher m = p.matcher(text);
		m.find();
		String emailAddress = m.group();
		if (emailAddress.length() == 0) {
			emailAddress = "Email Not Found";
		}
		return emailAddress;
	}
}

package com.springBatch.www2;

import java.util.Arrays;
import java.util.Comparator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exam {
	private String className;
	private String studentName;
	
	private double score; // 시험 점수 0~100점
	
	private String grade; // 시험 점수 스케일 A~F
	
	@Getter
	public static enum Grade {
		A("A", 90), B("B", 80), C("C", 70), D("D", 60), F("F", 0);
		
		private final String grade;
		private final double score;
		
		private Grade(String grade, double score) {
			this.grade = grade;
			this.score = score;
		}
		
		public static String converScore(double score) {
			return Arrays.stream(values())
					     .filter(grade -> grade.score < score)
					     .max(Comparator.comparing(grade -> grade.score)).get().getGrade();
		}
	}
}

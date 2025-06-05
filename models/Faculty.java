package models;

import java.util.*;

public class Faculty {
    private String name;
    private List<String> subjectCodes;
    private int maxLecturesPerDay;
    private Map<String, Integer> dayLectureCount;

    public Faculty(String name, List<String> subjectCodes, int maxLecturesPerDay) {
        this.name = name;
        this.subjectCodes = subjectCodes;
        this.maxLecturesPerDay = maxLecturesPerDay;
        this.dayLectureCount = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public boolean canTeach(String subjectCode) {
        return subjectCodes.contains(subjectCode);
    }

    public boolean canTeachMoreToday(String day) {
        return dayLectureCount.getOrDefault(day, 0) < maxLecturesPerDay;
    }

    public void assignLecture(String day) {
        dayLectureCount.put(day, dayLectureCount.getOrDefault(day, 0) + 1);
    }
}

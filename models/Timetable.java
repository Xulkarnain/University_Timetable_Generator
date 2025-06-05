package models;

import java.util.*;

public class Timetable {
    private Map<String, Map<String, String>> table;

    public Timetable() {
        table = new LinkedHashMap<>();

        // Days
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};

        // Time Slots
        String[] slots = {"9AM", "10AM", "11AM", "12PM", "2PM", "3PM"};

        for (String day : days) {
            table.put(day, new LinkedHashMap<>());
            for (String slot : slots) {
                table.get(day).put(slot, "");
            }
        }
    }

    public boolean isSlotFree(String day, String slot) {
        return table.get(day).get(slot).isEmpty();
    }

    public void assignSlot(String day, String slot, String value) {
        table.get(day).put(slot, value);
    }

    public void printTimetable() {
        for (String day : table.keySet()) {
            System.out.println(day + ": " + table.get(day));
        }
    }

    public Map<String, Map<String, String>> getTable() {
        return table;
    }
}

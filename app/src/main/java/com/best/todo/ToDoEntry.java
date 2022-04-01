package com.best.todo;

import java.util.ArrayList;

public class ToDoEntry {
    String name;
    String[] deadline;

    public ToDoEntry(String name, String deadline) {
        String[] monthList = {"january", "february", "march", "april", "may", "june", "july", "august",
                "september", "october", "november", "december"};
        this.name = name;
        try {
            String[] tempDeadline = deadline.split(" ", 2);
            if (tempDeadline.length != 2) {
                throw new IllegalArgumentException();
            } else {
                for (int i = 0; i < monthList.length; i++) {
                    if (tempDeadline[0].equals(monthList[i]) && Integer.parseInt(tempDeadline[1]) <= 31 && Integer.parseInt(tempDeadline[1]) > 0) {
                        this.deadline = tempDeadline;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Deadline must be in the format (M D)");
            System.out.println("Specified Month may not exist");
            System.out.println("Specified Day may not exist");
        } finally {
            System.out.println("Please try again");
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDeadline(String deadline) {
        String[] monthList = {"january", "february", "march", "april", "may", "june", "july", "august",
                "september", "october", "november", "december"};
        String[] tempDeadline = deadline.split(" ", 2);
        try {
            if (tempDeadline.length != 2) {
                throw new IllegalArgumentException();
            } else {
                for (int i = 0; i < monthList.length; i++) {
                    if (tempDeadline[0].equals(monthList[i]) && Integer.parseInt(tempDeadline[1]) <= 31 && Integer.parseInt(tempDeadline[1]) > 0) {
                        this.deadline = tempDeadline;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Deadline must be in the format (M D) (without parenthesis)");
            System.out.println("Specified Month may not exist");
            System.out.println("Specified Day may not exist");
        } finally {
            System.out.println("Please try again");
        }
    }

    public String[] getDeadline() {
        return deadline;
    }
}


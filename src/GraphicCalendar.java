import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class GraphicCalendar extends JFrame {


    private String[] daysOfWeek = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд"};
    private LocalDate localDate;
    private JPanel jPanel;

    //constructor without parameters, shows the current year, month, day.
    public GraphicCalendar()
    {
        localDate = LocalDate.now();
        GraphicCalendarInitialize(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 300, 300, Color.RED, Color.BLUE);
    }
    //shows the selected month and current year.
    public GraphicCalendar(Month month)
    {
        localDate = LocalDate.now();
        GraphicCalendarInitialize(localDate.getYear(), month, 1, 300, 300, Color.RED, Color.BLUE);
    }
    //shows the selected month and a selected year
    public GraphicCalendar(int year, Month month)
    {
        GraphicCalendarInitialize(year, month, 1, 300, 300, Color.RED, Color.BLUE);
    }
    //changes the width and height of the calendar window, and displays the current year, month, day
    public GraphicCalendar(int calendarWidth, int calendarHeight)
    {
        localDate = LocalDate.now();
        GraphicCalendarInitialize(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), calendarWidth, calendarHeight, Color.RED, Color.BLUE);
    }
    //shows the selected year, month, day
    public GraphicCalendar(int year, Month month, int dayOfMonth)
    {
        GraphicCalendarInitialize(year, month, dayOfMonth, 300, 300, Color.RED, Color.BLUE);
    }
    //changes the width and height of the calendar window, and shows the selected year, month, day
    public GraphicCalendar(int year, Month month, int dayOfMonth, int calendarWidth, int calendarHeight)
    {
        GraphicCalendarInitialize(year, month, dayOfMonth, calendarWidth, calendarHeight, Color.RED, Color.BLUE);
    }
    //shows the selected year, month, day, color weekend, color current day of month
    public GraphicCalendar(int year, Month month, int dayOfMonth, Color calendarWeekend, Color currentDayOfMonth)
    {
        GraphicCalendarInitialize(year, month, dayOfMonth, 300, 300, calendarWeekend, currentDayOfMonth);
    }
    //shows the selected year, month, day, calendar window width and height, color weekend, color current day of month
    public GraphicCalendar(int year, Month month, int dayOfMonth, int calendarWidth, int calendarHeight,
                           Color calendarWeekend, Color currentDayOfMonth)
    {
        GraphicCalendarInitialize(year, month, dayOfMonth, calendarWidth, calendarHeight, calendarWeekend, currentDayOfMonth);
    }


    private void GraphicCalendarInitialize(int year, Month month, int dayOfMonth, int calendarWidth, int calendarHeight,
                                           Color calendarWeekend, Color currentDayOfMonth)
    {
        //create date to set the value of year, month, day of the month
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        //get the day of the month
        int dayOfWeekInMonth = date.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
        //We get the number of days of the month, and do check for a leap year
        int numberOfDays;
        if (date.isLeapYear() && month == Month.FEBRUARY)
        {
            numberOfDays = month.maxLength();
        }
        else numberOfDays = month.minLength();
        //create a panel with the table
         jPanel = new JPanel(new GridLayout(0, 7));
        //add the days of the week in the first row
        for (int i = 0; i < 7; i++) {
            jPanel.add(new Label(daysOfWeek[i]));
        }
        //make indent for the day of the week corresponds to the table
        for (int i = 1; i < dayOfWeekInMonth; i++)
        {
            jPanel.add(new Label(null));
        }
        //add days, depending on the number of days in the month and align the number is less than 10
        for (int i = 1; i <= numberOfDays; i++) {;
            if (i < 10)
                jPanel.add(new Label(" " + i));
            else
                jPanel.add(new Label("" + i));
        }
        //change the color of the weekend
        for (int i = 12; i < jPanel.getComponents().length; i += 7) {
            jPanel.getComponent(i).setForeground(calendarWeekend);
            if (i != jPanel.getComponents().length - 1) {
                jPanel.getComponent(i + 1).setForeground(calendarWeekend);
            }
        }
        //change the color of the current day of the month
        jPanel.getComponents()[6 + dayOfWeekInMonth + (dayOfMonth - 1)].setForeground(currentDayOfMonth);
        //change the title and add the panel to the frame
        this.setTitle(month.toString() + " " + year);
        this.add(jPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(calendarWidth, calendarHeight);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void setDaysOfWeek(String[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
        for (int i = 0; i < 7; i++) {
            ((Label) jPanel.getComponents()[i]).setText(this.daysOfWeek[i]);
        }
    }

    public String[] getDaysOfWeek() {
        return daysOfWeek;
    }

}
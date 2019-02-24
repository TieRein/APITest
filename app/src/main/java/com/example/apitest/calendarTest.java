package com.example.apitest;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.apitest.apiclient.EventDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.example.apitest.apiclient.OneDayDecorator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executors;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

public class calendarTest extends AppCompatActivity implements OnDateSelectedListener {

    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();

    @BindView(R.id.monthCalendar)
    MaterialCalendarView widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_test);
        ButterKnife.bind(this);
        widget = findViewById(R.id.monthCalendar);
        widget.setOnDateChangedListener(this);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        final LocalDate instance = LocalDate.now();
        widget.setSelectedDate(instance);

        final LocalDate min = LocalDate.of(instance.getYear(), Month.JANUARY, 1);
        final LocalDate max = LocalDate.of(instance.getYear(), Month.DECEMBER, 31);

        //widget.state().edit().setMinimumDate(min).setMaximumDate(max).commit();

        widget.addDecorators(oneDayDecorator);



        }

    @Override
    public void onDateSelected( MaterialCalendarView widget, CalendarDay date, boolean selected) {
        //If you change a decorate, you need to invalidate decorators
        final ArrayList<CalendarDay> dates = new ArrayList<>();
        dates.add(date);
        widget.addDecorator(new EventDecorator(Color.RED, dates));
        oneDayDecorator.setDate(date.getDate());
        widget.invalidateDecorators();
    }
}

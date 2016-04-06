package com.appsrox.smartcalendar;

/**
 * Created by Crystal on 01-04-2016.
 */


        import android.app.Activity;
        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.DatePicker;
        import android.widget.EditText;

        import java.util.Calendar;
        import java.util.Date;

/**
 * Created by Crystal on 30-03-2016.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    EditText txtDate;


    public DateDialog(View view){
        txtDate = (EditText) view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day){

        //String date = day + "-" + (month+1)+ "-" +year;
        //txtDate.setText(date);
        Activity activity = getActivity();
        Intent intent = new Intent(activity, MainActivity.class);
        Bundle b = new Bundle();
        b.putInt("year", year); //Your id
        b.putInt("month", month);
        b.putInt("day", day);
        intent.putExtras(b);
        activity.startActivity(intent);
       //new DatePickerActivity().onClick(year,month,day);
        //startActivity(new Intent(this, MainActivity.class));
    }
}


package fr.charlin.mobileproject;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public  class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    TextView tv;

    public TimePickerFragment(TextView tv){
        super();
        this.tv = tv;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.tv.setText(getString(R.string.setTime) + hourOfDay + "h" + minute);
    }
}
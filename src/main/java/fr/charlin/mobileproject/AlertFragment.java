package fr.charlin.mobileproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class AlertFragment extends Fragment {

    private static final String CHANNEL_ID = "chan";
    private static final int notification_id = 1;

    public AlertFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View resView =  inflater.inflate(R.layout.fragment_alert, container, false);

        Button button_toast = resView.findViewById(R.id.button_toast);
        Button button_dialog = resView.findViewById(R.id.button_dialog);
        Button button_notification = resView.findViewById(R.id.button_notification);

        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),getString(R.string.randomToast), Toast.LENGTH_LONG).show();
            }
        });

        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(resView.findViewById(R.id.textView));
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        button_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(resView.getContext());
                notificationManager.notify(notification_id, notification_test().build());
            }
        });
        return resView;
    }

    public NotificationCompat.Builder notification_test(){
        return
                new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(getString(R.string.titleNotification))
                        .setContentText(getString(R.string.descNotification))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
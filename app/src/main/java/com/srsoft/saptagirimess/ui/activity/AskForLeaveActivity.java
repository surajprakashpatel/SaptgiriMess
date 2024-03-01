package com.srsoft.saptagirimess.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.srsoft.saptagirimess.R;
import com.srsoft.saptagirimess.databinding.ActivityAskForLeaveBinding;
import com.srsoft.saptagirimess.ui.modal.Attendance;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AskForLeaveActivity extends AppCompatActivity {

    private ActivityAskForLeaveBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAskForLeaveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialization();
    }

    private void initialization() {


        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (currentHour >= 11){
            binding.day.setEnabled(false);
        }
        if (currentHour >= 18) {
            binding.night.setEnabled(false);
        }
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                Calendar today = Calendar.getInstance();
                if (selectedDate.equals(today)) {
                    if (currentHour >= 11){
                        binding.day.setEnabled(false);
                        binding.day.setChecked(false);
                    }
                    if (currentHour >= 18) {
                        binding.night.setEnabled(false);
                        binding.night.setChecked(false);
                    }
                } else {

                 binding.day.setEnabled(true);
                 binding.night.setEnabled(true);
                }
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.day.isChecked() || binding.night.isChecked()){
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(binding.calendarView.getDate());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    String date = dateFormat.format(calendar.getTime());

                    db.collection("leave").document(date).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if(document.exists()){
                                    db.collection("leave").document(date).update("lunch", FieldValue.arrayUnion(user.getUid()), "dinner", FieldValue.arrayUnion(user.getUid()));
                                    Toast.makeText(AskForLeaveActivity.this, "Leave Applied Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Map<String, Object> docData = new HashMap<>();
                                    docData.put("date", date);
                                    docData.put("lunch", Arrays.asList(user.getUid()));
                                    docData.put("dinner", Arrays.asList(user.getUid()));
                                    db.collection("leave").document(date).set(docData, SetOptions.merge());
                                    Toast.makeText(AskForLeaveActivity.this, "Leave Applied Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }
                    });

                }else{
                    Toast.makeText(AskForLeaveActivity.this, "Please Select Date and Time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
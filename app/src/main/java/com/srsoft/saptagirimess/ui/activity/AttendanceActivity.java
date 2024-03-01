package com.srsoft.saptagirimess.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.srsoft.saptagirimess.adapter.AttendanceAdapter;
import com.srsoft.saptagirimess.databinding.ActivityAttendanceBinding;

import com.srsoft.saptagirimess.R;
import com.srsoft.saptagirimess.ui.modal.Attendance;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    private ActivityAttendanceBinding binding;
    private List<Attendance> attendance= new ArrayList<>();
    private AttendanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        initialization();
    }

    private void initialization() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AttendanceAdapter(attendance,AttendanceActivity.this,userId);
        binding.recyclerView.setAdapter(adapter);


        db.collection("attendance").orderBy("date", Query.Direction.DESCENDING).limit(35).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        attendance.clear();
                        if(task.isSuccessful()){
                            QuerySnapshot querySnapshot = task.getResult();
                            for(DocumentSnapshot documentSnapshot : querySnapshot){
                                attendance.add(documentSnapshot.toObject(Attendance.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }
}
package com.srsoft.saptagirimess.ui.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.srsoft.saptagirimess.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }


}
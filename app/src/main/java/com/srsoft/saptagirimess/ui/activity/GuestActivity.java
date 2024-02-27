package com.srsoft.saptagirimess.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.srsoft.saptagirimess.R;
import com.srsoft.saptagirimess.databinding.ActivityGuestBinding;

public class GuestActivity extends AppCompatActivity {

    private ActivityGuestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
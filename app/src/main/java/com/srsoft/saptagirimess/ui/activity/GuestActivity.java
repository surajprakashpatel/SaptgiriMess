package com.srsoft.saptagirimess.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
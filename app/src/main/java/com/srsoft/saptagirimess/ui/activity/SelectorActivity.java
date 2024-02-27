package com.srsoft.saptagirimess.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.srsoft.saptagirimess.R;
import com.srsoft.saptagirimess.databinding.ActivitySelectorBinding;
import com.srsoft.saptagirimess.ui.common.BaseActivity;


public class SelectorActivity extends BaseActivity {

    int itemSelected =0;
    private ActivitySelectorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initiization();
    }

    private void initiization() {


        binding.newRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlight(1);
            }
        });
        binding.regularMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlight(2);
            }
        });
        binding.guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               highlight(3);
            }
        });
        
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (itemSelected==1) {
                   Intent intent = new Intent(SelectorActivity.this, RegistrationActivity.class);
                   startActivity(intent);
                   finish();
               }
               if(itemSelected==2){
                   Intent intent = new Intent(SelectorActivity.this, LoginActivity.class);
                   startActivity(intent);
                   finish();
               }
               if(itemSelected==3){
                   Intent intent = new Intent(SelectorActivity.this, GuestActivity.class);
                   startActivity(intent);
                   finish();
               }

            }
        });
    }



    private void highlight(int i) {
        itemSelected = i;
        binding.btnNext.setEnabled(true);
        binding.tvnewRegistration.setBackgroundColor(getResources().getColor(R.color.white));
        binding.tvRegularMember.setBackgroundColor(getResources().getColor(R.color.white));
        binding.tvGuest.setBackgroundColor(getResources().getColor(R.color.white));
        binding.newRegistration.setStrokeWidth(2);
        binding.regularMember.setStrokeWidth(2);
        binding.guest.setStrokeWidth(2);
        if(i==1){
            binding.tvnewRegistration.setBackgroundColor(getResources().getColor(R.color.blue_light));
            binding.newRegistration.setStrokeWidth(5);
        }
        if(i==2){
            binding.tvRegularMember.setBackgroundColor(getResources().getColor(R.color.blue_light));
            binding.regularMember.setStrokeWidth(5);
        }
        if(i==3){
            binding.tvGuest.setBackgroundColor(getResources().getColor(R.color.blue_light));
            binding.guest.setStrokeWidth(5);
        }
    }

}
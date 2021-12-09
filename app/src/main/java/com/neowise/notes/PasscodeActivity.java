package com.neowise.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.neowise.notes.databinding.ActivityPasscodeBinding;

public class PasscodeActivity extends AppCompatActivity {

    private ActivityPasscodeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasscodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences("passcode", MODE_PRIVATE);
        String passcode = prefs.getString("passcode", null);

        binding.message.setText(passcode == null ? R.string.add_passcode : R.string.enter_passcode);

        binding.passcode.setOtpCompletionListener(otp -> {

            if (passcode != null) {
                if (passcode.equals(otp)) {
                    startActivity(new Intent(PasscodeActivity.this, SplashActivity.class));
                    finish();
                }
                else {
                    binding.passcode.setText("");
                    Snackbar.make(binding.getRoot(), "Wrong passcode!", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
            else {
                prefs.edit()
                        .putString("passcode", otp)
                        .apply();

                Snackbar.make(binding.getRoot(), "Passcode saved", Snackbar.LENGTH_SHORT)
                        .show();

                startActivity(new Intent(PasscodeActivity.this, SplashActivity.class));
                finish();
            }
        });
    }
}

package com.unbeatabletictactoe;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton btnChooseX = findViewById(R.id.btn_choose_x);
        MaterialButton btnChooseO = findViewById(R.id.btn_choose_o);

        btnChooseX.setOnClickListener(v -> startGame("X"));
        btnChooseO.setOnClickListener(v -> startGame("O"));
    }

    private void startGame(String player) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }
}

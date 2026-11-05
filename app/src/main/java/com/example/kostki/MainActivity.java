package com.example.kostki;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText IloscKostek;
    Button btnLosuj, btnRosnaco, btnMalejaco;
    GridLayout gridKostki;
    TextView Wyniki;
    ArrayList<Integer> wyniki = new ArrayList<>();
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IloscKostek = findViewById(R.id.IloscKostek);
        btnLosuj = findViewById(R.id.btnLosuj);
        btnRosnaco = findViewById(R.id.btnRosnaco);
        btnMalejaco = findViewById(R.id.btnMalejaco);
        gridKostki = findViewById(R.id.gridKostki);
        Wyniki = findViewById(R.id.Wyniki);
        btnLosuj.setOnClickListener(v -> losuj());
        btnRosnaco.setOnClickListener(v -> sortujRosnaco());
        btnMalejaco.setOnClickListener(v -> sortujMalejaco());
    }
    private void aktualizujKostki() {
        gridKostki.removeAllViews();
        for (int wynik : wyniki) {
            TextView kostka = new TextView(this);
            kostka.setText(String.valueOf(wynik));
            gridKostki.addView(kostka);
        }
        pokazWyniki();
    }
    private void losuj() {

        String liczba = IloscKostek.getText().toString();

        if (liczba.isEmpty()) {
            Toast.makeText(this, "Podaj liczbe kostek", Toast.LENGTH_SHORT).show();
            return;
        }
        int ile = Integer.parseInt(liczba);
        if (ile < 1 || ile > 100) {
            Toast.makeText(this, "Podaj liczbe od 1 do 100", Toast.LENGTH_SHORT).show();
            return;
        }
        wyniki.clear();
        gridKostki.removeAllViews();

        for (int i = 0; i < ile; i++) {
            int wynik = random.nextInt(6) + 1;
            wyniki.add(wynik);
            TextView kostka = new TextView(this);
            kostka.setText(String.valueOf(wynik));
            gridKostki.addView(kostka);
        }
        pokazWyniki();
    }
    private void pokazWyniki() {
        String tekst = "";
        for (int i = 0; i < wyniki.size(); i++) {
            tekst += wyniki.get(i);
            if (i < wyniki.size() - 1) {
                tekst += " | ";
            }
        }
        Wyniki.setText(tekst);
    }
    private void sortujRosnaco() {

        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < wyniki.size(); i++) {
            for (int j = 0; j < wyniki.size() - 1; j++) {
                if (wyniki.get(j) > wyniki.get(j + 1)) {
                    int pomoc = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, pomoc);
                }
            }
        }
        aktualizujKostki();
    }
    private void sortujMalejaco() {

        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < wyniki.size(); i++) {
            for (int j = 0; j < wyniki.size() - 1; j++) {
                if (wyniki.get(j) < wyniki.get(j + 1)) {
                    int pomoc = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, pomoc);
                }
            }
        }
        aktualizujKostki();
    }
}
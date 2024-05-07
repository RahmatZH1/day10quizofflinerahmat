package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupCarType;
    private RadioGroup radioGroupSewaType;
    private EditText editTextDayOfRent;
    private Button buttonCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupCarType = findViewById(R.id.radioGroupCarType);
        radioGroupSewaType = findViewById(R.id.radioGroupSewaType);
        editTextDayOfRent = findViewById(R.id.editTextDayOfRent);
        buttonCheckout = findViewById(R.id.buttonCheckout);

        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCarTypeId = radioGroupCarType.getCheckedRadioButtonId();
                int selectedSewaTypeId = radioGroupSewaType.getCheckedRadioButtonId();

                if (selectedCarTypeId == -1 || selectedSewaTypeId == -1) {
                    // Jika tidak ada tipe mobil atau tipe sewa yang dipilih, lakukan apa pun
                    return;
                }

                RadioButton selectedCarTypeRadioButton = findViewById(selectedCarTypeId);
                RadioButton selectedSewaTypeRadioButton = findViewById(selectedSewaTypeId);

                String carType = selectedCarTypeRadioButton.getText().toString();
                String sewaType = selectedSewaTypeRadioButton.getText().toString();

                String dayOfRentStr = editTextDayOfRent.getText().toString();
                int dayOfRent = 0;
                if (!dayOfRentStr.isEmpty()) {
                    dayOfRent = Integer.parseInt(dayOfRentStr);
                }

                double hargaSewaPerHari = 0;
                switch (carType) {
                    case "Pajero Sport":
                        hargaSewaPerHari = 2400000;
                        break;
                    case "Alphard":
                        hargaSewaPerHari = 1550000;
                        break;
                    case "Innova":
                        hargaSewaPerHari = 850000;
                        break;
                    case "Brio":
                        hargaSewaPerHari = 550000;
                        break;
                }

                double biayaTambahan = 0;
                switch (sewaType) {
                    case "Dalam Kota":
                        biayaTambahan = 135000;
                        break;
                    case "Luar Kota":
                        biayaTambahan = 250000;
                        break;
                }

                double totalBiayaSewa = (hargaSewaPerHari * dayOfRent) + biayaTambahan;

                double diskon = 0;
                if (totalBiayaSewa > 10000000) {
                    diskon = 0.07 * totalBiayaSewa;
                } else if (totalBiayaSewa > 5000000) {
                    diskon = 0.05 * totalBiayaSewa;
                }

                totalBiayaSewa -= diskon;

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("carType", carType);
                intent.putExtra("dayOfRent", dayOfRent);
                intent.putExtra("hargaSewa", hargaSewaPerHari);
                intent.putExtra("biayaTambahan", biayaTambahan);
                intent.putExtra("totalBiayaSewa", totalBiayaSewa);
                intent.putExtra("typeSewa", sewaType); // Tambahkan data tipe sewa
                startActivity(intent);
            }
        });
    }
}

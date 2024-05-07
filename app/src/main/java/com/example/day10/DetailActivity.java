package com.example.day10;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);

        // Menerima data dari MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String carType = extras.getString("carType");
            int dayOfRent = extras.getInt("dayOfRent");
            double hargaSewa = extras.getDouble("hargaSewa");
            double biayaTambahan = extras.getDouble("biayaTambahan");
            double totalBiayaSewa = extras.getDouble("totalBiayaSewa");

            // Menampilkan data pada TextView di layout activity_detail.xml
            TextView textViewCarType = findViewById(R.id.textViewCarType);
            textViewCarType.setText("Car Type: " + carType);

            TextView textViewDayOfRent = findViewById(R.id.textViewDayOfRent);
            textViewDayOfRent.setText("Day of Rent: " + dayOfRent);

            TextView textViewHargaSewa = findViewById(R.id.textViewHargaSewa);
            textViewHargaSewa.setText("Harga Sewa: Rp " + String.format("%.0f", hargaSewa));

            TextView textViewBiayaTambahan = findViewById(R.id.textViewBiayaTambahan);
            textViewBiayaTambahan.setText("Biaya Tambahan: Rp " + String.format("%.0f", biayaTambahan));

            double diskon = 0;
            if (totalBiayaSewa > 10000000) {
                diskon = 0.07 * totalBiayaSewa;
            } else if (totalBiayaSewa > 5000000) {
                diskon = 0.05 * totalBiayaSewa;
            }

            TextView textViewDiscount = findViewById(R.id.textViewDiscount);
            textViewDiscount.setText("Discount: Rp " + String.format("%.0f", diskon));

            TextView textViewTotalBiayaSewa = findViewById(R.id.textViewTotalBiayaSewa);
            textViewTotalBiayaSewa.setText("Total Biaya Sewa: Rp " + String.format("%.0f", totalBiayaSewa - diskon));

            TextView textViewTypeSewa = findViewById(R.id.textViewTypeSewa);
            textViewTypeSewa.setText("Type Sewa: " + extras.getString("typeSewa"));
        }
    }
}

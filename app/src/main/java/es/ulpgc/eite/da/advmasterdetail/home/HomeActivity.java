package es.ulpgc.eite.da.advmasterdetail.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.categories.CategoryListActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        Button satellitesButton = findViewById(R.id.communicationsButton);
        Button missionsButton = findViewById(R.id.missionsButton);

        satellitesButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, CategoryListActivity.class);
            startActivity(intent);
        });

        missionsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, CategoryListActivity.class);
            startActivity(intent);
        });
    }
}
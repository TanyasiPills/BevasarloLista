package com.example.bevasarlolista;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_show);

        // Retrieve the data passed from MainActivity
        String name = getIntent().getStringExtra("itemName");
        int count = getIntent().getIntExtra("itemCount", 0);  // Default value is 0 if not found

        // Log the received values
        Log.d("ItemDetail", "Received item: " + name + " with count: " + count);

        // Find the TextViews in the layout
        TextView showName = findViewById(R.id.showName);
        TextView showQuantity = findViewById(R.id.showQuantity);

        // Set the data to the TextViews
        showName.setText(name);
        showQuantity.setText(String.valueOf(count));  // Display count properly

        // Handle the back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(e -> finish());  // Finish the activity and go back
    }
}

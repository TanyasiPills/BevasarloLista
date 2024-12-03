package com.example.bevasarlolista;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText name, quantity;
    private Button addButton;
    private ListView listView;
    private List<Item> itemList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();

    }

    private void initialize(){
        name = findViewById(R.id.name);
        quantity = findViewById(R.id.count);
        addButton = findViewById(R.id.addbutton);
        listView = findViewById(R.id.listView);
        itemList = new ArrayList<>();
        adapter = new Adapter(this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Item item = itemList.get(position);
            itemList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, item.getName() + " deleted", Toast.LENGTH_SHORT).show();
            return true;
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Item item = itemList.get(position);

            Log.d("ItemDetail", "Passing item: " + item.getName() + " with count: " + item.getCount());

            Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);

            intent.putExtra("itemName", item.getName());
            intent.putExtra("itemCount", item.getCount());

            startActivity(intent);
        });

        addButton.setOnClickListener( e -> {
            String nameToList = name.getText().toString();
            String quantityToList = quantity.getText().toString();

            if (nameToList.isEmpty() || quantityToList.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int countToList;
            try {
                countToList = Integer.parseInt(quantityToList);  // Safely parse the quantity
            } catch (NumberFormatException ex) {
                Toast.makeText(this, "Please enter a valid number for quantity", Toast.LENGTH_SHORT).show();
                return;
            }

            Item item = new Item(nameToList, countToList);
            itemList.add(item);
            adapter.notifyDataSetChanged();

            name.setText("");
            quantity.setText("");
        });
    }
}
package com.example.admin.quanlynhansu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.quanlynhansu.DTO.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView myListView;
    MainMenu mainMenu;
    private int[] image = {R.drawable.ic_domain_black_24dp,
            R.drawable.ic_group_black_24dp};
    private String[] MenuItems = {"Phòng ban", "Nhân viên"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView)findViewById(R.id.myListView);
        List<MainMenu> list = getList();
        CustomMenuAdapter adapter = new CustomMenuAdapter(this, R.layout.main_menu, list);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent intent = new Intent(MainActivity.this, DeapartmentActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, StaffActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        });
    }

    public List<MainMenu> getList(){
        List<MainMenu> list = new ArrayList<MainMenu>();
        list.add(new MainMenu(image[0], MenuItems[0]));
        list.add(new MainMenu(image[1], MenuItems[1]));
        return list;
    }
}

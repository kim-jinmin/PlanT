package com.example.naviproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private home home;
    private plant plant;
    private board board;
    private store store;

    Button bt1, bt2;
    EditText name, title, review, price, inp;
    TextView result1, result2, result3, result4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.StoreName);
        title = (EditText) findViewById(R.id.GoodsPageTitle);
        review = (EditText) findViewById(R.id.ReviewCount);
        price = (EditText) findViewById(R.id.GoodsPrice);

        bt2 = (Button) findViewById(R.id.button2);
        inp = (EditText) findViewById(R.id.inp);
        result1 = (TextView) findViewById(R.id.textView);
        result2 = (TextView) findViewById(R.id.textView2);
        result3 = (TextView) findViewById(R.id.textView3);
        result4 = (TextView) findViewById(R.id.textView4);

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                add(name.getText().toString(), title.getText().toString(), review.getText().toString(), price.getText().toString());
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = String.valueOf(inp.getText());
                getgoods(a);
            }
        });

        public void add(String name, String title, String review, String price) {
            goods goods;
            goods = new goods(name, title, review, price);
            databaseReference.child("goods").child(name).setValue(goods);
        }

        public void getgoods(String s) {
            DatabaseReference data = databaseReference.child("goods").child(s);
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    goods value = snapshot.getValue(goods.class);
                    result1.setText(value.name);
                    result2.setText(value.title);
                    result3.setText(value.review);
                    result4.setText(value.price);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        bottomNavigationView = findViewById(R.id.Navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.NaviHome:
                        setFrag(0);
                        break;
                    case R.id.NaviPlant:
                        setFrag(1);
                        break;
                    case R.id.NaviBoard:
                        setFrag(2);
                        break;
                    case R.id.NaviStore:
                        setFrag(3);
                        break;


                }
                return true;
            }
        });
        home = new home();
        plant = new plant();
        board = new board();
        store = new store();
        setFrag(0); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택

    }

    //프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.Frame, home);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.Frame, plant);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.Frame, board);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.Frame, store);
                ft.commit();
                break;



        }



    }

}
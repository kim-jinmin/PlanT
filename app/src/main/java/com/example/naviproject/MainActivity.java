package com.example.naviproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private home home;
    private plant plant;
    private board board;
    private store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
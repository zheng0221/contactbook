package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    String tag="My";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(tag,"1");

        //Vector<miniCard> cardList=new Vector<miniCard>();
        //initList(cardList);

        //LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        //linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        Log.d(tag,"2");

        //RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rec_view);

        Log.d(tag,"3");

        //recyclerView.setLayoutManager(linearLayoutManager);

        Log.d(tag,"4");
        //miniCardAdapter miniCardAdapter_1=new miniCardAdapter(cardList);

        Log.d(tag,"5");

        //recyclerView.setAdapter(miniCardAdapter_1);



    }

    protected void initList(Vector<miniCard> inList){
        for(int i=0;i!=3;++i){
            inList.add(new miniCard("Alan"));
            inList.add(new miniCard("Anne"));
            inList.add(new miniCard("Bob"));
            inList.add(new miniCard("Bluse"));
            inList.add(new miniCard("Carly"));
            inList.add(new miniCard("Cindy"));
            inList.add(new miniCard("Dante"));
            inList.add(new miniCard("Dasic"));
            inList.add(new miniCard("Effa"));
            inList.add(new miniCard("Emily"));
        }
    }
}

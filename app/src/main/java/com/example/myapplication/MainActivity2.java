package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button reset ;
    Button [] buttons= new Button[9];
    TextView winner ;
    TextView scor;
    private int [] table={2,2,2,2,2,2,2,2,2};
    private int [][] solution={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    boolean player=true;
    private int partie=0;
    private int score1=0;
    private int score2=0;
    int nombre=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scor =(TextView) findViewById(R.id.textView2);
        winner = (TextView) findViewById(R.id.textView);
        for(int i=0;i<9;i++){
            String btnid="btn_"+i;
            int btid =getResources().getIdentifier(btnid,"id",getPackageName());
            buttons[i]=(Button) findViewById(btid);
            buttons[i].setOnClickListener(this);
        }
        Button reset =findViewById(R.id.button2);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playagain();
                score1=0;
                score2=0;
                scor.setText("player 1: "+0+"    player 2: "+0);

            }
        });
    }



    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        String btnid=v.getResources().getResourceEntryName(v.getId());
        winner.setText("player"+nombre);
        int pointeur =Integer.parseInt(btnid.substring(btnid.length()-1,btnid.length()));
        if(player){
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            table[pointeur]=0;
        }else{
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            table[pointeur]=1;
        }
        if(Checkwin()){
            updatescore();
            scor.setText("player 1: "+score1+"    player 2: "+score2);
            scor.setTextColor(Color.parseColor("#FFC34A"));
            playagain();
        }else if(partie==8){
            playagain();
        }else{
            player=!player;
            if(nombre==1){nombre=2;}else{nombre=1;}
            partie++;
        }
    }
    public boolean Checkwin(){
        boolean b=false;
        for(int [] x:solution){
            if(table[x[0]]==table[x[1]] && table[x[1]]==table[x[2]] && table[x[2]]!=2 ){
                b= true;
            }
        }
        return b;
    }
    public void playagain(){
        player=true;
        partie=0;
        nombre=2;
        for(int i=0;i<9;i++){
            table[i]=2;
            buttons[i].setText("");
        }
        winner.setText("player1");
    }
    public void updatescore(){
        if(player){
            score1++;
        }else{
            score2++;
        }
    }

}
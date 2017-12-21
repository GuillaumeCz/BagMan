package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.R;

public class MainActivity extends MenuHeader {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button carnet_listButton = (Button)findViewById(R.id.carnet_listButton);
        carnet_listButton.setOnClickListener(carnet_list);
    }

    private View.OnClickListener carnet_list = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class carnet_listActivityClass =Carnet_listActivity.class;
            Intent carnet_listIntent = new Intent(MainActivity.this, carnet_listActivityClass);
            startActivity(carnet_listIntent);
        }
    };
}

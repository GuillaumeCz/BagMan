package fr.utt.if26.mytravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.if26.mytravel.DAO.PageDAO;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Bdd database;
    private boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // ==== Exemples pour interagire avec la base de données simplement
        // initialisation/creation de la bdd
        database = new Bdd(this);
        // Suppression de son contenu si il en existe une (Pour tester dans une base de données vierge)


        PageDAO pdao = new PageDAO(database);

        if(first) {
            deleteDatabase(Bdd.DATABASE_NAME);
            Page t = new Page("TitleTest", "ContentTest", "SummaryTest");
            Page t2 = new Page("Title2", "ContentTest2", "SummaryTest2");
            Page t3 = new Page("Title3", "ContentTest3", "Summary3");
            t.setId(pdao.insertRow(t));
            t2.setId(pdao.insertRow(t2));
            t3.setId(pdao.insertRow(t3));
            first = false;
        }

        Button but = (Button)findViewById(R.id.button);
        but.setOnClickListener(this);

    }

    public void onClick(View v) {
        Class pageTargetActivity = PageActivity.class;
        Intent pageIntent = new Intent(MainActivity.this, pageTargetActivity);
        startActivity(pageIntent);
    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}

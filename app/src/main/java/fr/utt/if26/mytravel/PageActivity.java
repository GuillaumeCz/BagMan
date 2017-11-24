package fr.utt.if26.mytravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.if26.mytravel.DAO.PageDAO;

import static android.R.id.list;

public class PageActivity extends AppCompatActivity {
    private Bdd database;
    private PageDAO pdao;
    private boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new Bdd(this);

        // instanciation d'un Objet PageDao pour interagir avec la table Page
        pdao = new PageDAO(database);

        // Création d'objets Page

        // INSERTION d'une page dans la table Page
        // Idée de récupéré l'id que lui a attribué la base de données pour completer l'objet Page
        // precedemment construit


        list_pages();
        //fonction d'affichage de liste en debug pour constater les resultats
        /*display_pages(pdao.getList());

        // SUPPRESSION d'une page de la table
        Log.i("=====", "SUPPRESSION");
        pdao.deleteRow(t3);
        display_pages(pdao.getList());

        // OBTENTION d'une seule page
        // Ici recuperation de la page avec l'id "1" (normalement l'objet correspondant à la variable t)
        Log.i("=====", "GET 1");
        Page p = pdao.getRow(1);
        Log.i("===", p.toString());

        // OBTENTION de la liste des pages
        Log.i("=====", "GET ALL");
        ArrayList<Page> arr_page = pdao.getList();
        display_pages(arr_page);

        // MISE A JOUR d'une page en fonction de son ID
        // Ici le contenu de la table avec precedemment l'objet t est remplacé par l'objet t3
        Log.i("=====", "UPDATE");
        pdao.updateRow(t.getId(), t3);
        display_pages(pdao.getList());*/


    }

    public void list_pages() {
        setContentView(R.layout.activity_page);
        ArrayAdapter arr = new ArrayAdapter(this, R.layout.activity_page, R.id.item_page, pdao.getList());
        ListView lv = (ListView) findViewById(list);
        lv.setAdapter(arr);
        lv.setOnItemClickListener(item_action);
    }

    private AdapterView.OnItemClickListener item_action = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
            final Page page = (Page) parent.getItemAtPosition(position);
            Intent page_itemIntent = new Intent(PageActivity.this, Page_itemActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("id", page.getId());
            page_itemIntent.putExtras(extras);
            startActivity(page_itemIntent);
        }
    };


    /**
     * Affichage d'une liste de page à partir d'un arrayList
     * @param ps
     */
    public void display_pages(ArrayList ps) {
        Iterator<Page> it = ps.iterator();
        String sb = "";
        while(it.hasNext()) {
            Page p = it.next();
            sb = sb + " " + p.toString() + "\n";
        }
        Log.i("===", sb);
    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}

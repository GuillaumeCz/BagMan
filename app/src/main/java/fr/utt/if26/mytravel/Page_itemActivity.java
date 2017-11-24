package fr.utt.if26.mytravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.utt.if26.mytravel.DAO.PageDAO;

public class Page_itemActivity extends AppCompatActivity {

    private Bdd database;
    private PageDAO pdao;
    private int id;
    private EditText layout_title;
    private EditText layout_summary;
    private EditText layout_content;
    private Button layout_save;
    private Button layout_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_item);
        database = new Bdd(this);
        // instanciation d'un Objet PageDao pour interagir avec la table Page
        pdao = new PageDAO(database);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");

        Page page = pdao.getRow(id);

        layout_title = (EditText) findViewById(R.id.page_title);
        layout_summary = (EditText) findViewById(R.id.page_summary);
        layout_content = (EditText) findViewById(R.id.page_content);
        layout_save = (Button) findViewById(R.id.page_save);
        layout_delete = (Button) findViewById(R.id.page_delete);

        layout_title.setText(page.getTitle());
        layout_summary.setText(page.getSummary());
        layout_content.setText(page.getContent());
        layout_save.setOnClickListener(new Page_itemActivity.Page_action_update(id));
        layout_delete.setOnClickListener(new Page_itemActivity.Page_action_delete(id));
    }

    private class Page_action_update implements View.OnClickListener {
        private int id;
        public Page_action_update(int id_pf) {
            id = id_pf;
        }

        @Override
        public void onClick(View v) {
            String title = layout_title.getText().toString();
            String content = layout_content.getText().toString();
            String summary = layout_summary.getText().toString();
            Page page = new Page(title, content, summary);

            pdao.updateRow(id, page);
            Intent page_listIntent = new Intent(Page_itemActivity.this, PageActivity.class);
            startActivity(page_listIntent);
        }
    }

    private class Page_action_delete implements View.OnClickListener {
        private int target_id;
        public Page_action_delete(int id_pf) { target_id = id_pf; }

        @Override
        public void onClick(View v) {
            pdao.deleteRow(target_id);

            Intent page_listIntent = new Intent(Page_itemActivity.this, PageActivity.class);
            startActivity(page_listIntent);
        }
    }

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}

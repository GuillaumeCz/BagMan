package fr.utt.if26.mytravel.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Helpers.Sha256;
import fr.utt.if26.mytravel.R;

public class MainActivity extends MenuHeader {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button carnet_listButton = (Button)findViewById(R.id.carnet_listButton);
        carnet_listButton.setOnClickListener(carnet_list);

        File db = getDatabasePath(Bdd.DATABASE_NAME);
        if(!db.exists()) {
            promptDialogNewPass();
        }
    }

    private void promptDialogAccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.pass, null));
        builder.setPositiveButton("ok", accessClick).show();
    }
    private void promptDialogNewPass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.password, null));
        builder.setPositiveButton("ok", newPass).show();
    }

    private void initSQLCipher(String password) {
        SQLiteDatabase.loadLibs(this);
        File dbFile = getDatabasePath(Bdd.DATABASE_NAME);
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, password, null);
    }

    private String getPwHash() {
        return getSharedPreferences("fr.utt.if26.mytravel", Context.MODE_PRIVATE).getString("pass", "");
    }

    private View.OnClickListener carnet_list = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            promptDialogAccess();
        }
    };

    private DialogInterface.OnClickListener accessClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Dialog d = Dialog.class.cast(dialogInterface);
            EditText et = d.findViewById(R.id.password);

            String password = et.getText().toString();
            String pwHashPrefs = getPwHash();
            String pwHash = Sha256.sha256(password);

            if(pwHash.equals(pwHashPrefs)) {
                Toast.makeText(getApplicationContext(), "Success ! :D", Toast.LENGTH_LONG).show();
                initSQLCipher(password);
                Class carnet_listActivityClass = Carnet_listActivity.class;
                Intent carnet_listIntent = new Intent(MainActivity.this, carnet_listActivityClass);
                Bundle extras = new Bundle();
                extras.putString("password", password);
                carnet_listIntent.putExtras(extras);
                startActivity(carnet_listIntent);
            } else {
                Toast.makeText(getApplicationContext(), "Wrong pass ! D:", Toast.LENGTH_LONG).show();
            }
        }
    };
    private DialogInterface.OnClickListener newPass = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Dialog d = Dialog.class.cast(dialogInterface);
            EditText et = (EditText) d.findViewById(R.id.password);

            String password = et.getText().toString();
            String pwHash = Sha256.sha256(password);
            initSQLCipher(password);
            SharedPreferences.Editor ed = getSharedPreferences("fr.utt.if26.mytravel", Context.MODE_PRIVATE).edit();
            ed.putString("pass", pwHash);
            ed.commit();
        }
    };
}

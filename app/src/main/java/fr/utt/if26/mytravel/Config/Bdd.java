package fr.utt.if26.mytravel.Config;

import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


/**
 * AJOUTER gestion des exceptions pour création / suppression de tables !
 */
public class Bdd extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bagman.db";
    private SQLiteDatabase database;

    public Bdd(Context ct ) {
        super(ct, DATABASE_NAME, null, DATABASE_VERSION );
        SQLiteDatabase.loadLibs(ct);
        if (ct.getDatabasePath(DATABASE_NAME).exists()) {
            Log.e("===", "exists");
        } else {
            Log.e("===", "not exists");
        }
        SQLiteDatabase.openOrCreateDatabase(ct.getDatabasePath(DATABASE_NAME), "test123", null);
        Log.i("===", "The database has been created");
    }

    /**
     * Quand la base de données est créée une premiere fois
     * Ou se passe la création des tables et les initialisations
     * @param db, bade de donnée cible
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        Log.e("===", "Ici");
        try{
            createCompleteTables();
        } catch(Exception e) {
            Log.e("===e", e.getMessage());
        }

        Log.e("===", "The database has been Created");
    }

    /**
     * Méthode aggregatrice de la création de l'ensemble des tables
     * Idée qu'on peut tout faire en one-shot au lieu de faire x appels dans le onCreate(db)
     */
    private void createCompleteTables() throws Exception {
        try {
            createPageTable();
            createCarnetTable();
        } catch (Exception ex){
            throw new Exception("Probleme dans creation des tables : "+ ex.getMessage());
        }
    }


    /**
     * Méthode de création de la table Page
     */
    private void createPageTable() throws Exception {
        try {
            database.execSQL(FeedPage.SQL_CREATE_PAGES);
            Log.i("==", FeedPage.MODEL_NAME + " is created");
        } catch(Exception e) {
            throw new Exception("Page : "+e.getMessage());
        }
    }

    private void createCarnetTable() throws Exception {
        database.execSQL(FeedCarnet.SQL_CREATE_CARNET);
        Log.i("==", FeedCarnet.MODEL_NAME + " is created");
    }

    /**
     * Suppresion de l'ensemble des tables de la bdd
     * Methode qui appelle les méthodes de suppression de chaque tables
     */
    private void deleteTables() throws Exception {
        deletePageTable();
    }

    /**
     * Méthode de suppression de la table Page
     */
    private void deletePageTable() throws Exception {
        database.execSQL(FeedPage.SQL_DELETE_PAGES);
        Log.i("==", FeedPage.MODEL_NAME + " has been deleted");
    }

    public void deleteCarnetTable() throws Exception {
        database.execSQL(FeedCarnet.SQL_DELETE_CARNET);
        Log.i("==", FeedCarnet.MODEL_NAME + " has been deleted");
    }

    /**
     * Classe / structure du modele Page
     * Toutes les infos sur la strucutre et les commandes SQL de base
     */
    public class FeedPage implements BaseColumns {
        public static final String MODEL_NAME = "page";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String SUMMARY = "summary";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        public static final String SQL_CREATE_PAGES =
            "CREATE TABLE " + MODEL_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                TITLE + " TEXT," +
                CONTENT + " TEXT," +
                SUMMARY + " TEXT," +
                CREATED_AT + " INTEGER," +
                UPDATED_AT + " INTEGER)";
        public static final String SQL_DELETE_PAGES = "DROP TABLE IF EXISTS " + MODEL_NAME;
    }

    /**
     * Future classe pour Story, sous la même forme que Page
     */
    public class FeedStory implements BaseColumns {}

    public class FeedCarnet implements BaseColumns {

        public static final String MODEL_NAME = "carnet";
        public static final String NAME = "name";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        public static final String SQL_CREATE_CARNET =
                "CREATE TABLE " + MODEL_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        NAME + " TEXT," +
                        CREATED_AT + " INTEGER," +
                        UPDATED_AT + " INTEGER)";
        public static final String SQL_DELETE_CARNET = "DROP TABLE IF EXISTS " + MODEL_NAME;

    }


    /**
     * Called when the database needs to be upgraded.
     * The implementation should use this method to drop tables, add tables,
     * or do anything else it needs to upgrade to the new schema version.
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("===", "Upgrade");
        DATABASE_VERSION = i1;
        try {
            deleteTables();
            createPageTable();
        } catch (Exception e) {
            Log.e("ex===", e.getMessage());
        }
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }
}

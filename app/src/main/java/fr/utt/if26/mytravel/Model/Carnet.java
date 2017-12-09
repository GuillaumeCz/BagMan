package fr.utt.if26.mytravel.Model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by paf on 08/11/17.
 */

public class Carnet extends BaseModel {
    private String name;
    private ArrayList<Page> pages;

    public Carnet(String nom) {
        super();
        name = nom;
        pages = new ArrayList<Page>();
    }

    public Carnet(int id, String name, long created_at, long updated_at) {
        super(created_at, updated_at);
        setId(id);
        setName(name);
        pages = new ArrayList<Page>();
    }

    public Carnet(int id, String name, long created_at, long updated_at, ArrayList<Page> pages) {
        super(created_at, updated_at);
        setId(id);
        setName(name);
        setPages(pages);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Carnet : " + getId() +
                "\n- Nom : " + getName() +
                "\n - CreatedAt : " + getCreatedAt() +
                "\n - UpdatedAt : " + getUpdatedAt() +
                "\n";
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public int[] getPagesIds() {
        int[] arr_pages = new int[pages.size()];
        int cpt = 0;
        Iterator<Page> it = pages.iterator();
        while(it.hasNext()) {
            Page p = it.next();
            arr_pages[cpt] = p.getId();
            cpt++;
        }
        return arr_pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }
}

package fr.utt.if26.mytravel.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.R;

/**
 * Created by paf on 19/12/17.
 */

public class CarnetAdapter extends ArrayAdapter<Carnet> implements View.OnClickListener {

    public CarnetAdapter(Context ct, int ressource, ArrayList<Carnet> carnets) {
        super(ct, ressource, carnets);
    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        Carnet carnet = (Carnet) getItem(position);

        Log.e("====", "click ! ");
    }

    /**
     * TODO: Modifier noms des ids du visu item
     * @param position
     * @param convertV
     * @param viewG
     * @return
     */
    @Override
    public View getView(int position, View convertV, ViewGroup viewG) {
        View v = convertV;
        Carnet carnet = getItem(position);

        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.row_item, null);
        }

        if(carnet != null) {
            TextView name_tv = (TextView) v.findViewById(R.id.row_title);
            TextView updated_at = (TextView) v.findViewById(R.id.row_updatedAt);

            if(name_tv != null) {
                name_tv.setText(carnet.getName());
            }

            if(updated_at != null) {
                updated_at.setText(carnet.getUpdatedAtFormat());
            }
        }
        return v;
    }
}

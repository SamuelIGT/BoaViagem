package br.ufc.quixada.boaviagem.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.ufc.quixada.boaviagem.R;
import br.ufc.quixada.boaviagem.dao.Viagem;

/**
 * Created by samue on 25/09/2017.
 */

public class ListviewCustomAdapter extends BaseAdapter {
    private final List<Viagem> travels;
    private Activity activity;

    public ListviewCustomAdapter(List<Viagem> travels, Activity activity) {
        this.travels = travels;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return travels.size();
    }

    @Override
    public Object getItem(int i) {
        return travels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater()
                .inflate(R.layout.custom_listview_item, parent, false);

        Viagem travel = travels.get(i);

        ImageView thumbnail = (ImageView) view.findViewById(R.id.myTravels_thumbnail);
        TextView title = (TextView) view.findViewById(R.id.txt_title);
        TextView duration = (TextView) view.findViewById(R.id.txt_destination);
        TextView totalSpend = (TextView) view.findViewById(R.id.txt_totalSpend);

        thumbnail.setImageResource(travel.getThumbnail());
        title.setText(travel.getDestination());
        duration.setText(travel.getDuration());
        totalSpend.setText(travel.getTotalSpend()+"");
        return view;
    }
}

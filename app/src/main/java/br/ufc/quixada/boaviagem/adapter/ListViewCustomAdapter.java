package br.ufc.quixada.boaviagem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.boaviagem.R;
import br.ufc.quixada.boaviagem.dao.Viagem;

/**
 * Created by samue on 25/09/2017.
 */

public class ListViewCustomAdapter extends BaseAdapter {
    private final List<Viagem> travels;
    private Context context;
    private LayoutInflater inflater;

    public ListViewCustomAdapter(List<Viagem> travels, Context context) {
        this.travels = travels;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        convertView = (convertView != null) ? convertView : inflater.inflate(R.layout.custom_listview_item, null);

        Viagem travel = travels.get(i);

        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.myTravels_thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.txt_title);
        TextView duration = (TextView) convertView.findViewById(R.id.txt_duration);
        TextView totalSpend = (TextView) convertView.findViewById(R.id.txt_totalSpend);

        if(travel.isBusiness()){
            thumbnail.setImageResource(R.drawable.negocios);
        }else{
            thumbnail.setImageResource(R.drawable.lazer);
        }


        title.setText(travel.getDestination());
        duration.setText(travel.getDepartureDateString()+" a "+travel.getArrivalDateString());
        totalSpend.setText(context.getString(R.string.show_total_spend, travel.getTotalSpend()));
        return convertView;
    }
}

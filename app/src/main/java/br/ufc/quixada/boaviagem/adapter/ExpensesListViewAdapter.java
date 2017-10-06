package br.ufc.quixada.boaviagem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufc.quixada.boaviagem.R;
import br.ufc.quixada.boaviagem.dao.Cost;
import br.ufc.quixada.boaviagem.dao.Viagem;

/**
 * Created by samuel on 26/09/17.
 */

public class ExpensesListViewAdapter extends BaseAdapter{

    private List<Cost> expenses;
    private Context context;
    private LayoutInflater inflater;

    public ExpensesListViewAdapter(List<Cost> expenses, Context context) {
        this.expenses = expenses;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return expenses.size();
    }

    @Override
    public Object getItem(int i) {
        return expenses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return expenses.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = (convertView != null) ? convertView : inflater.inflate(R.layout.custom_listview_expenses, null);
        TextView costDate = (TextView) convertView.findViewById(R.id.txt_cost_date);
        TextView costTitle = (TextView) convertView.findViewById(R.id.txt_cost_title);
        TextView costAmount = (TextView) convertView.findViewById(R.id.txt_cost_amount);

        Cost cost = expenses.get(position);
        costDate.setText(cost.getStringDate());
        costTitle.setText(cost.getDescription());
        costAmount.setText(context.getString(R.string.New_Travel_Budget_Field, cost.getAmount()));

        return convertView;
    }
}

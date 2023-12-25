package com.fabiangonzalez.graphmessage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArrayAdapterPersonalChat extends ArrayAdapter<CargarEstiloChat> {
    private LayoutInflater layoutInflater;

    public ArrayAdapterPersonalChat(Context context, List<CargarEstiloChat> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.estilo_lista1, null);
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.textViewTitle));
            holder.setTextViewSubtitle((TextView) convertView.findViewById(R.id.textViewSubtitle));
            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }
        CargarEstiloChat row = getItem(position);
        holder.getTextViewTitle().setText(row.getTitle());
        holder.getTextViewSubtitle().setText(row.getSubtitle());
        return convertView;
    }

    static class Holder {
        TextView textViewTitle;
        TextView textViewSubtitle;

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle) {
            this.textViewTitle = textViewTitle;
        }

        public TextView getTextViewSubtitle(){
            return textViewSubtitle;
        }

        public void setTextViewSubtitle(TextView textViewSubtitle) {
            this.textViewSubtitle = textViewSubtitle;
        }

    }
}

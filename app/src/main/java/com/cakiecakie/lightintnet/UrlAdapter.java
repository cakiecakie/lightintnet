package com.cakiecakie.lightintnet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by naehokushin on 17/4/14.
 */

public class UrlAdapter extends ArrayAdapter<String> {
    List<String> titleList;

    private int resourceId;

    public UrlAdapter(Context context, int resourceId, List<String> titleList) {
        super(context, resourceId, titleList);
        this.resourceId = resourceId;
        this.titleList = titleList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = titleList.get(position);
        View view;
        ViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(resourceId, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.item);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(title);
        return view;
    }

    class ViewHolder {
        TextView textView;
    }
}

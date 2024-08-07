package com.ui.demo.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ui.demo.R;

import java.util.HashMap;
import java.util.List;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> titleList;
    private HashMap<String, List<String>> subtitleList;

    public MyExpandableListAdapter(Context context, List<String> titleList, HashMap<String, List<String>> subtitleList) {
        this.context = context;
        this.titleList = titleList;
        this.subtitleList = subtitleList;
    }

    @Override
    public int getGroupCount() {
        return titleList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (subtitleList.get(titleList.get(groupPosition)) != null) {
            return subtitleList.get(titleList.get(groupPosition)).size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return titleList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return subtitleList.get(titleList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) getGroup(groupPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_group, parent, false);
        }
        TextView listTitle = convertView.findViewById(R.id.listTitle);
        listTitle.setTypeface(null, Typeface.BOLD);
        listTitle.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String subtitle = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }
        TextView expandedListItem = convertView.findViewById(R.id.expandedListItem);
        expandedListItem.setText(subtitle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

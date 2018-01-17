package com.example.jensk.lab2;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    //Create a hash map that holds children
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    //This function calls expandableListAdapter to return the child positioned
    //at childPosition in group nr groupPosition (for example child 4 in group 1)
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    //This function returns the ID of the child located
    // at childPosition in group nr groupPosition (for example child 4 in group 1)
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //This function get's a view that displays the data for the child in childPosition in groupPosition
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflaInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflaInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    //This function fetches the amount of children in a group.
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    //This function fetches the group at groupPosition
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    //This function gets the amount of groups.
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    //This function gets the ID of the group at groupPosition
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //This function gets a View that displays the given group.
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    //This function returns a true/false that indicates whether the child
    // and group IDs are stable across changes to the underlying data
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //This function returns true/false that indicates if the child at childPosition
    // in group at groupPosition is selectable
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
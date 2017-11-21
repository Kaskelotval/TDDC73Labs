package com.example.jensk.lab2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
import android.widget.EditText;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private EditText searchBar;
    private int oldParent,oldChild;
    public int parent;
    public int child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        searchBar = findViewById(R.id.searchBar);
        oldParent = 0;
        oldChild = 0;
        searchBar.setText("/");
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                searchBar.setText("/"
                        + listDataHeader.get(groupPosition)
                        + "/"
                        + listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition));
                return false;
            }
        });
        final TextWatcher textWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String path = editable.toString();
                Boolean matchFound = false;
                for(int l = 0; l<expListView.getCount();l++)
                {
                    if(expListView.isGroupExpanded(l)){
                    }
                }
                if(path.length()>2){
                    if(compareLink(path.toLowerCase())==0) //No match
                    {

                        searchBar.setBackgroundColor(Color.RED);
                        Log.d("result: " , "No match");
                        matchFound = false;

                    }
                    else if(compareLink(path.toLowerCase())==1) //partial match
                    {
                        searchBar.setBackgroundColor(Color.TRANSPARENT);
                        if(path.endsWith("/"))
                            expListView.expandGroup(parent);
                        Log.d("result: " , " Partial match");
                        matchFound = false;
                    }
                    else //full match
                    {
                        searchBar.setBackgroundColor(Color.TRANSPARENT);
                        for(int k = 0; k<3;k++)
                        {
                            expListView.getChildAt(oldChild+oldParent+1).setBackgroundColor(Color.TRANSPARENT );
                            expListView.collapseGroup(k);
                        }
                        expListView.expandGroup(parent);
                        expListView.getChildAt(child+parent+1).setBackgroundColor(Color.GREEN);
                        Log.d("result: " , "Match");
                        oldParent = parent;
                        oldChild = child;
                        matchFound = true;
                    }
                }
            }
        };

        searchBar.addTextChangedListener(textWatcher);

    }

    private int compareLink(String p)
    {
        String pathString;
        String subPath;

        char[] input = p.toLowerCase().toCharArray();
        char[] path;

        Log.d("size ",  ""+listDataHeader.size());


        for(int i = 0; i < listDataHeader.size(); i++)
        {
            for(int j = 0; j<listDataChild.get(listDataHeader.get(i)).size(); j++)
            {
                pathString = "/" + listDataHeader.get(i) + "/" + listDataChild.get(listDataHeader.get(i)).get(j);
                if(pathString.length()>= p.length())
                {
                    subPath = pathString.substring(0,p.length()).toLowerCase();
                    Log.d("p ", p);
                    Log.d("pathString ", pathString);
                    Log.d("subPath ", subPath);

                    parent = i;
                    child = j;
                    if(subPath.length()==pathString.length() && subPath.equals(p))
                        return 2;

                    else if(subPath.equals(p))
                        return 1;
                }
            }
        }

        return 0;

        //if no match return 0
        //if partial match 1
        //if full match 2
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>(); //parent
        listDataChild = new HashMap<String, List<String>>(); //Child

        // Adding child data
        listDataHeader.add("Färger");
        listDataHeader.add("Frukter");
        listDataHeader.add("Ölsorter");

        // Adding child data
        List<String> colors = new ArrayList<String>();
        colors.add("Grön");
        colors.add("Röd");
        colors.add("Blå");
        colors.add("Orange");
        colors.add("Lila");
        colors.add("Rosa");

        List<String> fruits = new ArrayList<String>();
        fruits.add("Apelsin");
        fruits.add("Satsuma");
        fruits.add("Banan");
        fruits.add("Klementin");
        fruits.add("Äpple");

        List<String> beer = new ArrayList<String>();
        beer.add("Breznak");
        beer.add("Oppigård");
        beer.add("Åbro");
        beer.add("Mikeller");

        listDataChild.put(listDataHeader.get(0), colors); // Header, Child data
        listDataChild.put(listDataHeader.get(1), fruits);
        listDataChild.put(listDataHeader.get(2), beer);
    }


}
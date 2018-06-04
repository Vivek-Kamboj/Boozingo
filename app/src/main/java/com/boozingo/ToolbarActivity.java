package com.boozingo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ToolbarActivity extends AppCompatActivity implements SearchAnimationToolbar.OnSearchQueryChangedListener {

    private SearchAnimationToolbar toolbar;
    private TextView searchText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        toolbar = (SearchAnimationToolbar) findViewById(R.id.toolbar);
        searchText = (TextView) findViewById(R.id.searchText);

        toolbar.setSupportActionBar(ToolbarActivity.this);
        toolbar.setOnSearchQueryChangedListener(ToolbarActivity.this);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.action_search) {
            toolbar.onSearchIconClick();
            return true;
        } else if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        boolean handledByToolbar = toolbar.onBackPressed();

        if (!handledByToolbar) {
            super.onBackPressed();
        }
    }

    @Override
    public void onSearchCollapsed() {
        searchText.setText("collapsed");
    }

    @Override
    public void onSearchQueryChanged(String query) {
        searchText.setText("Searching: " + query);
    }

    @Override
    public void onSearchExpanded() {
        searchText.setText("expanded");
    }

    @Override
    public void onSearchSubmitted(String query) {
        searchText.setText(query);
    }
}
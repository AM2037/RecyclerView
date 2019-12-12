package com.example.android.recyclerview;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();

    // add member variables for RecyclerView and adapter
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int wordListSize = mWordList.size();
               // Add word to the wordList
                mWordList.addLast("+ Word " + wordListSize);
                // Notify adapter that data's changed
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to bottom
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        // Populate wordlist with up to 20 datapoints at the end using for loop
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

        // Creates RecyclerView and connects it to adapter/data. Insert AFTER mWordList initialization.
        // Get handle to RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create adapter and supply data to display
        mAdapter = new WordListAdapter(this, mWordList);
        // Connect adapter with RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        // Give RecyclerView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

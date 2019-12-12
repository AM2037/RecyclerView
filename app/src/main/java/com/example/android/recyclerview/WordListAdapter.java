package com.example.android.recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    // Variable to hold data in adapter, or list of strings
    private final LinkedList<String> mWordList;

    // Constructor to intialize wordlist from data. Inflate XML for list item. Reads description and
    // converts to views.
    private LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            // Connect onClickListener with the View
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get position of item clicked
            int mPosition = getLayoutPosition();
            // Use position to access affected item in mWordList
            String element = mWordList.get(mPosition);
            // Change word in mWordList
            mWordList.set(mPosition, "Clicked! " + element);
            // Notify adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Constructor for WordListAdapter(). Instantiate layout inflator and set mWordList to passed in data
     * @param context the passed in data
     * @param wordList list of words
     */
    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    /**
     * Similar to onCreate method. Inflates item layout
     * @return returns a ViewHolder with layout & adapter
     */
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    /**
     * onBindViewHolder() onnects your data to the viewholder
     * @param holder holds the views
     * @param position where the word is on the list (index?)
     */
    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    /**
     *  getItemCount() to get size of list
     * @return returns size of wordlist
     */
    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}

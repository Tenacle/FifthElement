package fifthelement.theelement.presentation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.SearchManager;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.logging.Logger;

import fifthelement.theelement.R;
import fifthelement.theelement.business.Services.SongService;
import fifthelement.theelement.objects.Song;
import fifthelement.theelement.presentation.SongsListAdapter;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {
    private SearchView mSearchView;
    private ListView mListView;
    private View view;
    private SongService songService;
    List<Song> songs;
    private SongsListAdapter songsListAdapter;
    private SearchView.OnQueryTextListener onQueryTextListener;

    private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(onQueryTextListener);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        songService = new SongService();
        view = inflater.inflate(R.layout.search_fragment, container, false);
        ListView listView = view.findViewById(R.id.search_song_list_view_item);
        mSearchView = view.findViewById(R.id.search_view_item);
        mListView = view.findViewById(R.id.search_song_list_view_item);

        onQueryTextListener = createNewOnQueryTextListener();

        songsListAdapter = new SongsListAdapter(getActivity(), songService.getSongs());
        mListView.setAdapter(songsListAdapter);

        //mListView.setTextFilterEnabled(true);
        setupSearchView();


        songs = songService.getSongs();

        if(songs != null) {
            final SongsListAdapter songListAdapter = new SongsListAdapter(getActivity(), songs);
            listView.setAdapter(songListAdapter);

        } else {

        }
        return view;
    }

    private void clearSearchViewResults(){
        mSearchView.onActionViewCollapsed();
        mSearchView.setQuery("", false);
        mSearchView.clearFocus();
    }

    // These methods override the below two, but the below two
    // must be present to satisfy the implement requirements
    private OnQueryTextListener createNewOnQueryTextListener(){
        return new OnQueryTextListener() {
            //TODO implement different type of search when search submitted?
            @Override
            // Search on submit button
            public boolean onQueryTextSubmit(String query) {
                songs = songService.search(query);
                songsListAdapter = new SongsListAdapter(getActivity(), songs);
                mListView.setAdapter(songsListAdapter);
                songsListAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                songs = songService.search(newText);
                songsListAdapter = new SongsListAdapter(getActivity(), songs);
                mListView.setAdapter(songsListAdapter);
                songsListAdapter.notifyDataSetChanged();
                return false;
            }
        };
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
package fifthelement.theelement.business.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fifthelement.theelement.application.Services;
import fifthelement.theelement.business.exceptions.SongAlreadyExistsException;
import fifthelement.theelement.objects.Album;
import fifthelement.theelement.objects.Author;
import fifthelement.theelement.objects.PlayList;
import fifthelement.theelement.objects.Song;
import fifthelement.theelement.persistence.AlbumPersistence;
import fifthelement.theelement.persistence.AuthorPersistence;
import fifthelement.theelement.persistence.PlayListPersistence;
import fifthelement.theelement.persistence.SongPersistence;
import fifthelement.theelement.persistence.hsqldb.PersistenceException;

public class SongService {

    private SongPersistence songPersistence;
    private AlbumPersistence albumPersistence;
    private AuthorPersistence authorPersistence;
    private PlayListPersistence playListPersistence;

    public SongService() {
        songPersistence = Services.getSongPersistence();
        albumPersistence = Services.getAlbumPersistence();
        authorPersistence = Services.getAuthorPersistence();
        playListPersistence = Services.getPlayListPersistence();
    }

    public Song getSongByUUID(UUID uuid) {
        return songPersistence.getSongByUUID(uuid);
    }

    public List<Song> getSongs() {
        return songPersistence.getAllSongs();
    }

    public boolean insertSong(Song song) throws PersistenceException, IllegalArgumentException, SongAlreadyExistsException {
        if(song == null)
            throw new IllegalArgumentException();
        if(pathExists(song.getPath()))
            throw new SongAlreadyExistsException(song.getPath());
        return songPersistence.storeSong(song);
    }

    public boolean updateSong(Song song) throws IllegalArgumentException {
        if(song == null)
            throw new IllegalArgumentException();
        return songPersistence.updateSong(song);
    }

    public boolean deleteSong(Song songToRemove) throws IllegalArgumentException {
        if(songToRemove == null)
            throw new IllegalArgumentException();
        Song song = songPersistence.getSongByUUID(songToRemove.getUUID());

        if( song != null ) {

            // deletes songs from existing PlayList if it's there
            // implementation for this hasn't been fully decided. this is a STUB
            for( PlayList p : playListPersistence.getAllPlayLists() ) {
                if( p.contains(song) ) {
                    p.removeSong(song);
                    playListPersistence.updatePlayList(p);
                }
            }

            songPersistence.deleteSong(song);
            return true;
        }
        return false;
    }

    // Method checks if any song already has the same path
    // and returns true if a songs exists with the same path
    public boolean pathExists(String path) {
        List<Song> songs = getSongs();
        boolean toReturn = false;

        for(Song song : songs) {
            if(song.getPath().equals(path)) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }

    public void sortSongs(List<Song> songs) {
        Collections.sort(songs);
    }

    public List<Song> search(String query) {
        List<Song> allSongs = getSongs();
        ArrayList<Song> matchesList = new ArrayList<>();
        Matcher matcher;

        if (validateRegex(query)){
            String regex = query;
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

            for (Song s: allSongs) {
                matcher = pattern.matcher(s.getName());
                if ( matcher.find()){
                    matchesList.add(s);
                }
            }
        }
        return matchesList;
    }

    // Making sure the regex pattern doesn't contain special
    // characters, which screws up regex compiling
    private boolean validateRegex(String regexPattern){
        boolean result = false;
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher matcher = special.matcher(regexPattern);
        if (matcher.find() == false)
            result = true;
        return result;
    }
}

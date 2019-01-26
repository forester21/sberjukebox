package com.sber.jukeBox.datastore;

import com.sber.jukeBox.datastore.api.JukeBoxStore;
import com.sber.jukeBox.model.TrackEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JukeBoxStoreImpl implements JukeBoxStore {

    private static Map<Integer, TrackEntity> tracks = new ConcurrentHashMap<>();

    //TODO think about intellectual balancing of the store
    public void addTrack(TrackEntity entity) {
        tracks.putIfAbsent(entity.getTrackId(), entity);
    }

    public TrackEntity getTrack(int trackId) {
        if (!tracks.containsKey(trackId)) {
            throw new RuntimeException("Track with id: " + trackId + " is not found");
        }
        return tracks.get(trackId);
    }

    public void remove(int trackId) {
        if (!tracks.containsKey(trackId)) {
            throw new RuntimeException("Track with id: " + trackId + " has been already removed");
        }
        tracks.remove(trackId);
    }
}
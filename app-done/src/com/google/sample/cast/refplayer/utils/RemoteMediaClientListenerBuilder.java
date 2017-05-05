package com.google.sample.cast.refplayer.utils;

import android.widget.SeekBar;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Consumer;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

public class RemoteMediaClientListenerBuilder {

    private Optional<Runnable> onStatusUpdated = Optional.empty();
    private Optional<Runnable> onMetadataUpdated = Optional.empty();
    private Optional<Runnable> onQueueStatusUpdated = Optional.empty();
    private Optional<Runnable> onPreloadStatusUpdated = Optional.empty();
    private Optional<Runnable> onSendingRemoteMediaRequest = Optional.empty();
    private Optional<Runnable> onAdBreakStatusUpdated = Optional.empty();


    public RemoteMediaClientListenerBuilder withStatusUpdated(Runnable val){ onStatusUpdated = Optional.of(val); return this; }
    public RemoteMediaClientListenerBuilder withMetadataUpdated(Runnable val){ onMetadataUpdated = Optional.of(val); return this; }
    public RemoteMediaClientListenerBuilder withQueueStatusUpdated(Runnable val){ onQueueStatusUpdated = Optional.of(val); return this; }
    public RemoteMediaClientListenerBuilder withPreloadStatusUpdated(Runnable val){ onPreloadStatusUpdated = Optional.of(val); return this; }
    public RemoteMediaClientListenerBuilder withSendingRemoteMediaRequest(Runnable val){ onSendingRemoteMediaRequest = Optional.of(val); return this; }
    public RemoteMediaClientListenerBuilder withAdBreakStatusUpdated(Runnable val){ onAdBreakStatusUpdated = Optional.of(val); return this; }

    public RemoteMediaClient.Listener build(){
        return new RemoteMediaClient.Listener() {
            @Override public void onStatusUpdated() {onStatusUpdated.ifPresent(Runnable::run);}
            @Override public void onMetadataUpdated() {onMetadataUpdated.ifPresent(Runnable::run);}
            @Override public void onQueueStatusUpdated() {onQueueStatusUpdated.ifPresent(Runnable::run);}
            @Override public void onPreloadStatusUpdated() {onPreloadStatusUpdated.ifPresent(Runnable::run);}
            @Override public void onSendingRemoteMediaRequest() {onSendingRemoteMediaRequest.ifPresent(Runnable::run);}
            @Override public void onAdBreakStatusUpdated() {onAdBreakStatusUpdated.ifPresent(Runnable::run);}
        };
    }
}

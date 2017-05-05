package com.google.sample.cast.refplayer.utils;

import android.widget.SeekBar;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Consumer;

public class OnSeekBarChangeListenerBuilder {

    private Optional<TriConsumer<SeekBar,Integer,Boolean>> onProgressChanged = Optional.empty();
    private Optional<Consumer<SeekBar>> onStartTrackingTouch = Optional.empty();
    private Optional<Consumer<SeekBar>> onStopTrackingTouch = Optional.empty();

    public OnSeekBarChangeListenerBuilder withProgressChanged(TriConsumer<SeekBar,Integer,Boolean> val){ onProgressChanged = Optional.of(val); return this; }
    public OnSeekBarChangeListenerBuilder withStartTrackingTouch(Consumer<SeekBar> val){ onStartTrackingTouch = Optional.of(val); return this; }
    public OnSeekBarChangeListenerBuilder withStopTrackingTouch(Consumer<SeekBar> val){ onStopTrackingTouch = Optional.of(val); return this; }

    public SeekBar.OnSeekBarChangeListener build(){
        return new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar s, int p, boolean f) { onProgressChanged.ifPresent(c->c.accept(s,p,f)); }
            @Override public void onStartTrackingTouch(SeekBar s) { onStartTrackingTouch.ifPresent(c->c.accept(s)); }
            @Override public void onStopTrackingTouch(SeekBar s) { onStopTrackingTouch.ifPresent(c->c.accept(s)); }
        };
    }
}

package com.google.sample.cast.refplayer.utils;

import com.annimon.stream.Optional;
import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.Consumer;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;

public class SessionManagerListenerBuilder<T extends Session> {
        private Optional<Consumer<T>> onSessionStarting = Optional.empty();
        private Optional<BiConsumer<T,String>> onSessionStarted = Optional.empty();
        private Optional<BiConsumer<T,Integer>> onSessionStartFailed = Optional.empty();
        private Optional<Consumer<T>> onSessionEnding = Optional.empty();
        private Optional<BiConsumer<T,Integer>> onSessionEnded = Optional.empty();
        private Optional<BiConsumer<T,String>> onSessionResuming = Optional.empty();
        private Optional<BiConsumer<T,Boolean>> onSessionResumed = Optional.empty();
        private Optional<BiConsumer<T,Integer>> onSessionResumeFailed = Optional.empty();
        private Optional<BiConsumer<T,Integer>> onSessionSuspended = Optional.empty();

        public SessionManagerListenerBuilder<T> withSessionStarting(Consumer<T> val){ onSessionStarting = Optional.of(val); return this; }
        public SessionManagerListenerBuilder<T> withSessionStarted(BiConsumer<T,String> val){ onSessionStarted = Optional.of(val); return this; }
        public SessionManagerListenerBuilder<T> withSessionStartFailed(BiConsumer<T,Integer> val){ onSessionStartFailed = Optional.of(val); return this; }
        public SessionManagerListenerBuilder<T> withSessionEnding(Consumer<T> val){ onSessionEnding = Optional.of(val); return this; }
        public SessionManagerListenerBuilder<T> withSessionEnded(BiConsumer<T,Integer> val){ onSessionEnded = Optional.of(val); return this; }
        public SessionManagerListenerBuilder<T> withSessionResuming(BiConsumer<T,String> val){ onSessionResuming = Optional.of(val); return this; }
        public SessionManagerListenerBuilder<T> withSessionResumed(BiConsumer<T,Boolean> val){ onSessionResumed = Optional.of(val); return this; }
        public SessionManagerListenerBuilder<T> withSessionResumeFailed(BiConsumer<T,Integer> val){ onSessionResumeFailed = Optional.of(val); return this; }
        public SessionManagerListenerBuilder<T> withSessionSuspended(BiConsumer<T,Integer> val){ onSessionSuspended = Optional.of(val); return this; }

        public SessionManagerListener<T> build(){
                return new SessionManagerListener<T>() {
                        @Override public void onSessionStarting(T t) { onSessionStarting.ifPresent(c -> c.accept(t)); }
                        @Override public void onSessionStarted(T t, String s) {onSessionStarted.ifPresent(c -> c.accept(t,s)); }
                        @Override public void onSessionStartFailed(T t, int i) { onSessionStartFailed.ifPresent(c -> c.accept(t,i));}
                        @Override public void onSessionEnding(T t) { onSessionEnding.ifPresent(c -> c.accept(t));}
                        @Override public void onSessionEnded(T t, int i) { onSessionEnded.ifPresent(c -> c.accept(t,i));}
                        @Override public void onSessionResuming(T t, String s) { onSessionResuming.ifPresent(c -> c.accept(t,s));}
                        @Override public void onSessionResumed(T t, boolean b) { onSessionResumed.ifPresent(c -> c.accept(t,b));}
                        @Override public void onSessionResumeFailed(T t, int i) { onSessionResumeFailed.ifPresent(c -> c.accept(t,i));}
                        @Override public void onSessionSuspended(T t, int i) { onSessionSuspended.ifPresent(c -> c.accept(t,i));}
                };
        }
}

package com.khunzohn.rxstreamer.model;

import com.google.auto.value.AutoValue;
import com.khunzohn.rxstreamer.util.Visibility;

import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by khunzohn on 12/10/17.
 */
@AutoValue
public abstract class NewsModel {

    @Visibility
    public abstract int newsVisibility();

    @Visibility
    public abstract int retryVisibility();

    @Visibility
    public abstract int progressVisibility();

    @Visibility
    public abstract int errorVisibility();

    @Nullable
    public abstract String retryMessage();

    @Nullable
    public abstract String errorMessage();

    @Nullable
    public abstract List<NewModel> newModels();

    public static NewsModel create(int newsVisibility, int retryVisibility, int progressVisibility, int errorVisibility, String retryMessage, String errorMessage, List<NewModel> newModels) {
        return builder()
                .newsVisibility(newsVisibility)
                .retryVisibility(retryVisibility)
                .progressVisibility(progressVisibility)
                .errorVisibility(errorVisibility)
                .retryMessage(retryMessage)
                .errorMessage(errorMessage)
                .newModels(newModels)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_NewsModel.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder retryVisibility(int retryVisibility);

        public abstract Builder progressVisibility(int progressVisibility);

        public abstract Builder errorVisibility(int errorVisibility);

        public abstract Builder retryMessage(String retryMessage);

        public abstract Builder newModels(List<NewModel> newModels);

        public abstract Builder errorMessage(String errorMessage);

        public abstract Builder newsVisibility(int newsVisibility);

        public abstract NewsModel build();
    }
}

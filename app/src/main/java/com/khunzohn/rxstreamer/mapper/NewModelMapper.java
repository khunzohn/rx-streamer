package com.khunzohn.rxstreamer.mapper;

import android.content.Context;

import com.khunzohn.domain.model.New;
import com.khunzohn.rxstreamer.model.NewModel;
import com.khunzohn.rxstreamer.util.DateFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by khunzohn on 12/18/17.
 */

public final class NewModelMapper extends ModelMapper<NewModel, New> {

    private final DateFormatter dateFormatter;

    @Inject
    public NewModelMapper(Context context, DateFormatter dateFormatter) {
        super(context);
        this.dateFormatter = dateFormatter;
    }

    @Override
    public NewModel map(New domainModel) {
        return NewModel.builder()
                .id(domainModel.id)
                .title(domainModel.title)
                .content(domainModel.content)
                .date(dateFormatter.format("", domainModel.date))
                .reporter(domainModel.reporter)
                .build();
    }

    List<NewModel> map(List<New> news) {
        List<NewModel> newModels = new ArrayList<>();
        for (New mNew : news) {
            newModels.add(map(mNew));
        }
        return newModels;
    }
}

package com.rs.leanbacknative.presenters;

import android.content.Context;

import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;

import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.models.Card;

import java.util.HashMap;

public class CardPresenterSelector extends PresenterSelector {
    private final Context mContext;
    private final ReadableMap mAttributes;
    private final HashMap<Card.Type, Presenter> presenters = new HashMap<Card.Type, Presenter>();

    public CardPresenterSelector(Context context, ReadableMap attributes) {
        mContext = context;
        mAttributes = attributes;
    }

    @Override
    public Presenter getPresenter(Object item) {
        if (!(item instanceof Card)) throw new RuntimeException(
                String.format("The PresenterSelector only supports data items of type '%s'",
                        Card.class.getName()));
        Card card = (Card) item;
        Presenter presenter = presenters.get(card.getPresenterType());

        if (presenter == null) {
            switch (card.getPresenterType()) {
                case FULL:
                    presenter = new OverlayLiveCardPresenter(mAttributes);
                    break;
                default:
                    presenter = new DefaultCardPresenter(mAttributes);
                    break;
            }
        }
        presenters.put(card.getPresenterType(), presenter);
        return presenter;
    }
}

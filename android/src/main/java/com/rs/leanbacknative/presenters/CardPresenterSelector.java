package com.rs.leanbacknative.presenters;

import android.content.Context;

import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;

import com.facebook.react.bridge.ReadableMap;
import com.rs.leanbacknative.models.Card;

import java.util.HashMap;

public class CardPresenterSelector extends PresenterSelector {
    private final ReadableMap mAttributes;
    private final HashMap<Card.Type, Presenter> presenters = new HashMap<Card.Type, Presenter>();

    public CardPresenterSelector(Context context, ReadableMap attributes) {
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
                case CHANNEL_TILE:
                    presenter = new ChannelCardPresenter(mAttributes);
                    break;
                case CONTINUE_WATCHING_TILE:
                    presenter = new ContinueWatchingCardPresenter(mAttributes);
                    break;
                case APP_TILE:
                    presenter = new AppCardPresenter(mAttributes);
                    break;
                case REGULAR_TILE:
                case GRID_TILE:
                    presenter = new RegularCardPresenter(mAttributes);
                    break;
                case SERVICE_CARD:
                    presenter = new ServiceCardPresenter(mAttributes);
                    break;
                default:
                    presenter = new DefaultCardPresenter(mAttributes, card);
                    break;
            }
        }

        presenters.put(card.getPresenterType(), presenter);

        return presenter;
    }
}

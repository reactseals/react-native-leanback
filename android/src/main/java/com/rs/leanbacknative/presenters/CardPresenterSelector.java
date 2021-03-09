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
                case PROGRESS_LOGO_OVERLAY:
                    presenter = new ProgressLogoOverlayCardPresenter(mAttributes);
                    break;
                case PROGRESS_LOGO:
                    presenter = new ProgressLogoCardPresenter(mAttributes);
                    break;
                case PROGRESS_OVERLAY:
                    presenter = new ProgressOverlayCardPresenter(mAttributes);
                    break;
                case LOGO_OVERLAY:
                    presenter = new LogoOverlayCardPresenter(mAttributes);
                    break;
                case LOGO:
                    presenter = new LogoCardPresenter(mAttributes);
                    break;
                case OVERLAY:
                    presenter = new OverlayCardPresenter(mAttributes);
                    break;
                case PROGRESS:
                    presenter = new ProgressCardPresenter(mAttributes);
                    break;
                case COLOR_TEXT:
                    presenter = new ColorTextCardPresenter(mAttributes);
                    break;
                case VIDEO:
                    presenter = new VideoCardPresenter(mAttributes);
                    break;
                case GRID:
                    presenter = new GridCardPresenter(mAttributes, card);
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

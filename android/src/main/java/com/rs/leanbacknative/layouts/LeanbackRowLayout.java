package com.rs.leanbacknative.layouts;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.leanback.app.RowsFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.rs.leanbacknative.presenters.CardPresenterSelector;
import com.rs.leanbacknative.presenters.RowPresenter;
import com.rs.leanbacknative.utils.Constants;
import com.rs.leanbacknative.utils.DataManager;
import com.rs.leanbacknative.models.Card;
import java.util.List;

@SuppressLint("ViewConstructor")
public class LeanbackRowLayout extends FrameLayout {

    private ThemedReactContext mContext;
    private ArrayObjectAdapter mRowsAdapter;
    private String mRowTitle;
    private Card mLastSelectedItem;
    private List<Card> mRows;
    private RowsFragment mRowsFragment;
    private boolean firstSelectEventIgnored = false;

    public LeanbackRowLayout(@NonNull ThemedReactContext context, RowsFragment rowsFragment) {
        super(context);

        mContext = context;
        mRowsFragment = rowsFragment;
        initializeFragmentManager();
        setupEventListeners();
    }

    private void initializeFragmentManager() {
        FragmentManager fragmentManager = mContext.getCurrentActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(mRowsFragment, "RowsFragment").commit();
        fragmentManager.executePendingTransactions();
    }

    private void initializeAdapter(ReadableMap attributes) {
        String focusedCardAlignment = attributes.hasKey("focusedCardAlignment") ? attributes.getString("focusedCardAlignment") : Constants.FOCUSED_CARD_ALIGNMENT_LEFT;
        int numberOfRows = attributes.hasKey("numberOfRows") ? attributes.getInt("numberOfRows") : 1;

        ListRowPresenter listRowPresenter = new RowPresenter(focusedCardAlignment);
        listRowPresenter.setNumRows(numberOfRows);

        listRowPresenter.setShadowEnabled(false);
        mRowsAdapter = new ArrayObjectAdapter(listRowPresenter);
        mRowsFragment.setAdapter(mRowsAdapter);
    }

    private void setupEventListeners() {
        mRowsFragment.setOnItemViewClickedListener(new ItemViewClickedListener());
        mRowsFragment.setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // it's a workaround for situation when we have multiple rows and navigating up and down between them
        // in that case onItemSelected is not triggered, therefore we're returning latest focused item in the row
        // or first one if row was never focused
        if (event.getAction() == 1) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_UP:
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    if (mLastSelectedItem == null) {
                        mLastSelectedItem = mRows.get(0);
                    }

                    WritableMap ev = Arguments.createMap();
                    ev.putString("item", mLastSelectedItem.toJSON());
                    mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), Constants.EVENT_ON_FOCUS, ev);
            }
        }

        return super.dispatchKeyEvent(event);
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(
            Presenter.ViewHolder itemViewHolder,
            Object item,
            androidx.leanback.widget.RowPresenter.ViewHolder rowViewHolder,
            Row row) {

            if (item instanceof Card && firstSelectEventIgnored) {
                Card card = (Card) item;
                mLastSelectedItem = card;
                WritableMap event = Arguments.createMap();
                event.putString("item", card.toJSON());
                mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), Constants.EVENT_ON_FOCUS, event);
            }

            //leanback fires this event initially when data is loaded even if item is not actually selected
            if (item instanceof Card) {
                firstSelectEventIgnored = true;
            }
        }
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  androidx.leanback.widget.RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof Card) {
                Card card = (Card) item;
                WritableMap event = Arguments.createMap();
                event.putString("item", card.toJSON());
                mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), Constants.EVENT_ON_PRESS, event);
            }
        }
    }

    public void setDataAndAttributes(ReadableMap dataAndAttributes) {
        ReadableArray data = dataAndAttributes.getArray("data");
        ReadableMap attributes = dataAndAttributes.getMap("attributes");

        mRows = DataManager.setupData(data, attributes);

        if (mRowsAdapter != null) {
            mRowsAdapter.clear();
        }

        CardPresenterSelector cardPresenterSelector = new CardPresenterSelector(mContext, attributes);
        ArrayObjectAdapter mListRowAdapterWithData = new ArrayObjectAdapter(cardPresenterSelector);

        if (attributes != null) {
            initializeAdapter(attributes);
        }

        for (int i = 0; i < mRows.size(); i++) {
            mListRowAdapterWithData.add(mRows.get(i));
        }

        HeaderItem header = new HeaderItem(0, mRowTitle);

        mRowsAdapter.add(new ListRow(header, mListRowAdapterWithData));
        WritableMap event = Arguments.createMap();
        event.putString("data", DataManager.getViewIds().toString());
        mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), Constants.EVENT_ON_DATA_IDS_READY, event);
    }

    public void setRowTitle(String title) {
        mRowTitle = title;
    }
}

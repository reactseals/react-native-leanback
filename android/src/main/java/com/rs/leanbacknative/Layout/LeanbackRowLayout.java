package com.rs.leanbacknative.Layout;

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
import com.rs.leanbacknative.Presenter.CardPresenter;
import com.rs.leanbacknative.Presenter.OverlayCardPresenter;
import com.rs.leanbacknative.DataManager;
import com.rs.leanbacknative.Model.NativeRowItem;

import java.util.List;

@SuppressLint("ViewConstructor")
public class LeanbackRowLayout extends FrameLayout {
    private final String LOG_DEBUG_TAG = "RowsFragmentDebug";

    private ThemedReactContext mContext;
    private ArrayObjectAdapter mRowsAdapter;
    private String mRowTitle;
    private NativeRowItem mLastSelectedItem;
    private List<NativeRowItem> mRows;
    private ListRowPresenter mListRowPresenter;
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
        String focusedCardAlignment = attributes.hasKey("focusedCardAlignment") ? attributes.getString("focusedCardAlignment") : "left";
        int numberOfRows = attributes.hasKey("numberOfRows") ? attributes.getInt("numberOfRows") : 1;

        mListRowPresenter = new OTTRowPresenter(focusedCardAlignment);
        mListRowPresenter.setNumRows(numberOfRows);

        mListRowPresenter.setShadowEnabled(false);
        mRowsAdapter = new ArrayObjectAdapter(mListRowPresenter);
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
                    mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onFocus", ev);
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

            if (item instanceof NativeRowItem && firstSelectEventIgnored) {
                NativeRowItem nativeRowItem = (NativeRowItem) item;
                mLastSelectedItem = nativeRowItem;
                WritableMap event = Arguments.createMap();
                event.putString("item", nativeRowItem.toJSON());
                mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onFocus", event);
            }

            //leanback fires this event initially when data is loaded even if item is not actually selected
            if (item instanceof NativeRowItem) {
                firstSelectEventIgnored = true;
            }
        }
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  androidx.leanback.widget.RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof NativeRowItem) {
                NativeRowItem nativeRowItem = (NativeRowItem) item;
                WritableMap event = Arguments.createMap();
                event.putString("item", nativeRowItem.toJSON());
                mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onPress", event);
            }
        }
    }

    public void setDataAndAttributes(ReadableMap dataAndAttributes) {
        ReadableArray data = dataAndAttributes.getArray("data");

        mRows = DataManager.setupData(data);
        ArrayObjectAdapter mListRowAdapterWithData;

        ReadableMap attributes = dataAndAttributes.getMap("attributes");
        if (attributes != null) {
            initializeAdapter(attributes);
            if (DataManager.isOverlayPresenter()) {
                OverlayCardPresenter cardPresenter =  new OverlayCardPresenter(attributes);
                mListRowAdapterWithData = new ArrayObjectAdapter(cardPresenter);
            } else {
                CardPresenter cardPresenter = new CardPresenter(attributes);
                mListRowAdapterWithData = new ArrayObjectAdapter(cardPresenter);
            }
        } else {
            if (DataManager.isOverlayPresenter()) {
                OverlayCardPresenter cardPresenter =  new OverlayCardPresenter();
                mListRowAdapterWithData = new ArrayObjectAdapter(cardPresenter);
            } else {
                CardPresenter cardPresenter = new CardPresenter();
                mListRowAdapterWithData = new ArrayObjectAdapter(cardPresenter);
            }
        }

        for (int i = 0; i < mRows.size(); i++) {
            mListRowAdapterWithData.add(mRows.get(i));
        }

        HeaderItem header = new HeaderItem(0, mRowTitle);

        mRowsAdapter.clear();
        mRowsAdapter.add(new ListRow(header, mListRowAdapterWithData));
        WritableMap event = Arguments.createMap();
        event.putString("data", DataManager.getViewIds().toString());
        mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onDataIdsReady", event);
    }


    public void setRowTitle(String title) {
        mRowTitle = title;
    }
}

package com.rs.leanbacknative.Layout;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.leanback.app.RowsFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.rs.leanbacknative.CardPresenter;
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


    public LeanbackRowLayout(@NonNull ThemedReactContext context, RowsFragment rowsFragment) {
        super(context);

        mContext = context;
        initializeAdapter(rowsFragment);
        setupEventListeners(rowsFragment);
    }

    private void initializeAdapter(RowsFragment rowsFragment) {
        ListRowPresenter listRowPresenter = new FocusedItemAtStartListRowPresenter();
        mRowsAdapter = new ArrayObjectAdapter(listRowPresenter);

        FragmentManager fragmentManager = mContext.getCurrentActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(rowsFragment, "RowsFragment").commit();
        fragmentManager.executePendingTransactions();
        rowsFragment.setAdapter(mRowsAdapter);
    }

    private void setupEventListeners(RowsFragment rowsFragment) {
        rowsFragment.setOnItemViewClickedListener(new ItemViewClickedListener());
        rowsFragment.setOnItemViewSelectedListener(new ItemViewSelectedListener());
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
                RowPresenter.ViewHolder rowViewHolder,
                Row row) {

            if (item instanceof NativeRowItem) {
                NativeRowItem nativeRowItem = (NativeRowItem) item;
                mLastSelectedItem = nativeRowItem;
                WritableMap event = Arguments.createMap();
                event.putString("item", nativeRowItem.toJSON());
                mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onFocus", event);
            }
        }
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

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
        CardPresenter mCardPresenter;

        ReadableMap attributes = dataAndAttributes.getMap("attributes");
        if (attributes != null) {
            mCardPresenter = new CardPresenter(attributes);
        } else {
            mCardPresenter = new CardPresenter();
        }

        mListRowAdapterWithData = new ArrayObjectAdapter(mCardPresenter);

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
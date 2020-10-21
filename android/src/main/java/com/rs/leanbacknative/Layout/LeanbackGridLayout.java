package com.rs.leanbacknative.Layout;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.leanback.app.VerticalGridFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.rs.leanbacknative.Presenter.OverlayCardPresenter;
import com.rs.leanbacknative.DataManager;
import com.rs.leanbacknative.Model.NativeRowItem;

import java.util.List;


@SuppressLint("ViewConstructor")
public class LeanbackGridLayout extends FrameLayout {
    private ThemedReactContext mContext;
    private OverlayCardPresenter mCardPresenter;
    private ArrayObjectAdapter mRowsAdapter;
    private VerticalGridFragment mVerticalGridFragment;

    public LeanbackGridLayout(@NonNull ThemedReactContext context, VerticalGridFragment verticalGridFragment) {
        super(context);

        mContext = context;
        mVerticalGridFragment = verticalGridFragment;
        initializeAdapter(verticalGridFragment);
        setupEventListeners(verticalGridFragment);
    }

    private void initializeAdapter(VerticalGridFragment verticalGridFragment) {
        VerticalGridPresenter verticalGridPresenter = new OTTGridPresenter();
        verticalGridPresenter.setNumberOfColumns(4);
        verticalGridFragment.setGridPresenter(verticalGridPresenter);


        mRowsAdapter = new ArrayObjectAdapter(new OverlayCardPresenter());

        FragmentManager fragmentManager = mContext.getCurrentActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(verticalGridFragment, "GridFragment").commit();
        fragmentManager.executePendingTransactions();
        verticalGridFragment.setAdapter(mRowsAdapter);
    }

    private void setupEventListeners(VerticalGridFragment verticalGridFragment) {
        verticalGridFragment.setOnItemViewClickedListener(new ItemViewClickedListener());
        verticalGridFragment.setOnItemViewSelectedListener(new ItemViewSelectedListener());
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
                WritableMap event = Arguments.createMap();
                event.putString("item", nativeRowItem.toJSON());
                event.putInt("focusedRowIndex", mRowsAdapter.indexOf(row));
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
        List<NativeRowItem> rows = DataManager.setupData(data);

        CardPresenter cardPresenter;

        ReadableMap attributes = dataAndAttributes.getMap("attributes");
        if (attributes != null) {
            cardPresenter = new CardPresenter(attributes);
        } else {
            cardPresenter = new CardPresenter();
        }
        
        mRowsAdapter = new ArrayObjectAdapter(cardPresenter);

        mRowsAdapter.clear();
        for (int i = 0; i < rows.size(); i++) {
            mRowsAdapter.add(rows.get(i));
        }

        mVerticalGridFragment.setAdapter(mRowsAdapter);

        WritableMap event = Arguments.createMap();
        event.putString("data", DataManager.getViewIds().toString());
        mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onDataIdsReady", event);
    }
}

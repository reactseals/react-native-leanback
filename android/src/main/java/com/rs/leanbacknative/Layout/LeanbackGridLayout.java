package com.rs.leanbacknative.Layout;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.rs.leanbacknative.CardPresenter;
import com.rs.leanbacknative.DataManager;
import com.rs.leanbacknative.Model.NativeRowItem;
import com.rs.leanbacknative.R;

import java.util.List;


@SuppressLint("ViewConstructor")
public class LeanbackGridLayout extends FrameLayout {
    private static final int BACKGROUND_UPDATE_DELAY = 300;
    private static final int GRID_ITEM_WIDTH = 500;
    private static final int GRID_ITEM_HEIGHT = 500;
    private final String LOG_DEBUG_TAG = "RowsFragmentDebug";

    private ThemedReactContext mContext;
    private CardPresenter mCardPresenter;
    private ArrayObjectAdapter mRowsAdapter;

    public LeanbackGridLayout(@NonNull ThemedReactContext context, VerticalGridFragment verticalGridFragment) {
        super(context);

        mContext = context;
        initializeAdapter(verticalGridFragment);
        setupEventListeners(verticalGridFragment);
    }

    private void initializeAdapter(VerticalGridFragment verticalGridFragment) {
        VerticalGridPresenter verticalGridPresenter = new VerticalGridPresenter();
        verticalGridPresenter.setNumberOfColumns(5);
        verticalGridFragment.setGridPresenter(verticalGridPresenter);
        mRowsAdapter = new ArrayObjectAdapter(new CardPresenter());

        GridItemPresenter mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);

        FragmentManager fragmentManager = mContext.getCurrentActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(verticalGridFragment, "RowsFragment").commit();
        fragmentManager.executePendingTransactions();
        verticalGridFragment.setAdapter(mRowsAdapter);

        fragmentManager.popBackStackImmediate(1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void setupEventListeners(VerticalGridFragment verticalGridFragment) {
        verticalGridFragment.setOnItemViewClickedListener(new ItemViewClickedListener());
        verticalGridFragment.setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }


    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(
                    ContextCompat.getColor(mContext.getCurrentActivity(), R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
        }
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
                mContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onClick", event);
            }
        }
    }

    public void setData(ReadableArray data) {
        List<NativeRowItem> rows = DataManager.setupData(data);

        for (int i = 0; i < rows.size(); i++) {
            mRowsAdapter.add(rows.get(i));
        }
    }
}

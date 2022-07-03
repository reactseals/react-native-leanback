package com.rs.leanbacknative.cardViews;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.rs.leanbacknative.R;
import com.rs.leanbacknative.models.Card;

public class AppCardView extends AbstractCardView {

    protected boolean isServiceCard = false;
    private PopupWindow contextMenu;

    public AppCardView(Context context) {
        super(context);

        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    closeContextMenu();
                }
            }
        });

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AppCardView card = (AppCardView) view;

                if (!card.isServiceCard) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    View menuView = layoutInflater.inflate(R.layout.context_menu, null);
                    contextMenu = new PopupWindow(menuView, card.getWidth(), LayoutParams.WRAP_CONTENT);
                    contextMenu.showAsDropDown(view, Gravity.CENTER, 35, 0);
                }

                return true;
            }
        });
    }

    public void setIsServiceCard(boolean isService) {
        this.isServiceCard = isService;
    }

    public void closeContextMenu() {
        if (isContextMenuOpen()) {
            contextMenu.dismiss();
            contextMenu = null;
        }
    }

    public boolean isContextMenuOpen() {
        return contextMenu != null;
    }

    @Override
    public void buildCardView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.card_view_app, this);
    }

    public void updateUI(Card card, int borderRadius, int cardWidth, int cardHeight) {
        setLayoutDimensions(cardWidth, cardHeight);
        setMainImageDimensions(cardWidth, cardHeight);
    }
}

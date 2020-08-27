package com.rs.leanbacknative.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class NativeRow implements Serializable {
    static final long serialVersionUID = 727566175075960653L;

    private long id;

    private String title;

    private String type;

    private List<NativeRowItem> items;

    public NativeRow() {
        items = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NativeRowItem> getItems() {
        return items;
    }

    public void setItems(List<NativeRowItem> items) {
        this.items = items;
    }

    public void addItem(NativeRowItem item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
    }
}

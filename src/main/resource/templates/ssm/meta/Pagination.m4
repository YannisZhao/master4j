package ${package};

import java.util.List;

public class Pagination<T> {

    private long total;
    private List<T> data;

    public Pagination() {
    }

    public Pagination(long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public Pagination setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public Pagination setData(List<T> data) {
        this.data = data;
        return this;
    }
}

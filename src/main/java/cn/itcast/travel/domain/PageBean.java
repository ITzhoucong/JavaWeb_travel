package cn.itcast.travel.domain;

import java.util.List;

/**
 * @author: ZhouCong
 * @date: Create in 2020/1/10 13:42
 * @description: 分页对象
 */
public class PageBean<T> {

    /**
     * @description: 总记录数
     */
    private int totalCount;

    /**
     * @description: 总页数
     */
    private int totalPage;

    /**
     * @description: 当前页码
     */
    private int currentPage;

    /**
     * @description: 每页显示的条数
     */
    private int pageSize;

    /**
     * @description: 每页显示的数据集合
     */
    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

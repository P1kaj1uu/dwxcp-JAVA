package com.springboot.dwxcp.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springboot.dwxcp.common.Constant;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class PageUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 默认页大小 */
    public static final int DEFAULT_PAGE_SIZE = 20;
    /** 单次最大页大小，防止前端传 10000 把数据库拖死 */
    public static final int MAX_PAGE_SIZE = 100;

    /**
     * 参数护栏：pageNum / pageSize 校验
     * - pageNum <= 0 修正为 1
     * - pageSize <= 0 修正为 DEFAULT_PAGE_SIZE
     * - pageSize > MAX_PAGE_SIZE 截断为 MAX_PAGE_SIZE
     */
    public static int guardPageNum(int pageNum) {
        return pageNum <= 0 ? 1 : pageNum;
    }

    public static int guardPageSize(int pageSize) {
        if (pageSize <= 0) return DEFAULT_PAGE_SIZE;
        return Math.min(pageSize, MAX_PAGE_SIZE);
    }

    /**
     * 一次性返回校正后的 (pageNum, pageSize)
     */
    public static PageParams guard(int pageNum, int pageSize) {
        return new PageParams(guardPageNum(pageNum), guardPageSize(pageSize));
    }

    @Data
    public static class PageParams {
        private final int pageNum;
        private final int pageSize;
        public PageParams(int pageNum, int pageSize) {
            this.pageNum = pageNum;
            this.pageSize = pageSize;
        }
    }

    //总记录数
    private int totalCount;

    //每页记录数
    private int pageSize;

    //总页数
    private int totalPage;

    //当前第几页
    private int currPage;

    //列表数据
    private List<?> list;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     */
    public PageUtil(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    /**
     * 分页
     */
    public PageUtil(IPage<?> page) {
        this.list = page.getRecords();
        this.totalCount = (int)page.getTotal();
        this.pageSize = (int)page.getSize();
        this.currPage = (int)page.getCurrent();
        this.totalPage = (int)page.getPages();
    }

    //第几条数目开始、结束
    public static Map<String,Object> getPageStartEnd(int curPage, int limit){
        Map<String,Object> resMap= Maps.newHashMap();
        if (curPage<=0 || limit<=0){
            curPage=1;
            limit=10;
        }

        resMap.put(Constant.PAGE_START,(curPage-1) * limit);
        resMap.put(Constant.PAGE_SIZE,(curPage-1) * limit + limit);

        resMap.put(Constant.PAGE_PREVIOUS,curPage-1);
        resMap.put(Constant.PAGE_NEXT,curPage+1);
        return resMap;
    }

    public static Map<String,Long> getPageStartEnd(final int curPage,final int limit,final int total){
        Map<String,Long> resMap= Maps.newHashMap();

        long fromIndex=(curPage-1) * limit;
        if (fromIndex>=total){
            fromIndex=total;
        }

        long toIndex=(curPage-1) * limit + limit + 1;
        if (toIndex>=total){
            toIndex=total;
        }

        resMap.put(Constant.PAGE_START,fromIndex);
        resMap.put(Constant.PAGE_SIZE,toIndex);
        return resMap;
    }


    public PageUtil() {
        this.list = Lists.newLinkedList();
        this.totalCount = 0;
        this.pageSize = 10;
        this.currPage = 1;
        this.totalPage = 0;
    }
}

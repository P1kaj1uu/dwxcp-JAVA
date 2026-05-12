package com.springboot.dwxcp.common;

import java.text.SimpleDateFormat;

public class Constant {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //超级管理员Id
    public static final Long SUPER_ADMIN = 1L;

    //最顶级的部门Id
    public static final long TOP_DEPT_ID = 0;

    //最顶级的菜单Id
    public static final long TOP_MENU_ID = 0;

    //最顶级的部门名称
    public static final String TOP_DEPT_NAME = "一级部门";

    //最顶级的菜单名称
    public static final String TOP_MENU_NAME = "一级菜单";

    //数据权限过滤
    public static final String SQL_FILTER = "sql_filter";

    //当前页码
    public static final String PAGE = "page";

    //每页显示记录数
    public static final String LIMIT = "limit";

    //排序字段
    public static final String ORDER_FIELD = "sidx";

    //排序方式
    public static final String ORDER = "order";

    //升序
    public static final String ASC = "asc";

    //降序
    public static final String DESC = "desc";

    //指定字段
    public static final String FieldUserId = "user_id";

    public static final String FieldCreateTime = "create_time";

    public static final String Profiles = "dev";

    public static final String DefaultPassword = "123456";


    //最顶级的课时Id
    public static final int TOP_LESSON_ID = 0;

    //最顶级的课时名称
    public static final String TOP_LESSON_NAME = "一级菜单";


    //当前页码
    public static final String PAGE_START = "pageStart";

    //当前页数据量
    public static final String PAGE_SIZE = "pageSize";

    //上一页
    public static final String PAGE_PREVIOUS = "pagePrevious";

    //下一页
    public static final String PAGE_NEXT = "pageNext";

    //总页数
    public static final String COUNT_NEXT = "countNext";

    public static final Integer BLOG_CENTER_PAGE_SIZE = 30;

    public static final Integer COURSE_CENTER_PAGE_SIZE = 20;

    //正则校验字符串-只能由字母、数字组成
    public static final String RegexRoute = "[A-Za-z0-9]+";

}

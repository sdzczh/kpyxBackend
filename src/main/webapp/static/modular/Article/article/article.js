/**
 * 文章管理管理初始化
 */
var Article = {
    id: "ArticleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Article.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '文章题目', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '作者名称', field: 'author', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '文章类型', field: 'Type', visible: true, align: 'center', valign: 'middle'},
            {title: '期数', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '点击次数', field: 'clinkNum', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Article.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Article.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章管理
 */
Article.openAddArticle = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章管理',
        area: ['800px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/article/article_add'
    });
    this.layerIndex = index;
};
/**
 * 点击查看文章
 */
Article.openShow = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章预览',
            area: ['800px', '760px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/article/show/' + Article.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看文章管理详情
 */
Article.openArticleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/article/article_update/' + Article.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章管理
 */
Article.delete = function () {
    if (this.check()) {
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        var ids = "";
        for(var i = 0; i < selected.length; i++) {
            ids += selected[i].id + ",";
        }
            var ajax = new $ax(Feng.ctxPath + "/article/delete", function (data) {
            Feng.success("删除成功!");
            Article.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ids",ids);
        ajax.start();
    }
};

/**
 * 查询文章管理列表
 */
Article.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    queryData['type'] = $("#type").val();
    Article.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Article.initColumn();
    var table = new BSTable(Article.id, "/article/list", defaultColunms);
    table.setPaginationType("client");
    Article.table = table.init();
});

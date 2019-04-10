/**
 * 涉税举报管理初始化
 */
var Report = {
    id: "ReportTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Report.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '举报人姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '举报人手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '被举报企业名称', field: 'beReportedCompanyName', visible: true, align: 'center', valign: 'middle'},
            {title: '被举报者姓名', field: 'beReportedName', visible: true, align: 'center', valign: 'middle'},
            {title: '被举报人地址', field: 'beReportedAddress', visible: true, align: 'center', valign: 'middle'},
            {title: '经济性质', field: 'economicNature', visible: true, align: 'center', valign: 'middle'},
            {title: '证明信息', field: 'proveInformation', visible: true, align: 'center', valign: 'middle'},
            {title: '举报内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'imgUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Report.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Report.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加涉税举报
 */
Report.openAddReport = function () {
    var index = layer.open({
        type: 2,
        title: '添加涉税举报',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/report/report_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看涉税举报详情
 */
Report.openReportDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '涉税举报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/report/report_update/' + Report.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除涉税举报
 */
Report.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/report/delete", function (data) {
            Feng.success("删除成功!");
            Report.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("reportId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询涉税举报列表
 */
Report.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['phone'] = $("#phone").val();
    Report.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Report.initColumn();
    var table = new BSTable(Report.id, "/report/list", defaultColunms);
    table.setPaginationType("client");
    Report.table = table.init();
});

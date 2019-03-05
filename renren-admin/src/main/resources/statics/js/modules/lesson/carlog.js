$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/carlog/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true , hidden:true},
			{ label: '车辆名称', name: 'carName', index: 'car_name', width: 80 },
			{ label: '车辆型号', name: 'carType', index: 'car_type', width: 80 },
			{ label: '开始时间', name: 'startTime', index: 'start_time', width: 80 },
			{ label: '结束时间', name: 'endTime', index: 'end_time', width: 80 },
			{ label: '授课教练', name: 'trainer', index: 'trainer', width: 80 },
			{ label: '授课方式', name: 'teachWay', index: 'teach_way', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
laydate.render({
    elem: '#startTime'//指定元素
    ,type: 'datetime'
    ,trigger: 'click'
    ,done: function(value, date, endDate){
        vm.carLog.startTime = value;
    }
});
laydate.render({
    elem: '#endTime'//指定元素
    ,type: 'datetime'
    ,trigger: 'click'
    ,done: function(value, date, endDate){
        vm.carLog.endTime = value;
    }
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		carLog: {},
        Lists: {},
        JL: {},
        dict:{},
        q:{
            car_name: null,
            car_type: null
        },
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.carLog = {};
            this.getDict()
            this.findJL()
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.carLog.id == null ? "sys/carlog/insertSelective" : "sys/carlog/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.carLog),
			    success: function(r){
			    	if(r.code === 0){
						alert('请确认客户是否已完成支付操作', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/carlog/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "sys/carlog/info/"+id, function(r){
                vm.carLog = r.carLog;
            });
		},
        //新增时获取数据	课程信息
        getDict: function () {
            var url = baseURL + "sys/dict/selectToAdd";
            $.ajax({
                type: "GET",
                url: url,
                contentType: "application/json",
                data: vm.dict,
                success: function (r) {
                    if (r.code === 0) {
                     /*   $('#ccarName').empty();
                        $('#ccarType').empty();
                        $('#pteachWay').empty();
                        var result = r.dict;
                        for (var i = 0; i < result.length; i++) {
                            if (result[i].type !== 'Car_name') {

                            }
                            else {
                                var item = result[i];
                                $('#ccarName').append(
                                    "<option value= '" + item.id + "'>"
                                    + item.value + "</option>");
                            }
                            if (result[i].type !== 'Car_type') {

                            }
                            else {
                                var item = result[i];
                                $('#ccarType').append(
                                    "<option value= '" + item.id + "'>"
                                    + item.value + "</option>");
                            }
                            if (result[i].type !== 'Lesson_way') {

                            }
                            else {
                                var item = result[i];
                                $('#pteachWay').append(
                                    "<option value= '" + item.id + "'>"
                                    + item.value + "</option>");
                            }
                        }*/
                        vm.Lists = r.dict;
                    } else {
                        alert(r.msg);
                    }
                },
                error: function () {
                }
            });
        },
        //	查找教练
        findJL: function () {
            var url = baseURL + "sys/user/findJL";
            $.ajax({
                type: "GET",
                url: url,
                contentType: "application/json",
                data: vm.JL,
                success: function (r) {
                    if (r.code === 0) {
                      /*  $('#ptrainer').empty();
                        var result = r.JL;
                        for (var i = 0; i < result.length; i++) {
                            var item = result[i];
                            $('#ptrainer').append(
                                "<option value= '" + item.userId + "'>"
                                + item.username + "</option>");
                        }*/
                        vm.JL=r.JL;
                    } else {
                        alert(r.msg);
                    }
                },
                error: function () {
                }
            });
        },

		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'car_name': vm.q.carName,  car_type: vm.q.carType},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
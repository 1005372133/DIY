$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/prolesson/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'id', index: 'id', width: 50, key: true , hidden:true},
			{ label: '课程名称', name: 'lessonName', index: 'lesson_name', width: 80 },
			{ label: '上课时间', name: 'lessonTime', index: 'lesson_time', width: 80 },
			{ label: '课程内容', name: 'content', index: 'content', width: 80 },
			{ label: '任课教练', name: 'trainer', index: 'trainer', width: 80 },
			{ label: '教授方式', name: 'teachWay', index: 'teach_way', width: 80 }
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
    elem: '#lessonTime' //指定元素
    ,type: 'datetime'
    ,trigger: 'click'
    ,done: function(value, date, endDate){
        vm.proLesson.lessonTime = value;
    }
});
var vm = new Vue({

	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		proLesson: {},
        q:{
            lesson_name: null,
            content:null
        },
        Lists:{},
		//	数据字典
        dict: {/*
            name: '',
            type: '',
            code: '',
            value: '',
            remark: ''*/
        },
        JL:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.proLesson = {};
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
			var url = vm.proLesson.id == null ? "sys/prolesson/insertSelective" : "sys/prolesson/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.proLesson),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
            proLesson.lessonName=$suitModel.value
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/prolesson/delete",
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
			$.get(baseURL + "sys/prolesson/info/"+id, function(r){
                vm.proLesson = r.proLesson;
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
                     /*   $('#suitModel').empty();
                        $('#pteachWay').empty();
                        var result = r.dict;
                        for (var i = 0; i < result.length; i++) {
                            if (result[i].type !== 'Lesson_name') {

                            }
                            else {
                                var item = result[i];
                                $('#suitModel').append(
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
                    $('#ptrainer').empty();
                    if (r.code === 0) {
                       /* var result = r.JL;
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
                postData:{'lesson_name': vm.q.lessonName, content:vm.q.content},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/hotel/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true , hidden:true},
			{ label: '酒店名称', name: 'hotelName', index: 'hotel_name', width: 80 },
			{ label: '酒店类型', name: 'hotelType', index: 'hotel_type', width: 80 },
			{ label: '价格', name: 'price', index: 'price', width: 80 },
			{ label: '地点', name: 'place', index: 'place', width: 80 },
            { label: '备注', name: 'remarks', index: 'remarks', width: 80 }
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
        view: {},
        dict: {},//字典
        Lists:{},//字典接受
        hotel:{},//酒店
        Hotel:{},
        q:{
            hotelName: null,
            hotelType: null
        },
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.hotel = {};
            this.getDict();
		},
        getDict: function () {
            var url = baseURL + "sys/dict/selectToAdd";
            $.ajax({
                type: "GET",
                url: url,
                contentType: "application/json",
                data: vm.dict,
                success: function (r) {
                    if (r.code === 0) {
                        vm.Lists = r.dict;
                    } else {
                        alert(r.msg);
                    }
                },
                error: function () {
                }
            });
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
			var url = vm.hotel.id == null ? "sys/hotel/save" : "sys/hotel/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.hotel),
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
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/hotel/delete",
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
			$.get(baseURL + "sys/hotel/info/"+id, function(r){
                vm.hotel = r.hotel;
            });
            this.getDict();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'hotel_name': vm.q.hotelName,  'hotel_type': vm.q.hotelType},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
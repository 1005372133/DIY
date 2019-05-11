var rid = getParameter("rid");
var disid = getParameter("disid");
var contest = getParameter("contest");
$.get("sys/view/infos",{rid:rid},function (rid) {
    $("#price").html("￥"+rid.view.price);
    $("#rname").html(rid.view.name);
    $("#area").html(rid.view.area);
    $("#rnamea").html(rid.view.name);
    $("#routeIntroduce").html(rid.view.remarks);
    $("#hotelName").html(rid.view.hotelName);
    $("#line").html(rid.view.line);
    $("#line2").html(rid.view.line2);
    $("#transport").html(rid.view.transport);
    $("#time").html(rid.view.time);
    /*    $("#favoriteCount").html("已收藏"+count+"次");*/


    var ddstr = '<a class="up_img up_img_disable"></a>';
    var dt = '<img alt="" class="big_img" src="'+rid.view.picture+'">';
    $("#dt").html(dt);
        var astr;
            astr = '<a title="" class="little_img" data-bigpic="'+rid.view.picture+'">\n' +
                '                        <img src="'+rid.view.picture+'">\n' +
                '                    </a>';
        astr += '<a title="" class="little_img" data-bigpic="'+rid.view.picture1+'">\n' +
            '                        <img src="'+rid.view.picture1+'">\n' +
            '                    </a>';
    astr += '<a title="" class="little_img" data-bigpic="'+rid.view.picture2+'">\n' +
        '                        <img src="'+rid.view.picture2+'">\n' +
        '                    </a>';
    astr += '<a title="" class="little_img" data-bigpic="'+rid.view.picture3+'">\n' +
        '                        <img src="'+rid.view.picture3+'">\n' +
        '                    </a>';
        ddstr += astr;
    ddstr += '<a class="down_img down_img_disable" style="margin-bottom: 0;"></a>';
    $("#dd").html(ddstr);
    goImg();
});

$(document).ready(function () {
    goImg();
    setInterval("auto_play()", 5000);
});
function goImg(){
    //焦点图效果
    //点击图片切换图片
    $('.little_img').on('mousemove', function() {
        $('.little_img').removeClass('cur_img');
        var big_pic = $(this).data('bigpic');
        $('.big_img').attr('src', big_pic);
        $(this).addClass('cur_img');
    });
    //上下切换
    var picindex = 0;
    var nextindex = 4;
    $('.down_img').on('click',function(){
        var num = $('.little_img').length;
        if((nextindex + 1) <= num){
            $('.little_img:eq('+picindex+')').hide();
            $('.little_img:eq('+nextindex+')').show();
            picindex = picindex + 1;
            nextindex = nextindex + 1;
        }
    });
    $('.up_img').on('click',function(){
        var num = $('.little_img').length;
        if(picindex > 0){
            $('.little_img:eq('+(nextindex-1)+')').hide();
            $('.little_img:eq('+(picindex-1)+')').show();
            picindex = picindex - 1;
            nextindex = nextindex - 1;
        }
    });
}
//自动轮播方法
function auto_play() {
    var cur_index = $('.prosum_left dd').find('a.cur_img').index();
    cur_index = cur_index - 1;
    var num = $('.little_img').length;
    var max_index = 3;
    if ((num - 1) < 3) {
        max_index = num - 1;
    }
    if (cur_index < max_index) {
        var next_index = cur_index + 1;
        var big_pic = $('.little_img:eq(' + next_index + ')').data('bigpic');
        $('.little_img').removeClass('cur_img');
        $('.little_img:eq(' + next_index + ')').addClass('cur_img');
        $('.big_img').attr('src', big_pic);
    } else {
        var big_pic = $('.little_img:eq(0)').data('bigpic');
        $('.little_img').removeClass('cur_img');
        $('.little_img:eq(0)').addClass('cur_img');
        $('.big_img').attr('src', big_pic);
    }
}
//预加载
$(function () {
    notice();
    flag();
});
//判断是否收藏
function flag() {
    //发送请求，判断用户是否收藏过该线路
    var rid = getParameter("rid");
    $.get("sys/diy/flag",{rid:rid},function (flag) {
        if(flag){
            //用户已收藏
            $("#favorite").addClass("already");
            $("#favorite").attr("disabled","disabled");
            $("#favorite").removeAttr("onclick");
        }else{
            //没有收藏
            $("#disfavorite").addClass("already");
            $("#disfavorite").attr("disabled","disabled");
            $("#disfavorite").removeAttr("onclick");
        }
    });
}
//添加收藏
function addFavorite() {
    var rid = getParameter("rid");
    $.post("sys/diy/saveweb",{rid:rid},function (data) {
        if (data) {
            alert(data.msg);
        }else{
            alert(data.msg);
        }
    });
    flag();
    location.reload();
}
//查询评论
function notice(){
    var rid = getParameter("rid");
    var notice=""
    $.post("sys/notice/selectByNotice",{viewId:rid},function (rid) {
        for (var i = 0; i < rid.page.length; i++) {
            notice+= '<div class="notice">\n'+
                '<strong id="notice_user">'+rid.page[i].userId+'说：</strong>\n'+
                '<p class="pros_title" >'+rid.page[i].contest+'</p>\n'+
                '</div>';
        }
        $("#notice").html(notice);
    });
}
//取消收藏
function disFavorite() {
    var disid = getParameter("disid");
    $.post("sys/diy/delete/"+disid,{},function (data) {
        if (data) {
            alert(data.msg);
        }else{
            alert(data.msg);
        }
    });
    flag();
    location.reload();
}
function noticeOpen1() {
    layer.open({
        type: 1,
        skin: 'layui-layer-molv',
        title: "写评论",
        area: ['600px', '400px'],
        shadeClose: false,
        content: jQuery("#noticeOpen"),
        btn: ['提交','取消'],
        btn1: function (index) {
            var contest = document.getElementById("contest").value;
            var data = "viewId=" + rid + "&contest=" + contest;
            //  console.log(this.vm.uname)
            $.ajax({
                type: "POST",
                url: "sys/notice/save",
                data: data,
                dataType: "json",
                success: function(result){
                    layer.close(index);
                    location.reload();
                }
            });
        }
    });
}
var vm = new Vue({
    el:'#rrapp',
    data:{
        notices:''
    },

    methods: {
    }
});

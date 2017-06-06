/**
 * Created by Betamee on 2017/6/6.
 */

$(document).ready(function () {
    // 绑定事件
    function bindEvt() {
        var $btn = $("#btn");
        $btn.bind('click',function(e) {
            e.preventDefault();
            e.stopPropagation();
            // console.log($("#start_date").val());
            // console.log($("#train_id").val());
            var startDate = $("#start_date").val();
            var trainId = $("#train_id").val();
            // 调用函数处理
            getData({
                start_date: startDate,
                train_id: trainId
            })
        });
    }

    // 获取数据
    function getData(param) {
        var url = `/ticket?startdate=${param.start_date}&startcity=北京&endcity=上海&tranname=${param.train_id}`;
        console.log(url);
        // ajax请求
        $.get(url,function(data,status) {
            if (status === "success") {
                console.log(status);
                console.log(data);
                render(data);
            }
        });
    }

    function render(data) {
        if(data.length === 0) {
            return;
        }

        var $parent = $("#table");
        var $thead = $("#thead"); // 保留thead元素
        $parent.empty();

        for(var i = 0; i< data.length; i++) {
            var html = `<tr>
                            <td>${data[i].train_name}</td>
                            <td>${data[i].start_date}</td>
                            <td>${data[i].start_time}</td>
                            <td>${data[i].start_city}</td>
                            <td>${data[i].end_city}</td>
                            <td>${data[i].price}</td>
                            <td>${data[i].tickets}</td>
                            <td>购买</td>
                        </tr>`;
            $parent.append(html);
            $parent.prepend($thead);
        }
    }

    // 执行
    bindEvt();
});



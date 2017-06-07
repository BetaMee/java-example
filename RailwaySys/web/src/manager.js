/**
 * Created by Betamee on 2017/6/6.
 */
$(document).ready(function () {
    // 绑定事件
    console.log("dd");
    console.log($);
    function bindEvt() {
        $("#btn").bind("click", function (e) {
            e.stopPropagation();
            e.preventDefault();
            var url = "http://localhost:8080/ticket";

            // var d = {
            //     tranname:"hhh",
            //     startdate:"ddd",
            //     starttime:"ddd",
            //     startcity:"dsss",
            //     endcity:"safd",
            //     price :52,
            //     tickets:100
            // };

            var data = getInputval();
            console.log(data);
            $.post(url,data,function (data,status) {
                console.log(data);
                console.log(status);
                if(status == "success") {
                    alert("success!");
                    claerInput();
                }
            });
        })
    }

    function getInputval() {
        return {
            tranname: $("#tranname").val(),
            startdate: $("#startdate").val(),
            starttime: $("#starttime").val(),
            startcity: $("#startcity").val(),
            endcity: $("#endcity").val(),
            price: $("#price").val(),
            tickets: $("#tickets").val(),
        };
    }

    function claerInput() {
        $("#tranname").val("");
        $("#startdate").val("");
        $("#starttime").val("");
        $("#startcity").val("");
        $("#endcity").val("");
        $("#price").val("");
        $("#tickets").val("");
    }

    bindEvt();
});
$.extend({
    creatSelectHead:function creatSelectHead(groupName,data){
    var tableHead="";
    if(navigator.appName.indexOf("Explorer")>-1)    {       <!--IE 和 火狐浏览器的不兼容-->
        document.getElementById("selectTank").innerText=groupName;
    }
    else
        document.getElementById("selectTank").textContent=groupName;
    for(var i=0 ;i<data.length ; i++){
        tableHead=tableHead+ '<li data-value="'+i+1+'"><a href="#" tabindex="-1">'+data[i].name+'</a></li>'
    }
    document.getElementById("selectOptions").innerHTML=tableHead;
},
    creatSensorTable:function creatSensorTable(data){
        var tableHead="";
        var qwe="";
        for(var i=0 ;i<data.length ; i++){
            $.ajax({
                url:$.URL.sensor.getSensorsByAreaName,
                async:false,
                dataType:'json',
                type:'POST',
                data:{"AreaName":data[i],"GroupName":groupName},
                success:(function(sensoResult){
                    var result=sensoResult.data;
                    qwe=result;
                    for(var j=0;j<result.length;j++){
                        tableHead=tableHead+ '<tr role="row" class="wijmo-wijgrid-row ui-widget-content wijmo-wijgrid-datarow" style="border-color: rgb(170, 170, 170) -moz-use-text-color -moz-use-text-color; border-width: 0px; border-style: none; -moz-border-top-colors: none; -moz-border-right-colors: none; -moz-border-bottom-colors: none; -moz-border-left-colors: none; border-image: none; height: 22px;">'+
                            '<td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u6CB9%u7F50%u7F16%u53F7"  tabindex="0" aria-selected="true" style="background: none repeat scroll 0% 0% rgb(255, 255, 255); color: rgb(0, 0, 0); border-right-color: rgb(170, 170, 170); font-size: 12px; font-family: Microsoft YaHei; font-weight: normal;">' +
                            '<div class="wijmo-wijgrid-innercell" style="text-align: center;">'+groupName+'</div></td>'+
                            '<td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u5173%u952E%u90E8%u4EF6" aria-selected="true"'+
                            'style="background: none repeat scroll 0% 0% rgb(255, 255, 255); color: rgb(0, 0, 0); border-right-color: rgb(170, 170, 170); font-size: 12px; font-family: Microsoft YaHei; font-weight: normal;">'+
                            '<div class="wijmo-wijgrid-innercell" style="text-align: center;">罐体</div></td>';
                        tableHead=tableHead+ '<td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u6D4B%u70B9%u4F4D%u7F6E" aria-selected="true" '+
                            'style="background: none repeat scroll 0% 0% rgb(255, 255, 255); color: rgb(0, 0, 0); border-right-color: rgb(170, 170, 170); font-size: 12px; font-family: Microsoft YaHei; font-weight: normal;"> '+
                            '<div class="wijmo-wijgrid-innercell" style="text-align: center;">'+data[i]+'</div></td> ';
                        tableHead=tableHead+ '<td class="wijgridtd wijdata-type-string" role="gridcell"  headers="%u4F20%u611F%u5668%u7F16%u53F7" aria-selected="true" '+
                            ' style="background: none repeat scroll 0% 0% rgb(255, 255, 255); color: rgb(0, 0, 0); border-right-color: rgb(170, 170, 170); font-size: 12px; font-family: Microsoft YaHei; font-weight: normal;"> '+
                            '<div class="wijmo-wijgrid-innercell" style="text-align: center;">'+result[j].number+'</div> '+
                            ' </td> <td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u76D1%u6D4B%u7269%u7406%u91CF" aria-selected="true" '+
                            ' style="background: none repeat scroll 0% 0% rgb(255, 255, 255); color: rgb(0, 0, 0); border-right-color: rgb(170, 170, 170); font-size: 12px; font-family: Microsoft YaHei; font-weight: normal;">'+
                            ' <div class="wijmo-wijgrid-innercell" style="text-align: center;">应变</div>'+
                            ' </td> <td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u5B9E%u6D4B%u503C" aria-selected="true"'+
                            ' style="background: none repeat scroll 0% 0% rgb(255, 255, 255); color: rgb(0, 0, 0); border-right-color: rgb(170, 170, 170); font-size: 12px; font-family: Microsoft YaHei; font-weight: normal;" id="UP0_ST"> '+
                            '<div class="wijmo-wijgrid-innercell" style="text-align: center;">UP0_ST</div>'+
                            ' </td><td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u53D8%u5F62%u72B6%u6001" aria-selected="true"'+
                            ' style="background: none repeat scroll 0% 0% rgb(255, 255, 255); color: rgb(0, 0, 0); border-right-color: rgb(170, 170, 170); font-size: 12px; font-family: Microsoft YaHei; font-weight: normal;" id="UP_ST_AL">'+
                            '<div class="wijmo-wijgrid-innercell" style="text-align: center;">UP_ST_AL</div> '+
                            '</td> <td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u64CD%u4F5C"  aria-selected="true"'+
                            'style="background: none repeat scroll 0% 0% rgb(255, 255, 255); color: rgb(0, 0, 0); border-right-color: rgb(170, 170, 170); font-size: 12px; font-family: Microsoft YaHei; font-weight: normal;"> '+
                            '<div class="wijmo-wijgrid-innercell lookXingbianRealdata" style="text-align: center;">查看</div></td></tr>  '
                    }
                }
                    )
            });
        }
        document.getElementById("wijmo-wijgrid-data").innerHTML=tableHead;
        $(".lookXingbianRealdata").click(function(){
            window.parent.jumpURL("xingbianRealData.html");
        });
    },
    creatTable:function creatTable(data){
    $.ajax({
        url:$.URL.area.getAreaListByGroupName,
        async:false,
        dataType:'json',
        type:'POST',
        data:{"groupName":data},
        success:(function(areaResult){
            areaName = areaResult.data;
            $.creatSensorTable(areaName);
        })
    });
},
    creatTableHead:function creatTableHead(){
        var tableHead="";
        tableHead ='<table cellspacing="0" cellpadding="0" border="0" id="DataGrid34430" style="border-collapse: separate; width: 100%; table-layout: fixed;" class="wijmo-wijgrid-root wijmo-wijobserver-visibility wijmo-wijgrid-table" role="grid" aria-disabled="false" tabindex="0">';
        tableHead=tableHead+'<thead>';
        tableHead=tableHead+'<tr role="row" class="wijmo-wijgrid-headerrow">';
        tableHead=tableHead+'<th class="wijgridth ui-widget wijmo-c1basefield ui-state-default wijmo-c1field" role="columnheader" scope="col" style="width: 145px;">';
        tableHead=tableHead+'<div class="wijmo-wijgrid-innercell">';
        tableHead=tableHead+'<a role="button" href="#" class="wijmo-wijgrid-headertext" style="color: rgb(0, 0, 0);">';
        tableHead=tableHead+'油罐编号'+' </a></div></th>';
        tableHead=tableHead+'<th class="wijgridth ui-widget wijmo-c1basefield ui-state-default wijmo-c1field" role="columnheader" scope="col" style="width: 186px;">';
        tableHead=tableHead+'<div class="wijmo-wijgrid-innercell">';
        tableHead=tableHead+'<a role="button" href="#"class="wijmo-wijgrid-headertext" style="color: rgb(0, 0, 0);">';
        tableHead=tableHead+'油罐类型'+' </a></div></th>';
        tableHead=tableHead+'<th class="wijgridth ui-widget wijmo-c1basefield ui-state-default wijmo-c1field" role="columnheader" scope="col" style="width: 143px;">';
        tableHead=tableHead+'<div class="wijmo-wijgrid-innercell">';
        tableHead=tableHead+'<a role="button" href="#" class="wijmo-wijgrid-headertext" style="color: rgb(0, 0, 0);">';
        tableHead=tableHead+'工作介质'+' </a></div></th>';
        tableHead=tableHead+'<th class="wijgridth ui-widget wijmo-c1basefield ui-state-default wijmo-c1field" role="columnheader" scope="col" style="width: 163px;">';
        tableHead=tableHead+'<div class="wijmo-wijgrid-innercell">';
        tableHead=tableHead+'<a role="button" href="#" class="wijmo-wijgrid-headertext" style="color: rgb(0, 0, 0);">';
        tableHead=tableHead+'油罐容积'+' </a></div></th>';
        tableHead=tableHead+'<th class="wijgridth ui-widget wijmo-c1basefield ui-state-default wijmo-c1field" role="columnheader" scope="col" style="width: 201px;">';
        tableHead=tableHead+'<a role="button" href="#" class="wijmo-wijgrid-headertext" style="color: rgb(0, 0, 0);">';
        tableHead=tableHead+'应力状态'+' </a></div></th>';
        tableHead=tableHead+'<th class="wijgridth ui-widget wijmo-c1basefield ui-state-default wijmo-c1field" role="columnheader" scope="col" style="width: 201px;">';
        tableHead=tableHead+'<div class="wijmo-wijgrid-innercell">';
        tableHead=tableHead+'<a role="button" href="#" class="wijmo-wijgrid-headertext" style="color: rgb(0, 0, 0);">';
        tableHead=tableHead+'温度状态'+' </a></div></th>';
        tableHead=tableHead+'<th class="wijgridth ui-widget wijmo-c1basefield ui-state-default wijmo-c1field" role="columnheader" scope="col" style="width: 204px;">';
        tableHead=tableHead+'<div class="wijmo-wijgrid-innercell">';
        tableHead=tableHead+'<a role="button" href="#" class="wijmo-wijgrid-headertext" style="color: rgb(0, 0, 0);">';
        tableHead=tableHead+'可燃气体浓度监测'+' </a></div></th>';
        tableHead=tableHead+'</tr></thead><colgroup>';
        tableHead=tableHead+'<col style="width: 146px;">';
        tableHead=tableHead+'<col style="width: 187px;">';
        tableHead=tableHead+'<col style="width: 144px;">';
        tableHead=tableHead+'<col style="width: 164px;">';
        tableHead=tableHead+'<col style="width: 202px;">';
        tableHead=tableHead+'<col style="width: 202px;">';
        tableHead=tableHead+'<col style="width: 205px;"> ';
        tableHead=tableHead+'</colgroup>';
        tableHead=tableHead+'<tbody class="ui-widget-content wijmo-wijgrid-data">';
        return  tableHead;
    },
    creatTableGrid:function creatTableGrid(cgData){
        var  tableGrid="";
        for(var i=0 ;i<cgData.length ; i++){
            tableGrid=tableGrid+'<tr role="row" class="wijmo-wijgrid-row ui-widget-content wijmo-wijgrid-datarow" style="border-top-color: rgb(170, 170, 170); height: 35px;"> ';
            tableGrid=tableGrid+'<td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u6CB9%u7F50%u7F16%u53F7" tabindex="0" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;"> ';
            tableGrid=tableGrid+'<div class="wijmo-wijgrid-innercell" style="text-align: center;"> '+cgData[i].name+'</div></td>';
            tableGrid=tableGrid+'<td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u6CB9%u7F50%u7C7B%u578B" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;">';
            tableGrid=tableGrid+'<div class="wijmo-wijgrid-innercell" style="text-align: center;">双盘式浮顶油罐</div></td>';
            tableGrid=tableGrid+'<td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u5DE5%u4F5C%u4ECB%u8D28" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;">';
            tableGrid=tableGrid+'<div class="wijmo-wijgrid-innercell" style="text-align: center;">原油</div></td>';
            tableGrid=tableGrid+'<td class="wijgridtd wijdata-type-string" role="gridcell" headers="%u6CB9%u7F50%u5BB9%u79EF" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;">';
            tableGrid=tableGrid+'<div class="wijmo-wijgrid-innercell" style="text-align: center;">100000m?</div></td>';
            tableGrid=tableGrid+'<td class="wijgridtd wijdata-type-string lookXingbian" role="gridcell" headers="%u5E94%u529B%u72B6%u6001" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;">';
            tableGrid=tableGrid+'<div class="wijmo-wijgrid-innercell" style="text-align: center;">正常（查看）</div></td>';
            tableGrid=tableGrid+'<td class="wijgridtd wijdata-type-string lookWendu" role="gridcell" headers="%u6E29%u5EA6%u72B6%u6001" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;"> ';
            tableGrid=tableGrid+'<div class="wijmo-wijgrid-innercell"  style="text-align: center;">正常（查看）</div> </td>';
            tableGrid=tableGrid+'<td class="wijgridtd wijdata-type-string lookLongdu" role="gridcell" headers="%u53EF%u71C3%u6C14%u4F53%u6D53%u5EA6%u76D1%u6D4B" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;">';
            tableGrid=tableGrid+'<div class="wijmo-wijgrid-innercell"  style="text-align: center;">正常（查看）</div></td></tr> ';
        }
        tableGrid=tableGrid+'</tbody></table>';
        return tableGrid;
    },
    updataXingbian: function updataXingbian(sensorMap,warnMap){
        var oTable = document.getElementById("DataGrid34430");
        var updateException='<td class="wijgridtd wijdata-type-string lookXingbian" role="gridcell" headers="%u5E94%u529B%u72B6%u6001" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;">'
            +'<div class="wijmo-wijgrid-innercell" style="text-align: center;color: rgb(255,0,0);">'+'异常（查看）'+'</div></td>';
        var update='<td class="wijgridtd wijdata-type-string lookXingbian" role="gridcell" headers="%u5E94%u529B%u72B6%u6001" aria-selected="true" style="color: rgb(0, 0, 0); font-size: 12px; background-color: rgb(255, 255, 255); border-right-color: rgb(170, 170, 170); font-family: Microsoft YaHei; font-weight: normal;">'
            +'<div class="wijmo-wijgrid-innercell" style="text-align: center;">'+'正常（查看）'+'</div></td>';
        for(var i=1;i<oTable.rows.length-1;i++)
        {
            var gName="";
            if(navigator.appName.indexOf("Explorer")>-1)
                gName=oTable.rows[i].cells[0].innerText;
            else
                gName=oTable.rows[i].cells[0].textContent;
                gName=gName.replace(/[ ]/g,"");
            var k=0;
            for(var j=0;j<sensorList.length;j++){
                if(parseInt(sensorMap.get(gName+":"+sensorList[j]))>parseInt(warnMap.get(gName+":"+sensorList[j]))){
                    k=1;
                    oTable.rows[i].cells[4].innerHTML =updateException;
                    break;
                }
                if(k==0){
                    oTable.rows[i].cells[4].innerHTML =update;
                }

            }
        }

    },
    createShowingTable:function createShowingTable(cgData){
        var tableStr="";
        tableStr= $.creatTableHead()+$.creatTableGrid(cgData);
        $("#showData").html(tableStr);
        $(".lookWendu").click(function(){
            window.parent.jumpURL("wendu.html");
            window.parent.jumpTab(2);
        });
        $(".lookLongdu").click(function(){
            window.parent.jumpURL("longdu.html");
            window.parent.jumpTab(3);
        });
    }
});
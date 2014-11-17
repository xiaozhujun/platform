$.extend({
    subscription:function subscription(data){
        for(var i=0;i<data.data.length;i++){
            if(i==0){
                sNum=data.data[i].number+",";
            }
            else {
                sNum=sNum+data.data[i].number+",";
            }
        }
        var lastIndex = sNum.lastIndexOf(',');
        if (lastIndex > -1) {
            sNum = sNum.substring(0, lastIndex) + sNum.substring(lastIndex + 1, sNum.length);
        }
        s ="{c:'Subscribe',sensors:["+sNum+"]}";
        return s;

    },
    webSocketCallback:function webSocketCallback(data){
        var jsonData = eval("(" +data+ ")");
        wsData=jsonData.sensors[0].data;
        var gn=jsonData.sensors[0].groupName;
        var sensorNumber=jsonData.sensors[0].sensorNum;
        sensorNumber=sensorNumber.replace(/[ ]/g,"");
        console.log ("收到消息！"+wsData);
        var max=wsData[0];
        if(gn==groupName){
            if(sensorNumber==prefix+'0000')
                document.getElementById("s1").innerHTML='UP0_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0001')
                document.getElementById("s2").innerHTML='UP45_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0002')
                document.getElementById("s3").innerHTML='UP90_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0003')
                document.getElementById("s4").innerHTML='UP135_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0004')
                document.getElementById("s5").innerHTML='UP180_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0005')
                document.getElementById("s6").innerHTML='UP225_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0006')
                document.getElementById("s7").innerHTML='UP270_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0007')
                document.getElementById("s8").innerHTML='UP315_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0008')
                document.getElementById("z1").innerHTML='MID0_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0009')
                document.getElementById("z2").innerHTML='MID45_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0010')
                document.getElementById("z3").innerHTML='MID90_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0011')
                document.getElementById("z4").innerHTML='MID135_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0012')
                document.getElementById("z5").innerHTML='MID180_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0013')
                document.getElementById("z6").innerHTML='MID225_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0014')
                document.getElementById("z7").innerHTML='MID270_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0015')
                document.getElementById("z8").innerHTML='MID315_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0016')
                document.getElementById("x1").innerHTML='DOWN0_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0017')
                document.getElementById("x2").innerHTML='DOWN45_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0018')
                document.getElementById("x3").innerHTML='DOWN90_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0019')
                document.getElementById("x4").innerHTML='DOWN135_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0020')
                document.getElementById("x5").innerHTML='DOWN180_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0021')
                document.getElementById("x6").innerHTML='DOWN225_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0022')
                document.getElementById("x7").innerHTML='DOWN270_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0023')
                document.getElementById("x8").innerHTML='DOWN315_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0024' )
                document.getElementById("s1").innerHTML='UP0_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0025')
                document.getElementById("s2").innerHTML='UP45_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0026')
                document.getElementById("s3").innerHTML='UP90_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0027')
                document.getElementById("s4").innerHTML='UP135_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0028')
                document.getElementById("s5").innerHTML='UP180_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0029')
                document.getElementById("s6").innerHTML='UP225_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0030')
                document.getElementById("s7").innerHTML='UP270_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0031')
                document.getElementById("s8").innerHTML='UP315_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0032')
                document.getElementById("z1").innerHTML='MID0_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0033')
                document.getElementById("z2").innerHTML='MID45_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0034')
                document.getElementById("z3").innerHTML='MID90_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0035')
                document.getElementById("z4").innerHTML='MID135_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0036')
                document.getElementById("z5").innerHTML='MID180_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0037')
                document.getElementById("z6").innerHTML='MID225_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0038')
                document.getElementById("z7").innerHTML='MID270_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0039')
                document.getElementById("z8").innerHTML='MID315_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0040')
                document.getElementById("x1").innerHTML='DOWN0_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0041')
                document.getElementById("x2").innerHTML='DOWN45_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0042')
                document.getElementById("x3").innerHTML='DOWN90_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0043')
                document.getElementById("x4").innerHTML='DOWN135_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0044')
                document.getElementById("x5").innerHTML='DOWN180_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0045')
                document.getElementById("x6").innerHTML='DOWN225_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0046')
                document.getElementById("z7").innerHTML='DOWN270_ST&nbsp;&nbsp;&nbsp'+max;
            if(sensorNumber==prefix+'0047')
                document.getElementById("z8").innerHTML='DOWN315_ST&nbsp;&nbsp;&nbsp'+max;
        }
        var sName="";
        var tbody = document.getElementById('DataGrid414255');
        for(var i = 1;i<tbody.rows.length;i++){
            if(navigator.appName.indexOf("Explorer")>-1)
                sName= tbody.rows[i].cells[3].innerText;
            else
                sName= tbody.rows[i].cells[3].textContent;
            sName=sName.replace(/[ ]/g,"");

            if(sName==sensorNumber) {
                if(navigator.appName.indexOf("Explorer")>-1)
                    tbody.rows[i].cells[5].innerText="UP0_ST "+max;
                else
                    tbody.rows[i].cells[5].textContent="UP0_ST "+max;
            }
        }
    } ,
    belowAlgorithmProcess:function belowAlgorithmProcess(i,locName,loc,map){
      sum=0;
         var l;
        var shapeValue=0
        if(loc==locName[0]){
            l= Math.PI*(80*1000+24);
        }
        else if(loc==locName[1]){
            l= Math.PI*(80*1000+38);
        }
        else if(loc==locName[2]){
            l= Math.PI*(80*1000+58);
        }

        for(var key in map){
            sum=sum+Number(map[key]);
        }
        shapeValue=(sum/8)*l;
       return shapeValue;
    }
});
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
    showWarnValue:function showWarnValue(data){
        alert(data);
        this.title="warnValue:"+data;
    },
    webSocketCallback:function webSocketCallback(data){
        map={ };
        var jsonData = eval("(" +data+ ")");
        console.log ("收到消息！"+jsonData);
        var wsData=jsonData.sensors[0].data;
        var gn=jsonData.sensors[0].groupName;
        var sensorNumber=jsonData.sensors[0].sensorNum;
        var warnValue= jsonData.sensors[0].warnValue;
        map[sensorNumber]=warnValue;
        var max=wsData[0];
        var beforeS='<div style="color: red;text-align: center">';
        var beforeS2='<div style="text-align: center">';
        var afterS='</div>';
        var s="";
        var downTableS="";
        if(gn==groupName){
            if(sensorNumber==prefix+'0000') {
                s='UP0_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)){
                    s=beforeS+s+afterS;
                }
                document.getElementById("s1").innerHTML=s;
                document.getElementById("s1").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0001'){
                s='UP45_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)){
                    s=beforeS+s+afterS;
                }
                document.getElementById("s2").innerHTML=s;
                document.getElementById("s2").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0002'){
                s='UP90_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s3").innerHTML=s;
                document.getElementById("s3").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0003'){
                s='UP135_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s4").innerHTML=s;
                document.getElementById("s4").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0004'){
                s='UP180_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s5").innerHTML=s;
                document.getElementById("s5").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0005'){
                s='UP225_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s6").innerHTML=s;
                document.getElementById("s6").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0006'){
                s='UP270_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s7").innerHTML=s;
                document.getElementById("s7").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0007'){
                s='UP315_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s8").innerHTML=s;
                document.getElementById("s8").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0008'){
                s='MID0_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z1").innerHTML=s;
                document.getElementById("z1").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0009'){
                s='MID45_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z2").innerHTML=s;
                document.getElementById("z2").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0010'){
                s='MID90_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z3").innerHTML=s;
                document.getElementById("z3").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0011'){
                s='MID135_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z4").innerHTML=s;
                document.getElementById("z4").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0012'){
                s='MID180_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z5").innerHTML=s;
                document.getElementById("z5").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0013'){
                s='MID225_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z6").innerHTML=s;
                document.getElementById("z6").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0014'){
                s='MID270_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z7").innerHTML=s;
                document.getElementById("z7").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0015')    {
                s='MID315_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z8").innerHTML=s;
                document.getElementById("z8").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0016')   {
                s='DOWN0_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x1").innerHTML=s;
                document.getElementById("x1").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0017')    {
                s='DOWN45_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x2").innerHTML=s;
                document.getElementById("x2").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0018')    {
                s='DOWN90_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x3").innerHTML=s;
                document.getElementById("x3").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0019')   {
                s='DOWN135_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x4").innerHTML=s;
                document.getElementById("x4").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0020')    {
                s='DOWN180_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x5").innerHTML=s;
                document.getElementById("x5").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0021')    {
                s='DOWN225_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x6").innerHTML=s;
                document.getElementById("x6").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0022')  {
                s='DOWN270_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x7").innerHTML=s;
                document.getElementById("x7").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0023')     {
                s='DOWN315_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x8").innerHTML=s;
                document.getElementById("x8").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0024' )   {
                s='UP0_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s1").innerHTML=s;
                document.getElementById("s1").title="报警值:"+map[sensorNumber];

            }
            if(sensorNumber==prefix+'0025')   {
                s='UP45_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s2").innerHTML=s;
                document.getElementById("s2").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0026')  {
                s='UP90_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s3").innerHTML=s;
                document.getElementById("s3").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0027')   {
                s='UP135_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s4").innerHTML=s;
                document.getElementById("s4").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0028')   {
                s='UP180_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s5").innerHTML=s;
                document.getElementById("s5").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0029')  {
                s='UP225_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s6").innerHTML=s;
                document.getElementById("s6").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0030')  {
                s='UP270_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s7").innerHTML=s;
                document.getElementById("s7").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0031')   {
                s='UP315_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("s8").innerHTML=s;
                document.getElementById("s8").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0032')   {
                s='MID0_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z1").innerHTML=s;
                document.getElementById("z1").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0033')  {
                s='MID45_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z2").innerHTML=s;
                document.getElementById("z2").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0034')    {
                s='MID90_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z3").innerHTML=s;
                document.getElementById("z3").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0035')    {
                s='MID135_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z4").innerHTML=s;
                document.getElementById("z4").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0036')  {
                s='MID180_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z5").innerHTML=s;
                document.getElementById("z5").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0037')   {
                s='MID225_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z6").innerHTML=s;
                document.getElementById("z6").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0038')   {
                s='MID270_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z7").innerHTML=s;
                document.getElementById("z7").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0039')    {
                s='MID315_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("z8").innerHTML=s;
                document.getElementById("z8").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0040')   {
                s='DOWN0_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x1").innerHTML=s;
                document.getElementById("x1").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0041')    {
                s='DOWN45_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x2").innerHTML=s;
                document.getElementById("x2").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0042')    {
                s='DOWN90_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x3").innerHTML=s;
                document.getElementById("x3").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0043')   {
                s='DOWN135_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x4").innerHTML=s;
                document.getElementById("x4").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0044')    {
                s='DOWN180_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x5").innerHTML=s;
                document.getElementById("x5").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0045')    {
                s='DOWN225_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x6").innerHTML=s;
                document.getElementById("x6").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0046')  {
                s='DOWN270_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)){
                    s=beforeS+s+afterS;
                }
                document.getElementById("x7").innerHTML=s;
                document.getElementById("x7").title="报警值:"+map[sensorNumber];
            }
            if(sensorNumber==prefix+'0047'){
                s='DOWN315_ST&nbsp;&nbsp;&nbsp'+max;
                if(parseInt(max)>parseInt(warnValue)) {
                    s=beforeS+s+afterS;
                }
                document.getElementById("x8").innerHTML=s;
                document.getElementById("x8").title="报警值:"+map[sensorNumber];
            }
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
                downTableS=beforeS2+max+afterS;
                if(parseInt(max)>parseInt(warnValue)) {
                    downTableS=beforeS+max+afterS;
                }
                tbody.rows[i].cells[5].innerHTML=downTableS;
                tbody.rows[i].cells[5].title="报警值:"+map[sensorNumber];
            }
        }
    },

    belowAlgorithmProcess:function belowAlgorithmProcess(i,locName,loc,map){
        sum=0;
        var l;
        var shapeValue=0 ;
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
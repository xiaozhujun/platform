function   subscription(data){
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

}
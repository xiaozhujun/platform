function dp(time,data){
    var newArray=[];
    var p= 0,str,date;
  for(var i=0;i<time.length;i++){
      str = time[i].replace(/-/g,'/');
      date = new Date(str);
      for(var j=0;j<data[i].length;j++){
         newArray[p]=[date.getTime(),data[i][j]];
         p++;
     }
  }
    return newArray;
}
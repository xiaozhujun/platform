function drawBelowChartOfMap(container,map,firstRequest,loc,locName){
    var shapeValue=0;
    var l;
    var sum=0;
    locFunction(); //定义全局location，传参定义l


    //定义时间 uTC

    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    function locFunction(){

        if(loc==locName[0]){
            l= Math.PI*(80*1000+24);
        }
        else if(loc==locName[1]){
            l= Math.PI*(80*1000+38);
        }
        else if(loc==locName[2]){
            l= Math.PI*(80*1000+58);
        }

        for(key in map){
            sum=sum+Number(map[key]);
        }
        shapeValue=(sum/8)*l;
        if(firstRequest==1){
            $(container).highcharts({

                chart: {
                    type: 'spline',
                    defaultSeriesType: 'line',
                    marginRight: 10,
                    marginBottom: 30,
                    borderRadius:0,
                    animation: Highcharts.svg,
                    events: {
                        load: function() {
                        }
                    }
                },

                xAxis: {
                    type: 'datetime',
                    tickPixelInterval: 150,
                    style: {
                        color: '#000000',
                        fontWeight: 'normal'
                    } ,
                    text: '',
                    align: "high",
                    margin: 0


                },
                title : {
                    enabled: false,
                    text : ''
                },

                yAxis: {
//                    maxPadding:0.01,
//                    minPadding:0.01,
//                    tickPixelInterval:30,
                    title: {
                        text: '',
                        align: "high",
                        rotation: 270,
                        margin: 10,
//                style: {
//                    color: '#000000',
//                    fontWeight: 'normal'
//                }
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    }
                },
                tooltip: {
                    enabled: true,
                    formatter: function() {
                        var s = 'value:<b>'+ this.y + 'mm' + '</b>';
                        return s;
                    }
                },
                plotOptions: {
                    line: {
                        lineWidth: 1,
                        states: {
                            hover: {
                                lineWidth: 1
                            }
                        },
                        marker: {
                            enabled: false,
                            states: {
                                hover: {
                                    enabled: true,
                                    symbol: 'circle',
                                    radius: 5,
                                    lineWidth: 1
                                }
                            }
                        },
                        pointInterval: 1 // one hour
                    },
                    series: {
                        animation: false,
                        shadow: false
                    }
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series :function(){
                        var temp = [];
                        for(var k=0;k<locName.length;k++) {
                            temp.push({name:locName[k],data: (function() { // generate an array of random data
                                var data = [],
                                    time = (new Date()).getTime(),
                                    j;
                                for (j = -19; j <= 0; j++) {
                                    data.push({
                                        x: time + j * 1000,
                                        y: shapeValue
                                    });
                                }
                                return data;
                            }
                                )()  });  }

                        return temp;
                    }(),
                credits: {
                    enabled: false
                }
            });
        }
        else{
            for(var p=0;p<locName.length;p++){
                if(loc==locName[p]) {
                    $(container).highcharts().series[p].addPoint([new Date().getTime(),shapeValue],true,true);
                }

            }
        }
    }
}



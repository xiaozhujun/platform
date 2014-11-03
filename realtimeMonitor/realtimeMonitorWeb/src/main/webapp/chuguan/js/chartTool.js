function drawChartOfMap(container,map,d,sensorNum){
    var x="",y="", i=0;
    a();
    //定义时间
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    function a(){
        if(d==1){
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
            maxPadding:0.01,
            minPadding:0.01,
            tickPixelInterval:30,
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
                var s = 'value:<b>'+ this.y + 'unit' + '</b>';
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
        series : function(){
            var temp = [];
            for(key in map.data){
                temp.push({name:key,data: (function() { // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        j;
                    for (j = -19; j <= 0; j++) {
                        data.push({
                            x: time + j * 1000,
                            y: map.get(key)
                        });
                    }
                    return data;
                })()  });
            }
            return temp;
        }(),
        credits: {
            enabled: false
        }
    });}
        else{
            for(var j=0;j<map.size();j++){
                var name =  $(container).highcharts().series[j].name;
                if(sensorNum==name) {
                    i=j;
                    console.log("i:"+i);
                    break;
                }
            }
                x= new Date();
                $(container).highcharts().series[i].addPoint([x.getTime(),map.data[key]],true,true);
        }

    }

}
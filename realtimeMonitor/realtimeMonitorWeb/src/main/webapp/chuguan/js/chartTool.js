function drawChartOfMap(container,map,d,sensorNum){
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
            for(key in map){
                temp.push({name:key,data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;

                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: map[key]
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
    });
  }
        else  if(d>1){
            $(container).highcharts().destroy();
            d=1;
            a();
        }
        else{
            for(var j=0;j<8;j++){
                var name =  $(container).highcharts().series[j].name;
                if(sensorNum==name){
                    var  i=j;
                    break;
                }
            }
          $(container).highcharts().series[i].addPoint([new Date().getTime(),map[sensorNum]],true,true);
        }

    }

}
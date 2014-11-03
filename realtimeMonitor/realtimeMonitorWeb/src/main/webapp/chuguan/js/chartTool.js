function drawChartOfMap(container,map,d,sensorNum){
    var x="",y="", i=0;
    a();
    function a(){
        if(d==1){
   $(container).highcharts({
        chart: {
            type: 'spline',
            defaultSeriesType: 'line',
            marginRight: 20,
            marginBottom: 30,
            borderRadius:0,
            animation:false,
            events: {
                load: function() {
                    setInterval(2000);
                }
            }
        },

     xAxis: {
         type: 'datetime',
         tickPixelInterval: 2000,
         title: {
                text: '',
                align: "high",
                margin: 0,
                style: {
                    color: '#000000',
                    fontWeight: 'normal'
                }
            }
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
                style: {
                    color: '#000000',
                    fontWeight: 'normal'
                }
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
                console.log("name:"+name);
                console.log("sensoNum:"+sensorNum);
                if(sensorNum==name) {
                    i=j;
                    console.log("i:"+i);
                    break;
                }
            }
                console.log("map"+map);
                x= new Date();
                $(container).highcharts().series[i].addPoint([x.getTime(),map.data[key]],true,true);
        }

    }

}
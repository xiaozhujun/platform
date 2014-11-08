/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-22
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
function drawChart4(container,data,unit,d){
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
   var  y=data[0];
    a();
    function a(){
    if(d==1){

    $(container).highcharts({
        chart: {
            defaultSeriesType: 'spline',
            marginRight: 20,
            marginBottom: 30,
            borderRadius:0,
            animation:false
        },
        xAxis: {
            type: 'datetime',
            title: {
                text: '(xUnit)',
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
        legend: {
            enabled : false,
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -10,
            y: 100,
            borderWidth: 0
        },

        yAxis: {
            maxPadding:0.01,
            minPadding:0.01,
            tickPixelInterval:30,
            title: {
                text: '('+unit+')',
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
                var s = 'value:<b>'+ this.y + unit + '</b>';
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
        series : [{
            data: (function() {
                var data =[],
                    time = (new Date()).getTime();
                for (var i = -19; i <= 0; i++) {
                    data.push({
                        x: time + i * 1000,
                        y: y
                    });
                }
                return data;
            })()
        }],
        credits: {
            enabled: false
        }
    });
    }else{
        var chart = $(container).highcharts();
         chart.series[0].addPoint([(new Date()).getTime(),y],true,true);
    }
    }
}
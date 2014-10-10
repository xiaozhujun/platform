function drawChart2(container,data,unit,d){
    var y=data[data.length-1];

    a();
    function a(){
        if(d==1){
    $(container).highcharts({
        chart: {
            defaultSeriesType: 'column',
            marginRight: 20,
            marginBottom: 30,
            borderRadius:0,
            animation:false
        },
        xAxis: {
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
            data: data
        }],
        credits: {
            enabled: false
        }
    });

        }else{
            var chart = $(container).highcharts();
            var x= (new Date()).getTime();
            chart.series[0].addPoint([y],true,true);
        }     }
}

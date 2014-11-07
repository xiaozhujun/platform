function drawChartSingleMap(container,map,d,sNum){
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
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
                        }
                    }
                },

                xAxis: {
                    type: 'datetime',
                    tickPixelInterval: 150,
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
                series :  [{   name: sNum,
                    data: (function() {
                        var data =[],
                            time = (new Date()).getTime();
                        for (var i = -19; i <= 0; i++) {
                            data.push({
                                x: time + i * 1000,
                                y: map[sNum]
                            });
                        }
                        return data;
                    })()
                }],
                credits: {
                    enabled: false
                }
            });}
        else  if(d>1){
            $(container).highcharts().destroy();
            d=1;
            a();
        }
        else{
             $(container).highcharts().series[0].addPoint([(new Date()).getTime(),map[sNum]],true,true);
        }

    }

}
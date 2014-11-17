$.extend({

    initHighChart:function initHighChart(container,map){
        var x="",y="", i=0;

        //定义时间
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

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
    },
    addPointOnChart:function addPointOnChart(container,map,sensorNum){
        for(var j=0;j<8;j++){
            var name = $(container).highcharts().series[j].name;
            if(sensorNum==name){
               var i=j;
                break;
            }
        }

        $(container).highcharts().series[i].addPoint([new Date().getTime(),map[sensorNum]],true,true);
    },
    destroyChart:function destroyChart(container,map){
        $(container).highcharts().destroy();

        $.initHighChart(container,map);


    },
    initAlgorithmHighChart:function  initAlgorithmHighChart(container,locName,shapeValue){
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

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
    ,
    addPointOnAlgorithmChart:function addPointOnAlgorithmChart(container,loc,locName,shapeValue) {
    for(var p=0;p<locName.length;p++){
        if(loc==locName[p]) {
            $(container).highcharts().series[p].addPoint([new Date().getTime(),shapeValue],true,true);
        }

    }

}

});

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-22
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
function drawChart3(container,data,unit,d) {
     // alert(data);
   // alert(data.length)  ;
    var sum = 0;
    for (var i=0;i<data.length;i++) {
        sum += data[i];
    }
   var average = sum/(data.length);
                //  alert(average);
    a()

     function a(){
    if(d==1){
    $(container).highcharts({

            chart: {
                type: 'gauge',
                plotBackgroundColor: null,
                plotBackgroundImage: null,
                plotBorderWidth: 0,
                plotShadow: false
            },

            title: {
                text: '均方差'
            },

            pane: {
                startAngle: -150,
                endAngle: 150,
                background: [{
                    backgroundColor: {
                        linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                        stops: [
                            [0, '#FFF'],
                            [1, '#333']
                        ]
                    },
                    borderWidth: 0,
                    outerRadius: '109%'
                }, {
                    backgroundColor: {
                        linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                        stops: [
                            [0, '#333'],
                            [1, '#FFF']
                        ]
                    },
                    borderWidth: 1,
                    outerRadius: '107%'
                }, {
                    // default background
                }, {
                    backgroundColor: '#DDD',
                    borderWidth: 0,
                    outerRadius: '105%',
                    innerRadius: '103%'
                }]
            },

            // the value axis
            yAxis: {
                min: 0,
                max: 600,

                minorTickInterval: 'auto',
                minorTickWidth: 1,
                minorTickLength: 10,
                minorTickPosition: 'inside',
                minorTickColor: '#666',

                tickPixelInterval: 30,
                tickWidth: 2,
                tickPosition: 'inside',
                tickLength: 10,
                tickColor: '#666',
                labels: {
                    step: 2,
                    rotation: 'auto'
                },
                title: {
                    text: ''+unit+''
                },
                plotBands: [{
                    from: 0,
                    to: 360,
                    color: '#55BF3B' // green
                }, {
                    from: 360,
                    to: 480,
                    color: '#DDDF0D' // yellow
                }, {
                    from: 480,
                    to: 600,
                    color: '#DF5353' // red
                }]
            },

            series: [{
                name: 'Speed',
                data: [average],
                tooltip: {
                    valueSuffix: ' ('+unit+')'
                }
            }]

        }

        ) ;  }  else{

        var chart = $(container).highcharts();
        var x= (new Date()).getTime();
       // chart.series[0].addPoint([average],true,true);
                var point = chart.series[0].points[0];
                                point.update(average);

    }
     }

};
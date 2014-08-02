function drawChart(container,data,categoriesData,unit){
    $(container).highcharts('StockChart',{
        chart: {
            //  defaultSeriesType: 'line',
            marginRight: 20,
            marginBottom: 30,
            borderRadius:0,
            animation:false,
            zoomType:"x"
        },
        scrollbar:{
            enabled:true //是否产生滚动条
        },
        xAxis: {
            //  categories:categoriesData ,
            min:0,
            max:10,
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
                var s = 'value:<b>'+ this.y+ unit +'  '+'time:'+this.x+ '</b>';
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
                pointInterval: 1  // one hour
            },
            series: {
                animation: false,
                shadow: false
            }
        },
        series : [{
            data: data

        }],
        rangeSelector:{
            enabled:false
        },
        credits: {
            enabled: false
        }
    });
}
var startX=50,startY=200;
var topWidth=30,topHeight=topWidth/10;
var midWidth=0.6*topHeight,midHeight=topWidth-2*midWidth;
var orderWidth=1.1*topWidth,orderHeight=topHeight;
var strokeWidth= 1,bridgeStrokeColor='#f00';
var bridgeArchColor='yellow';
var bridgeArchStrokeWidth=5;
var archCenterX=startX+22*topWidth,archCenterY=startY+(14.14-4.2)*topWidth;
var startArchX=startX+12*topWidth;
var archRadius = 14.14*topWidth;
var lineStrokeColor='yellow';
var lineStokeWidth=2;
var scale=0;

var unitStrokeDash=strokeDashFulfill=[10000],strokeDashBuilding=[5];
var unitStrokeColorFulfill='#f00',unitStrokeColorBuilding='#666';
var unitData=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];


function drawStart(startX,StartY,complete){
    if(complete==0){
        bridgeStrokeColor=unitStrokeColorBuilding;
        unitStrokeDash=strokeDashBuilding;
    }else if(complete==1){
        bridgeStrokeColor=unitStrokeColorFulfill;
        unitStrokeDash=strokeDashFulfill;
    }
    $('#progressCanvas').drawRect({
        strokeDash:unitStrokeDash,
        strokeDashOffset:5,
        strokeStyle: bridgeStrokeColor,
        strokeWidth: strokeWidth,
        x: startX-midWidth/2, y: startY+topHeight,
        width: midWidth,
        height: midHeight,
        cornerRadius:100,
        fromCenter:false
    });
}

function drawUnit(startX,startY,complete){
    if(complete==0){
        bridgeStrokeColor=unitStrokeColorBuilding;
        unitStrokeDash=strokeDashBuilding;
    }else if(complete==1){
        bridgeStrokeColor=unitStrokeColorFulfill;
        unitStrokeDash=strokeDashFulfill;
    }
    $('#progressCanvas').drawRect({
        strokeDash:unitStrokeDash,
        strokeDashOffset:5,
        strokeStyle: bridgeStrokeColor,
        strokeWidth: strokeWidth,
        x: startX, y: startY,
        width: topWidth,
        height: topHeight,
        fromCenter:false
    }).drawRect({
            strokeDash:unitStrokeDash,
            strokeDashOffset:5,
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth/2, y: startY+midHeight/2+topHeight,
            width: orderWidth,
            height: orderHeight,
            fromCenter:true,
            rotate:45
        }).drawRect({
            strokeDash:unitStrokeDash,
            strokeDashOffset:5,
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX, y: startY+topHeight+midHeight,
            width: topWidth,
            height: topHeight,
            fromCenter:false
        }).drawRect({
            strokeDash:unitStrokeDash,
            strokeDashOffset:5,
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth-midWidth/2, y:startY+ topHeight,
            width: midWidth,
            height: midHeight,
            cornerRadius:100,
            fromCenter:false
        });
}

function drawReverseUnit(startX,startY,complete){
    if(complete==0){
        bridgeStrokeColor=unitStrokeColorBuilding;
        unitStrokeDash=strokeDashBuilding;
    }else if(complete==1){
        bridgeStrokeColor=unitStrokeColorFulfill;
        unitStrokeDash=strokeDashFulfill;
    }
    $('#progressCanvas').drawRect({
        strokeDash:unitStrokeDash,
        strokeDashOffset:5,
        strokeStyle: bridgeStrokeColor,
        strokeWidth: strokeWidth,
        x: startX, y: startY,
        width: topWidth,
        height: topHeight,
        fromCenter:false
    }).drawRect({
            strokeDash:unitStrokeDash,
            strokeDashOffset:5,
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth/2, y: startY+midHeight/2+topHeight,
            width: orderWidth,
            height: orderHeight,
            fromCenter:true,
            rotate:-45
        }).drawRect({
            strokeDash:unitStrokeDash,
            strokeDashOffset:5,
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX, y: startY+topHeight+midHeight,
            width: topWidth,
            height: topHeight,
            fromCenter:false
        }).drawRect({
            strokeDash:unitStrokeDash,
            strokeDashOffset:5,
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth-midWidth/2, y:startY+ topHeight,
            width: midWidth,
            height: midHeight,
            cornerRadius:100,
            fromCenter:false
        });
}

var pierStrokeColor = 'blue';
var pierStrokeWidth=3;
function drawPier(startX,startY){
    $('#progressCanvas').drawRect({
        strokeStyle: pierStrokeColor,
        strokeWidth: pierStrokeWidth,
        x: startX+topWidth/15, y: startY,
        width: topWidth/5,
        height: topHeight,
        fromCenter:false
    }).drawRect({
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x: startX+topWidth/5+topWidth/10, y: startY,
            width: topWidth/5,
            height: topHeight,
            fromCenter:false
        }).drawRect({
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x: startX, y: startY+topHeight,
            width: topWidth/5*3,
            height: topHeight*2,
            fromCenter:false
        }).drawRect({
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x: startX+topWidth/20, y: startY+3*topHeight,
            width: topWidth/2,
            height: topHeight*2.5,
            fromCenter:false
        }).drawLine({
            closed:true,
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x1:startX+topWidth/20, y1:startY+11*topHeight/2,
            x2: startX-topWidth/10, y2: startY+5.5*topHeight+3*midHeight,
            x3:  startX+topWidth/20+topWidth/2+topWidth/10, y3: startY+5.5*topHeight+3*midHeight,
            x4: startX+topWidth/20+topWidth/2, y4: startY+5.5*topHeight
        }).drawRect({
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x: startX+topWidth/20-0.7*topWidth/2, y: startY+5.5*topHeight+3*midHeight,
            width: 1.2*topWidth,
            height: topHeight*5,
            fromCenter:false
        }).drawRect({
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x: startX+topWidth/20-0.7*topWidth/2+ 1.1*topWidth/16, y: startY+5.5*topHeight+3*midHeight+topHeight*5,
            width: 1.1*topWidth/8,
            height: topHeight*8,
            fromCenter:false
        }).drawRect({
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x: startX+topWidth/20-0.7*topWidth/2+1.1*topWidth/16+1.1*topWidth/4, y: startY+5.5*topHeight+3*midHeight+topHeight*5,
            width: 1.1*topWidth/8,
            height: topHeight*8,
            fromCenter:false
        }).drawRect({
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x: startX+topWidth/20-0.7*topWidth/2+1.1*topWidth/16+1.1*topWidth/2, y: startY+5.5*topHeight+3*midHeight+topHeight*5,
            width: 1.1*topWidth/8,
            height: topHeight*8,
            fromCenter:false
        }).drawRect({
            strokeStyle: pierStrokeColor,
            strokeWidth: pierStrokeWidth,
            x: startX+topWidth/20-0.7*topWidth/2+1.1*topWidth/16+1.1*topWidth/4*3, y: startY+5.5*topHeight+3*midHeight+topHeight*5,
            width: 1.1*topWidth/8,
            height: topHeight*8,
            fromCenter:false
        })
}

function drawHolder(startX,startY){
    bridgeStrokeColor='#f00';
    $('#progressCanvas').drawRect({
        strokeStyle: bridgeStrokeColor,
        strokeWidth: strokeWidth,
        x: startX+topWidth, y: startY+0.5*topWidth,
        width:midWidth,
        height:  2.2*topWidth,
        fromCenter:true,
        rotate:-65
    }).drawRect({
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth, y:startY-0.5*topWidth+0.5*topWidth,
            width: midWidth,
            height: 0.5*topWidth,
            fromCenter:false
        }).drawRect({
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth*2, y:startY-0.5*topWidth+0.5*topWidth,
            width: midWidth,
            height: topWidth,
            fromCenter:false
        }).drawRect({
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth*2.5, y: startY+0.5*topWidth,
            width:midWidth,
            height:  1.4*topWidth,
            fromCenter:true,
            rotate:45
        }).drawArc({
            strokeStyle:bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth*2, y:startY+topWidth,
            radius: 0.28*topWidth,
            start: -90, end: 90,
            closed:true
        })
    return 0.28*topWidth;
}

function drawReverseHolder(startX,startY){
    $('#progressCanvas').drawRect({
        strokeStyle: bridgeStrokeColor,
        strokeWidth: strokeWidth,
        x: startX+topWidth*1.5,y: startY+0.5*topWidth,
        width:midWidth,
        height:  1.4*topWidth,
        fromCenter:true,
        rotate:-45
    }).drawRect({
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth*2, y:startY-0.5*topWidth+0.5*topWidth,
            width: midWidth,
            height: topWidth,
            fromCenter:false
        }).drawRect({
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth*3, y:startY-0.5*topWidth+0.5*topWidth,
            width: midWidth,
            height: 0.5*topWidth,
            fromCenter:false
        }).drawRect({
            strokeStyle: bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth*3, y: startY+0.5*topWidth,
            width:midWidth,
            height:  2.2*topWidth,
            fromCenter:true,
            rotate:65
        }).drawArc({
            strokeStyle:bridgeStrokeColor,
            strokeWidth: strokeWidth,
            x: startX+topWidth*2, y:startY+topWidth,
            radius: 0.28*topWidth,
            start: -90, end: 90,
            closed:true
        })
    return 0.28*topWidth;
}


function drawArch(startX,startY){

    $('#progressCanvas').drawArc({
        strokeDash: strokeDashBuilding,
        strokeDashOffset: 5,
        strokeStyle:bridgeArchColor,
        strokeWidth: bridgeArchStrokeWidth,
        x: startX, y:startY,
        radius: archRadius,
        start: -45, end: 45})
}


function getArchY(startX){
    return archCenterY-Math.sqrt(archRadius*archRadius-(archCenterX-startX)*(archCenterX-startX));
}

function drawArchLine(){
    for(var i=1;i<20;i++){
        var lineStartX = startArchX+i*topWidth;
        var lineStartY=getArchY(lineStartX);
        $('#progressCanvas').drawLine({
            strokeStyle:lineStrokeColor,
            strokeWidth: lineStokeWidth,
            strokeDash: [5],
            strokeDashOffset: 0,
            x1:lineStartX,y1:startY,
            x2:lineStartX,y2:lineStartY
        });
    }
}

function drawProjectName(startX,startY,text){
    $('#progressCanvas').drawText({
        fillStyle: 'black',
        strokeStyle: '#25a',
        strokeWidth: 2,
        x: startX, y: startY,
        fontSize: 48,
        fontFamily: 'Verdana, sans-serif',
        text: text,
        fromCenter:true
    });
}


function drawBridge(){
    topWidth=30+scale*10;
    topHeight=topWidth/10;
    midWidth=0.6*topHeight;
    midHeight=topWidth-2*midWidth;
    orderWidth=1.1*topWidth;
    orderHeight=topHeight;
    archCenterX=startX+22*topWidth
    archCenterY=startY+(14.14-4.2)*topWidth;
    startArchX=startX+12*topWidth;
    archRadius = 14.14*topWidth;
    bridgeArchStrokeWidth=5;
    bridgeArchColor='yellow';

    drawProjectName(startX+22*topWidth,30,'沪通长江大桥建设项目');
    drawStart(startX,startY,unitData[0]);
    var flag=0;

    for(var j=0;j<44;j++){
        //alert("i="+i+" value="+unitData[i]);
        if(j==11){
            flag=1;
        }else if(j==32){
            flag=1;
        }else if(j==35){
            flag=0;
        }
        if(j%2!=flag){
            drawUnit(startX+j*topWidth,startY,unitData[j]);
        }else{
            drawReverseUnit(startX+j*topWidth,startY,unitData[j])
        }

        if(j==8){
            radius = drawHolder(startX+j*topWidth,startY+topWidth);
            drawPier(startX+(j+2)*topWidth-radius,startY+2*topWidth+3);
        }

        if(j==32){
            radius = drawReverseHolder(startX+j*topWidth,startY+topWidth);
            drawPier(startX+(j+2)*topWidth-radius,startY+2*topWidth+3);
        }
    }
    drawPier(startX-radius,startY+midHeight+topHeight*2+2);
    drawPier(startX+44*topWidth-radius,startY+midHeight+topHeight*2+2);

    drawArch(startX+22*topWidth,startY+(14.14-4.2)*topWidth);
    bridgeArchStrokeWidth=3;
    bridgeArchColor='white';
    drawArch(archCenterX,archCenterY);

    drawArchLine();
}

function prepareBridgeData(data){
    if(data.data.length!=4){
        alert('对不起，数据与桥梁结构设计不符！');
        return;
    }

    for(var i=1;i<=data.data[0].fulfillCount;i++){
        unitData[10-i] =1;
    }
    for(var i=1;i<=data.data[1].fulfillCount;i++){
        unitData[9+i] =1;
    }
    for(var i=1;i<=data.data[2].fulfillCount;i++){
        unitData[34-i] =1;
    }
    for(var i=1;i<=data.data[3].fulfillCount;i++){
        unitData[33+i] =1;
    }
}
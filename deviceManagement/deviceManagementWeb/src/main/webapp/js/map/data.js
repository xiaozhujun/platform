/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-7-9
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    myData:function myData(){
        var data={data:[{"province":"湖北","city":"武汉","area":"洪山区","appId":"1","app":"武汉三六起重设备有限公司","address":"武汉市武昌区烽火村郁馨花园","userId":"14","username":"xiaozhujun","lng":"114.380494","lat":"30.507115","flag":"1"},
            {"province":"湖南","city":"株洲","area":"荷塘区","appId":"2","app":"株洲联德起重机有限公司","address":"中国湖南株洲市荷塘区红旗中路伟大国际广场d座903号","userId":"3","username":"suihui","lng":"113.177633","lat":"27.859618","flag":"2"},
            {"province":"湖北","city":"武汉","area":"江汉区","appId":"3","app":"武汉汽车起重机厂","address":"罗家咀路5","userId":"6","username":"赵伟","lng":"114.26731","lat":"30.603794","flag":"3"}]};
        return data;
    },
    getMyData:function getData(){
       return $.myData();
    }
});
/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-7-9
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
$.extend({
    data:function data(){
        var data={data:[{"province":"湖北","city":"武汉","area":"洪山区","app":"武汉三六起重设备有限公司","userId":"14","username":"xiaozhujun","lng":"114.380494","lat":"30.507115","flag":"1"},
            {"province":"湖南","city":"株洲","area":"荷塘区","app":"株洲联德起重机有限公司","userId":"3","username":"suihui","lng":"113.177633","lat":"27.859618","flag":"2"},
            {"province":"湖北","city":"武汉","area":"江汉区","app":"武汉汽车起重机厂","userId":"6","username":"赵伟","lng":"114.26731","lat":"30.603794","flag":"3"}]};
        return data;
    },
    getData:function getData(){
       return $.data();
    }
});
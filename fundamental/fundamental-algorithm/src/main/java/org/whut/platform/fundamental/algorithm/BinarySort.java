package org.whut.platform.fundamental.algorithm;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-6-8
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class BinarySort implements Sort {
    public static void main(String[] args){
//        int[] array={3,2,5,7,4,8,100,1,3,5,99};
//        new BinarySort().sort(array);
//        for (int i=0;i<array.length;i++){
//            System.out.print(array[i]+",");
//        }

        int []a={4,1,1,6,3,6,0,-5,1,1};
        new BinarySort().test(a);
        for(int e=0;e<10;e++){
            System.out.print(a[e]+",");
        }
    }

    public void test(int[] array){
        if(array==null||array.length<2) return;

        for(int i=1;i<array.length;i++){
            int low=0,high=i-1,mid;
            int temp=array[i];
            while(low<=high){
                mid=(low+high)/2;
                if(array[mid]>temp) high=mid-1;
                else low=mid+1;
            }
            for(int j=i;j>high+1;j--) array[j]=array[j-1];
            array[high+1]=temp;
        }
    }



    @Override
    public void sort(int[] array){
        if(array==null||array.length<2) return;
        doSort(array,0,array.length-1);
    }

    private void doSort(int[] array,int low,int high){
        if(low>high)return;
        int mid=array[low];
        int start=low+1,end=high;
        int target=low;
        while (start<=end){
            while (start<=end){
                if(mid<=array[end]){
                    end--;
                    continue;
                }
                else if(mid>array[end]){
                    array[target]=array[end];
                    target=end;
                    end--;
                    break;
                }
            }
            while (start<=end){
                if(mid>=array[start]){
                    start++;
                    continue;
                }
                else if(mid<array[start]){
                    array[target]=array[start];
                    target=start;
                    start++;
                    break;
                }
            }
        }
        array[target]=mid;
        doSort(array, low, target-1);
        doSort(array, target+1, high);
    }
}

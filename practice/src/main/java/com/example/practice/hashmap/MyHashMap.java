package com.example.practice.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baipan
 * Date: 2018-07-12
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    private static int defaultSize = 16;//Map的数组默认大小(不小于容量的2的整数次幂)默认16
    private static double loadFactor = 0.75;//扩容阀值因子默认0.75
    private Entry<K, V>[] tables;//HashMap的载体数组
    private int useSize = 0;//Map的实际size

    public MyHashMap(){
        this(defaultSize, loadFactor);
    }

    private MyHashMap(int defaultSize, double loadFactor){
        if (defaultSize<1)
            throw new IllegalArgumentException("HashMap的默认大小不能为负数或0");
        if (Double.isNaN(loadFactor) || loadFactor>=1 || loadFactor<=0)
            throw new IllegalArgumentException("HashMap的扩容因子应该为0-1之间的小数");
        MyHashMap.defaultSize = defaultSize;
        MyHashMap.loadFactor = loadFactor;
        tables = new Entry[MyHashMap.defaultSize];
    }

    @Override
    public void put(K k, V v) throws Exception {
        //1、判断是否应该扩容
        if (useSize > tables.length * loadFactor){
            up2Size(tables);
        }
        //2、通过hashcode获取下标
        int index = getIndex(k, tables.length);
        //3、判断是否存在链表结构
        Entry<K, V> entry = tables[index];
        if (entry == null)//不存在链表结构
            tables[index] = new Entry<>(k, v, null);
        else //存在链表结构
            tables[index] = new Entry<>(k, v, entry);//这个存在的entry会被挤压下去而不会丢失
        useSize++;
    }

    /**
     * 通过key获取下标
     * 通过下标hash散列算出来一定在合法范围内
     */
    private int getIndex(K k, int length) {
        int index = hash(k) & (length-1);
        return index >=0 ? index : -index;//因为hash值有可能是负数
    }

    /**
     * 哈希散列算法
     */
    private int hash(K k) {
        int hashcode = k.hashCode();
        hashcode = hashcode ^ ((hashcode >>> 20) ^ (hashcode >>> 12));
        hashcode = hashcode ^ ((hashcode >>> 7) ^ (hashcode >>> 4));
        return hashcode;
    }

    /**
     * 扩容2倍数组大小
     */
    private void up2Size(Entry<K, V>[] oldtable) throws Exception {
        //1、把之前数组中存在的Entry对象全部拿出来放到List中
        //2、开创新的2倍大小的数组从新将list中的Entry重新散列到新的数组中
        //（因为k的下标位置通篇都是散列计算的，为了能够快存快取，获取的时候一哈子就通过key拿到下标index去获取值）
        //3、把新的数组赋值回原来的数组
        List<Entry<K, V>> list = new ArrayList<>();
        for (Entry<K, V> temp : oldtable) {
            if (temp == null)
                continue;
            findEntryAndEnxt(temp, list);//一个递归找链表存入list
        }
        this.tables = new Entry[tables.length * 2];
        useSize = 0;
        for (Entry<K, V> temp : list) {
            put(temp.getKey(), temp.getValue());
        }
    }

    /**
     * 一个递归找链表存入list
     */
    private void findEntryAndEnxt(Entry<K, V> temp, List<Entry<K, V>> list) {
        if (temp.getNext() != null) {
            list.add(temp);
            findEntryAndEnxt(temp.getNext(), list);
            temp.setNext(null);//他的链表子孙已经单独放入list中了，给置空，减少内存
        }else {
            list.add(temp);
        }
    }

    @Override
    public V get(K k) throws Exception {
        if (k==null)
            return null;
        //1、通过k获取index
        int index = getIndex(k, tables.length);
        if (tables[index] == null)
            return null;
        //2、通过递归链表与比对key获取对象值
        return recursionGetValue(k, tables[index]);
    }

    private V recursionGetValue(K k, Entry<K, V> entries) {
        if (k == entries.getKey() || k.equals(entries.getKey())){
            return entries.getValue();
        }else if(entries.getNext()!=null){
            return recursionGetValue(k, entries.getNext());
        }else{
            return null;
        }
    }


    @Override
    public int size() throws Exception {
        return useSize;
    }

    class Entry<K, V> implements MyMap.Entry<K, V>{

        private K k;
        private V v;
        private Entry<K, V> next;//被自身挤压下去的Entry对象（按照先存先被挤压往下的顺序）


        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }

    public static void main(String[] args){
        try {
            long current = System.currentTimeMillis();
//            MyMap<String, String> map = new MyHashMap<>();
            Map<String, String> map = new HashMap<>();
            for (int i = 1; i <= 5000000; i++){
                map.put("k"+i, "v"+i);
            }

            for (int i = 1; i <= 50; i++){
                System.out.println("Key：key"+i+"\t\tValue："+map.get("k"+i));
            }


            System.out.println("Size："+map.size());
            System.out.println(System.currentTimeMillis() - current);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

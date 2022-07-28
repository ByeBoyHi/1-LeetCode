package Middle.股票价格波动;

import java.util.*;

public class StockPriceFluctuation {

}

// TLE
class StockPrice {

    // 记录最新价格
    int newPrice = -1;
    int newTime = -1;
    // 记录最低价格
    int minPrice = Integer.MAX_VALUE;
    // 记录最高价格
    int maxPrice = -1;
    // 一个记录这个价格的出现时间戳
    Map<Integer, List<E>> memo;
    // 另一个记录价格和时间戳的直接对应关系
    Map<Integer, Integer> map;
    // 记录是否更新过
    boolean isUpdate = false;


    public StockPrice() {
        memo = new HashMap<>();
        map = new HashMap<>();
    }

    public void update(int timestamp, int price) {
        if (timestamp>=newTime) {
            newPrice = price;
            newTime = timestamp;
        }
        if (map.containsKey(timestamp)){
            List<E> es = memo.get(map.get(timestamp));
            int i=0;
            for (E e: es){
                if (e.timestamp==timestamp) break;
                i++;
            }
            es.remove(i);
            if(es.size()==0){
                memo.remove(map.get(timestamp));
            }
        }
        map.put(timestamp, price);
        memo.putIfAbsent(price, new ArrayList<>());
        memo.get(price).add(new E(timestamp));
        isUpdate = true;
    }

    public int current() {
        return newPrice;
    }

    public int maximum() {
        if (!isUpdate || memo.containsKey(maxPrice)) return maxPrice;
        maxPrice = -1;
        for (int price: memo.keySet()){
            maxPrice = Math.max(maxPrice, price);
        }
        isUpdate = false;
        return maxPrice;
    }

    public int minimum() {
        if (!isUpdate || memo.containsKey(minPrice)) return minPrice;
        minPrice = Integer.MAX_VALUE;
        for (int price: memo.keySet()){
            minPrice = Math.min(minPrice, price);
        }
        isUpdate = false;
        return minPrice;
    }
}

class E{
    int timestamp;
    public E(int timestamp){
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        E e = (E) o;
        return timestamp == e.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }
}
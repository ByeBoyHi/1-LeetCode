package 周赛.第262场周赛.股票价格波动;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class 股票价格波动 {

}

// 超出时间限制
class StockPrice {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int cur = 0;
    int curTime = 0;
    Map<Integer, Integer> history;


    public StockPrice() {
        this.history = new HashMap<>();
    }

    public void update(int timestamp, int price) {
        this.history.put(timestamp, price);
        if (price > this.max) this.max = price;
        if (price < this.min) this.min = price;

        if (timestamp >= this.curTime) {
            this.curTime = timestamp;
            this.cur = price;
        }
    }

    public int current() {
        return this.cur;
    }

    public int maximum() {
        if (this.history.containsValue(this.max))
            return this.max;
        this.max = Integer.MIN_VALUE;
        for (int num : this.history.keySet()) {
            int cur = this.history.get(num);
            if (cur > this.max) {
                this.max = cur;
            }
        }
        return this.max;
    }

    public int minimum() {
        if (this.history.containsValue(this.min))
            return this.min;
        this.min = Integer.MAX_VALUE;
        for (int num : this.history.keySet()) {
            int cur = this.history.get(num);
            if (cur < this.min) {
                this.min = cur;
            }
        }
        return this.min;
    }
}

class StockPrice2 {

    // 给一个HashMap用于存储时间价格表 —— 每个时间戳的股票价格，可以用于修改被覆盖的时间戳和价格
    // 用一个TreeMap用于存储价格数量表 —— 每个价格出现的次数，可以返回最高价格和最低价格
    // TreeMap是一个红黑树数据结构，排序顺序是从小到大。
    HashMap<Integer, Integer> tpMap;
    TreeMap<Integer, Integer> pnMap;
    private int currentTime;

    public StockPrice2() {
        tpMap = new HashMap<>();
        pnMap = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        // 需要判断传进来的时间戳是否存在
        // 如果存在可能会覆盖原来的价格，对应的价格数量表需要变化
        if (this.tpMap.containsKey(timestamp)){
            int oldPrice = this.tpMap.get(timestamp);
            // 被覆盖修改后，原来的价格数量需要减一
            pnMap.put(oldPrice,pnMap.get(oldPrice)-1);
            if (pnMap.get(oldPrice)==0){  // 如果减一后数量为0，说明所有的股价已经没有这个价格了，就删除了
                pnMap.remove(oldPrice);
            }
        }
        this.tpMap.put(timestamp,price);
        this.pnMap.put(price,this.pnMap.getOrDefault(price,0)+1);
        this.currentTime = Math.max(this.currentTime, timestamp);
    }

    public int current() {
        return this.tpMap.get(this.currentTime);
    }

    public int maximum() {
        return this.pnMap.lastKey();
    }

    public int minimum() {
        return this.pnMap.firstKey();
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
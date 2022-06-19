package Easy.换酒问题;

public class WaterBottles {
    /**
     * 计算从最初买的几瓶酒，通过换瓶规则，最终可以喝多少酒
     * @param numBottles    最开始买了这么多瓶酒
     * @param numExchange   这么多个空瓶子可以换一瓶新的酒
     * @return              返回可以获得的多少瓶酒
     *
     * 思路：每次都只需要加上我们新换的酒就好了，之前剩下的我们加过了
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int rest = numBottles;
        int ans = rest;
        while (rest>=numExchange){
            int quotient = rest/numExchange;  // 当前可以换多少瓶
            int remainder = rest%numExchange; // 当前换了后，还剩下多少瓶
            ans+=quotient;  // 加上这次能换的瓶子个数
            rest = quotient+remainder;  // 这次之后，还剩下的总瓶子个数
        }
        return ans;
    }
}

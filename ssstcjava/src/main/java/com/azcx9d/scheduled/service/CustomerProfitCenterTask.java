package com.azcx9d.scheduled.service;

import com.azcx9d.user.dao.ProfitCenterDao;
import com.azcx9d.user.entity.MarketEntity;
import com.azcx9d.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 消费者收益
 *
 * @author fby
 */

@Component("profitCenterTask")
public class CustomerProfitCenterTask {

    private Logger logger = LoggerFactory.getLogger(CustomerProfitCenterTask.class);

    @Autowired
    private ProfitCenterDao dao;


    private static final float CUSTOMER_AWARD_PERCENTAGE = 0.13f;//消费者奖励比例
    private static final float CUSTOMER_POINTS_LOVE_PERCENTAGE = 0.002f;//消费者积分兑换善心比例




    private final static Executor executor = Executors.newCachedThreadPool();//启用多线程


    /**
     * 获取善心比例
     *
     * @return 消费都大盘善心比例
     */
 //   @Scheduled(cron = "0 0 0 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void customerLovePercentage()
    {

        MarketEntity marketEntity = dao.queryMarket();

        if (null==marketEntity)
        {
            marketEntity = new MarketEntity();
            marketEntity.setOldLove(0);
        }


        float old_love=marketEntity.getOldLove();//沉淀善心

        double totalMoney1 =0;
        double totalMoney2 =0;
        double totalMoney3 =0;

        totalMoney1 = dao.getVeryDayMarketTotalMoney(1);
        totalMoney2 = dao.getVeryDayMarketTotalMoney(2);
        totalMoney3 = dao.getVeryDayMarketTotalMoney(3);

        double totalLovePoint=0;
        double totalLove=0;

        double newLove=0;

        double totalIntegal =0;

        double surplusIntegal;//剩余积分

        surplusIntegal=marketEntity.getSurplusIntegal();

        try {
            //TODO 暂时都按照100%激励
            totalIntegal = totalMoney1 * 1 + totalMoney2 * 1 + totalMoney3 * 1 + surplusIntegal;//(商家让利5%-激励25%，商家让利10%-激励50%,商家让利20%-激励100% )
            //TODO 可分配善点数暂时都按照13%计算
            totalLovePoint = (totalMoney1 + totalMoney2 + totalMoney3) * CUSTOMER_AWARD_PERCENTAGE;//消费者可分配善点数
            newLove = totalIntegal * CUSTOMER_POINTS_LOVE_PERCENTAGE;
            totalLove = newLove + old_love;//消费者总善心数
            surplusIntegal = totalIntegal % 500;//剩余积分
            logger.info("消费者大盘计算:剩余积分" + surplusIntegal);
        }catch (Exception e)
        {
            logger.error("消费者大盘善心比例计算出错:"+e.getMessage());
        }

        //善心比例
        double lovePercentage=0;
        try{
            if (totalLove==0.0)
                throw new RuntimeException("除数为0(消费者总善心数totalLove)");
             lovePercentage =totalLovePoint / totalLove;
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("totalLovePoint",totalLovePoint);
        map.put("totalLove",totalLove);
        map.put("lovePercentage",lovePercentage);
        map.put("surplusIntegal",surplusIntegal);
        map.put("expenditure",totalMoney1+totalMoney2+totalMoney3);
        map.put("totalIntegal",totalIntegal);
        map.put("surplusIntegal",surplusIntegal);
        map.put("oldLove",old_love);
        map.put("newLove",newLove);
        try {
            int result=dao.insertMarketCustomer(map);//记录大盘
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        Date date= new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=fmt.format(date);
        logger.info(dateStr+"总消费消费金额："+(totalMoney1+totalMoney2+totalMoney3)+",新增善心："+newLove);
    }





    /**
     *
     */
//    @Scheduled(cron = "0 5 0 * * ?")
    public void countCustomerLovePoints() {
        List<User> list = dao.queryCustomerList();

        MarketEntity mapMarket = dao.queryMarket();

        float lovePercentage = mapMarket.getLovePercentage();


        /*if (null!=list && list.size()>0) {
            for (User user:list) {
                executor.execute(new CountLovePoints(dao,user,lovePercentage));
            }
        }*/

        if (null != list && list.size() > 0) {
            for (User user : list) {
                try {
                    countCustomerLovePoints(user, lovePercentage);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

        }
    }

    /**
     * 用户(商家)善点变更
     * @param user
     * @param lovePercentage
     */
    @Transactional(rollbackFor = Exception.class)
    private void countCustomerLovePoints(User user,float lovePercentage) throws Exception {
        double love= user.getShanxin();

        double lovePoints = lovePercentage*love;

        user.setShandian(lovePoints);

        dao.updateUserLovePoints(user);

        logger.info("商家："+user.getId()+"善点新增："+lovePoints);
        Map<String,Object>  map = new HashMap<String,Object>();

        map.put("lovePoints",lovePoints);
        map.put("type",0);
        map.put("jieyu",lovePoints+user.getShandian());
        map.put("userId",user.getId());
        dao.insertLovePointRecord(map);
    }
}

# Market-Data-Analysis
# 行情数据分析系统  

系统配置介绍  

1）腾讯云服务器（操作系统：CentOS 7.6 64bit，CPU：1核，内存：2GB，数据盘：50GB）  

2）存储工具：MongoDB-linux-x86_64-rhel70-4.2.3（存储股票、基金数据），MySQL（存储管理员、用户信息）  

3）后台框架：SpringBoot-2.2.5  

4）前端框架：Bootstrap-4.0.0，JQuery-3.4.1  

===============================================================================

系统功能介绍  

1 登录及拦截
![image](https://github.com/guanggong2020/Market-Data-Analysis/blob/master/images/1.PNG)

2 主页
![image](https://github.com/guanggong2020/Market-Data-Analysis/blob/master/images/2.PNG)

3 管理员管理
![image](https://github.com/guanggong2020/Market-Data-Analysis/blob/master/images/3.PNG)

4 用户管理
![image](https://github.com/guanggong2020/Market-Data-Analysis/blob/master/images/4.PNG)

5 搜索展示
![image](https://github.com/guanggong2020/Market-Data-Analysis/blob/master/images/5.PNG)

6 详细展示
![image](https://github.com/guanggong2020/Market-Data-Analysis/blob/master/images/6.PNG)

7 可视化展示
![image](https://github.com/guanggong2020/Market-Data-Analysis/blob/master/images/7.PNG)

===============================================================================

系统接口介绍  

1）http://134.175.192.53:8080/gupiao_data/findDataByCodeOrName/input/pageNum/pageSize

功能：查询A股数据（精确查询）  
input：输入代码/名称  
pageNum：输入页数  
pageSize：输入每页大小  

例如：  
1 http://134.175.192.53:8080/gupiao_data/findDataByCodeOrName/000001/1/10  
2 http://134.175.192.53:8080/gupiao_data/findDataByCodeOrName/平安银行/1/10  

2）http://134.175.192.53:8080/gupiao_data/findDataByRegex/input/selection/pageNum/pageSize

功能：查询A股数据（模糊查询）  
input：输入代码/名称  
selection：自定义排序，可选：comprehensive（综合），closingPriceRise（收盘价升序），closingPriceDrop（收盘价降序），changeRise（涨跌额升序），changeDrop（涨跌额降序），quoteChangeRise（涨跌幅升序），quoteChangeDrop（涨跌幅降序）  
pageNum：输入页数  
pageSize：输入每页大小  

例如：  
1 http://134.175.192.53:8080/gupiao_data/findDataByRegex/%20/comprehensive/1/10  
2 http://134.175.192.53:8080/gupiao_data/findDataByRegex/01/comprehensive/1/10  
3 http://134.175.192.53:8080/gupiao_data/findDataByRegex/平安/comprehensive/1/10  

3）http://134.175.192.53:8080/jijin_data/findDataByCodeOrName/input/pageNum/pageSize

功能：查询基金数据（精确查询）  
input：输入代码/名称  
pageNum：输入页数  
pageSize：输入每页大小  

例如：  
1 http://134.175.192.53:8080/jijin_data/findDataByCodeOrName/000310/1/10  
2 http://134.175.192.53:8080/jijin_data/findDataByCodeOrName/安信永利信用A/1/10  

4）http://134.175.192.53:8080/jijin_data/findDataByRegex/input/selection/pageNum/pageSize

功能：查询基金数据（模糊查询）    
input：输入代码/名称  
selection：自定义排序，可选：comprehensive（综合），unitNetWorthRise（单位净值升序），unitNetWorthDrop（单位净值降序），cumulativeNetWorthRise（累计净值升序），cumulativeNetWorthDrop（累计净值降序），growthRateRise（增长率升序），growthRateDrop（增长率降序）  
pageNum：输入页数  
pageSize：输入每页大小  

例如：  
1 http://134.175.192.53:8080/jijin_data/findDataByRegex/%20/comprehensive/1/10  
2 http://134.175.192.53:8080/jijin_data/findDataByRegex/31/comprehensive/1/10  
3 http://134.175.192.53:8080/jijin_data/findDataByRegex/利/comprehensive/1/10  

5）http://134.175.192.53:8080/USA_stock_data/findDataByCodeOrName/input/pageNum/pageSize

功能：查询美股数据（精确查询）  
input：输入代码/名称  
pageNum：输入页数  
pageSize：输入每页大小  

例如：  
1 http://134.175.192.53:8080/USA_stock_data/findDataByCodeOrName/HAPP/1/10  
2 http://134.175.192.53:8080/USA_stock_data/findDataByCodeOrName/幸福来/1/10  

6）http://134.175.192.53:8080/USA_stock_data/findDataByRegex/input/selection/pageNum/pageSize

功能：查询美股数据（模糊查询）  
input：输入代码/名称  
selection：自定义排序，可选：comprehensive（综合），closingPriceRise（收盘价升序），closingPriceDrop（收盘价降序），changeRise（涨跌额升序），changeDrop（涨跌额降序），quoteChangeRise（涨跌幅升序），quoteChangeDrop（涨跌幅降序）  
pageNum：输入页数  
pageSize：输入每页大小  

例如：  
1 http://134.175.192.53:8080/USA_stock_data/findDataByRegex/%20/closingPriceRise/1/10  
2 http://134.175.192.53:8080/USA_stock_data/findDataByRegex/HA/closingPriceRise/1/10  
3 http://134.175.192.53:8080/USA_stock_data/findDataByRegex/来/closingPriceRise/1/10  

7）增加根据时间返回数据接口  
1 http://134.175.192.53:8080/gupiao_data/findDataByCodeOrName/input/fromDate/toDatepageNum/pageSize  
2 http://134.175.192.53:8080/jijin_data/findDataByCodeOrName/input/fromDate/toDatepageNum/pageSize  
3 http://134.175.192.53:8080/USA_stock_data/findDataByCodeOrName/input/fromDate/toDatepageNum/pageSize  

ps:
fromDate：开始日期（格式：2020-03-12）  
toDate：结束日期

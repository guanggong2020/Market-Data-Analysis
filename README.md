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
1 http://134.175.192.53:8080/gupiao_data/findDataByCodeOrName/input/fromDate/toDate/pageNum/pageSize  
2 http://134.175.192.53:8080/jijin_data/findDataByCodeOrName/input/fromDate/toDate/pageNum/pageSize  
3 http://134.175.192.53:8080/USA_stock_data/findDataByCodeOrName/input/fromDate/toDate/pageNum/pageSize  

ps:  
fromDate：开始日期（格式：2020-03-12）  
toDate：结束日期  

8）上证、深证查询接口  
1 http://134.175.192.53:8080/shangzheng_shenzheng_data/findDataByCodeOrName/input/pageNum/pageSize  
2 http://134.175.192.53:8080/shangzheng_shenzheng_data/findDataByCodeOrName/input/pageNum/pageSize  
3 http://134.175.192.53:8080/shangzheng_shenzheng_data/findDataByRegex/input/selection/pageNum/pageSize  

input：上证/深证+（代码或者名称，可选）  

9）数据字段说明：  

*A股字段含义：  
日期date  
股票代码code  
名称name  
收盘价closingPrice  
最高价maxPrice  
最低价minPrice  
开盘价openingPrice  
前收盘previousClose  
涨跌额change  
涨跌幅quoteChange（百分数）  
换手率turnoverRate（百分数）  
成交量volume  
成交金额turnover  
总市值totalMarketCapitalization  
流通市值marketCapitalization  


*基金（国内）字段含义：  
时间date  
代码code  
基金名称fundName  
单位净值unitNetWorth  
累计净值cumulativeNetWorth  
增长率growthRate（百分数）  


*美股字段含义  
date时间  
code代码  
name名称  
closingPrice收盘价  
openingPrice开盘价  
maxPrice最高价  
minPrice最低价  
volume成交量  
previousClose前收盘  
quoteChange涨跌幅（百分数）  
change涨跌额  
turnover成交金额  
amplitude振幅（百分数）  
turnoverRate换手率（百分数）  
PER市盈率（百分数）  
QRR量比  
PBR市净率（百分数）  
totalMarketCapitalization总市值  
TTM上市时间  
EPS每股收益  
NAVPS每股净资产  


*上证指数字段含义：  
date时间  
code代码  
name名称  
closingPrice收盘价  
openingPrice开盘价  
maxPrice最高价  
minPrice最低价  
previousClose前收盘  
change涨跌额  
quoteChange涨跌幅（百分数）  
volume成交量  
turnover成交金额  
amplitude振幅（百分数）  
QRR量比  

*深证指数字段含义：  
date时间  
code代码  
name名称  
closingPrice收盘价  
openingPrice开盘价  
maxPrice最高价  
minPrice最低价  
previousClose前收盘  
change涨跌额  
quoteChange涨跌幅（百分数）  
volume成交量  
turnover成交金额  
amplitude振幅（百分数）  
QRR量比
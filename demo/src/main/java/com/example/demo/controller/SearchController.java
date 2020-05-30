package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.entities.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private GuPiaoDataDao guPiaoDataDao;

    @Autowired
    private JiJinDataDao jiJinDataDao;

    @Autowired
    private USAStockDataDao usaStockDataDao;

    @Autowired
    private ShangZhengShenZhengDataDao shangZhengShenZhengDataDao;

    @Autowired
    private USAFundDataDao usaFundDataDao;

    @PostMapping("/search")
    public String listAll(@RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("input") String input, Model model){
        int pageNum = 1;
        int pageSize = 12;
        String t = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd");
        String date = simpleDateFormat.format(new Date());
        model.addAttribute("date", date);

        /**
         * 股票排序选择
         */
        if (selection02.equals("综合"))
            t = "comprehensive";
        else if (selection02.equals("股票-收盘价升序"))
            t = "closingPriceRise";
        else if (selection02.equals("股票-收盘价降序"))
            t = "closingPriceDrop";
        else if (selection02.equals("股票-涨跌额升序"))
            t = "changeRise";
        else if (selection02.equals("股票-涨跌额降序"))
            t = "changeDrop";
        else if (selection02.equals("股票-涨跌幅升序"))
            t = "quoteChangeRise";
        else if (selection02.equals("股票-涨跌幅降序"))
            t = "quoteChangeDrop";

        /**
         * 国内基金排序选择
         */
        else if (selection02.equals("国内基金-单位净值升序"))
            t = "unitNetWorthRise";
        else if (selection02.equals("国内基金-单位净值降序"))
            t = "unitNetWorthDrop";
        else if (selection02.equals("国内基金-累计净值升序"))
            t = "cumulativeNetWorthRise";
        else if (selection02.equals("国内基金-累计净值降序"))
            t = "cumulativeNetWorthDrop";
        else if (selection02.equals("国内基金-增长率升序"))
            t = "growthRateRise";
        else if (selection02.equals("国内基金-增长率降序"))
            t = "growthRateDrop";

        /**
         * 国外基金排序选择
         */
        else if (selection02.equals("国外基金-收盘价升序"))
            t = "closingPriceRise";
        else if (selection02.equals("国外基金-收盘价降序"))
            t = "closingPriceDrop";
        else if (selection02.equals("国外基金-涨跌额升序"))
            t = "changeRise";
        else if (selection02.equals("国外基金-涨跌额降序"))
            t = "changeDrop";
        else if (selection02.equals("国外基金-增长率升序"))
            t = "growthRateRise";
        else if (selection02.equals("国外基金-增长率降序"))
            t = "growthRateDrop";

        if (input.split("\\+").length == 0)
            input = selection01 + "+" + "" + "+" + t;
        else if (input.split("\\+").length == 1)
            input = selection01 + "+" + input + "+" + t;

        String[] ss = input.split("\\+");
        List dataList = null;
        long recordTotal = 0;
        int pageTotal = 0;

        if (ss[0].equals("A股")) {
            if (ss.length == 1)
                dataList = guPiaoDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = guPiaoDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else {
                dataList = guPiaoDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
                recordTotal = guPiaoDataDao.recordTotal(ss[1], ss[2], pageNum, pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }

            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", 1);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/gupiaoListAll";
        }
        else if (ss[0].equals("美股")) {
            if (ss.length == 1)
                dataList = usaStockDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = usaStockDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else {
                dataList = usaStockDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
                recordTotal = usaStockDataDao.recordTotal(ss[1], ss[2], pageNum, pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }

            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", 1);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/USAStockListAll";
        }
        else if (ss[0].equals("国内基金")) {
            if (ss.length == 1)
                dataList = jiJinDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = jiJinDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else {
                dataList = jiJinDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
                recordTotal = jiJinDataDao.recordTotal(ss[1], ss[2], pageNum, pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }
            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", 1);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/jijinListAll";
        }
        else if (ss[0].equals("国外基金")) {
            if (ss.length == 1)
                dataList = usaFundDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = usaFundDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else {
                dataList = usaFundDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
                recordTotal = usaFundDataDao.recordTotal(ss[1], ss[2], pageNum, pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }
            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", 1);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/USAFundListAll";
        }
        else if (ss[0].equals("上证") || ss[0].equals("深证")) {
            if (ss.length == 1)
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0], "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0] + "+" + ss[1], "comprehensive", pageNum, pageSize);
            else {
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0] + "+" + ss[1], ss[2], pageNum, pageSize);
                recordTotal = shangZhengShenZhengDataDao.recordTotal(ss[0] + "+" + ss[1], ss[2], pageNum, pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }
            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", 1);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/shangzhengshenzhengListAll";
        }
        return "redirect:/main.html";
    }

    /**
     * 分页请求
     */
    @GetMapping("/search/{selection01}/{selection02}/{input}/{pageNum}")
    public String paging(@PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, @PathVariable("input") String input, @PathVariable("pageNum") String pageNum, Model model){
        int pageSize = 12;
        String t = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd");
        String date = simpleDateFormat.format(new Date());
        model.addAttribute("date", date);

        System.out.println("看看我调用了没......");

        /**
         * 股票排序选择
         */
        if (selection02.equals("综合"))
            t = "comprehensive";
        else if (selection02.equals("股票-收盘价升序"))
            t = "closingPriceRise";
        else if (selection02.equals("股票-收盘价降序"))
            t = "closingPriceDrop";
        else if (selection02.equals("股票-涨跌额升序"))
            t = "changeRise";
        else if (selection02.equals("股票-涨跌额降序"))
            t = "changeDrop";
        else if (selection02.equals("股票-涨跌幅升序"))
            t = "quoteChangeRise";
        else if (selection02.equals("股票-涨跌幅降序"))
            t = "quoteChangeDrop";

        /**
         * 国内基金排序选择
         */
        else if (selection02.equals("国内基金-单位净值升序"))
            t = "unitNetWorthRise";
        else if (selection02.equals("国内基金-单位净值降序"))
            t = "unitNetWorthDrop";
        else if (selection02.equals("国内基金-累计净值升序"))
            t = "cumulativeNetWorthRise";
        else if (selection02.equals("国内基金-累计净值降序"))
            t = "cumulativeNetWorthDrop";
        else if (selection02.equals("国内基金-增长率升序"))
            t = "growthRateRise";
        else if (selection02.equals("国内基金-增长率降序"))
            t = "growthRateDrop";

        /**
         * 国外基金排序选择
         */
        else if (selection02.equals("国外基金-收盘价升序"))
            t = "closingPriceRise";
        else if (selection02.equals("国外基金-收盘价降序"))
            t = "closingPriceDrop";
        else if (selection02.equals("国外基金-涨跌额升序"))
            t = "changeRise";
        else if (selection02.equals("国外基金-涨跌额降序"))
            t = "changeDrop";
        else if (selection02.equals("国外基金-增长率升序"))
            t = "growthRateRise";
        else if (selection02.equals("国外基金-增长率降序"))
            t = "growthRateDrop";

        if (input.split("\\+").length == 0)
            input = selection01 + "+" + "" + "+" + t;
        else if (input.split("\\+").length == 1)
            input = selection01 + "+" + input + "+" + t;

        String[] ss = input.split("\\+");
        List dataList = null;
        long recordTotal = 0;
        int pageTotal = 0;

        if (ss[0].equals("A股")) {
            if (ss.length == 1)
                dataList = guPiaoDataDao.findDataByRegex("", "comprehensive", Integer.parseInt(pageNum), pageSize);
            else if (ss.length == 2)
                dataList = guPiaoDataDao.findDataByRegex(ss[1], "comprehensive", Integer.parseInt(pageNum), pageSize);
            else {
                dataList = guPiaoDataDao.findDataByRegex(ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                recordTotal = guPiaoDataDao.recordTotal(ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }

            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", pageNum);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/gupiaoListAll";
        }
        else if (ss[0].equals("美股")) {
            if (ss.length == 1)
                dataList = usaStockDataDao.findDataByRegex("", "comprehensive", Integer.parseInt(pageNum), pageSize);
            else if (ss.length == 2)
                dataList = usaStockDataDao.findDataByRegex(ss[1], "comprehensive", Integer.parseInt(pageNum), pageSize);
            else {
                dataList = usaStockDataDao.findDataByRegex(ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                recordTotal = usaStockDataDao.recordTotal(ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }

            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", pageNum);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/USAStockListAll";
        }
        else if (ss[0].equals("国内基金")) {
            if (ss.length == 1)
                dataList = jiJinDataDao.findDataByRegex("", "comprehensive", Integer.parseInt(pageNum), pageSize);
            else if (ss.length == 2)
                dataList = jiJinDataDao.findDataByRegex(ss[1], "comprehensive", Integer.parseInt(pageNum), pageSize);
            else {
                dataList = jiJinDataDao.findDataByRegex(ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                recordTotal = jiJinDataDao.recordTotal(ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }

            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", pageNum);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/jijinListAll";
        }
        else if (ss[0].equals("国外基金")) {
            if (ss.length == 1)
                dataList = usaFundDataDao.findDataByRegex("", "comprehensive", Integer.parseInt(pageNum), pageSize);
            else if (ss.length == 2)
                dataList = usaFundDataDao.findDataByRegex(ss[1], "comprehensive", Integer.parseInt(pageNum), pageSize);
            else {
                dataList = usaFundDataDao.findDataByRegex(ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                recordTotal = usaFundDataDao.recordTotal(ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }

            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", pageNum);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/USAFundListAll";
        }
        else if (ss[0].equals("上证") || ss[0].equals("深证")) {
            if (ss.length == 1)
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0], "comprehensive", Integer.parseInt(pageNum), pageSize);
            else if (ss.length == 2)
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0] + "+" + ss[1], "comprehensive", Integer.parseInt(pageNum), pageSize);
            else {
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0] + "+" + ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                recordTotal = shangZhengShenZhengDataDao.recordTotal(ss[0] + "+" + ss[1], ss[2], Integer.parseInt(pageNum), pageSize);
                pageTotal = (int) (recordTotal / pageSize + (recordTotal % pageSize == 0 ? 0 : 1));
            }

            if (ss.length >= 2) {
                if (ss[1].length() == 0)
                    model.addAttribute("input", "+");
                else
                    model.addAttribute("input", ss[1]);
                model.addAttribute("selection01", selection01);
                model.addAttribute("selection02", selection02);
                model.addAttribute("pageNum", pageNum);
            }
            model.addAttribute("dataList", dataList);
            model.addAttribute("recordTotal", recordTotal);
            model.addAttribute("pageTotal", pageTotal);
            return "search/shangzhengshenzhengListAll";
        }
        return "redirect:/main.html";
    }

    @GetMapping("/search/gupiao/{code}/{selection01}/{selection02}")
    public String listGPOne(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/gupiaoListOne";
    }

    @PostMapping("/search/gupiao")
    public String listGPOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            GuPiaoData guPiaoData = new GuPiaoData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            dataList.add(guPiaoData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/gupiaoListOne";
    }

    @GetMapping("/search/gupiao/graph/{code}/{selection01}/{selection02}")
    public String listGPByGraph(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/gupiaoListByGraph";
    }

    @PostMapping("/search/gupiao/graph")
    public String listGPByGraphOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            GuPiaoData guPiaoData = new GuPiaoData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            dataList.add(guPiaoData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/gupiaoListByGraph";
    }

    @GetMapping("/search/jijin/{code}/{selection01}/{selection02}")
    public String listJJOne(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/jijinListOne";
    }

    @PostMapping("/search/jijin")
    public String listJJOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            JiJinData jiJinData = new JiJinData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0);
            dataList.add(jiJinData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/jijinListOne";
    }

    @GetMapping("/search/jijin/graph/{code}/{selection01}/{selection02}")
    public String listJJByGraph(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/jijinListByGraph";
    }

    @PostMapping("/search/jijin/graph")
    public String listJJByGraphOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            JiJinData jiJinData = new JiJinData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0);
            dataList.add(jiJinData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/jijinListByGraph";
    }

    @GetMapping("/search/us/{code}/{selection01}/{selection02}")
    public String listUSOne(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/USAStockListOne";
    }

    @PostMapping("/search/us")
    public String listUSOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            USAStockData usaStockData = new USAStockData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            dataList.add(usaStockData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/USAStockListOne";
    }

    @GetMapping("/search/us/graph/{code}/{selection01}/{selection02}")
    public String listUSByGraph(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/USAStockListByGraph";
    }

    @PostMapping("/search/us/graph")
    public String listUSByGraphOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            USAStockData usaStockData = new USAStockData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            dataList.add(usaStockData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/USAStockListByGraph";
    }

    @GetMapping("/search/shangzhengshenzheng/{code}/{selection01}/{selection02}")
    public String listShangZhengShenZhengOne(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/shangzhengshenzhengListOne";
    }

    @PostMapping("/search/shangzhengshenzheng")
    public String listShangZhengShenZhengOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            ShangZhengShenZhengData shangZhengShenZhengData = new ShangZhengShenZhengData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            dataList.add(shangZhengShenZhengData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/shangzhengshenzhengListOne";
    }

    @GetMapping("/search/shangzhengshenzheng/graph/{code}/{selection01}/{selection02}")
    public String listShangZhengShenZhengByGraph(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/shangzhengshenzhengListByGraph";
    }

    @PostMapping("/search/shangzhengshenzheng/graph")
    public String listShangZhengShenZhengByGraphOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            ShangZhengShenZhengData shangZhengShenZhengData = new ShangZhengShenZhengData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            dataList.add(shangZhengShenZhengData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/shangzhengshenzhengListByGraph";
    }

    @GetMapping("/search/uf/{code}/{selection01}/{selection02}")
    public String listUFOne(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<USAFundData> dataList = usaFundDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/USAFundListOne";
    }

    @PostMapping("/search/uf")
    public String listUFOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<USAFundData> dataList = usaFundDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            USAFundData usaFundData = new USAFundData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    "0M",
                    "0B");
            dataList.add(usaFundData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/USAFundListOne";
    }

    @GetMapping("/search/uf/graph/{code}/{selection01}/{selection02}")
    public String listUFByGraph(@PathVariable("code") String code, @PathVariable("selection01") String selection01, @PathVariable("selection02") String selection02, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<USAFundData> dataList = usaFundDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/USAFundListByGraph";
    }

    @PostMapping("/search/uf/graph")
    public String listUFByGraphOneMore(@RequestParam("code") String code, @RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<USAFundData> dataList = usaFundDataDao.findDataByCodeOrName(code, fromDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            USAFundData usaFundData = new USAFundData(new ObjectId(new Date()),
                    fromDate,
                    code,
                    "不合法日期",
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    "0M",
                    "0B");
            dataList.add(usaFundData);
        }
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dataList", dataList);
        model.addAttribute("selection01", selection01);
        model.addAttribute("selection02", selection02);
        return "search/USAFundListByGraph";
    }
}

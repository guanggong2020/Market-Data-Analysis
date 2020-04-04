package com.example.demo.controller;

import com.example.demo.dao.GuPiaoDataDao;
import com.example.demo.dao.JiJinDataDao;
import com.example.demo.dao.ShangZhengShenZhengDataDao;
import com.example.demo.dao.USAStockDataDao;
import com.example.demo.entities.GuPiaoData;
import com.example.demo.entities.JiJinData;
import com.example.demo.entities.ShangZhengShenZhengData;
import com.example.demo.entities.USAStockData;
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

    @PostMapping("/search")
    public String listAll(@RequestParam("selection01") String selection01, @RequestParam("selection02") String selection02, @RequestParam("input") String input, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd");
        String date = simpleDateFormat.format(new Date());
        model.addAttribute("date", date);

        if (selection02.equals("综合"))
            selection02 = "comprehensive";
        else if (selection02.equals("收盘价升序"))
            selection02 = "closingPriceRise";
        else if (selection02.equals("收盘价降序"))
            selection02 = "closingPriceDrop";
        else if (selection02.equals("涨跌额升序"))
            selection02 = "changeRise";
        else if (selection02.equals("涨跌额降序"))
            selection02 = "changeDrop";
        else if (selection02.equals("涨跌幅升序"))
            selection02 = "quoteChangeRise";
        else
            selection02 = "quoteChangeDrop";

        if (input.split("\\+").length == 1)
            input = selection01 + "+" + input + "+" + selection02;

        String[] ss = input.split("\\+");
        List dataList = null;

        if (ss[0].equals("A股")) {
            if (ss.length == 1)
                dataList = guPiaoDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = guPiaoDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else
                dataList = guPiaoDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
            if (ss.length >= 2)
                model.addAttribute("input", ss[1]);
            model.addAttribute("dataList", dataList);
            return "search/gupiaoListAll";
        }
        else if (ss[0].equals("美股")) {
            if (ss.length == 1)
                dataList = usaStockDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = usaStockDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else
                dataList = usaStockDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
            if (ss.length >= 2)
                model.addAttribute("input", ss[1]);
            model.addAttribute("dataList", dataList);
            return "search/USAStockListAll";
        }
        else if (ss[0].equals("基金")) {
            if (ss.length == 1)
                dataList = jiJinDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = jiJinDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else
                dataList = jiJinDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
            if (ss.length >= 2)
                model.addAttribute("input", ss[1]);
            model.addAttribute("dataList", dataList);
            return "search/jijinListAll";
        }
        else if (ss[0].equals("上证") || ss[0].equals("深证")) {
            if (ss.length == 1)
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0], "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0] + "+" + ss[1], "comprehensive", pageNum, pageSize);
            else
                dataList = shangZhengShenZhengDataDao.findDataByRegex(ss[0] + "+" + ss[1], ss[2], pageNum, pageSize);
            if (ss.length >= 2)
                model.addAttribute("input", ss[1]);
            model.addAttribute("dataList", dataList);
            return "search/shangzhengshenzhengListAll";
        }
        return "redirect:/main.html";
    }

    @GetMapping("/search/gupiao/{code}")
    public String listGPOne(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/gupiaoListOne";
    }

    @PostMapping("/search/gupiao")
    public String listGPOneMore(@RequestParam("code") String code, @RequestParam("formDate") String formDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, formDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            GuPiaoData guPiaoData = new GuPiaoData(new ObjectId(new Date()),
                    formDate,
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
        model.addAttribute("dataList", dataList);
        return "search/gupiaoListOne";
    }

    @GetMapping("/search/gupiao/graph/{code}")
    public String listGPByGraph(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/gupiaoListByGraph";
    }

    @PostMapping("/search/gupiao/graph")
    public String listGPByGraphOneMore(@RequestParam("code") String code, @RequestParam("formDate") String formDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, formDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            GuPiaoData guPiaoData = new GuPiaoData(new ObjectId(new Date()),
                    formDate,
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
        model.addAttribute("dataList", dataList);
        return "search/gupiaoListByGraph";
    }

    @GetMapping("/search/jijin/{code}")
    public String listJJOne(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/jijinListOne";
    }

    @PostMapping("/search/jijin")
    public String listJJOneMore(@RequestParam("code") String code, @RequestParam("formDate") String formDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, formDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            JiJinData jiJinData = new JiJinData(new ObjectId(new Date()),
                    formDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0);
            dataList.add(jiJinData);
        }
        model.addAttribute("dataList", dataList);
        return "search/jijinListOne";
    }

    @GetMapping("/search/jijin/graph/{code}")
    public String listJJByGraph(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/jijinListByGraph";
    }

    @PostMapping("/search/jijin/graph")
    public String listJJByGraphOneMore(@RequestParam("code") String code, @RequestParam("formDate") String formDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, formDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            JiJinData jiJinData = new JiJinData(new ObjectId(new Date()),
                    formDate,
                    code,
                    "不合法日期",
                    0.0,
                    0.0,
                    0.0);
            dataList.add(jiJinData);
        }
        model.addAttribute("dataList", dataList);
        return "search/jijinListByGraph";
    }

    @GetMapping("/search/us/{code}")
    public String listUSOne(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/USAStockListOne";
    }

    @PostMapping("/search/us")
    public String listUSOneMore(@RequestParam("code") String code, @RequestParam("formDate") String formDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, formDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            USAStockData usaStockData = new USAStockData(new ObjectId(new Date()),
                    formDate,
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
        model.addAttribute("dataList", dataList);
        return "search/USAStockListOne";
    }

    @GetMapping("/search/us/graph/{code}")
    public String listUSByGraph(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/USAStockListByGraph";
    }

    @PostMapping("/search/us/graph")
    public String listUSByGraphOneMore(@RequestParam("code") String code, @RequestParam("formDate") String formDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, formDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            USAStockData usaStockData = new USAStockData(new ObjectId(new Date()),
                    formDate,
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
        model.addAttribute("dataList", dataList);
        return "search/USAStockListByGraph";
    }

    @GetMapping("/search/shangzhengshenzheng/{code}")
    public String listShangZhengShenZhengOne(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/shangzhengshenzhengListOne";
    }

    @PostMapping("/search/shangzhengshenzheng")
    public String listShangZhengShenZhengOneMore(@RequestParam("code") String code, @RequestParam("formDate") String formDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(code, formDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            ShangZhengShenZhengData shangZhengShenZhengData = new ShangZhengShenZhengData(new ObjectId(new Date()),
                    formDate,
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
        model.addAttribute("dataList", dataList);
        return "search/shangzhengshenzhengListOne";
    }

    @GetMapping("/search/shangzhengshenzheng/graph/{code}")
    public String listShangZhengShenZhengByGraph(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/shangzhengshenzhengListByGraph";
    }

    @PostMapping("/search/shangzhengshenzheng/graph")
    public String listShangZhengShenZhengByGraphOneMore(@RequestParam("code") String code, @RequestParam("formDate") String formDate, @RequestParam("toDate") String toDate, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;

        List<ShangZhengShenZhengData> dataList = shangZhengShenZhengDataDao.findDataByCodeOrName(code, formDate, toDate, pageNum, pageSize);
        if (dataList.size() == 0) {
            ShangZhengShenZhengData shangZhengShenZhengData = new ShangZhengShenZhengData(new ObjectId(new Date()),
                    formDate,
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
        model.addAttribute("dataList", dataList);
        return "search/shangzhengshenzhengListByGraph";
    }
}

package com.example.demo.controller;

import com.example.demo.dao.GuPiaoDataDao;
import com.example.demo.dao.JiJinDataDao;
import com.example.demo.dao.USAStockDataDao;
import com.example.demo.entities.GuPiaoData;
import com.example.demo.entities.JiJinData;
import com.example.demo.entities.USAStockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private GuPiaoDataDao guPiaoDataDao;

    @Autowired
    private JiJinDataDao jiJinDataDao;

    @Autowired
    private USAStockDataDao usaStockDataDao;

    @PostMapping("/search")
    public String listAll(@RequestParam("input") String input, Model model){
        int pageNum = 1;
        int pageSize = Integer.MAX_VALUE;
        String[] ss = input.split("\\+");
        List dataList = null;

        if (ss[0].equals("A股")) {
            if (ss.length == 1)
                dataList = guPiaoDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = guPiaoDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else
                dataList = guPiaoDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
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
            model.addAttribute("dataList", dataList);
            return "search/USAStockListAll";
        }
        else if (ss[0].equals("基金")){
            if (ss.length == 1)
                dataList = jiJinDataDao.findDataByRegex("", "comprehensive", pageNum, pageSize);
            else if (ss.length == 2)
                dataList = jiJinDataDao.findDataByRegex(ss[1], "comprehensive", pageNum, pageSize);
            else
                dataList = jiJinDataDao.findDataByRegex(ss[1], ss[2], pageNum, pageSize);
            model.addAttribute("dataList", dataList);
            return "search/jijinListAll";
        }
        else
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

    @GetMapping("/search/gupiao/graph/{code}")
    public String listGPByGraph(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<GuPiaoData> dataList = guPiaoDataDao.findDataByCodeOrName(code, pageNum, pageSize);
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

    @GetMapping("/search/jijin/graph/{code}")
    public String listJJByGraph(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<JiJinData> dataList = jiJinDataDao.findDataByCodeOrName(code, pageNum, pageSize);
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

    @GetMapping("/search/us/graph/{code}")
    public String listUSByGraph(@PathVariable("code") String code, Model model){
        int pageNum = 1;
        int pageSize = 30;

        List<USAStockData> dataList = usaStockDataDao.findDataByCodeOrName(code, pageNum, pageSize);
        model.addAttribute("dataList", dataList);
        return "search/USAStockListByGraph";
    }
}

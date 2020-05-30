package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.entities.ShangZhengShenZhengData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GP_JJ_Sum_Controller {

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

    @GetMapping("/gpjjlist")
    public String list(Model model) {
        Long gpCount = guPiaoDataDao.guPiaoCount();
        Long usCount = usaStockDataDao.guPiaoCount();
        Long jjCount = jiJinDataDao.jiJinCount();
        Long ufCount = usaFundDataDao.jiJinCount();
        Long shangzhengCount = shangZhengShenZhengDataDao.ShangZhengCount();
        Long shenzhengCount = shangZhengShenZhengDataDao.ShenZhengCount();
        model.addAttribute("gpCount", gpCount);
        model.addAttribute("usCount", usCount);
        model.addAttribute("jjCount", jjCount);
        model.addAttribute("ufCount", ufCount);
        model.addAttribute("shangzhengCount", shangzhengCount);
        model.addAttribute("shenzhengCount", shenzhengCount);
        return "dashboard";
    }
}

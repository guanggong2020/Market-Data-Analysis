package com.example.demo.filter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class XssFilterUtil {
    /**
     * 使用自带的 basicWithImages 白名单，允许的便签有 a,b,blockquote,br,cite,code,
     * dd,dl,dt,em,i,li,ol,p,pre,q,small,span,strike,strong,sub,sup,u,ul,img
     * 以及 a 标签的 href,img 标签的 src,align,alt,height,width,title 属性
     */
    private static final Whitelist whitelist = Whitelist.basicWithImages();
    // 配置过滤化参数,不对代码进行格式化
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    /*
      富文本编辑时一些样式是使用 style 来进行实现的
      比如红色字体 style="color:red;"
      所以需要给所有标签添加 style 属性
     */
    static {
        whitelist.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }
}

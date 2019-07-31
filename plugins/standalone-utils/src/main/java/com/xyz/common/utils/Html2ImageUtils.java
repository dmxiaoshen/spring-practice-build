package com.xyz.common.utils;


import gui.ava.html.Html2Image;
import gui.ava.html.renderer.ImageRenderer;
import org.w3c.dom.Document;

import java.io.File;

public class Html2ImageUtils {

    public static void main(String[] args) throws Exception {

        //String htmlstr = "<p>/**<br />&nbsp;* <br />&nbsp;*/<br />package com.cxsoft.rap.ed.util;没有中文这一说</p><img src='https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556623960436&di=df0fdeadf5dc975540afc28cf2f845fc&imgtype=0&src=http%3A%2F%2Ffmn.rrimg.com%2Ffmn064%2Fxiaozhan%2F20130227%2F2235%2Fp%2Fm2w614hq85lt_xlarge_yase_06af000125b31260.jpg' />";
        //Html2Image html2Image = Html2Image.fromURL(new URL("https://ymliusu.com/"));
        //Html2Image html2Image = Html2Image.fromHtml(htmlstr);
        Html2Image html2Image = Html2Image.fromFile(new File("d:\\1cc57f65681d4127be037dfc48716a21.html"));
        Document d = html2Image.getParser().getDocument();
        html2Image.getImageRenderer().saveImage("d:/aaaa.jpg");
        //html2Image.getPdfRenderer().saveToPDF("d://ymliusu.pdf");

    }

    public static void test9() {
//        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
//        //Html2Image html2Image = Html2Image.fromURI(URI.create("https://ymliusu.com"));
//        //String htmlstr = "<table width='654' cellpadding='0' cellspacing='0' bordercolor='#FFFFFF'><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr></table>";
//        imageGenerator.loadUrl("https://ymliusu.com");
//        imageGenerator.getBufferedImage();
//        imageGenerator.saveAsImage("d:/hello-world.png");
//        imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
//        //html2Image.getImageRenderer().saveImage("d:/ymliusu.png");
    }

    public static String converHtml2Image(String htmlFile,String tempDir,String imageName,int... width){
        Html2Image html2Image = Html2Image.fromFile(new File(htmlFile));
        Document document = html2Image.getParser().getDocument();
        String imageFile = tempDir+File.separator+imageName;
        ImageRenderer renderer = html2Image.getImageRenderer();
        if(width.length>0){
            renderer.setWidth(width[0]);
        }
        renderer.saveImage(imageFile);
        return imageFile;
    }
}

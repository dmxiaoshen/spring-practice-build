package com.xiaouchina.common.utils;


import cz.vutbr.web.css.MediaSpec;
import org.fit.cssbox.css.CSSNorm;
import org.fit.cssbox.css.DOMAnalyzer;
import org.fit.cssbox.demo.ImageRenderer;
import org.fit.cssbox.io.DOMSource;
import org.fit.cssbox.io.DefaultDOMSource;
import org.fit.cssbox.io.DefaultDocumentSource;
import org.fit.cssbox.io.DocumentSource;
import org.fit.cssbox.layout.BrowserCanvas;
import org.fit.cssbox.layout.BrowserConfig;
import org.fit.cssbox.layout.Viewport;
import org.fit.cssbox.render.SVGRenderer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.URL;

public class CssboxUtils {

    public static String renderHtml2ImagePng(String url,String tempDir,String imageName,Dimension dimension){
        CssboxUtils render = new CssboxUtils();
        if(dimension==null){
            dimension = new Dimension(1200,600);
        }
        render.setWindowSize(dimension,false);
        try {
            String imageFile = tempDir+File.separator+imageName;
            render.renderURL(url, new FileOutputStream(new File(imageFile)), ImageRenderer.Type.PNG);
            return imageFile;
        }catch (Exception e){
            throw new RuntimeException("cssbox render image error",e);
        }

    }
    public static void main(String[] args) {
        CssboxUtils render = new CssboxUtils();
        render.setWindowSize(new Dimension(359, 600), false);
        String url = "https://qiniu.xblz.org/61228769620c4ac7afda1cc85367909f.html?e=1560929654&token=U691UmTFuzccWD0qzR6A2Xbn5OwzPou_o3UoO8Dt:pWTssmgn_iwdGlJDnBnLiw0G1Qo=";
        try {
            render.renderURL(url, new FileOutputStream(new File("D:" + File.separator + "ppp2.png")), ImageRenderer.Type.PNG);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    private String mediaType = "screen";
    private Dimension windowSize = new Dimension(1200, 600);
    private boolean cropWindow = false;
    private boolean loadImages = true;
    private boolean loadBackgroundImages = true;

    public CssboxUtils() {
    }

    public void setMediaType(String media) {
        this.mediaType = new String(media);
    }

    public void setWindowSize(Dimension size, boolean crop) {
        this.windowSize = new Dimension(size);
        this.cropWindow = crop;
    }

    public void setLoadImages(boolean content, boolean background) {
        this.loadImages = content;
        this.loadBackgroundImages = background;
    }

    public boolean renderURL(String urlstring, OutputStream out, ImageRenderer.Type type) throws IOException, SAXException {
        if (!urlstring.startsWith("http:") && !urlstring.startsWith("https:") && !urlstring.startsWith("ftp:") && !urlstring.startsWith("file:")) {
            urlstring = "http://" + urlstring;
        }

        DocumentSource docSource = new DefaultDocumentSource(urlstring);
        DOMSource parser = new DefaultDOMSource(docSource);
        parser.setContentType("text/html; charset=utf-8");
        Document doc = parser.parse();
        MediaSpec media = new MediaSpec(this.mediaType);
        media.setDimensions((float)this.windowSize.width, (float)this.windowSize.height);
        media.setDeviceDimensions((float)this.windowSize.width, (float)this.windowSize.height);
        DOMAnalyzer da = new DOMAnalyzer(doc, docSource.getURL());
        da.setMediaSpec(media);
        da.attributesToStyles();
        da.addStyleSheet((URL)null, CSSNorm.stdStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.addStyleSheet((URL)null, CSSNorm.userStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.addStyleSheet((URL)null, CSSNorm.formsStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.getStyleSheets();
        BrowserCanvas contentCanvas = new BrowserCanvas(da.getRoot(), da, docSource.getURL());
        contentCanvas.setAutoMediaUpdate(false);
        contentCanvas.getConfig().setClipViewport(this.cropWindow);
        contentCanvas.getConfig().setLoadImages(this.loadImages);
        contentCanvas.getConfig().setLoadBackgroundImages(this.loadBackgroundImages);
        if (type == ImageRenderer.Type.PNG) {
            contentCanvas.createLayout(this.windowSize);
            ImageIO.write(contentCanvas.getImage(), "png", out);
        } else if (type == ImageRenderer.Type.SVG) {
            this.setDefaultFonts(contentCanvas.getConfig());
            contentCanvas.createLayout(this.windowSize);
            Writer w = new OutputStreamWriter(out, "utf-8");
            this.writeSVG(contentCanvas.getViewport(), w);
            w.close();
        }

        docSource.close();
        return true;
    }

    protected void setDefaultFonts(BrowserConfig config) {
        config.setDefaultFont("Serif", "Times New Roman");
        config.setDefaultFont("SansSerif", "Arial");
        config.setDefaultFont("Monospaced", "Courier New");
        config.setDefaultFont("simsun", "simsun");
    }

    protected void writeSVG(Viewport vp, Writer out) throws IOException {
        int w = vp.getClippedContentBounds().width;
        int h = vp.getClippedContentBounds().height;
        SVGRenderer render = new SVGRenderer(w, h, out);
        vp.draw(render);
        render.close();
    }

//    public static void main(String[] args) {
//        if (args.length != 3) {
//            System.err.println("Usage: ImageRenderer <url> <output_file> <format>");
//            System.err.println();
//            System.err.println("Renders a document at the specified URL and stores the document image");
//            System.err.println("to the specified file.");
//            System.err.println("Supported formats:");
//            System.err.println("png: a Portable Network Graphics file (bitmap image)");
//            System.err.println("svg: a SVG file (vector image)");
//            System.exit(0);
//        }
//
//        try {
//            ImageRenderer.Type type = null;
//            if (args[2].equalsIgnoreCase("png")) {
//                type = ImageRenderer.Type.PNG;
//            } else if (args[2].equalsIgnoreCase("svg")) {
//                type = ImageRenderer.Type.SVG;
//            } else {
//                System.err.println("Error: unknown format");
//                System.exit(0);
//            }
//
//            FileOutputStream os = new FileOutputStream(args[1]);
//            ImageRenderer r = new ImageRenderer();
//            r.renderURL(args[0], os, type);
//            os.close();
//            System.err.println("Done.");
//        } catch (Exception var4) {
//            System.err.println("Error: " + var4.getMessage());
//        }

    //}

    public static enum Type {
        PNG,
        SVG;

        private Type() {
        }
    }
}

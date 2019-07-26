package com.xiaouchina.common.utils;

import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XhtmlToImageUtils {

    public static void main(String[] args) throws Exception{
        String inputFile = "D:\\927e53d68a4749a7912f0d58a5eb46cb.html";
        String outFile = "D:\\aasaa.jpg";
        convertXhtmlToImage(inputFile,outFile,1024,768);
    }

    public static File convertXhtmlToImage(String inputFilename,String outputFilename,int widthImage,int heightImage)throws IOException {
        //将html转成文件
//        FileWriter writer = new FileWriter(inputFilename);
//        writer.write(html);
//        writer.flush();
//        writer.close();
        //将xhtml文件转成图片
        final File f = new File(inputFilename);
        final Java2DRenderer renderer = new Java2DRenderer(f, widthImage, heightImage);
        final BufferedImage img = renderer.getImage();
        final FSImageWriter imageWriter = new FSImageWriter();
        imageWriter.setWriteCompressionQuality(0.9f);
        imageWriter.write(img, outputFilename);
        final File fout = new File(outputFilename);
        return fout;
    }
}

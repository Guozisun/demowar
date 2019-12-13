package com.sun.demo.thumbnailator;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: demo
 * Author: 果子
 * Create Time:2019/12/13 09:03
 */
public class TestDemo {
    public static void main(String[] args){
        //测试图片地址
        String picPath = "C:/Users/果子/Desktop/ceshi.jpg";
//        String newPicPath = compressPic(picPath,400,300,Double.valueOf(4)/Double.valueOf(3));
//        //s输出地址
//        System.out.println(newPicPath);
        File file = new File(picPath);
        System.out.println("ddd:"+file.length());
        double aa = (double)16/9;
        System.out.println("sss:"+aa);
//        String toPath ="D:/testpic/ceshi_app2.jpg";
//        File file = new File(toPath);
//        if (!file.delete()){
//            System.out.println("ssss");
//        }
//        if ()
//        try {
//            Thumbnails.of(picPath)
//                    .scale(1f)
//                    .outputQuality(0.5f)
//                    .toFile(toPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static String compressPic(String picPath, Integer width, Integer high,double picScale){
        File filePath = new File(picPath);
        int picHigh=0;
        int picWidth=0;
        //文件是否存在
        if (!filePath.exists()){
            System.out.println("11");
            return "";
        }
        //获取图片高和宽
        try {
            BufferedImage bufferedImage = ImageIO.read(filePath);
            picHigh=bufferedImage.getHeight();
            picWidth=bufferedImage.getWidth();
        } catch (Exception e) {
//            logger.error("ImageIo读取文件失败！", e);
            System.out.println("sss");
        }
        if (picHigh<=high&&picWidth<=width&&Double.valueOf(picWidth)/Double.valueOf(picHigh)==picScale){
            //图片名称
            String pcPicFileName = filePath.getName();
            //图片去除名称外的路径
            String pcPicPath = picPath.replace(pcPicFileName, "");
            //不带图片后缀的图片名称
            String picNamePre = pcPicFileName.substring(0, pcPicFileName.lastIndexOf('.'));
            String newPicName = picNamePre + "_app";
            //压缩后的app端图片地址
            String newPicUrl = pcPicPath + newPicName + ".jpg";
            File appFile = new File(newPicUrl);
            if (!appFile.exists()) {
                try {
                    Thumbnails.of(filePath)
                            .scale(1f)
                            .outputQuality(0.5f)
                            .outputFormat("jpg")
                            .toFile(new File(pcPicPath + newPicName));
                } catch (Exception ex) {
//                    logger.error("生成app图片失败：", ex);
                }
            }
//            newPicUrl = newPicUrl.replace(constants.getUploadPath(), constants.getNginxPath());
            return newPicUrl;
        }else {
//            火腿霞
//            if (!filePath.delete()){
//                System.out.println("111");
////                logger.error("文件删除失败");
//                return "";
//            }
            return "";
        }
    }
}

package com.pltfhs.car.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MultimediaUtil {

    @Autowired
    ServletContext context;

    private static String basePath = getRealPath();

    public static String uploadPhoto(byte[] file, String type) {
        String UPLOADED_FOLDER = basePath + File.separator + Config.IMAGES_DIR
                + File.separator + type
                + File.separator;

        File f = new File(UPLOADED_FOLDER);
        // if directory exists use the path if not make directory
        if (!f.exists()) {
            f.mkdirs();
        }

        String extension = ".png";
        byte[] bytes = file;

        Random r = new Random();
        String fileName = "" + r.nextInt(1001) + System.currentTimeMillis() + extension;

        Path path = Paths.get(UPLOADED_FOLDER + fileName);

        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(UPLOADED_FOLDER + fileName);
        return Config.IMAGES_DIR + "/" + type + "/" + fileName;
    }

    public static String uploadPhoto(MultipartFile file, String type) {
        try {
            return uploadPhoto(file.getBytes(), type);
        } catch (IOException ex) {
            Logger.getLogger(MultimediaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String uploadFile(MultipartFile file, String type) {
        try {
            String UPLOADED_FOLDER = basePath + File.separator + Config.IMAGES_DIR
                    + File.separator + type
                    + File.separator;

            File f = new File(UPLOADED_FOLDER);
            // if directory exists use the path if not make directory
            if (!f.exists()) {
                f.mkdir();
            }
            String[] splited = file.getOriginalFilename().split("\\.");
            String extension = splited[splited.length - 1];
            byte[] bytes = file.getBytes();

            Random r = new Random();
            String fileName = "" + r.nextInt(1001) + System.currentTimeMillis() + "." + extension;

            Path path = Paths.get(UPLOADED_FOLDER + fileName);

            try {
                Files.write(path, bytes);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println(UPLOADED_FOLDER + fileName);
            return Config.IMAGES_DIR + "/" + type + "/" + fileName;
        } catch (IOException ex) {
            Logger.getLogger(MultimediaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String uploadVideo(MultipartFile videoFile) {
        String UPLOADED_FOLDER = basePath + File.separator + Config.VIDEOS_DIR
                + File.separator + Config.getCurrentDate() + File.separator;
        String video_name;
        File f = new File(UPLOADED_FOLDER);
        if (!f.exists()) {
            f.mkdir();
        }
        byte[] bytes;
        try {
            //Save Video
            video_name = Config.getCurrentDate() + "-"
                    + Config.getCurrentTime(Config.FILE_TIME_FORMATE) + "-" + videoFile.getOriginalFilename();
            bytes = videoFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + video_name);
            Files.write(path, bytes);
            //End Saving Video
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return Config.SAVING_VIDEO_ERROR;
        }
        return Config.VIDEOS_DIR + "/" + Config.getCurrentDate() + "/" + video_name;
    }

    public static String uploadVideoImage(MultipartFile imageFile) {
        String UPLOADED_FOLDER = basePath + File.separator + Config.VIDEOS_DIR
                + File.separator + Config.getCurrentDate() + File.separator;
        String videoImageName;
        File f = new File(UPLOADED_FOLDER);
        if (!f.exists()) {
            f.mkdir();
        }
        String UPLOAD_VIDEO_IMAGE = UPLOADED_FOLDER + Config.IMAGES_DIR + File.separator;
        f = new File(UPLOAD_VIDEO_IMAGE);
        if (!f.exists()) {
            f.mkdir();
        }
        byte[] bytes;
        try {
            //Save video image
            videoImageName = Config.getCurrentDate() + "-"
                    + Config.getCurrentTime(Config.FILE_TIME_FORMATE) + "-" + imageFile.getOriginalFilename();
            bytes = imageFile.getBytes();
            Path path = Paths.get(UPLOAD_VIDEO_IMAGE + videoImageName);
            Files.write(path, bytes);
            //End saving video image
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return Config.SAVING_VIDEO_ERROR;
        }
        return Config.VIDEOS_DIR + "/" + Config.getCurrentDate() + "/" + Config.IMAGES_DIR + "/" + videoImageName;
    }

    public static boolean deleteVideo(String videoPath, String imagePath) {
        String realVideoPath = basePath + videoPath;
        File videoFile = new File(realVideoPath);
        if (videoFile.exists()) {
            if (videoFile.delete()) {
                return deleteImage(imagePath);
            }
        }
        return false;
    }

    public static boolean deleteImage(String imagePath) {
        String realImagePath = basePath + File.separator + imagePath;
        File imageFile = new File(realImagePath);
        if (imageFile.exists()) {
            return imageFile.delete();
        }
        return false;
    }

    private static String getRealPath() {
        //if(Config.IS_APP_LIVE) {
        //	return "media/";
        //	}else {
        //return "src/main/resources/static";
//                return "src/main/resources/static";
//        return "/home/ubuntu";
        return "/var/www/html";
//    	return "/home/m_salah512";

        //}
    }

    public Resource loadFile(String filename, String type) {
        try {
            Path rootLocation = null;
            String UPLOADED_FOLDER = null;
            UPLOADED_FOLDER = basePath + File.separator + Config.IMAGES_DIR
                    + File.separator;
            String UPLOAD_FOLDER_TYPE = UPLOADED_FOLDER + type + File.separator;

            rootLocation = Paths.get(UPLOAD_FOLDER_TYPE);

            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }
}

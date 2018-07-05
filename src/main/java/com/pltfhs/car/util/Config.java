package com.pltfhs.car.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Config {

    public static final boolean IS_APP_LIVE = true;
    public static final Integer NEWS_TYPE_DEFAULT = 1;
    public static final Integer STAD_VODAFONE_TOURNAMENT_TYPE = 1;
    public static final Integer ACTIVE_PLAYERS = 1;
    public static final Integer NOT_ACTIVE_PLAYERS = 0;
    public static final Integer SUBSCRIBERS = 1;
    public static final Integer UNSUBSCRIBERS = 2;
    public static final Integer VISIBLE_POLL = 1;
    public static final Integer UNVISIBLE_POLL = 0;
    public static final Integer ACTIVE_TRIVIA = 1;
    /**
     * *****************************************************************************************
     */
    /**
     * ***********************************ACCESS
     * CONFIG*****************************************
     */
    /**
     * *****************************************************************************************
     */
    // Bad Request 			(400);
    // Method Not Allowed 	(405);
    // Internal Server Error	(500);

    public static final String ACCESS_DENIED = "Access denied";
    public static final Integer ACCESS_DENIED_CODE = 403;
    public static final String INVALID_PAGE = "Invalid page value";
    public static final Integer INVALID_PAGE_CODE = 602;
    public static final String DATA_SAVING_ERROR = "Error while saving the data.";
    public static final Integer DATA_SAVING_ERROR_CODE = 601;
    public static final String ERROR_SAVING_IMAGE = "Error while saving the image";
    public static final Integer ERROR_SAVING_IMAGE_CODE = 603;
    public static final String ERROR_SAVING_VIDEO = "Error while saving the video";
    public static final Integer ERROR_SAVING_VIDEO_CODE = 604;
    public static final String INVALID_ORDER = "Invalid order value";
    public static final Integer INVALID_ORDER_CODE = 606;
    public static final String SUCCESS = "Success";
    public static final String UPDATE_SUCCESS = "Data updated successfully";
    public static final String DELETE_SUCCESS = "Data deleted successfully";
    public static final Integer SUCCESS_CODE = 0;
    public static final String BAD_REQUEST = "Bad Request";
    public static final Integer BAD_REQUEST_CODE = 400;
    public static final String INVALID_USER_STATUS = "Invalid user status";
    public static final Integer INVALID_USER_STATUS_CODE = 990;

    private static final String[] API_KEYS = {
        "1b5521b0-4604-4981-98c6-ad54338f3326", // CMS API_KEY
        "976fe720-9734-4d2a-8d1c-c4da177664a5", // EgyptLinx API_KEY
        "3ca32c3d-58d4-4123-bf81-342b2df7bc87" // Vodafone Portal API_KEY
    };
    //Need to be changed to the real IP's
    private static final String[] VALID_IP_ADDRESS = {
        " ", // My IP Address On LocalHost
        ":", // StadVodafone IP Address
        "." // StadVodafone IP Address
    };

    public static String[] getIPList() {
        return VALID_IP_ADDRESS;
    }

    public static String[] getApiKeys() {
        return API_KEYS;
    }
    /**
     * *****************************************************************************************
     */
    /**
     * ************************************MEDIA
     * CONFIG*****************************************
     */
    /**
     * *****************************************************************************************
     */
    public static final String SAVING_IMAGE_ERROR = "image_error";
    public static final String SAVING_VIDEO_ERROR = "video_error";
    public static final String IMAGES_DIR = "media";
    public static final String Brand_DIR = "brand";
    public static final String VIDEOS_DIR = "videos";

    /**
     * *****************************************************************************************
     */
    /**
     * *************************************TIME
     * CONFIG*****************************************
     */
    /**
     * *****************************************************************************************
     */
    public static final String FILE_TIME_FORMATE = "HH-mm-ss";
    public static final String TIME_FORMATE = "HH:mm:ss";

    public static String getCurrentDate() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public static String getCurrentTime(String formate) {
        String timeStamp = new SimpleDateFormat(formate).format(Calendar.getInstance().getTime());
        return timeStamp;
    }
    /**
     * *****************************************************************************************
     */
    /**
     * ***********************************Tracking
     * CONFIG***************************************
     */
    /**
     * *****************************************************************************************
     */
    public static final String ADD_TRACKING_TYPE = "added new";
    public static final String UPDATE_TRACKING_TYPE = "update";
    public static final String DELETE_TRACKING_TYPE = "delete";

    /**
     * *****************************************************************************************
     */
    /**
     * *********************************SUBSCRIPTION
     * CONFIG*************************************
     */
    /**
     * *****************************************************************************************
     */
    public static final String SUBSCRIBE = "subscribe";
    public static final String UNSUBSCRIBE = "unsubscribe";

    /**
     * HOW TO CONVERT FROM LIVE TO LOCAL First: convert the variable IS_APP_LIVE
     * at the top of this file to false. Second: got to
     * StaticResourceConfiguration and comment the class. Third: in the
     * application.properties change the password to root and uncomment
     * spring.jpa.show-sql=true **
     */
}

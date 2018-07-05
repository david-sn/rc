/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.NewsPostBlog;
import com.pltfhs.car.entity.Users;
import com.pltfhs.car.repository.NewsPosBlogtRepository;
import com.pltfhs.car.repository.UsersRepository;
import com.pltfhs.car.util.MultimediaUtil;
import com.pltfhs.car.view.NewsBlogPostTemplate;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Client 1
 */
@Service
public class NewsPostBlogService {

    Integer newsPostExpireDays;

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private NewsPosBlogtRepository newsPosBlogtRepository;

    public GeneralResponse<NewsPostBlog> addNewsPostBlog(String email, String title, String description, String subDescription, String label, MultipartFile coverImage, Boolean isPinned, MultipartFile attache1, MultipartFile attache2, MultipartFile attache3, Integer blogId, short type) {
        GeneralResponse<NewsPostBlog> response = null;

        Users userDB = usersRepository.findByEmail(email);
        if (userDB != null) {
            NewsPostBlog news = new NewsPostBlog();
            news.setAddedByUserId(BigInteger.valueOf(userDB.getUserId()));
            news.setIsDeleted(false);
            news.setCode(UUID.randomUUID().toString().replaceAll("-", ""));
            news.setCreationDate(new Date());
            if (newsPostExpireDays != null) {
                Calendar newsExp = Calendar.getInstance();
                newsExp.add(Calendar.DATE, newsPostExpireDays);

                news.setExpireDate(newsExp.getTime());
            }
            news.setLabel(label);
            news.setIsPinned(isPinned);
            news.setDescription(description);
            news.setSubDescription(subDescription);
            news.setTitle(title);
            news.setTypeId(type);

            if (coverImage != null) {
                news.setCoverImg(MultimediaUtil.uploadPhoto(coverImage, "social"));
            }
            if (attache1 != null) {
                news.setAttache1(MultimediaUtil.uploadFile(attache1, "social"));
            }
            if (attache2 != null) {
                news.setAttache2(MultimediaUtil.uploadFile(attache2, "social"));
            }
            if (attache3 != null) {
                news.setAttache3(MultimediaUtil.uploadFile(attache3, "social"
                        + ""));
            }

            if (blogId != null) {
                news.setBlogId(blogId);
            }
            NewsPostBlog savedNews = newsPosBlogtRepository.save(news);
            response = new GeneralResponse<>(StatusCode.OK, savedNews);
        } else {
            response = new GeneralResponse<>(StatusCode.USER_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse<NewsPostBlog> updateNewsPostBlog(String email, long newsPostId, String title, String description, String subDescription, String label, MultipartFile coverImage, Boolean isPinned, MultipartFile attache1, MultipartFile attache2, MultipartFile attache3) {
        GeneralResponse<NewsPostBlog> response = null;

        Users userDB = usersRepository.findByEmail(email);
        if (userDB != null) {
            NewsPostBlog newsPostDB = newsPosBlogtRepository.findByDbidAndIsDeletedIsFalse(newsPostId);
            if (newsPostDB != null) {
                newsPostDB.setLabel(label);
                newsPostDB.setIsPinned(isPinned);
                newsPostDB.setDescription(description);
                newsPostDB.setSubDescription(subDescription);
                newsPostDB.setTitle(title);
                if (coverImage != null) {
                    newsPostDB.setCoverImg(MultimediaUtil.uploadPhoto(coverImage, "social"));
                }
                if (attache1 != null) {
                    newsPostDB.setAttache1(MultimediaUtil.uploadPhoto(attache1, "social"));
                }
                if (attache2 != null) {
                    newsPostDB.setAttache2(MultimediaUtil.uploadPhoto(attache2, "social"));
                }
                if (attache3 != null) {
                    newsPostDB.setAttache3(MultimediaUtil.uploadPhoto(attache3, "social"));
                }
                NewsPostBlog updateNews = newsPosBlogtRepository.saveAndFlush(newsPostDB);
                response = new GeneralResponse<>(StatusCode.OK, updateNews);
            } else {
                response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
            }
        } else {
            response = new GeneralResponse<>(StatusCode.USER_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse deleteNewsPostBlog(String email, long newsPostId) {
        GeneralResponse response = null;
        Users userDB = usersRepository.findByEmail(email);
        if (userDB != null) {
            NewsPostBlog newsPostDB = newsPosBlogtRepository.findByDbidAndIsDeletedIsFalse(newsPostId);
            if (newsPostDB != null) {
                newsPostDB.setIsDeleted(Boolean.TRUE);
                newsPosBlogtRepository.saveAndFlush(newsPostDB);
                response = new GeneralResponse<>(StatusCode.OK);
            } else {
                response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
            }
        } else {
            response = new GeneralResponse<>(StatusCode.USER_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse<List<NewsBlogPostTemplate>> listNewsPostBlog(int page, int noOfRows, Integer blogId, short type, byte language) {
        List<NewsPostBlog> result;
        if (blogId == null) {
            result = newsPosBlogtRepository.findByTypeIdAndIsDeletedIsFalse(type, new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "creationDate"));
        } else {
            result = newsPosBlogtRepository.findByTypeIdAndBlogIdAndIsDeletedIsFalse(type, blogId, new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "creationDate"));
        }
        List<NewsBlogPostTemplate> data = new ArrayList<>(result.size());
        result.forEach(npb -> {
            data.add(new NewsBlogPostTemplate(npb, language, type));
        });
        GeneralResponse response = new GeneralResponse<>(StatusCode.OK, data, newsPosBlogtRepository.countByIsDeletedIsFalseAndTypeId(type));
        return response;
    }

    public GeneralResponse findNewsPostBlogById(Long id, short typeId, byte language) {
        NewsPostBlog newsPostBlogDB = newsPosBlogtRepository.findByDbidAndTypeIdAndIsDeletedIsFalse(id, typeId);
        if (newsPostBlogDB != null) {
            return new GeneralResponse(StatusCode.OK, new NewsBlogPostTemplate(newsPostBlogDB, language, typeId));
        } else {
            return new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }

    }
}

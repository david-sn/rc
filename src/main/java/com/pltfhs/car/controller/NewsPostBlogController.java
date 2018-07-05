/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.NewsPostBlog;
import com.pltfhs.car.service.NewsPostBlogService;
import com.pltfhs.car.view.NewsBlogPostTemplate;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Client 1
 */
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class NewsPostBlogController {

    @Autowired
    private NewsPostBlogService newsPostService;

    //---------------------------News----------------------------
    @RequestMapping(value = "/addNews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse<NewsPostBlog> addNews(
            @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("subDescription") String subDescription,
            @RequestBody MultipartFile coverImg, @RequestBody MultipartFile attach1, @RequestBody MultipartFile attach2,
            Principal principal) {
        return newsPostService.addNewsPostBlog(
                principal.getName(),
                title,
                description,
                subDescription,
                null,
                coverImg,
                null,
                null,
                null,
                null,
                null,
                (short) 1
        );
    }

    @RequestMapping(value = "/updateNews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse<NewsPostBlog> updateNews(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("subDescription") String subDescription,
            @RequestParam("label") String label, @RequestParam("dbId") long dbId, @RequestBody MultipartFile coverImg,
            @RequestParam("isPinned") boolean isPinned, @RequestBody MultipartFile attache1,
            @RequestBody MultipartFile attache2, @RequestBody MultipartFile attache3, Principal principal) {
        return newsPostService.updateNewsPostBlog(
                principal.getName(),
                dbId,
                title,
                description,
                subDescription,
                label,
                coverImg,
                isPinned,
                attache1,
                attache2,
                attache3
        );
    }

    @RequestMapping("/deleteNews")
    public GeneralResponse deleteNews(@RequestBody NewsPostBlog body, Principal principal) {
        return newsPostService.deleteNewsPostBlog(
                principal.getName(),
                body.getDbid()
        );
    }

    @RequestMapping("/listNews")
    public GeneralResponse<List<NewsBlogPostTemplate>> listNews(@RequestBody GeneralParameter body) {
        return newsPostService.listNewsPostBlog(
                body.getPage(),
                body.getNoOfRows(),
                null,
                (short) 1,
                body.getLanguage()
        );
    }

    @RequestMapping("/findNewsById/{id}/{language}")
    public GeneralResponse findNewsById(@PathVariable("id") long newsId, @PathVariable("language") byte language, Principal principal) {
        return newsPostService.findNewsPostBlogById(newsId, (short) 1, language);
    }

    //---------------------------POSTS----------------------------
    @RequestMapping(value = "/addPosts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse<NewsPostBlog> addPosts(
            @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("subDescription") String subDescription,
            @RequestParam("label") String label, @RequestBody MultipartFile coverImg,
            @RequestParam("isPinned") boolean isPinned, @RequestBody MultipartFile attache1,
            @RequestBody MultipartFile attache2, @RequestBody MultipartFile attache3, @RequestParam("blogId") Integer blogId, Principal principal) {
        return newsPostService.addNewsPostBlog(
                principal.getName(),
                title,
                description,
                subDescription,
                label,
                coverImg,
                isPinned,
                attache1,
                attache2,
                attache3,
                blogId,
                (short) 2
        );
    }

    @RequestMapping(value = "/updatePosts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse<NewsPostBlog> updatePosts(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("subDescription") String subDescription,
            @RequestParam("label") String label, @RequestParam("dbId") long dbId, @RequestBody MultipartFile coverImg,
            @RequestParam("isPinned") boolean isPinned, @RequestBody MultipartFile attache1,
            @RequestBody MultipartFile attache2, @RequestBody MultipartFile attache3, Principal principal) {
        return newsPostService.updateNewsPostBlog(
                principal.getName(),
                dbId,
                title,
                description,
                subDescription,
                label,
                coverImg,
                isPinned,
                attache1,
                attache2,
                attache3
        );
    }

    @RequestMapping("/deletePosts")
    public GeneralResponse deletePosts(@RequestBody NewsPostBlog body, Principal principal) {
        return newsPostService.deleteNewsPostBlog(
                principal.getName(),
                body.getDbid()
        );
    }

    @RequestMapping("/listPosts")
    public GeneralResponse<List<NewsBlogPostTemplate>> listPosts(@RequestBody GeneralParameter body) {
        return newsPostService.listNewsPostBlog(
                body.getPage(),
                body.getNoOfRows(),
                body.getBlogId(),
                (short) 2,
                body.getLanguage()
        );
    }

    @RequestMapping("/findPostsById/{id}/{language}")
    public GeneralResponse findPostsById(@PathVariable("id") long postId, @PathVariable("language") byte language, Principal principal) {
        return newsPostService.findNewsPostBlogById(postId, (short) 2, language);
    }

    //---------------------------blogs----------------------------
    @RequestMapping(value = "/addBlog", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse<NewsPostBlog> addBlog(
            @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("subDescription") String subDescription,
            @RequestBody MultipartFile coverImg,
            Principal principal) {

        return newsPostService.addNewsPostBlog(
                principal.getName(),
                title,
                description,
                subDescription,
                null,
                coverImg,
                null,
                null,
                null,
                null,
                null,
                (short) 3
        );
    }

    @RequestMapping(value = "/updateBlog", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse<NewsPostBlog> updateBlog(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("subDescription") String subDescription,
            @RequestParam("dbid") long id, @RequestBody MultipartFile coverImg,
            Principal principal) {
        return newsPostService.updateNewsPostBlog(
                principal.getName(),
                id,
                title,
                description,
                subDescription,
                null,
                coverImg,
                null,
                null,
                null,
                null
        );
    }

    @RequestMapping("/deleteBlog")
    public GeneralResponse deleteBlog(@RequestBody NewsPostBlog body, Principal principal) {
        return newsPostService.deleteNewsPostBlog(
                principal.getName(),
                body.getDbid()
        );
    }

    @RequestMapping("/listBlog")
    public GeneralResponse<List<NewsBlogPostTemplate>> listBlog(@RequestBody GeneralParameter body) {
        return newsPostService.listNewsPostBlog(
                body.getPage(),
                body.getNoOfRows(),
                null,
                (short) 3,
                body.getLanguage()
        );
    }

    @RequestMapping("/findBlogById/{id}/{language}")
    public GeneralResponse findBlogById(@PathVariable("id") long blogId, @PathVariable("language") byte language, Principal principal) {
        return newsPostService.findNewsPostBlogById(blogId, (short) 3, language);
    }
}

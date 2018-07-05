/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.pltfhs.car.entity.Faqs;

/**
 *
 * @author Client 1
 */
public class FaqTemplate {

    private Long faqId;
    private String faqTitle;
    private String faqDescription;
    private Long creationDate;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    private String answer;
    private String[] tags;

    public FaqTemplate(Faqs faq, String[] tags) {
        this.faqId = faq.getFaqId();
        this.faqTitle = faq.getFaqTitle();
        this.faqDescription = faq.getFaqDescription();
        this.creationDate = faq.getCreationDate().getTime();
        this.answer1 = faq.getAnswer1();
        this.answer2 = faq.getAnswer2();
        this.answer3 = faq.getAnswer3();
        this.answer4 = faq.getAnswer4();
        this.answer5 = faq.getAnswer5();
        this.answer = this.answer1;
        this.tags = tags;

    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getFaqId() {
        return faqId;
    }

    public void setFaqId(Long faqId) {
        this.faqId = faqId;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqDescription() {
        return faqDescription;
    }

    public void setFaqDescription(String faqDescription) {
        this.faqDescription = faqDescription;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.FaqTags;
import com.pltfhs.car.entity.Faqs;
import com.pltfhs.car.entity.Users;
import com.pltfhs.car.repository.FagTagsRepository;
import com.pltfhs.car.repository.FaqsRepository;
import com.pltfhs.car.repository.UsersRepository;
import com.pltfhs.car.view.FaqTemplate;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Client 1
 */
@Service
public class FAQService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private FagTagsRepository fagTagsRepository;
    @Autowired
    private FaqsRepository faqsRepository;
    @Autowired
    private EmailService EmailService;

    public GeneralResponse<Faqs> addFAQuestion(String email, String title, String description, String[] tags, String answer) {
        GeneralResponse<Faqs> response;
        Users userDB = usersRepository.findByEmail(email);
        Calendar past15Day = Calendar.getInstance();
        past15Day.add(Calendar.DAY_OF_MONTH, -15);

        if (userDB != null) {
            Faqs isDublicateFound = faqsRepository.getIsFAqDublicate(title, description, BigInteger.valueOf(userDB.getUserId()), past15Day.getTime());
            if (isDublicateFound == null) {
                Faqs fags = new Faqs();
                fags.setCreationDate(new Date());
                fags.setFaqDescription(description);
                fags.setFaqTitle(title);
                fags.setUserId(BigInteger.valueOf(userDB.getUserId()));
                fags.setAnswer1(answer);
                Faqs faqsDB = faqsRepository.save(fags);
                if (tags != null) {
                    for (String tag : tags) {
                        FaqTags fagTags = new FaqTags();
                        fagTags.setTagValue(tag);
                        fagTags.setFaqId(BigInteger.valueOf(faqsDB.getFaqId()));
                        fagTagsRepository.save(fagTags);
                    }
                }
                response = new GeneralResponse<>(StatusCode.OK, faqsDB);
            }//end dublicATIOn period
            else {
                //dublication found
                response = new GeneralResponse<>(StatusCode.DATA_DUBLICATE);
            }
        } else {
            //user not found
            response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse<Faqs> addFAQAnswer(long questionId, String answer) {
        GeneralResponse<Faqs> response = null;

        Faqs faqDB = faqsRepository.findOne(questionId);

        if (faqDB != null) {
            Users user = usersRepository.findOne(faqDB.getUserId().longValue());
            if (faqDB.getAnswer1() == null) {
                faqDB.setAnswer1(answer);
            } else if (faqDB.getAnswer2() == null) {
                faqDB.setAnswer2(answer);
            } else if (faqDB.getAnswer3() == null) {
                faqDB.setAnswer3(answer);
            } else if (faqDB.getAnswer4() == null) {
                faqDB.setAnswer4(answer);
            } else {//5
                faqDB.setAnswer5(answer);
            }
            Faqs updatedQuestion = faqsRepository.saveAndFlush(faqDB);
            EmailService.sendMail(user.getEmail(), "Your Question is Answered", answer);
            response = new GeneralResponse<>(StatusCode.OK, updatedQuestion);
        } else {
            //FQA not found
            response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse<List<Faqs>> listAllQuestion(int page, int noOfRows) {
        List<Faqs> result = faqsRepository.findAll(new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "creationDate")).getContent();
        GeneralResponse<List<Faqs>> response = new GeneralResponse(StatusCode.OK, result, faqsRepository.count());
        return response;
    }

    public GeneralResponse updateFaqQuestion(long faqId, String title, String description, String answer, String[] newTags, String[] deletedTags) {
        GeneralResponse response;
        Faqs faqDB = faqsRepository.findOne(faqId);
        if (faqDB == null) {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        } else {
            faqDB.setAnswer1(answer);
            faqDB.setFaqDescription(description);
            faqDB.setFaqTitle(title);
            if (deletedTags != null) {
                for (int i = 0; i < deletedTags.length; i++) {
                    fagTagsRepository.deleteByTagValue(deletedTags[i]);
                }
            }
            if (newTags != null) {
                for (int i = 0; i < newTags.length; i++) {
                    FaqTags fagTags = new FaqTags();
                    fagTags.setTagValue(newTags[i]);
                    fagTags.setFaqId(BigInteger.valueOf(faqDB.getFaqId()));
                    fagTagsRepository.save(fagTags);
                }
            }
            faqsRepository.saveAndFlush(faqDB);
            response = new GeneralResponse(StatusCode.OK, faqDB);
        }
        return response;
    }

    public GeneralResponse deleteFaq(long faqId) {
        Faqs faqDB = faqsRepository.findOne(faqId);
        if (faqDB == null) {
            return new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }
        faqsRepository.delete(faqDB);
        return new GeneralResponse(StatusCode.OK);
    }

    public GeneralResponse getFaqById(long id) {
        Faqs faqDB = faqsRepository.findOne(id);
        List<FaqTags> tags = fagTagsRepository.findByFaqId(BigInteger.valueOf(id));
        String tagsVal[] = new String[tags.size()];
        for (int i = 0; i < tags.size(); i++) {
            tagsVal[i] = tags.get(i).getTagValue();
        }

        return new GeneralResponse(StatusCode.OK, new FaqTemplate(faqDB, tagsVal));

    }
}

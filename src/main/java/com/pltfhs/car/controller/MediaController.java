package com.pltfhs.car.controller;

import com.pltfhs.car.util.MultimediaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaController {

    @Autowired
    private MultimediaUtil multimediaUtil;

    @ResponseBody
    @RequestMapping(value = "/media/brand/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getBrandImage(@PathVariable("id") String id) {
        Resource file = multimediaUtil.loadFile(id, "brand");
        //return ResponseEntity.ok()
        //		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        //		.body(file);
        return ResponseEntity.ok(file);
    }

    @ResponseBody
    @RequestMapping(value = "/media/model/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getModelImage(@PathVariable("id") String id) {
        Resource file = multimediaUtil.loadFile(id, "model");
        //return ResponseEntity.ok()
        //		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        //		.body(file);
        return ResponseEntity.ok(file);
    }

    @ResponseBody
    @RequestMapping(value = "/media/car/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getCarImage(@PathVariable("id") String id) {
        Resource file = multimediaUtil.loadFile(id, "car");
        //return ResponseEntity.ok()
        //		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        //		.body(file);
        return ResponseEntity.ok(file);
    }

    @ResponseBody
    @RequestMapping(value = "/media/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getMediaImage(@PathVariable("id") String id) {
        Resource file = multimediaUtil.loadFile(id, "media");
        //return ResponseEntity.ok()
        //		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        //		.body(file);
        return ResponseEntity.ok(file);
    }

    @ResponseBody
    @RequestMapping(value = "/media/social/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getSocialImage(@PathVariable("id") String id) {
        Resource file = multimediaUtil.loadFile(id, "social");
        //return ResponseEntity.ok()
        //		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        //		.body(file);
        return ResponseEntity.ok(file);
    }

    @ResponseBody
    @RequestMapping(value = "media/carCatalog/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> carCatalog(@PathVariable("id") String id) {
        Resource file = multimediaUtil.loadFile(id, "carCatalog");
        //return ResponseEntity.ok()
        //		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        //		.body(file);
        return ResponseEntity.ok(file);
    }

    @ResponseBody
    @RequestMapping(value = "/media/profile/{id:.+}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> userProfile(@PathVariable("id") String id) {
        Resource file = multimediaUtil.loadFile(id, "profile");
        //return ResponseEntity.ok()
        //		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        //		.body(file);
        return ResponseEntity.ok(file);
    }

}

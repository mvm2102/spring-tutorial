package org.baeldung.web.controller.mediatypes;

import org.baeldung.web.dto.BaeldungItem;
import org.baeldung.web.dto.BaeldungItemSecondVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = "application/vnd.baeldung.api.v1+json")
public class CustomMediaTypeController {

    @RequestMapping(method = RequestMethod.GET, value = "/public/api/endpoint", produces = "application/vnd.baeldung.api.v1+json")
    public @ResponseBody BaeldungItem getItem() {
        return new BaeldungItem("itemId1");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/public/api/endpoint", produces = "application/vnd.baeldung.api.v2+json")
    public @ResponseBody BaeldungItemSecondVersion getItemSecondAPIVersion() {
        return new BaeldungItemSecondVersion("itemName");
    }
}

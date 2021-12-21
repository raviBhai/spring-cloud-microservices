package com.in28minutes.rest.webservices.restfulwebservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {


    // ******************************************************************************************
    //URI Versioning

    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("ravi agrawal");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("ravi", " agrawal"));
    }

    // ******************************************************************************************
    //Request parameter Versioning

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("ravi agrawal");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("ravi", " agrawal"));
    }

    // ******************************************************************************************
    //header Versioning

/*
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("ravi agrawal");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("ravi", " agrawal"));
    }
*/

    // ******************************************************************************************
    //MIME type Versioning

/*
    @GetMapping(value = "/person/produces", produces = "application/ravi.company-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("ravi agrawal");
    }

    @GetMapping(value = "/person/produces", produces = "application/ravi.company-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("ravi", " agrawal"));
    }
*/
}

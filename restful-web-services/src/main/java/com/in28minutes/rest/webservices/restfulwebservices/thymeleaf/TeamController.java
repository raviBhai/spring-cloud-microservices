package com.in28minutes.rest.webservices.restfulwebservices.thymeleaf;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TeamController {

    @GetMapping("/menu")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main-menu");
        modelAndView.addObject("randomId", "12345");
        List<Integer> userIds = Arrays.asList(1,2,3);
        modelAndView.addObject("userIds", userIds);
        return modelAndView;
    }

    @GetMapping("/ajaxPage")
    public String ajax(@RequestParam(value = "ajaxId", required = false) String ajaxId) {
        System.out.println("*************************** + ajaxId --- " + ajaxId);
        return "ajax";
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Team team) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("team-data");
        modelAndView.addObject("team", team);
        return modelAndView;
    }

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(
            @Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        Team team1 = new Team();
        team1.setName("team_1");
        team1.setDepartment("dept_1");

        Team team2 = new Team();
        team2.setName("team_2");
        team2.setDepartment("dept_2");

        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        if (teams.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(teams);

        return ResponseEntity.ok(result);

    }

}

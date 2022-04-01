package vttp2022.ssf.assessment.videosearch.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.assessment.videosearch.models.Game;
import vttp2022.ssf.assessment.videosearch.models.GameCreation;
import vttp2022.ssf.assessment.videosearch.service.SearchService;

@Controller
@RequestMapping(path="/search")
public class SearchController {
    
    @GetMapping
    public String search(
        @RequestParam (name="searchString") String searchString, 
        @RequestParam (name="count") Integer count, 
        Model model){
        
        System.out.printf("COUNT123>>>>>>>>>>>>>>%s",count);
        System.out.printf("searchString123>>>>>>>>>>>>>>%s",searchString);
        
        SearchService searchsvc = new SearchService();
        
        List<GameCreation> games = new LinkedList<>();
        games = searchsvc.search(searchString,count);
        System.out.printf("RESULT123>>>>>>>>>>>>>>%s",games);
        model.addAttribute("games", games);
        
        return "results";
    }


}

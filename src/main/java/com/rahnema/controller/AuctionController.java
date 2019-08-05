package com.rahnema.controller;

import com.rahnema.domain.AuctionDomain;
import com.rahnema.model.Auction;
import com.rahnema.model.Category;
import com.rahnema.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAuction(@RequestBody AuctionDomain auction, @PathVariable long id) {
        auctionService.addAuction(auction, id);
        return "a auction added";
    }


    @GetMapping("/getOne/{id}")
    public Optional<Auction> getOneAuction(long id){
        return auctionService.getAuction(id);
    }

    @GetMapping("/category")
    public List getCategories(){
        Category category = Category.DIGITAL_GOODS;
        return category.getCategories();

    }
}

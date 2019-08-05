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

    @PostMapping("/create/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAuction(@RequestBody AuctionDomain auction, @PathVariable long id) {
        auctionService.addAuction(auction, id);
        return "an auction added";
    }


    @GetMapping("/{id}")
    public Optional<Auction> getOneAuction(@PathVariable long id){
        return auctionService.getAuction(id);
    }

    @GetMapping("/category")
    public List getCategories(){
        // change
        Category category = Category.DIGITAL_GOODS;
        return category.getCategories();


    }

    @PutMapping("/winner/{auction_id}/{user_id}")
    public String setWinner(@PathVariable long auction_id, @PathVariable long user_id){
        auctionService.settWinner(auction_id, user_id);
        return "a user with id: " + user_id + " won action with id: " + auction_id;
    }

}

package com.rahnema.controller;

import com.rahnema.domain.AuctionDomain;
import com.rahnema.domain.AuctionInfoDomain;
import com.rahnema.domain.CategoryDomain;
import com.rahnema.domain.HomepageDomain;
import com.rahnema.model.Auction;
import com.rahnema.model.Category;
import com.rahnema.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping("/create/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AuctionDomain addAuction(@RequestBody AuctionDomain auctionDomain, @PathVariable long id) {
        if (isValid(auctionDomain) || !isValid(auctionDomain)) {
            Auction auction = auctionService.addAuction(auctionDomain, id);
            return new AuctionDomain(auction);
        } else throw new IllegalArgumentException("Arguments are not valid!");
    }

    private boolean isValid(AuctionDomain auctionDomain) {
        return !auctionDomain.getTitle().isEmpty() &&
                auctionDomain.getBasePrice() > 0 &&
                new Date().getTime() < auctionDomain.getDueDate() &&
                Category.ALL.getCategories().stream().anyMatch(category -> auctionDomain.getCategoryId() == category.getId()) &&
                auctionDomain.getMaxUsers() >= 0;
    }

    @GetMapping("/info/{id}")
    public AuctionInfoDomain getAuctionInfo(@PathVariable long id) {

        return auctionService.getAuctionInfo(id);

    }

    @GetMapping("/{id}")
    public Optional<Auction> getOneAuction(@PathVariable long id){

        return auctionService.getAuction(id);

    }

    @GetMapping("/categories")
    public List<CategoryDomain> getCategoryDomains() {
        return Category.ALL.getCategoryDomains();
    }

    @PutMapping("/winner/{auction_id}/{user_id}")
    public String setWinner(@PathVariable long auction_id, @PathVariable long user_id){
        auctionService.settWinner(auction_id, user_id);
        return "a user with id: " + user_id + " won action with id: " + auction_id;
    }

    @PostMapping("/homepage/{id}")
    public List<AuctionDomain> getHomepage(@RequestBody HomepageDomain homepageDomain, @PathVariable long id) {
        if (isValid(homepageDomain)) {
            Page<Auction> homepageAuctions = auctionService.getHomepage(homepageDomain);
            Page<AuctionDomain> map = homepageAuctions.map(AuctionDomain::new);
            return map.get().collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Arguments are invalid!");
    }

    @GetMapping("/myauctions/{id}")
    public List<Auction> getMyAuctions(@PathVariable long userId) {
        return auctionService.getMyAuctions(userId);
    }

    private boolean isValid(HomepageDomain homepageDomain) {
        return homepageDomain.getCount() > 0 &&
                homepageDomain.getPage() >= 0;
    }

    @GetMapping("/mybookmarked/{id}")
    public List<Auction> getMyBookmarked(@PathVariable long userId) {
        return auctionService.getMyBookmarked(userId);
    }

}

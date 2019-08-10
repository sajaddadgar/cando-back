package com.rahnema.controller;

import com.rahnema.domain.*;
import com.rahnema.exception.WrongArgumantException;
import com.rahnema.model.Auction;
import com.rahnema.model.Category;
import com.rahnema.service.AuctionService;
import com.rahnema.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    private boolean isValid(HomepageDomain homepageDomain) {
        return homepageDomain.getCount() > 0 &&
                homepageDomain.getPage() >= 0;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AuctionDomain addAuction(@RequestBody AuctionDomain auctionDomain) {
        if (auctionDomain.isValid()) {
            Auction auction = auctionService.addAuction(auctionDomain, userDetailsService.getUser().getId());
            return new AuctionDomain(auction);
        } else throw new WrongArgumantException("Arguments are not valid!");
    }

    @GetMapping("/info/{id}")
    public AuctionInfoDomain getAuctionInfo(@PathVariable long id) {
        return auctionService.getAuctionInfo(id);
    }

    @GetMapping("/categories")
    public List<CategoryDomain> getCategoryDomains() {
        return Category.ALL.getCategoryDomains();
    }

    @PostMapping("/bookmark/{id}")
    public String doBookmark(@RequestBody BookmarkedDomain bookmarked, @PathVariable long id) {
        auctionService.doBookmark(bookmarked.isBookmarked(), id, userDetailsService.getUser().getId());
        return "bookmarked status changed";
    }

    @PostMapping("/homepage")
    public List<AuctionDomain> getHomepage(@RequestBody HomepageDomain homepageDomain) {
        if (isValid(homepageDomain)) {
            Page<Auction> homepageAuctions = auctionService.getHomepage(homepageDomain, userDetailsService.getUser().getId());
            Page<AuctionDomain> map = homepageAuctions.map(AuctionDomain::new);
            return map.get().collect(Collectors.toList());
        }
        throw new WrongArgumantException("homepage is not valid");
    }

    @GetMapping("/myauctions")
    public List<Auction> getMyAuctions() {
        return auctionService.getMyAuctions(userDetailsService.getUser().getId());
    }

    @GetMapping("/mybookmarked")
    public Set<Auction> getMyBookmarked() {
        return auctionService.getMyBookmarked(userDetailsService.getUser().getId());
    }

}

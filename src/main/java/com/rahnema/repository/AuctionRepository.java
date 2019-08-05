package com.rahnema.repository;

import com.rahnema.model.Auction;
import com.rahnema.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository<Auction, Long> {

    Page<Auction> findByTitleAndCategory(String title, Category category, Pageable pageable);

    Page<Auction> findByCategory(Category category, Pageable pageable);
}
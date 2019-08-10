package com.rahnema.repository;

import com.rahnema.model.Auction;
import com.rahnema.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository<Auction, Long> {

    Page<Auction> findByTitleStartsWithAndCategory(String title, Category category, Pageable pageable);

    Page<Auction> findByTitleStartsWith(String title, Pageable pageable);

    Page<Auction> findByCategory(Category category, Pageable pageable);

    List<Auction> getAllByCreatorId(long id);
}
package com.book_information.repositories;

import com.book_information.models.BookInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookInformationRepository extends CrudRepository<BookInformation, String> {

    List<BookInformation> findByTitleContaining(@Param("title") String title);

}

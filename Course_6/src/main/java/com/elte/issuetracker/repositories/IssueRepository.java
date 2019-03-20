package com.elte.issuetracker.repositories;

import com.elte.issuetracker.entities.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue, Integer> {

}

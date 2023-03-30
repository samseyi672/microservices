package com.activedge;

import com.activedge.model.Balance;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountReposisitory extends PagingAndSortingRepository<Balance,Long> {
}

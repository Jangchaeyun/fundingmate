package com.fund.fundingmate.global.file.Repository;

import com.fund.fundingmate.global.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}

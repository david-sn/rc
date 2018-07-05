package com.pltfhs.car.repository;

import com.pltfhs.car.entity.TestDirveRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDriveRequestRepository extends JpaRepository<TestDirveRequest, Long> {

    List<TestDirveRequest> findByNoteAndClientNameContainsAndClientEmailContainsAndClientPhoneContains(String note, String ClientName, String clientEmail, String ClientPhone);

    long countByIsReadedIsFalse();

}

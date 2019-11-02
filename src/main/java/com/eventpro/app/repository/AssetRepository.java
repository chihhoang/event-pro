package com.eventpro.app.repository;

import com.eventpro.app.model.Asset;
import com.eventpro.app.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
  List<Asset> findByUser(User user);
}

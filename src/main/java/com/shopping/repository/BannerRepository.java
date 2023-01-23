/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Banner;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ajmal
 */
@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

    public List<Banner> findAllByStatusAndType(byte value, Byte type);

    public List<Banner> findAllByStatus(byte value);

    public Optional<Banner> findByBannerIdAndStatus(Integer bannerId, byte value);

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.cron;

import com.shopping.service.DeleteExpiredCouponsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author ajmal
 */
@Component
public class DeleteExpiredCoupons {
   private static final Logger LOG = LoggerFactory.getLogger(DeleteExpiredCoupons.class);
   
   @Autowired
   private DeleteExpiredCouponsService servce;

    
    @Value("${run.batch.project.status.completed}")
    private boolean runBatchProjectStatusCompleted;
    
    
    @Scheduled(cron = "0 5 0 * * *")
    public void deleteExpiredCoupons(){
        
        if(runBatchProjectStatusCompleted){
               LOG.info("preparing for batch execution of updateProjectStatusToCompleted");
               servce.deleteExpiredCoupons();
        
        }
    
    }
    
}

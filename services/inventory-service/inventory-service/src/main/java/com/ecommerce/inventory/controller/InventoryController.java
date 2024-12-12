package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.dto.InventoryDto;
import com.ecommerce.inventory.dto.InventoryRequest;
import com.ecommerce.inventory.dto.InventoryResponse;
import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    @Autowired
    private final InventoryService inventoryService;

    @PostMapping
    public String addInventory(@RequestBody InventoryDto inventoryDto){
        return inventoryService.addInventory(inventoryDto);
    }
    @PostMapping("/stock")
    public List<InventoryResponse> isInStock(@RequestBody List<InventoryRequest> inventoryRequest){
        return inventoryService.isInStock(inventoryRequest);
    }
    @PutMapping
    public String updateInventory(@RequestBody List<InventoryRequest> inventoryRequest){
        return inventoryService.updateInventory(inventoryRequest);
    }
    @PutMapping("roll-back")
    public String rollBack(@RequestBody List<InventoryRequest> inventoryRequest){
        return inventoryService.rollBackUpdate(inventoryRequest);
    }

    @PostMapping("checkItem")
    public Boolean checkItem(@RequestBody InventoryRequest inventoryRequest){
        return this.inventoryService.checkItem(inventoryRequest);
    }

    @GetMapping("getAll")
    public List<Inventory> getAll(){
        return this.inventoryService.getAll();
    }

}

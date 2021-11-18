package br.com.microservice.book.shoppingapi.controller;

import br.com.microservice.book.shoppingapi.service.ReportService;
import br.com.microservice.book.shoppingapi.service.ShopService;
import br.com.microservice.book.shoppingclient.DTO.ShopDTO;
import br.com.microservice.book.shoppingclient.DTO.ShopReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<ShopDTO> getShops(){
        return shopService.getAll();
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShopsByUser(@PathVariable String userIdentifier){
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public List<ShopDTO> getShopsByDate(@RequestBody ShopDTO shopDto){
        return shopService.getByDate(shopDto);
    }

    @GetMapping("/{id}")
    public ShopDTO findById(@PathVariable Long id){
        return shopService.findById(id);
    }

    @GetMapping("/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "startDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy")Date endDate,
            @RequestParam(name = "minimumValue", required = false) Float minimumValue){

        return reportService.getShopsByFilter(startDate, endDate, minimumValue);

    }

    @GetMapping("/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(name = "startDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(name = "endDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy")Date endDate){

        return reportService.getReportByDate(startDate, endDate);
    }

    @PostMapping("/newShop")
    public ShopDTO newShop(@Valid @PathVariable ShopDTO shopDto){
        return shopService.save(shopDto);
    }
}

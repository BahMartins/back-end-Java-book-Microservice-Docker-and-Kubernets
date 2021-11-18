package br.com.microservice.book.shoppingapi.service.impl;

import br.com.microservice.book.shoppingapi.converter.DTOConverter;
import br.com.microservice.book.shoppingapi.model.Shop;
import br.com.microservice.book.shoppingapi.repository.ShopRepository;
import br.com.microservice.book.shoppingapi.service.ProductService;
import br.com.microservice.book.shoppingapi.service.ShopService;
import br.com.microservice.book.shoppingapi.service.UserService;
import br.com.microservice.book.shoppingclient.DTO.ItemDTO;
import br.com.microservice.book.shoppingclient.DTO.ProductDTO;
import br.com.microservice.book.shoppingclient.DTO.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopDTO> getByDate(ShopDTO shopDto) {
        List<Shop> shops = shopRepository.findAllByDateGreaterThanEquals(shopDto.getDate());
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ShopDTO findById(Long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        return shop.map(DTOConverter::convert).orElse(null);
    }

    @Override
    public ShopDTO save(ShopDTO shopDto) {

        if(userService.getUserByCPF(shopDto.getUserIdentifier()) == null){
            return null;
        }

        if(!validateProducts(shopDto.getItems())){
            return null;
        }

        shopDto.setTotal(shopDto.getItems()
                .stream()
                .map(ItemDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        Shop shop = Shop.convert(shopDto);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items){
            ProductDTO productDto = productService.getProductByIdentifier(item.getProductIdentifier());
            if(productDto == null){
                return false;
            }
            item.setPrice(productDto.getPrice());
        }
        return true;
    }
}

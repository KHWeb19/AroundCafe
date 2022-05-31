package com.example.demo.mypage.cafe.service.menu;

import com.example.demo.mypage.cafe.dto.CafeMenuDto;
import com.example.demo.mypage.cafe.entity.Cafe;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import com.example.demo.mypage.cafe.repository.cafe.CafeRepository;
import com.example.demo.mypage.cafe.repository.menu.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuRepository repository;

    @Autowired
    CafeRepository cafeRepository;


    @Override
    public void includeImgSave(CafeMenuDto info, String originalFilename) {
        String file = info.getCafe_name()+"." +originalFilename;

        log.info("file: " +file);
        log.info("cafe no : " +info.getCafe_no());

        Optional<Cafe> findCafe = cafeRepository.findById(info.getCafe_no());
        Cafe cafe = findCafe.get();

        CafeMenu cafeMenu = new CafeMenu(info.getMenu_name(), info.getMenu_price(), file, info.getMenu_content(), cafe);
        repository.save(cafeMenu);

    }

    @Override
    public void exceptImgSave(CafeMenuDto info) {
        log.info("cafe no : " +info.getCafe_no());

        Optional<Cafe> findCafe = cafeRepository.findById(info.getCafe_no());
        Cafe cafe = findCafe.get();

        CafeMenu cafeMenu = new CafeMenu(info.getMenu_name(), info.getMenu_price(), info.getMenu_content(), cafe);
        repository.save(cafeMenu);
    }

    @Override
    public void includeImgModify(CafeMenu info, String originalFilename, String cafeName) throws IOException {
        String file = cafeName+"." +originalFilename;

        log.info("include img modify");
        log.info("file: " +file);
        log.info("memu no : " +info.getMenu_no());

        Optional<CafeMenu> findCafeMenu = repository.findById(info.getMenu_no());
        CafeMenu cafeMenu = findCafeMenu.get();

        if(cafeMenu.getMenu_img() != null) {
            Path filePath = Paths.get("../../cafefront/around_cafe/src/asserts/cafeMenu" + info.getMenu_img());
            Files.delete(filePath);
        }

        info.setMenu_img(file);
        repository.save(info);
    }

    @Override
    public void exceptImgModify(CafeMenu info) {
        log.info("except img modify");

        Optional<CafeMenu> findCafeMenu = repository.findById(info.getMenu_no());
        CafeMenu cafeMenu = findCafeMenu.get();

        if(cafeMenu.getMenu_img() != null) {
            info.setMenu_img(cafeMenu.getMenu_img());
            repository.save(info);
        }else if(cafeMenu.getMenu_img() == null) {
            repository.save(info);
        }

    }

    @Override
    public List<CafeMenu> list(Integer membNo) {
        Optional<Cafe> findCafeNo = cafeRepository.findByMemberNo(Long.valueOf(membNo));
        Cafe cafe = findCafeNo.get();
        Long CafeNo = cafe.getCafeNo();

        return repository.findByCafeNo(CafeNo);
    }
}
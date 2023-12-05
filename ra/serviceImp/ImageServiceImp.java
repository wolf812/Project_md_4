package com.ra.serviceImp;

import com.ra.model.Images;
import com.ra.repository.ImagesRepository;
import com.ra.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImp implements ImageService {
    @Autowired
    private ImagesRepository imagesRepository;
    @Override
    public boolean save(Images images) {
        try {
            imagesRepository.save(images);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}

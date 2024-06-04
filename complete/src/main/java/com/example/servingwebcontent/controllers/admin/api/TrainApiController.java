package com.example.servingwebcontent.controllers.admin.api;

import com.example.servingwebcontent.forms.TrainForm;
import com.example.servingwebcontent.models.Train;
import com.example.servingwebcontent.repositories.TrainRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/api/")
public class TrainApiController {

    private final TrainRepository trainRepository;

    public TrainApiController(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @PostMapping("/trains/create")
    public RedirectView createTrain(@ModelAttribute TrainForm trainForm) {

        Train train = new Train();
        train.setCode(trainForm.getCode());
        train.setType(trainForm.getType());

        trainRepository.save(train);

        return new RedirectView("/admin");
    }

    @PostMapping("/trains/delete/{id}")
    public RedirectView deleteTrain(@PathVariable("id") Long id) {
        trainRepository.deleteById(id);
        return new RedirectView("/admin/trains/create");
    }

    @PostMapping("/trains/update/{id}")
    public RedirectView updateTrain(@PathVariable("id") Long id, @ModelAttribute TrainForm trainForm) {
        Train train = trainRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid train Id:" + id));
        train.setCode(trainForm.getCode());
        train.setType(trainForm.getType());
        trainRepository.save(train);

        return new RedirectView("/admin/trains/create");
    }
}

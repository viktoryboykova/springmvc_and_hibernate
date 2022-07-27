package ru.mycats.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mycats.model.Cat;
import ru.mycats.model.dto.CatDTO;
import ru.mycats.service.MyService;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/")
@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping(value = "/getAll"/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public List<CatDTO> getAll() {
        return myService.getAll().stream().map(CatDTO::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/save"/*, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public CatDTO save(@RequestBody CatDTO catDTO) {
        Cat cat = myService.save(new Cat(catDTO.getName(), catDTO.getAge()));
        return new CatDTO(cat);
    }
}

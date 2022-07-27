package ru.mycats.service;

import org.springframework.stereotype.Service;
import ru.mycats.model.Cat;
import ru.mycats.store.MyRepository;

import java.util.List;

@Service
public class MyService {
    private final MyRepository myRepository;

    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public List<Cat> getAll() {
        return myRepository.getAll();
    }

    public Cat save(Cat cat) {
        return myRepository.save(cat);
    }
}

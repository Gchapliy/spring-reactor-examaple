package com.example.spring_reactor_example.initialize;

import com.example.spring_reactor_example.document.Item;
import com.example.spring_reactor_example.repository.ItemReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Component
public class ItemDataInitializer implements CommandLineRunner {
    @Autowired
    ItemReactiveRepository itemReactiveRepository;

    @Override
    public void run(String... args) throws Exception {
        initialDataSetUp();
    }

    public List<Item> data() {
        return Arrays.asList(
                new Item(null, "item1", 223.01),
                new Item(null, "item2", 23423.11),
                new Item(null, "item3", 300.22),
                new Item(null, "item4", 401.9)
        );
    }

    private void initialDataSetUp() {
        itemReactiveRepository.deleteAll()
                .thenMany(Flux.fromIterable(data()))
                .flatMap(itemReactiveRepository::save)
                .thenMany(itemReactiveRepository.findAll())
                .subscribe(item -> System.out.println("Item inserted from command line runner: " + item));
    }
}

package home.transaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/volatile")
public class VolatileController {

    private int num = 0;

    @RequestMapping(value = "/validate")
    public String validateVolatile() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2000; j++) {
                    num++;
                    System.out.println(Thread.currentThread().getName() + "\t" + j);
                }
            }, "").start();
        }
        return num + "";
    }
}

package com.company.banksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BanksystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanksystemApplication.class, args);
    }

}
//TODO Надо создать общий счет
// Построить фактори
//TODO Сделать так,чтоб невозможно было 2 раза подтверждать заявки       сделать для кажого свой ендпоинт
//TODO ClosedDate скрыть во всех ентити                                      DONE
// Security
//TODO  Для валют задать 2 цены,продажа и покупка                            DONE
//TODO поиск по реквизиту
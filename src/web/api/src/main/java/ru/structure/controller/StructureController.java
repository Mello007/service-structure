package ru.structure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.structure.service.StructureService;

@RestController
@RequestMapping(value = "app")
public class StructureController {

    @Autowired StructureService structureService;

    // возвращает список структур
    @RequestMapping(value = "structures", method = RequestMethod.GET)
    public void getStructures() throws Exception {
        structureService.getStructures();
    }

    // возвращает список записей по id структуры
    @RequestMapping(value = "structures/{id}/records", method = RequestMethod.GET)
    public void getRecordsByStructure(@RequestBody String itemInfo, @PathVariable("id") Long id) throws Exception {

    }

    // создает новую структуру
    @RequestMapping(value = "structures", method = RequestMethod.POST)
    public void addStructure(@RequestBody String itemInfo) throws Exception {
        structureService.create("name", 0);
    }

    @RequestMapping(value = "structures", method = RequestMethod.DELETE)
    public void deleteStructure(@RequestBody String id) throws Exception {
        structureService.delete(Integer.valueOf(id));
    }

    // обновляет структуру
    @RequestMapping(value = "structures", method = RequestMethod.PUT)
    public void updateStructure(@RequestBody String itemInfo) throws Exception {
        structureService.update(null, null);
    }

    // создает новую запись по айди структуры
    @RequestMapping(value = "structures/{id}/records", method = RequestMethod.POST)
    public void createNewRecordByStructure(@RequestBody String itemInfo) throws Exception {

    }

    // обновляет конкретную структуру
    @RequestMapping(value = "structures/{id}/records", method = RequestMethod.PUT)
    public void updateCertainStructure(@RequestBody String itemInfo) throws Exception {

    }


}

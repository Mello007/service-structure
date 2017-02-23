package ru.structure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.structure.service.StructureService;
import ru.structure.service.entity.Record;
import ru.structure.service.entity.Structure;

import java.util.List;

@RestController
@RequestMapping(value = "app")
public class StructureController {

    @Autowired StructureService structureService;

    // возвращает список всех структур
    @RequestMapping(value = "structures", method = RequestMethod.GET, produces = "application/json")
    public List<Structure> getStructures() throws Exception {
        return structureService.getStructures();
    }

    // возвращает структуру по id
    @RequestMapping(value = "structures/{id}", method = RequestMethod.GET)
    public Structure getStructureById(@PathVariable("id") Long id) throws Exception {
       return structureService.getStructure(id);
    }

    // возвращает список записей по id структуры
    @RequestMapping(value = "structures/{id}/records", method = RequestMethod.GET, produces = "application/json")
    public List<Record> getRecordsByStructure(@PathVariable("id") Long id) throws Exception {
      return structureService.getRecordsByStructureId(id);
    }

    // создает новую структуру
    @RequestMapping(value = "structures", method = RequestMethod.POST, consumes = "application/json")
    public void addStructure(@RequestBody String json) throws Exception {
        structureService.create(json);
    }

    // удаляет структуру по id
    @RequestMapping(value = "structures/{id}", method = RequestMethod.DELETE)
    public void deleteStructure(@RequestBody String id) throws Exception {
        structureService.delete(Integer.valueOf(id));
    }

    // обновляет структуру по id
    @RequestMapping(value = "structures/{id}", method = RequestMethod.PUT)
    public void updateStructure(@PathVariable("id") Long id, @RequestBody String json) throws Exception {
        structureService.updateStructure(id, json);
    }

    // создает новую запись по id структуры
    @RequestMapping(value = "structures/{id}/records", method = RequestMethod.POST)
    public void createNewRecordByStructure(@PathVariable("id") Long id, @RequestBody String json) throws Exception {
        structureService.createRecordByStructure(id, json);
    }

    // обновляет запись по id
    @RequestMapping(value = "structures/records/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public void updateCertainStructure(@PathVariable("id") Long id, @RequestBody String json) throws Exception {
        structureService.updateRecordByStructureId(id, json);
    }

    // удаляет запись по id
    @RequestMapping(value = "structures/records/{record_id}", method = RequestMethod.DELETE)
    public void deleteRecordByStructureId(@PathVariable("record_id") Long recordId) throws Exception {
        structureService.deleteRecordByStructureId(recordId);
    }

    // возвращает запись по id
    @RequestMapping(value = "structures/records/{record_id}", method = RequestMethod.GET)
    public Record getRecordById(@PathVariable("record_id") Long recordId) throws Exception {
        return structureService.getRecordById(recordId);
    }
}

package ru.structure.controller;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.JsonNode;
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

    // возвращает список структур
    @RequestMapping(value = "structures", method = RequestMethod.GET, produces = "application/json")
    public List<Structure> getStructures() throws Exception {
        return structureService.getStructures();
    }

    @RequestMapping(value = "structures/{id}", method = RequestMethod.GET, produces = "application/json")
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

    @RequestMapping(value = "structures/{id}", method = RequestMethod.DELETE)
    public void deleteStructure(@RequestBody String id) throws Exception {
        structureService.delete(Integer.valueOf(id));
    }

    // обновляет структуру
    @RequestMapping(value = "structures/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public void updateStructure(@PathVariable("id") Long id, @RequestBody String json) throws Exception {
        structureService.update(id, json);
    }

    // создает новую запись по айди структуры
    @RequestMapping(value = "structures/{id}/records", method = RequestMethod.POST)
    public void createNewRecordByStructure(@PathVariable("id") Long id, @RequestBody String json) throws Exception {
        structureService.createRecordByStructure(id, json);
    }

    // обновляет конкретную запись
    @RequestMapping(value = "structures/records/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public void updateCertainStructure(@PathVariable("id") Long id, @RequestBody String json) throws Exception {
        structureService.updateRecordByStructureId(id, json);
    }

    @RequestMapping(value = "structures/records/{record_id}", method = RequestMethod.DELETE)
    public void deleteRecordByStructureId(@PathVariable("record_id") Long recordId) throws Exception {
        structureService.deleteRecordByStructureId(recordId);
    }

    @RequestMapping(value = "structures/records/{record_id}", method = RequestMethod.GET)
    public Record getRecordById(@PathVariable("record_id") Long recordId) throws Exception {
        return structureService.getRecordById(recordId);
    }
}

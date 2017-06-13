package com.agh.controller;

import com.agh.data.ChartDataProxy;
import com.agh.data.FileProxy;
import com.agh.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dawid on 11.06.2017.
 */
@RestController
public class InputController {

    @Autowired
    private InputService inputService;

    @RequestMapping(value = "/getLoadedFiles", method = RequestMethod.GET)
    public List<FileProxy> getLoadedFiles(){
        return inputService.getLoadedFiles();
    }

    @RequestMapping(value = "/sendData", method = RequestMethod.POST)
    public void getData(@RequestParam("filename") String filename, @RequestParam("data") String data){
        inputService.validateData(filename, data);
    }

    @RequestMapping(value = "/getFileData", method = RequestMethod.GET)
    public ChartDataProxy sendData(@RequestParam("id") int id){
        return inputService.getNodesAndElementsForFile(id);
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public void deleteFile(@RequestParam("id") long id){
        inputService.deleteFile(id);
    }
}

package water.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import water.domian.Transducer;
import water.repository.TransducerRepository;


/**
 * 传感器的增删查
 */
@SessionAttributes("id")
@Controller
@RequestMapping("/sensor")
public class TransducerController {
    private static final String SENSOR_LIST="sensor/TransducerList";
    private static final String SENSOR_ADD="sensor/TransducerAdd";
    private static final String SENSOR_URL="redirect:/sensor";

    @Autowired
    private TransducerRepository transducerRepository;

    /**
     * 查询所有的传感器
     * @return
     */
    @GetMapping
    public String getTransducers(ModelMap modelMap){
        modelMap.addAttribute("sensors", transducerRepository.findAll());
        return SENSOR_LIST;
    }

    @GetMapping("/addTransducer")
    public String addTransducerForm(ModelMap map){
     map.addAttribute("sensor",new Transducer());
     map.addAttribute("action","addTransducer");
        return SENSOR_ADD;
    }


    @PostMapping("/addTransducer")
    public String addTransducer(@RequestBody Transducer transducer){
        transducerRepository.save(transducer);
        return SENSOR_URL;
    }

    /**
     * 通过id删除传感器
     * @param id
     */
    @DeleteMapping("/deleteTransducer/{id}")
    public String deleteTransducer(@PathVariable Integer id){
        transducerRepository.deleteById(id);
        return SENSOR_LIST;
    }

    @GetMapping("/getTransducerById/{id}")
    public String getTransducerById(@PathVariable Integer id){
        transducerRepository.getOne(id);
        return null;
    }


}

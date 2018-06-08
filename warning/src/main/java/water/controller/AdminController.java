package water.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import water.domian.Admin;
import water.repository.AdminRepository;
import java.util.Optional;

/**
 * 管理员的增删改查
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes("id")
public class AdminController {

    private static final String ADMIN_LIST="manager/adminList";
    private static final String ADMIN_ADD="manager/adminAdd";
    private static final String ADMIN_URL="redirect:/admin";
    private static final String ADMIN_UPDATE="manager/adminUpdate";
    private static final String SENSOR_URL="redirect:/sensor";
    @Autowired
    private AdminRepository adminRepository;

    /**
     * 返回所有管理员
     * @return
     */
    @GetMapping
    public String getAdmins(ModelMap modelMap){
        modelMap.addAttribute("admins",adminRepository.findAll());
        return ADMIN_LIST;
    }

    /**
     * 获取创建 admin 表单
     */
    @GetMapping(value = "/adminAdd")
    public String createAdminForm(ModelMap map) {
        map.addAttribute("admin", new Admin());
        map.addAttribute("action", "adminAdd");
        return ADMIN_ADD;
    }

  /**
     * 创建普通管理员
     * 处理 "/admin/addAdmin" 的 POST 请求，用来新建 admin 信息
     *通过 @ModelAttribute 绑定表单实体参数
     */
  @PostMapping(value = "/adminAdd")
    public String addAdmin(@ModelAttribute Admin admin){
       adminRepository.save(admin);
        return ADMIN_URL;

    }


    /**
     * 通过Id删除管理员  只有超级管理员有这个权限
     * @param id
     */
    @RequestMapping(value="/deleteAdminById/{id}")
   public String deleteAdmin(@PathVariable Integer id){
       adminRepository.deleteById(id);
       return ADMIN_URL;
   }

   @GetMapping("/updateAdmin/{id}")
    public String getAdmin(@PathVariable Integer id,ModelMap map){
       map.addAttribute("admin",adminRepository.findById(id).get());//这里真的是气死我了，没修改之前返回的是optional<Admin>对象
       map.addAttribute("action","updateAdmin");
        return ADMIN_UPDATE;
    }

    /**
     * 修改管理员信息 只能由该管理员本人修改
     * @param admin
     * @return 返回修改后的管理员
     */
    @PutMapping("/updateAdmin")
   public String updateAdmin(@ModelAttribute Admin admin){
       adminRepository.save(admin);
        return SENSOR_URL; //更新完成后返回到传感器列表
   }

    /**
     * 管理员登录
     * @param admin
     * @return
     */
   @PostMapping("/adminLogin")
   public String login(@ModelAttribute Admin admin,ModelMap map){
       Optional<Admin> data = adminRepository.findById(admin.getId());
       //判断是普通管理员还是超级管理员
       if(data.isPresent()&&data.get().getPassword().equals(admin.getPassword())&&data.get().getName().equals(admin.getName())){
           if(data.get().getRole()==1){
               return "redirect:/admin";  //超级管理员管理普通管理员页面
           }
           else {
               map.addAttribute("id",admin.getId());//将管理员id存起来，以便后面进行更新
               return "redirect:/sensor"; //普通管理员管理传感器
           }

       }

       else
           return "redirect:/login";  //登录失败返回登录页面
   }

}

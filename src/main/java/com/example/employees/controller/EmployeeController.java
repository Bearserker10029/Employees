package com.example.employees.controller;

import com.example.employees.model.Departments;
import com.example.employees.model.Employees;
import com.example.employees.model.Jobs;
import com.example.employees.repository.DepartmentsRepository;
import com.example.employees.repository.EmployeesRepository;
import com.example.employees.repository.JobsRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    final EmployeesRepository employeesRepository;
    final JobsRepository jobsRepository;
    final DepartmentsRepository departmentsRepository;

    public EmployeeController(EmployeesRepository employeesRepository,
                              JobsRepository jobsRepository,
                              DepartmentsRepository departmentsRepository) {
        this.employeesRepository = employeesRepository;
        this.jobsRepository = jobsRepository;
        this.departmentsRepository = departmentsRepository;
    }

    @GetMapping(value = {"/list", ""})
    public String listarEmpleados(Model model,
                                  @RequestParam(value = "campo", defaultValue = "all") String campo,
                                  @RequestParam(value = "termino", required = false) String termino) {

        String terminoBusqueda = termino == null ? "" : termino.trim();
        List<Employees> lista = employeesRepository.searchEmployees(campo, terminoBusqueda);
        model.addAttribute("employeeList", lista);
        model.addAttribute("selectedCampo", campo);
        model.addAttribute("termino", terminoBusqueda);

        return "Employee/ListEmployee";
    }

    @GetMapping("/new")
    public String nuevoEmpleadoFrm(Model model) {
        model.addAttribute("employee", new Employees());
        model.addAttribute("jobs", jobsRepository.findAll());
        model.addAttribute("managers", employeesRepository.findAll());
        model.addAttribute("departments", departmentsRepository.findAll());
        return "Employee/NewEmployee";
    }

    @PostMapping("/save")
    public String guardarNuevoEmpleado(@ModelAttribute("employee") @Valid Employees employee,
                                       BindingResult bindingResult,
                                       @RequestParam(required = false) String jobId,
                                       @RequestParam(required = false) Integer managerId,
                                       @RequestParam(required = false) Integer departmentId,
                                       RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("jobs", jobsRepository.findAll());
            model.addAttribute("managers", employeesRepository.findAll());
            model.addAttribute("departments", departmentsRepository.findAll());
            if (employee.getEmployeeId() > 0) {
                return "Employee/EditEmployee";
            }
            return "Employee/NewEmployee";
        }

        if (employee.getSalary() != null && employee.getSalary() < 0) {
            bindingResult.rejectValue("salary", "error.salary", "El sueldo no puede ser negativo");
            model.addAttribute("jobs", jobsRepository.findAll());
            model.addAttribute("managers", employeesRepository.findAll());
            model.addAttribute("departments", departmentsRepository.findAll());
            if (employee.getEmployeeId() > 0) {
                return "Employee/EditEmployee";
            }
            return "Employee/NewEmployee";
        }

        Employees existingEmployee = null;
        if (employee.getEmployeeId() > 0) {
            existingEmployee = employeesRepository.findById(employee.getEmployeeId()).orElse(null);
        }

        if (existingEmployee != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setSalary(employee.getSalary());
            employee = existingEmployee;
        }

        if (jobId != null && !jobId.isEmpty()) {
            Jobs job = new Jobs();
            job.setJobId(jobId);
            employee.setJobId(job);
        }

        if (managerId != null) {
            Employees manager = new Employees();
            manager.setEmployeeId(managerId);
            employee.setManagerId(manager);
        }

        if (departmentId != null) {
            Departments dept = new Departments();
            dept.setDepartmentId(departmentId);
            employee.setDepartmentId(dept);
        }

        if (employee.getHireDate() == null) {
            employee.setHireDate(new java.util.Date());
        }

        employeesRepository.save(employee);
        redirectAttributes.addFlashAttribute("msg", "Empleado guardado correctamente");
        return "redirect:/employee/list";
    }

    @GetMapping("/edit")
    public String editarEmpleado(Model model, @RequestParam("id") int id,  RedirectAttributes redirectAttributes) {

        Optional<Employees> optEmployee = employeesRepository.findByIdWithRelations(id);

        if (optEmployee.isPresent()) {
            Employees employee = optEmployee.get();
            model.addAttribute("employee", employee);
            model.addAttribute("jobs", jobsRepository.findAll());
            model.addAttribute("managers", employeesRepository.findAll());
            model.addAttribute("departments", departmentsRepository.findAll());
            redirectAttributes.addFlashAttribute("msg","Empleado actualizado correctamente");
            return "Employee/EditEmployee";
        } else {
            return "redirect:/employee/list";
        }
    }

    @GetMapping("/delete")
    public String borrarEmpleado(@RequestParam("id") int id,  RedirectAttributes redirectAttributes) {

        Optional<Employees> optEmployee = employeesRepository.findById(id);

        if (optEmployee.isPresent()) {
            try {
                employeesRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("msg", "Empleado borrado correctamente");
            } catch (DataIntegrityViolationException ex) {
                redirectAttributes.addFlashAttribute("msg", "No se puede eliminar al empleado");
            }
        }
        return "redirect:/employee/list";

    }

    @PostMapping("/buscarPorNombre")
    public String buscarPorNombre(@RequestParam("searchField") String searchField, Model model) {

        List<Employees> lista = employeesRepository.searchEmployees("all", searchField);
        model.addAttribute("employeeList", lista);
        model.addAttribute("termino", searchField);
        model.addAttribute("selectedCampo", "all");

        return "Employee/ListEmployee";
    }
}


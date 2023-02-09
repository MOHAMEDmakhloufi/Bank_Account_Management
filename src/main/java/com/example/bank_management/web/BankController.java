package com.example.bank_management.web;

import com.example.bank_management.entities.Account;
import com.example.bank_management.entities.CurrentAccount;
import com.example.bank_management.entities.Operation;
import com.example.bank_management.entities.SavingAccount;
import com.example.bank_management.services.BankServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@Controller
public class BankController {
    @Autowired
    BankServices bankServices;

    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    public String index(){

        return "accounts";
    }
    @GetMapping("/displayAccount")
    public String display(Model model,
                          @RequestParam(name = "code") String codeAccount,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "4") int size){
        try {

            Account account= bankServices.displayAccount(codeAccount);
            model.addAttribute("account", account);

            if(account instanceof CurrentAccount){
                model.addAttribute("currentAccount", (CurrentAccount)account);
            }

            else
                model.addAttribute("savingAccount", (SavingAccount)account);



            Page<Operation> operationPage= bankServices.listOfOperations(codeAccount, page, size);
            model.addAttribute("operations", operationPage.getContent());
            model.addAttribute("code",codeAccount);
            int[] pages= new int[operationPage.getTotalPages()];
            model.addAttribute("pages", pages);
            model.addAttribute("pageCurrent", page);
        }catch (Exception e){
            model.addAttribute("exception", e);
        }finally {
            return "accounts";
        }

    }
    @RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
    public String save(Model model,
                       @RequestParam String code,
                       @RequestParam String op,
                       @RequestParam("account2") String code2,
                       @RequestParam Double amount){

        try {
            if(op.equals("deposit")){
                bankServices.deposit(code,amount);
            }else if(op.equals("withdrawal")){
                bankServices.withdraw(code, amount);
            }else{
                bankServices.bankTransfer(code, code2, amount);
            }
        }catch (Exception e){
            return "redirect:/displayAccount?code="+code+"&error="+e.getMessage();
        }
        return "redirect:/displayAccount?code="+code;
    }
}

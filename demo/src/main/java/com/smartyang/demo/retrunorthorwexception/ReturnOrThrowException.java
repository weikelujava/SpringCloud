package com.smartyang.demo.retrunorthorwexception;


import com.smartyang.demo.exception.LockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnOrThrowException {

    @GetMapping("/test/return/throw-exception")
    public Boolean testReturnOrThrowException() throws LockException{
        Integer a = 10;
        System.out.println(a);
        while (a>1){
            if(a%3 == 0){

                    throwException();
                    return false;
            }
            a--;
        }
        return true;
    }

    private void throwException() throws LockException{
        throw new LockException("bbbb");
    }
}

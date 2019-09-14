package com.thekitchenfridge.security.activation;

import com.thekitchenfridge.exceptions.BadActTokenException;
import com.thekitchenfridge.security.entities.ActivationToken;
import com.thekitchenfridge.users.repository.ActivationRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
@Service
public class ActivationService {

    ActivationRepository activationRepo;

    public Boolean activateUser(String tokenStr)throws BadActTokenException {
        ActivationToken token = activationRepo.findByTokenId(tokenStr).orElseThrow(BadActTokenException::new);
        if(!tokenExpired(token)){
            token.activateUser();
            return true;
        }
        return false;
    }

    public boolean tokenExpired(ActivationToken token){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        return new Date(cal.getTime().getTime()).after(token.getExpirationDate());
    }

}

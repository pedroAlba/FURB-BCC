package com.sd.trab3;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class LicencaService {

    private List<Licenca> lics;

    public LicencaService(){
        this.lics = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            lics.add(new Licenca(i));
        }
    }

    public Licenca get() throws Exception {
        Licenca lic = lics.stream().filter(l -> !l.isInUse()).findAny().orElseThrow(() -> new Exception("Licencas esgotadas"));
        lic.setInUse(true);
        return lic;
    }

    public void returnLic(Licenca l){
        l.setInUse(false);
        lics.add(l);
    }
}
